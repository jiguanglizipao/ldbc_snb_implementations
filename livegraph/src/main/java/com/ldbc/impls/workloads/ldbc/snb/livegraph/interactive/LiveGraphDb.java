package com.ldbc.impls.workloads.ldbc.snb.livegraph.interactive;

import com.ldbc.driver.*;
import com.ldbc.driver.control.LoggingService;
import com.ldbc.driver.workloads.ldbc.snb.interactive.*;
import com.ldbc.driver.workloads.ldbc.snb.interactive.db.DummyLdbcSnbInteractiveOperationResultSets;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LiveGraphDb extends Db {

    private static final List<LdbcShortQuery2PersonPostsResult> LDBC_SHORT_QUERY_2_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short2Results();
    private static final List<LdbcShortQuery3PersonFriendsResult> LDBC_SHORT_QUERY_3_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short3Results();
    private static final LdbcShortQuery4MessageContentResult LDBC_SHORT_QUERY_4_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short4Results();
    private static final LdbcShortQuery5MessageCreatorResult LDBC_SHORT_QUERY_5_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short5Results();
    private static final LdbcShortQuery6MessageForumResult LDBC_SHORT_QUERY_6_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short6Results();
    private static final List<LdbcShortQuery7MessageRepliesResult> LDBC_SHORT_QUERY_7_RESULTS =
            DummyLdbcSnbInteractiveOperationResultSets.short7Results();
    protected LiveGraphDbConnectionState dcs;

    @Override
    protected void onInit(Map<String, String> properties, LoggingService loggingService) throws DbException {
        try {
            dcs = new LiveGraphDbConnectionState(properties);
        } catch (TException e) {
            e.printStackTrace();
        }
        // Long Reads
        registerOperationHandler(LdbcQuery1.class, LdbcQuery1Handler.class);
        registerOperationHandler(LdbcQuery2.class, LdbcQuery2Handler.class);
        registerOperationHandler(LdbcQuery3.class, LdbcQuery3Handler.class);
        registerOperationHandler(LdbcQuery4.class, LdbcQuery4Handler.class);
        registerOperationHandler(LdbcQuery5.class, LdbcQuery5Handler.class);
        registerOperationHandler(LdbcQuery6.class, LdbcQuery6Handler.class);
        registerOperationHandler(LdbcQuery7.class, LdbcQuery7Handler.class);
        registerOperationHandler(LdbcQuery8.class, LdbcQuery8Handler.class);
        registerOperationHandler(LdbcQuery9.class, LdbcQuery9Handler.class);
        registerOperationHandler(LdbcQuery10.class, LdbcQuery10Handler.class);
        registerOperationHandler(LdbcQuery11.class, LdbcQuery11Handler.class);
        registerOperationHandler(LdbcQuery12.class, LdbcQuery12Handler.class);
        registerOperationHandler(LdbcQuery13.class, LdbcQuery13Handler.class);
        registerOperationHandler(LdbcQuery14.class, LdbcQuery14Handler.class);
        // Short Reads
        registerOperationHandler(LdbcShortQuery1PersonProfile.class, LdbcShortQuery1PersonProfileHandler.class);
        registerOperationHandler(LdbcShortQuery2PersonPosts.class, LdbcShortQuery2PersonPostsHandler.class);
        registerOperationHandler(LdbcShortQuery3PersonFriends.class, LdbcShortQuery3PersonFriendsHandler.class);
        registerOperationHandler(LdbcShortQuery4MessageContent.class, LdbcShortQuery4MessageContentHandler.class);
        registerOperationHandler(LdbcShortQuery5MessageCreator.class, LdbcShortQuery5MessageCreatorHandler.class);
        registerOperationHandler(LdbcShortQuery6MessageForum.class, LdbcShortQuery6MessageForumHandler.class);
        registerOperationHandler(LdbcShortQuery7MessageReplies.class, LdbcShortQuery7MessageRepliesHandler.class);
        // Updates
        registerOperationHandler(LdbcUpdate1AddPerson.class, LdbcUpdate1AddPersonHandler.class);
        registerOperationHandler(LdbcUpdate2AddPostLike.class, LdbcUpdate2AddPostLikeHandler.class);
        registerOperationHandler(LdbcUpdate3AddCommentLike.class, LdbcUpdate3AddCommentLikeHandler.class);
        registerOperationHandler(LdbcUpdate4AddForum.class, LdbcUpdate4AddForumHandler.class);
        registerOperationHandler(LdbcUpdate5AddForumMembership.class, LdbcUpdate5AddForumMembershipHandler.class);
        registerOperationHandler(LdbcUpdate6AddPost.class, LdbcUpdate6AddPostHandler.class);
        registerOperationHandler(LdbcUpdate7AddComment.class, LdbcUpdate7AddCommentHandler.class);
        registerOperationHandler(LdbcUpdate8AddFriendship.class, LdbcUpdate8AddFriendshipHandler.class);

    }

    @Override
    protected void onClose() {
        dcs.close();
    }

    @Override
    protected DbConnectionState getConnectionState() {
        return dcs;
    }

    public static class LdbcQuery1Handler implements OperationHandler<LdbcQuery1, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery1 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query1Request request = new Query1Request();
            request.personId = operation.personId();
            request.firstName = operation.firstName();
            request.limit = operation.limit();
            List<Query1Response> response = new ArrayList<>();
            ArrayList<LdbcQuery1Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query1(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query1Response resp : response) {
                ArrayList<List<Object>> friendUniversities = new ArrayList<>();
                ArrayList<List<Object>> friendCompanies = new ArrayList<>();
                for (int i = 0; i < resp.friendUniversities_name.size(); i++) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(resp.friendUniversities_name.get(i));
                    list.add(resp.friendUniversities_year.get(i));
                    list.add(resp.friendUniversities_city.get(i));
                    friendUniversities.add(list);
                }
                for (int i = 0; i < resp.friendCompanies_name.size(); i++) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(resp.friendCompanies_name.get(i));
                    list.add(resp.friendCompanies_year.get(i));
                    list.add(resp.friendCompanies_city.get(i));
                    friendCompanies.add(list);
                }
                LdbcQuery1Result res = new LdbcQuery1Result(resp.friendId,
                        resp.friendLastName,
                        resp.distanceFromPerson,
                        resp.friendBirthday,
                        resp.friendCreationDate,
                        resp.friendGender,
                        resp.friendBrowserUsed,
                        resp.friendLocationIp,
                        resp.friendEmails,
                        resp.friendLanguages,
                        resp.friendCityName,
                        friendUniversities,
                        friendCompanies);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery2Handler implements OperationHandler<LdbcQuery2, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery2 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query2Request request = new Query2Request();
            request.personId = operation.personId();
            request.maxDate = operation.maxDate().getTime();
            request.limit = operation.limit();
            List<Query2Response> response = new ArrayList<>();
            ArrayList<LdbcQuery2Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query2(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query2Response resp : response) {
                LdbcQuery2Result res = new LdbcQuery2Result(resp.personId,
                        resp.personFirstName,
                        resp.personLastName,
                        resp.messageId,
                        resp.messageContent,
                        resp.messageCreationDate);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery3Handler implements OperationHandler<LdbcQuery3, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery3 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query3Request request = new Query3Request();
            request.personId = operation.personId();
            request.countryXName = operation.countryXName();
            request.countryYName = operation.countryYName();
            request.startDate = operation.startDate().getTime();
            request.durationDays = operation.durationDays();
            request.limit = operation.limit();
            List<Query3Response> response = new ArrayList<>();
            ArrayList<LdbcQuery3Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query3(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query3Response resp : response) {
                LdbcQuery3Result res = new LdbcQuery3Result(resp.personId,
                        resp.personFirstName,
                        resp.personLastName,
                        resp.xCount,
                        resp.yCount,
                        resp.count);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery4Handler implements OperationHandler<LdbcQuery4, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery4 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query4Request request = new Query4Request();
            request.personId = operation.personId();
            request.startDate = operation.startDate().getTime();
            request.durationDays = operation.durationDays();
            request.limit = operation.limit();
            List<Query4Response> response = new ArrayList<>();
            ArrayList<LdbcQuery4Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query4(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query4Response resp : response) {
                LdbcQuery4Result res = new LdbcQuery4Result(resp.tagName, resp.postCount);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery5Handler implements OperationHandler<LdbcQuery5, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery5 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query5Request request = new Query5Request();
            request.personId = operation.personId();
            request.minDate = operation.minDate().getTime();
            request.limit = operation.limit();
            List<Query5Response> response = new ArrayList<>();
            ArrayList<LdbcQuery5Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query5(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query5Response resp : response) {
                LdbcQuery5Result res = new LdbcQuery5Result(resp.forumTitle, resp.postCount);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery6Handler implements OperationHandler<LdbcQuery6, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery6 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query6Request request = new Query6Request();
            request.personId = operation.personId();
            request.tagName = operation.tagName();
            request.limit = operation.limit();
            List<Query6Response> response = new ArrayList<>();
            ArrayList<LdbcQuery6Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query6(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query6Response resp : response) {
                LdbcQuery6Result res = new LdbcQuery6Result(resp.tagName, resp.postCount);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery7Handler implements OperationHandler<LdbcQuery7, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery7 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query7Request request = new Query7Request();
            request.personId = operation.personId();
            request.limit = operation.limit();
            List<Query7Response> response = new ArrayList<>();
            ArrayList<LdbcQuery7Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query7(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query7Response resp : response) {
                LdbcQuery7Result res = new LdbcQuery7Result(resp.personId,
                        resp.personFirstName,
                        resp.personLastName,
                        resp.likeCreationDate,
                        resp.commentOrPostId,
                        resp.commentOrPostContent,
                        resp.minutesLatency,
                        resp.isNew);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery8Handler implements OperationHandler<LdbcQuery8, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery8 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query8Request request = new Query8Request();
            request.personId = operation.personId();
            request.limit = operation.limit();
            List<Query8Response> response = new ArrayList<>();
            ArrayList<LdbcQuery8Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query8(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query8Response resp : response) {
                LdbcQuery8Result res = new LdbcQuery8Result(resp.personId,
                        resp.personFirstName,
                        resp.personLastName,
                        resp.commentCreationDate,
                        resp.commentId,
                        resp.commentContent);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery9Handler implements OperationHandler<LdbcQuery9, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery9 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query9Request request = new Query9Request();
            request.personId = operation.personId();
            request.maxDate = operation.maxDate().getTime();
            request.limit = operation.limit();
            List<Query9Response> response = new ArrayList<>();
            ArrayList<LdbcQuery9Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query9(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query9Response resp : response) {
                LdbcQuery9Result res = new LdbcQuery9Result(resp.personId,
                        resp.personFirstName,
                        resp.personLastName,
                        resp.messageId,
                        resp.messageContent,
                        resp.messageCreationDate);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery10Handler implements OperationHandler<LdbcQuery10, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery10 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query10Request request = new Query10Request();
            request.personId = operation.personId();
            request.month = operation.month();
            request.limit = operation.limit();
            List<Query10Response> response = new ArrayList<>();
            ArrayList<LdbcQuery10Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query10(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query10Response resp : response) {
                LdbcQuery10Result res = new LdbcQuery10Result(resp.personId,
                        resp.personFirstName,
                        resp.personLastName,
                        resp.commonInterestSore,
                        resp.personGender,
                        resp.personCityName);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery11Handler implements OperationHandler<LdbcQuery11, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery11 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query11Request request = new Query11Request();
            request.personId = operation.personId();
            request.countryName = operation.countryName();
            request.workFromYear = operation.workFromYear();
            request.limit = operation.limit();
            List<Query11Response> response = new ArrayList<>();
            ArrayList<LdbcQuery11Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query11(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query11Response resp : response) {
                LdbcQuery11Result res = new LdbcQuery11Result(resp.personId,
                        resp.personFirstName,
                        resp.personLastName,
                        resp.organizationName,
                        resp.organizationWorkFromYear);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery12Handler implements OperationHandler<LdbcQuery12, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery12 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query12Request request = new Query12Request();
            request.personId = operation.personId();
            request.tagClassName = operation.tagClassName();
            request.limit = operation.limit();
            List<Query12Response> response = new ArrayList<>();
            ArrayList<LdbcQuery12Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query12(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query12Response resp : response) {
                LdbcQuery12Result res = new LdbcQuery12Result(resp.personId,
                        resp.personFirstName,
                        resp.personLastName,
                        resp.tagNames,
                        resp.replyCount);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery13Handler implements OperationHandler<LdbcQuery13, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery13 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query13Request request = new Query13Request();
            request.person1Id = operation.person1Id();
            request.person2Id = operation.person2Id();
            Query13Response response = new Query13Response();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query13(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            LdbcQuery13Result result = new LdbcQuery13Result(response.shortestPathLength);
            resultReporter.report(0, result, operation);
        }
    }

    public static class LdbcQuery14Handler implements OperationHandler<LdbcQuery14, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcQuery14 operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Query14Request request = new Query14Request();
            request.person1Id = operation.person1Id();
            request.person2Id = operation.person2Id();
            List<Query14Response> response = new ArrayList<>();
            ArrayList<LdbcQuery14Result> result = new ArrayList<>();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                response = client.query14(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            for (Query14Response resp : response) {
                LdbcQuery14Result res = new LdbcQuery14Result(resp.personIdsInPath, resp.pathWeight);
                result.add(res);
            }
            resultReporter.report(0, result, operation);
        }
    }

    /*
    SHORT READS
     */

    public static class LdbcShortQuery1PersonProfileHandler implements OperationHandler<LdbcShortQuery1PersonProfile, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcShortQuery1PersonProfile operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            ShortQuery1Request request = new ShortQuery1Request();
            request.personId = operation.personId();
            ShortQuery1Response response = new ShortQuery1Response();
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
            resultReporter.report(0, result, operation);
        }


    }

    public static class LdbcShortQuery2PersonPostsHandler
            implements OperationHandler<LdbcShortQuery2PersonPosts, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcShortQuery2PersonPosts operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            resultReporter.report(0, LDBC_SHORT_QUERY_2_RESULTS, operation);
        }
    }

    public static class LdbcShortQuery3PersonFriendsHandler
            implements OperationHandler<LdbcShortQuery3PersonFriends, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcShortQuery3PersonFriends operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            resultReporter.report(0, LDBC_SHORT_QUERY_3_RESULTS, operation);
        }
    }

    public static class LdbcShortQuery4MessageContentHandler
            implements OperationHandler<LdbcShortQuery4MessageContent, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcShortQuery4MessageContent operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            resultReporter.report(0, LDBC_SHORT_QUERY_4_RESULTS, operation);
        }
    }

    public static class LdbcShortQuery5MessageCreatorHandler
            implements OperationHandler<LdbcShortQuery5MessageCreator, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcShortQuery5MessageCreator operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            resultReporter.report(0, LDBC_SHORT_QUERY_5_RESULTS, operation);
        }
    }

    public static class LdbcShortQuery6MessageForumHandler
            implements OperationHandler<LdbcShortQuery6MessageForum, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcShortQuery6MessageForum operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            resultReporter.report(0, LDBC_SHORT_QUERY_6_RESULTS, operation);
        }
    }

    public static class LdbcShortQuery7MessageRepliesHandler
            implements OperationHandler<LdbcShortQuery7MessageReplies, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcShortQuery7MessageReplies operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            resultReporter.report(0, LDBC_SHORT_QUERY_7_RESULTS, operation);
        }
    }

    /*
    UPDATES
     */

    public static class LdbcUpdate1AddPersonHandler
            implements OperationHandler<LdbcUpdate1AddPerson, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcUpdate1AddPerson operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Update1Request request = new Update1Request();
            request.personId =  operation.personId();
            request.personFirstName = operation.personFirstName();
            request.personLastName = operation.personLastName();
            request.gender = operation.gender();
            request.birthday = operation.birthday().getTime();
            request.creationDate = operation.creationDate().getTime();
            request.locationIp = operation.locationIp();
            request.browserUsed = operation.browserUsed();
            request.cityId = operation.cityId();
            request.languages = operation.languages();
            request.emails = operation.emails();
            request.tagIds = operation.tagIds();
            request.studyAt_id = new ArrayList<>();
            request.studyAt_year = new ArrayList<>();
            for (LdbcUpdate1AddPerson.Organization org : operation.studyAt()) {
                request.studyAt_id.add(org.organizationId());
                request.studyAt_year.add(org.year());
            }
            request.workAt_id = new ArrayList<>();
            request.workAt_year = new ArrayList<>();
            for (LdbcUpdate1AddPerson.Organization org : operation.workAt()) {
                request.workAt_id.add(org.organizationId());
                request.workAt_year.add(org.year());
            }
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                client.update1(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            resultReporter.report(0, LdbcNoResult.INSTANCE, operation);
        }
    }

    public static class LdbcUpdate2AddPostLikeHandler
            implements OperationHandler<LdbcUpdate2AddPostLike, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcUpdate2AddPostLike operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Update2Request request = new Update2Request();
            request.personId = operation.personId();
            request.postId = operation.postId();
            request.creationDate = operation.creationDate().getTime();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                client.update2(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            resultReporter.report(0, LdbcNoResult.INSTANCE, operation);
        }
    }

    public static class LdbcUpdate3AddCommentLikeHandler
            implements OperationHandler<LdbcUpdate3AddCommentLike, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcUpdate3AddCommentLike operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Update3Request request = new Update3Request();
            request.personId = operation.personId();
            request.commentId = operation.commentId();
            request.creationDate = operation.creationDate().getTime();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                client.update3(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            resultReporter.report(0, LdbcNoResult.INSTANCE, operation);
        }
    }

    public static class LdbcUpdate4AddForumHandler
            implements OperationHandler<LdbcUpdate4AddForum, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcUpdate4AddForum operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Update4Request request = new Update4Request();
            request.forumId =  operation.forumId();
            request.forumTitle = operation.forumTitle();
            request.creationDate = operation.creationDate().getTime();
            request.moderatorPersonId = operation.moderatorPersonId();
            request.tagIds = operation.tagIds();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                client.update4(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            resultReporter.report(0, LdbcNoResult.INSTANCE, operation);
        }
    }

    public static class LdbcUpdate5AddForumMembershipHandler
            implements OperationHandler<LdbcUpdate5AddForumMembership, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcUpdate5AddForumMembership operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Update5Request request = new Update5Request();
            request.personId = operation.personId();
            request.forumId =  operation.forumId();
            request.joinDate = operation.joinDate().getTime();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                client.update5(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            resultReporter.report(0, LdbcNoResult.INSTANCE, operation);
        }
    }

    public static class LdbcUpdate6AddPostHandler implements OperationHandler<LdbcUpdate6AddPost, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcUpdate6AddPost operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            resultReporter.report(0, LdbcNoResult.INSTANCE, operation);
        }
    }

    public static class LdbcUpdate7AddCommentHandler
            implements OperationHandler<LdbcUpdate7AddComment, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcUpdate7AddComment operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            resultReporter.report(0, LdbcNoResult.INSTANCE, operation);
        }
    }

    public static class LdbcUpdate8AddFriendshipHandler
            implements OperationHandler<LdbcUpdate8AddFriendship, LiveGraphDbConnectionState> {
        @Override
        public void executeOperation(LdbcUpdate8AddFriendship operation, LiveGraphDbConnectionState dbConnectionState,
                                     ResultReporter resultReporter) throws DbException {
            Update8Request request = new Update8Request();
            request.person1Id = operation.person1Id();
            request.person2Id = operation.person2Id();
            request.creationDate = operation.creationDate().getTime();
            try {
                TTransport transport = dbConnectionState.getConnection();
                TBinaryProtocol protocol = new TBinaryProtocol(transport);
                Interactive.Client client = new Interactive.Client(protocol);
                client.update8(request);
                dbConnectionState.returnConnection(transport);
            } catch (TException e) {
                e.printStackTrace();
            }
            resultReporter.report(0, LdbcNoResult.INSTANCE, operation);
        }
    }


}
