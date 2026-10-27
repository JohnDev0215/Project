package p;

/* JADX INFO: loaded from: classes.dex */
public final class oo0 implements fz1 {
    public final int a;

    public oo0(int i) {
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
        ro0 ro0Var = ro0.c;
        w1 w1Var = c5.a;
        return new wo1(ro0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "67e63f21e7df1c0040ae6e3e10f033ce4ac1b41ec86bf739d4b379edee616f6e";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchLibraryAlbums($limit: Int!) { me { library { albums(limit: $limit) { __typename ... on UserLibraryAlbumPage { items { __typename ... on UserLibraryAlbumResponse { album { __typename ...albumWrapper } } } } } } } }  fragment image on Image { sources { url width height } }  fragment album on Album { uri name coverArt { __typename ...image } artists { items { profile { name } } } date { year } name uri }  fragment albumWrapper on AlbumResponseWrapper { data { __typename ... on Error { message } ... on Album { __typename ...album } } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof oo0) && this.a == ((oo0) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    @Override // p.tq1
    public final String name() {
        return "fetchLibraryAlbums";
    }

    public final String toString() {
        return u4.k(new StringBuilder("FetchLibraryAlbumsQuery(limit="), this.a, ')');
    }
}
