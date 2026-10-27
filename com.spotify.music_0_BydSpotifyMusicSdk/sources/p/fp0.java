package p;

/* JADX INFO: loaded from: classes.dex */
public final class fp0 implements fz1 {
    public final int a;

    public fp0(int i) {
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
        ip0 ip0Var = ip0.c;
        w1 w1Var = c5.a;
        return new wo1(ip0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "9a7129d2768ac974038fc307943831b22e63c8b170fcf92f2dd5d8ac129d0d40";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchLibraryArtists($limit: Int!) { me { library { artists(limit: $limit) { __typename ... on UserLibraryArtistPage { items { __typename ... on UserLibraryArtistResponse { artist { __typename ...artistWrapper } } } } } } } }  fragment image on Image { sources { url width height } }  fragment artist on Artist { uri profile { name } visuals { avatarImage { __typename ...image } } }  fragment artistWrapper on ArtistResponseWrapper { data { __typename ... on Error { message } ... on Artist { __typename ...artist } } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof fp0) && this.a == ((fp0) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    @Override // p.tq1
    public final String name() {
        return "fetchLibraryArtists";
    }

    public final String toString() {
        return u4.k(new StringBuilder("FetchLibraryArtistsQuery(limit="), this.a, ')');
    }
}
