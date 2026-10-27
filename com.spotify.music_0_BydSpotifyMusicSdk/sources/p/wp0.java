package p;

/* JADX INFO: loaded from: classes.dex */
public final class wp0 implements fz1 {
    public final int a;

    public wp0(int i) {
        this.a = i;
    }

    @Override // p.tq1
    public final void a(a81 a81Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(cVar, "customScalarAdapters");
        a81Var.l0("limit");
        c5.b.b(a81Var, cVar, Integer.valueOf(this.a));
    }

    @Override // p.tq1
    public final wo1 b() {
        zp0 zp0Var = zp0.c;
        w1 w1Var = c5.a;
        return new wo1(zp0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "55d9753203023f413cf3d888fcfc4ca436cdf5e82c550eff1fb091e05bd77a86";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchLibraryAudiobooks($limit: Int!) { me { library { audiobooks(limit: $limit) { __typename ... on UserLibraryAudiobookPage { items { __typename ... on UserLibraryAudiobookResponse { audiobook { __typename ...audiobookWrapper } } } } } } } }  fragment image on Image { sources { url width height } }  fragment audiobookInfo on Audiobook { accessInfo { isUserMemberOfAtLeastOneGroup } authors { name } coverArt { __typename ...image } name uri }  fragment audiobookWrapper on AudiobookResponseWrapper { data { __typename ... on Error { message } ...audiobookInfo } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof wp0) && this.a == ((wp0) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    @Override // p.tq1
    public final String name() {
        return "fetchLibraryAudiobooks";
    }

    public final String toString() {
        return u4.k(new StringBuilder("FetchLibraryAudiobooksQuery(limit="), this.a, ')');
    }
}
