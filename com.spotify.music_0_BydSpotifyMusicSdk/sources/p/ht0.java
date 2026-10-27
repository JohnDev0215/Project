package p;

/* JADX INFO: loaded from: classes.dex */
public final class ht0 implements fz1 {
    public final String a;

    public ht0(String str) {
        this.a = str;
    }

    @Override // p.tq1
    public final void a(a81 a81Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(cVar, "customScalarAdapters");
        a81Var.l0("uri");
        c5.a.b(a81Var, cVar, this.a);
    }

    @Override // p.tq1
    public final wo1 b() {
        it0 it0Var = it0.c;
        w1 w1Var = c5.a;
        return new wo1(it0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "0a76a2ef6b71e9f31df9ad7c281347f3caeace46e24765e7fd8f378776d451d0";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchShowInfo($uri: ID!) { podcastUnionV2(uri: $uri) { __typename ...audiobookInfo ...podcastInfo } }  fragment image on Image { sources { url width height } }  fragment audiobookInfo on Audiobook { accessInfo { isUserMemberOfAtLeastOneGroup } authors { name } coverArt { __typename ...image } name uri }  fragment podcastInfo on Podcast { coverArt { __typename ...image } mediaType name publisher { name } uri }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ht0) && v03.b(this.a, ((ht0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // p.tq1
    public final String name() {
        return "fetchShowInfo";
    }

    public final String toString() {
        return hf2.d(new StringBuilder("FetchShowInfoQuery(uri="), this.a, ')');
    }
}
