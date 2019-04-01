namespace cpp interactive
namespace java com.ldbc.impls.workloads.ldbc.snb.livegraph.interactive

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
    6: i32 yCount,
    7: i32 count,
}

service Interactive {
    list<Query1Response> query1(1:Query1Request request),
    list<Query2Response> query2(1:Query2Request request),
    list<Query3Response> query3(1:Query3Request request),
    ShortQuery1Response shortQuery1(1:ShortQuery1Request request),
}
