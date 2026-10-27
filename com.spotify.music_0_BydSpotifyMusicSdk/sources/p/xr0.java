package p;

/* JADX INFO: loaded from: classes.dex */
public final class xr0 implements fz1 {
    public final int a;

    public xr0(int i) {
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
        yr0 yr0Var = yr0.c;
        w1 w1Var = c5.a;
        return new wo1(yr0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "f6d1053d470e93a89bb79744a1e565155cafdb3a30496a5e9a5f179356922c9f";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchLibraryPlaylists($limit: Int!) { me { library { tracks { __typename ... on UserLibraryTrackPage { totalCount } } } libraryV2 { page(limit: $limit, filterIds: [\"Playlists\"], sortOrderId: \"Custom Order\") { __typename ... on LibraryPage { items { item { __typename ...playlistWrapper } } } } } } }  fragment image on Image { sources { url width height } }  fragment playlistWrapper on PlaylistResponseWrapper { data { __typename ... on Error { message } ... on Playlist { description images { items { __typename ...image } } name ownerV2 { data { __typename ... on Error { message } ... on User { name } } } uri } } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof xr0) && this.a == ((xr0) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    @Override // p.tq1
    public final String name() {
        return "fetchLibraryPlaylists";
    }

    public final String toString() {
        return u4.k(new StringBuilder("FetchLibraryPlaylistsQuery(limit="), this.a, ')');
    }
}
