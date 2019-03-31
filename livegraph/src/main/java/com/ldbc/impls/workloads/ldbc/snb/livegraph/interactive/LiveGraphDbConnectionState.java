package com.ldbc.impls.workloads.ldbc.snb.livegraph.interactive;

import com.ldbc.driver.DbConnectionState;
import org.apache.thrift.TException; 
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.Map;
import java.util.LinkedList;

public class LiveGraphDbConnectionState extends DbConnectionState {

    protected String hostname;
    protected int port;
    
    static final int INITIAL_CAPACITY = 24;
    LinkedList<TTransport> pool = new LinkedList<TTransport>();

    public LiveGraphDbConnectionState(Map<String, String> properties) throws TException {

        hostname = properties.get("hostname");
        port = Integer.parseInt(properties.get("port"));

        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            TTransport transport = new TSocket(hostname, port);
            transport.open();
            pool.add(transport);
        } 
    }

    public synchronized TTransport getConnection() throws TException {
        if (pool.isEmpty()) {
            TTransport transport = new TSocket(hostname, port);
            transport.open();
            pool.add(transport);
        }
        return pool.pop();
    }

    public synchronized void returnConnection(TTransport connection) {
        pool.push(connection);
    }  

    @Override
    public void close() {
        while (!pool.isEmpty()) {
            pool.pop().close();
        } 
    }
}
