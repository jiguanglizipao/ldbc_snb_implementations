package com.ldbc.impls.workloads.ldbc.snb.livegraph.interactive;

import com.ldbc.driver.Db;
import com.ldbc.driver.DbException;
import com.ldbc.driver.DbConnectionState;
import com.ldbc.driver.OperationHandler;
import com.ldbc.driver.ResultReporter;
import com.ldbc.driver.control.LoggingService;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery1;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery10;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery10Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery11;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery11Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery12;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery12Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery13;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery13Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery14;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery14Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery1Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery2;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery2Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery3;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery3Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery4;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery4Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery5;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery5Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery6;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery6Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery7;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery7Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery8;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery8Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery9;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcQuery9Result;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery1PersonProfile;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery1PersonProfileResult;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery2PersonPosts;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery2PersonPostsResult;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery3PersonFriends;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery3PersonFriendsResult;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery4MessageContent;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery4MessageContentResult;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery5MessageCreator;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery5MessageCreatorResult;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery6MessageForum;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery6MessageForumResult;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery7MessageReplies;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcShortQuery7MessageRepliesResult;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcUpdate1AddPerson;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcUpdate2AddPostLike;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcUpdate3AddCommentLike;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcUpdate4AddForum;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcUpdate5AddForumMembership;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcUpdate6AddPost;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcUpdate7AddComment;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcUpdate8AddFriendship;
import com.ldbc.driver.workloads.ldbc.snb.interactive.db.DummyLdbcSnbInteractiveOperationResultSets;
import com.ldbc.driver.workloads.ldbc.snb.interactive.LdbcNoResult;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TTransport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LiveGraphDb extends Db {

    protected LiveGraphDbConnectionState dcs;

    @Override
    protected void onInit(Map<String, String> properties, LoggingService loggingService) throws DbException {
        try {
            dcs = new LiveGraphDbConnectionState(properties);
        } catch (TException e) {
            e.printStackTrace();
        }
        // Long Reads
        registerOperationHandler( LdbcQuery1.class, LdbcQuery1Handler.class );
        registerOperationHandler( LdbcQuery2.class, LdbcQuery2Handler.class );
        registerOperationHandler( LdbcQuery3.class, LdbcQuery3Handler.class );
        registerOperationHandler( LdbcQuery4.class, LdbcQuery4Handler.class );
        registerOperationHandler( LdbcQuery5.class, LdbcQuery5Handler.class );
        registerOperationHandler( LdbcQuery6.class, LdbcQuery6Handler.class );
        registerOperationHandler( LdbcQuery7.class, LdbcQuery7Handler.class );
        registerOperationHandler( LdbcQuery8.class, LdbcQuery8Handler.class );
        registerOperationHandler( LdbcQuery9.class, LdbcQuery9Handler.class );
        registerOperationHandler( LdbcQuery10.class, LdbcQuery10Handler.class );
        registerOperationHandler( LdbcQuery11.class, LdbcQuery11Handler.class );
        registerOperationHandler( LdbcQuery12.class, LdbcQuery12Handler.class );
        registerOperationHandler( LdbcQuery13.class, LdbcQuery13Handler.class );
        registerOperationHandler( LdbcQuery14.class, LdbcQuery14Handler.class );
        // Short Reads
        registerOperationHandler( LdbcShortQuery1PersonProfile.class, LdbcShortQuery1PersonProfileHandler.class );
        registerOperationHandler( LdbcShortQuery2PersonPosts.class, LdbcShortQuery2PersonPostsHandler.class );
        registerOperationHandler( LdbcShortQuery3PersonFriends.class, LdbcShortQuery3PersonFriendsHandler.class );
        registerOperationHandler( LdbcShortQuery4MessageContent.class, LdbcShortQuery4MessageContentHandler.class );
        registerOperationHandler( LdbcShortQuery5MessageCreator.class, LdbcShortQuery5MessageCreatorHandler.class );
        registerOperationHandler( LdbcShortQuery6MessageForum.class, LdbcShortQuery6MessageForumHandler.class );
        registerOperationHandler( LdbcShortQuery7MessageReplies.class, LdbcShortQuery7MessageRepliesHandler.class );
        // Updates
        registerOperationHandler( LdbcUpdate1AddPerson.class, LdbcUpdate1AddPersonHandler.class );
        registerOperationHandler( LdbcUpdate2AddPostLike.class, LdbcUpdate2AddPostLikeHandler.class );
        registerOperationHandler( LdbcUpdate3AddCommentLike.class, LdbcUpdate3AddCommentLikeHandler.class );
        registerOperationHandler( LdbcUpdate4AddForum.class, LdbcUpdate4AddForumHandler.class );
        registerOperationHandler( LdbcUpdate5AddForumMembership.class, LdbcUpdate5AddForumMembershipHandler.class );
        registerOperationHandler( LdbcUpdate6AddPost.class, LdbcUpdate6AddPostHandler.class );
        registerOperationHandler( LdbcUpdate7AddComment.class, LdbcUpdate7AddCommentHandler.class );
        registerOperationHandler( LdbcUpdate8AddFriendship.class, LdbcUpdate8AddFriendshipHandler.class );

    }

    @Override
    protected void onClose()
    {
        dcs.close();
    }

    @Override
    protected DbConnectionState getConnectionState()
    {
        return dcs;
    }

    private static final List<LdbcQuery1Result> LDBC_QUERY_1_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read1Results();

    public static class LdbcQuery1Handler implements OperationHandler<LdbcQuery1, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery1 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_1_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery2Result> LDBC_QUERY_2_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read2Results();

    public static class LdbcQuery2Handler implements OperationHandler<LdbcQuery2, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery2 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_2_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery3Result> LDBC_QUERY_3_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read3Results();

    public static class LdbcQuery3Handler implements OperationHandler<LdbcQuery3, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery3 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_3_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery4Result> LDBC_QUERY_4_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read4Results();

    public static class LdbcQuery4Handler implements OperationHandler<LdbcQuery4, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery4 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_4_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery5Result> LDBC_QUERY_5_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read5Results();

    public static class LdbcQuery5Handler implements OperationHandler<LdbcQuery5, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery5 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_5_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery6Result> LDBC_QUERY_6_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read6Results();

    public static class LdbcQuery6Handler implements OperationHandler<LdbcQuery6, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery6 operation, LiveGraphDbConnectionState dummyDbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_6_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery7Result> LDBC_QUERY_7_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read7Results();

    public static class LdbcQuery7Handler implements OperationHandler<LdbcQuery7, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery7 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_7_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery8Result> LDBC_QUERY_8_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read8Results();

    public static class LdbcQuery8Handler implements OperationHandler<LdbcQuery8, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery8 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_8_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery9Result> LDBC_QUERY_9_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read9Results();

    public static class LdbcQuery9Handler implements OperationHandler<LdbcQuery9, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery9 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_9_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery10Result> LDBC_QUERY_10_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read10Results();

    public static class LdbcQuery10Handler implements OperationHandler<LdbcQuery10, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery10 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_10_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery11Result> LDBC_QUERY_11_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read11Results();

    public static class LdbcQuery11Handler implements OperationHandler<LdbcQuery11, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery11 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_11_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery12Result> LDBC_QUERY_12_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read12Results();

    public static class LdbcQuery12Handler implements OperationHandler<LdbcQuery12, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery12 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_12_RESULTS, operation );
        }
    }

    private static final LdbcQuery13Result LDBC_QUERY_13_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read13Results();

    public static class LdbcQuery13Handler implements OperationHandler<LdbcQuery13, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery13 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_13_RESULTS, operation );
        }
    }

    private static final List<LdbcQuery14Result> LDBC_QUERY_14_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.read14Results();

    public static class LdbcQuery14Handler implements OperationHandler<LdbcQuery14, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcQuery14 operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_QUERY_14_RESULTS, operation );
        }
    }

    /*
    SHORT READS
     */

    public static class LdbcShortQuery1PersonProfileHandler implements OperationHandler<LdbcShortQuery1PersonProfile, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation( LdbcShortQuery1PersonProfile operation, LiveGraphDbConnectionState dbConnectionState, ResultReporter resultReporter ) throws DbException {
            ShortQuery1Request request = new ShortQuery1Request();
            request.personId = operation.personId();
            ShortQuery1Response response = null;
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.shortQuery1(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            LdbcShortQuery1PersonProfileResult result = new LdbcShortQuery1PersonProfileResult(response.firstName, 
                    response.lastName, 
                    response.birthday, 
                    response.locationIp, 
                    response.browserUsed, 
                    response.cityId, 
                    response.gender, 
                    response.creationDate);
            resultReporter.report( response.ret, result , operation );
        }


    }

    private static final List<LdbcShortQuery2PersonPostsResult> LDBC_SHORT_QUERY_2_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short2Results();

    public static class LdbcShortQuery2PersonPostsHandler
            implements OperationHandler<LdbcShortQuery2PersonPosts, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcShortQuery2PersonPosts operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_SHORT_QUERY_2_RESULTS, operation );
        }
    }

    private static final List<LdbcShortQuery3PersonFriendsResult> LDBC_SHORT_QUERY_3_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short3Results();

    public static class LdbcShortQuery3PersonFriendsHandler
            implements OperationHandler<LdbcShortQuery3PersonFriends, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcShortQuery3PersonFriends operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_SHORT_QUERY_3_RESULTS, operation );
        }
    }

    private static final LdbcShortQuery4MessageContentResult LDBC_SHORT_QUERY_4_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short4Results();

    public static class LdbcShortQuery4MessageContentHandler
            implements OperationHandler<LdbcShortQuery4MessageContent, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcShortQuery4MessageContent operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_SHORT_QUERY_4_RESULTS, operation );
        }
    }

    private static final LdbcShortQuery5MessageCreatorResult LDBC_SHORT_QUERY_5_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short5Results();

    public static class LdbcShortQuery5MessageCreatorHandler
            implements OperationHandler<LdbcShortQuery5MessageCreator, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcShortQuery5MessageCreator operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_SHORT_QUERY_5_RESULTS, operation );
        }
    }

    private static final LdbcShortQuery6MessageForumResult LDBC_SHORT_QUERY_6_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short6Results();

    public static class LdbcShortQuery6MessageForumHandler
            implements OperationHandler<LdbcShortQuery6MessageForum, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcShortQuery6MessageForum operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_SHORT_QUERY_6_RESULTS, operation );
        }
    }

    private static final List<LdbcShortQuery7MessageRepliesResult> LDBC_SHORT_QUERY_7_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short7Results();

    public static class LdbcShortQuery7MessageRepliesHandler
            implements OperationHandler<LdbcShortQuery7MessageReplies, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcShortQuery7MessageReplies operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LDBC_SHORT_QUERY_7_RESULTS, operation );
        }
    }

    /*
    UPDATES
     */

    public static class LdbcUpdate1AddPersonHandler
            implements OperationHandler<LdbcUpdate1AddPerson, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcUpdate1AddPerson operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LdbcNoResult.INSTANCE, operation );
        }
    }

    public static class LdbcUpdate2AddPostLikeHandler
            implements OperationHandler<LdbcUpdate2AddPostLike, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcUpdate2AddPostLike operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LdbcNoResult.INSTANCE, operation );
        }
    }

    public static class LdbcUpdate3AddCommentLikeHandler
            implements OperationHandler<LdbcUpdate3AddCommentLike, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcUpdate3AddCommentLike operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LdbcNoResult.INSTANCE, operation );
        }
    }

    public static class LdbcUpdate4AddForumHandler
            implements OperationHandler<LdbcUpdate4AddForum, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcUpdate4AddForum operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LdbcNoResult.INSTANCE, operation );
        }
    }

    public static class LdbcUpdate5AddForumMembershipHandler
            implements OperationHandler<LdbcUpdate5AddForumMembership, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcUpdate5AddForumMembership operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LdbcNoResult.INSTANCE, operation );
        }
    }

    public static class LdbcUpdate6AddPostHandler implements OperationHandler<LdbcUpdate6AddPost, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcUpdate6AddPost operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LdbcNoResult.INSTANCE, operation );
        }
    }

    public static class LdbcUpdate7AddCommentHandler
            implements OperationHandler<LdbcUpdate7AddComment, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcUpdate7AddComment operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LdbcNoResult.INSTANCE, operation );
        }
    }

    public static class LdbcUpdate8AddFriendshipHandler
            implements OperationHandler<LdbcUpdate8AddFriendship, LiveGraphDbConnectionState>
    {
        @Override
        public void executeOperation( LdbcUpdate8AddFriendship operation, LiveGraphDbConnectionState dbConnectionState,
                ResultReporter resultReporter ) throws DbException
        {
            resultReporter.report( 0, LdbcNoResult.INSTANCE, operation );
        }
    }


}
