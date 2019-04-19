namespace cpp interactive
namespace java com.ldbc.impls.workloads.ldbc.snb.livegraph.interactive

struct Query1Request {
    1: i64 personId,
    2: string firstName,
    3: i32 limit,
}

struct Query1Response {
    1: i64 friendId,
    2: string friendLastName,
    3: i32 distanceFromPerson,
    4: i64 friendBirthday,
    5: i64 friendCreationDate,
    6: string friendGender,
    7: string friendBrowserUsed,
    8: string friendLocationIp,
    9: list<string> friendEmails,
    10: list<string> friendLanguages,
    11: string friendCityName;
    12: list<string> friendUniversities_name,
    13: list<i32> friendUniversities_year,
    14: list<string> friendUniversities_city,
    15: list<string> friendCompanies_name,
    16: list<i32> friendCompanies_year,
    17: list<string> friendCompanies_city,
}

struct Query2Request {
    1: i64 personId,
    2: i64 maxDate,
    3: i32 limit,
}

struct Query2Response {
    1: i64 personId,
    2: string personFirstName,
    3: string personLastName,
    4: i64 messageId,
    6: string messageContent,
    7: i64 messageCreationDate,
}

struct Query3Request {
    1: i64 personId,
    2: string countryXName,
    3: string countryYName,
    4: i64 startDate,
    5: i32 durationDays,
    6: i32 limit,
}

struct Query3Response {
    1: i64 personId,
    2: string personFirstName,
    3: string personLastName,
    4: i32 xCount,
    5: i32 yCount,
    6: i32 count,
}

struct Query4Request {
    1: i64 personId,
    2: i64 startDate,
    3: i32 durationDays,
    4: i32 limit,
}

struct Query4Response {
    1: string tagName,
    2: i32 postCount,
}


struct Query5Request {
    1: i64 personId,
    2: i64 minDate,
    3: i32 limit,
}

struct Query5Response {
    1: string forumTitle,
    2: i32 postCount,
}

struct Query6Request {
    1: i64 personId,
    2: string tagName,
    3: i32 limit,
}

struct Query6Response {
    1: string tagName,
    2: i32 postCount,
}

struct Query7Request {
    1: i64 personId,
    2: i32 limit,
}

struct Query7Response {
    1: i64 personId,
    2: string personFirstName,
    3: string personLastName,
    4: i64 likeCreationDate,
    5: i64 commentOrPostId,
    6: string commentOrPostContent,
    7: i32 minutesLatency,
    8: bool isNew,
}

struct Query8Request {
    1: i64 personId,
    2: i32 limit,
}

struct Query8Response {
    1: i64 personId,
    2: string personFirstName,
    3: string personLastName,
    4: i64 commentCreationDate,
    5: i64 commentId,
    6: string commentContent,
}

struct Query9Request {
    1: i64 personId,
    2: i64 maxDate,
    3: i32 limit,
}

struct Query9Response {
    1: i64 personId,
    2: string personFirstName,
    3: string personLastName,
    4: i64 messageId,
    5: string messageContent,
    6: i64 messageCreationDate,
}

struct Query10Request {
    1: i64 personId,
    2: i32 month,
    3: i32 limit,
}

struct Query10Response {
    1: i64 personId,
    2: string personFirstName,
    3: string personLastName,
    4: i32 commonInterestSore
    5: string personGender,
    6: string personCityName,
}

struct Query11Request {
    1: i64 personId,
    2: string countryName,
    3: i32 workFromYear,
    4: i32 limit,
}

struct Query11Response {
    1: i64 personId,
    2: string personFirstName,
    3: string personLastName,
    4: string organizationName,
    5: i32 organizationWorkFromYear,
}

struct Query12Request {
    1: i64 personId,
    2: string tagClassName,
    3: i32 limit,
}

struct Query12Response {
    1: i64 personId,
    2: string personFirstName,
    3: string personLastName,
    4: list<string> tagNames,
    5: i32 replyCount,
}

struct Query13Request {
    1: i64 person1Id,
    2: i64 person2Id,
}

struct Query13Response {
    1: i32 shortestPathLength,
}

struct Query14Request {
    1: i64 person1Id,
    2: i64 person2Id,
}

struct Query14Response {
    1: list<i64> personIdsInPath,
    2: double pathWeight,
}

struct ShortQuery1Request {
    1: i64 personId,
}

struct ShortQuery1Response {
    1: string firstName,
    2: string lastName,
    3: i64 birthday,
    4: string locationIp,
    5: string browserUsed,
    6: i64 cityId,
    7: string gender,
    8: i64 creationDate,
}

struct ShortQuery2Request {
    1: i64 personId,
    2: i32 limit,
}

struct ShortQuery2Response {
    1: i64 messageId,
    2: string messageContent,
    3: i64 messageCreationDate,
    4: i64 originalPostId,
    5: i64 originalPostAuthorId,
    6: string originalPostAuthorFirstName,
    7: string originalPostAuthorLastName,
}

struct ShortQuery3Request {
    1: i64 personId,
}

struct ShortQuery3Response {
    1: i64 personId,
    2: string firstName,
    3: string lastName,
    4: i64 friendshipCreationDate,
}

struct ShortQuery4Request {
    1: i64 messageId,
}

struct ShortQuery4Response {
    1: i64 messageCreationDate,
    2: string messageContent,
}

struct ShortQuery5Request {
    1: i64 messageId,
}

struct ShortQuery5Response {
    1: i64 personId,
    2: string firstName,
    3: string lastName,
}

struct ShortQuery6Request {
    1: i64 messageId,
}

struct ShortQuery6Response {
    1: i64 forumId,
    2: string forumTitle,
    3: i64 moderatorId,
    4: string moderatorFirstName,
    5: string moderatorLastName,
}

struct ShortQuery7Request {
    1: i64 messageId,
}

struct ShortQuery7Response {
    1: i64 commentId,
    2: string commentContent,
    3: i64 commentCreationDate,
    4: i64 replyAuthorId,
    5: string replyAuthorFirstName,
    6: string replyAuthorLastName,
    7: bool replyAuthorKnowsOriginalMassageAuthor,
}

struct Update1Request {
    1: i64 personId,
    2: string personFirstName,
    3: string personLastName,
    4: string gender,
    5: i64 birthday,
    6: i64 creationDate,
    7: string locationIp,
    8: string browserUsed,
    9: i64 cityId,
    10: list<string> languages,
    11: list<string> emails,
    12: list<i64> tagIds,
    13: list<i64> studyAt_id,
    14: list<i32> studyAt_year,
    15: list<i64> workAt_id,
    16: list<i32> workAt_year,
}

struct Update2Request {
    1: i64 personId,
    2: i64 postId,
    3: i64 creationDate,
}

struct Update3Request {
    1: i64 personId,
    2: i64 commentId,
    3: i64 creationDate,
}

struct Update4Request {
    1: i64 forumId,
    2: string forumTitle,
    3: i64 creationDate,
    4: i64 moderatorPersonId,
    5: list<i64> tagIds,
}

struct Update5Request {
    1: i64 personId,
    2: i64 forumId,
    3: i64 joinDate,
}

struct Update6Request {
    1: i64 postId,
    2: string imageFile,
    3: i64 creationDate,
    4: string locationIp,
    5: string browserUsed,
    6: string language,
    7: string content,
    8: i32 length,
    9: i64 authorPersonId,
    10: i64 forumId,
    11: i64 countryId,
    12: list<i64> tagIds,
}

struct Update7Request {
    1: i64 commentId,
    2: i64 creationDate,
    3: string locationIp,
    4: string browserUsed,
    5: string content,
    6: i32 length,
    7: i64 authorPersonId,
    8: i64 countryId,
    9: i64 replyToPostId,
    10: i64 replyToCommentId,
    11: list<i64> tagIds,
}

struct Update8Request {
    1: i64 person1Id,
    2: i64 person2Id,
    3: i64 creationDate,
}

service Interactive {
    list<Query1Response> query1(1:Query1Request request),
    list<Query2Response> query2(1:Query2Request request),
    list<Query3Response> query3(1:Query3Request request),
    list<Query4Response> query4(1:Query4Request request),
    list<Query5Response> query5(1:Query5Request request),
    list<Query6Response> query6(1:Query6Request request),
    list<Query7Response> query7(1:Query7Request request),
    list<Query8Response> query8(1:Query8Request request),
    list<Query9Response> query9(1:Query9Request request),
    list<Query10Response> query10(1:Query10Request request),
    list<Query11Response> query11(1:Query11Request request),
    list<Query12Response> query12(1:Query12Request request),
    Query13Response query13(1:Query13Request request),
    list<Query14Response> query14(1:Query14Request request),
    ShortQuery1Response shortQuery1(1:ShortQuery1Request request),
    list<ShortQuery2Response> shortQuery2(1:ShortQuery2Request request),
    list<ShortQuery3Response> shortQuery3(1:ShortQuery3Request request),
    ShortQuery4Response shortQuery4(1:ShortQuery4Request request),
    ShortQuery5Response shortQuery5(1:ShortQuery5Request request),
    ShortQuery6Response shortQuery6(1:ShortQuery6Request request),
    list<ShortQuery7Response> shortQuery7(1:ShortQuery7Request request),
    void update1(1:Update1Request request),
    void update2(1:Update2Request request),
    void update3(1:Update3Request request),
    void update4(1:Update4Request request),
    void update5(1:Update5Request request),
    void update6(1:Update6Request request),
    void update7(1:Update7Request request),
    void update8(1:Update8Request request),
}
