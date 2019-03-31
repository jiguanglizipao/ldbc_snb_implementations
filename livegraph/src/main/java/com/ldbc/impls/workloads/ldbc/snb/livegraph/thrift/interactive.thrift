namespace cpp interactive
namespace java com.ldbc.impls.workloads.ldbc.snb.livegraph.interactive

struct ShortQuery1Request {
    1: i64 personId,
}

struct ShortQuery1Response {
    1: i32 ret;
    2: string firstName,
    3: string lastName,
    4: i64 birthday,
    5: string locationIp,
    6: string browserUsed,
    7: i64 cityId,
    8: string gender,
    9: i64 creationDate,
}

service Interactive {
    ShortQuery1Response shortQuery1(1:ShortQuery1Request request),
}
