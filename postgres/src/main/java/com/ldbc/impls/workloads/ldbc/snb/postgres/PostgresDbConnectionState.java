package com.ldbc.impls.workloads.ldbc.snb.postgres;

import com.ldbc.driver.DbException;
import com.ldbc.impls.workloads.ldbc.snb.BaseDbConnectionState;
import com.ldbc.impls.workloads.ldbc.snb.QueryStore;
import org.postgresql.ds.jdbc4.AbstractJdbc4PoolingDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.LinkedList;

public class PostgresDbConnectionState<TDbQueryStore extends QueryStore> extends BaseDbConnectionState<TDbQueryStore> {

    protected String endPoint;
    protected AbstractJdbc4PoolingDataSource ds;
    
    static final int INITIAL_CAPACITY = 128;
    LinkedList<Connection> pool = new LinkedList<Connection>();

    public PostgresDbConnectionState(Map<String, String> properties, TDbQueryStore store) throws ClassNotFoundException, DbException {
        super(properties, store);

        endPoint = properties.get("endpoint");
        try {
            ds = this.getClass().getClassLoader()
                    .loadClass(properties.get("jdbcDriver"))
                    .asSubclass(AbstractJdbc4PoolingDataSource.class).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Could not instantiate pooling data source", e);
        }

        ds.setDatabaseName(properties.get("databaseName"));
        ds.setServerName(endPoint);
        ds.setUser(properties.get("user"));
        ds.setPassword(properties.get("password"));
        ds.setMaxConnections(2*INITIAL_CAPACITY);

        try {
            for (int i = 0; i < INITIAL_CAPACITY; i++) {
                pool.add(ds.getConnection());
            } 
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    public synchronized Connection getConnection() throws DbException {
        try {
            if (pool.isEmpty()) {
                pool.add(ds.getConnection());
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
        return pool.pop();
    }

    public synchronized void returnConnection(Connection connection) {
        pool.push(connection);
    }  

    @Override
    public void close() {
        ds.close();

        try {
            while (!pool.isEmpty()) {
                pool.pop().close();
            } 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
