package p;

/* JADX INFO: loaded from: classes.dex */
public final class un0 implements fz1 {
    public final String a;
    public final int b = 6;

    public un0(String str) {
        this.a = str;
    }

    @Override // p.tq1
    public final void a(a81 a81Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(cVar, "customScalarAdapters");
        a81Var.l0("uri");
        c5.a.b(a81Var, cVar, this.a);
        a81Var.l0("limit");
        c5.b.b(a81Var, cVar, Integer.valueOf(this.b));
    }

    @Override // p.tq1
    public final wo1 b() {
        wn0 wn0Var = wn0.c;
        w1 w1Var = c5.a;
        return new wo1(wn0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "f51d7630516e96ae2ec89cca5cf423f41fdd00287f32fa04b33e3f91557e532f";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchArtistPage($uri: ID!, $limit: Int!) { artistUnion(uri: $uri) { __typename ... on Artist { discography { topTracks(limit: $limit) { __typename ... on TrackPage { items { track { __typename ...track } } } } popularReleasesAlbums { __typename ... on AlbumPage { items { __typename ...album } } } } } } }  fragment image on Image { sources { url width height } }  fragment contentRating on ContentRating { label }  fragment track on Track { artists { items { profile { name } } } albumOfTrack { coverArt { __typename ...image } } contentRating { __typename ...contentRating } name uri trackNumber discNumber playability { playable } duration { totalMilliseconds } }  fragment album on Album { uri name coverArt { __typename ...image } artists { items { profile { name } } } date { year } name uri }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof un0)) {
            return false;
        }
        un0 un0Var = (un0) obj;
        return v03.b(this.a, un0Var.a) && this.b == un0Var.b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    @Override // p.tq1
    public final String name() {
        return "fetchArtistPage";
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FetchArtistPageQuery(uri=");
        sb.append(this.a);
        sb.append(", limit=");
        return u4.k(sb, this.b, ')');
    }
}
