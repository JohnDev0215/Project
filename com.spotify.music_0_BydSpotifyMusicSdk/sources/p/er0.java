package p;

/* JADX INFO: loaded from: classes.dex */
public final class er0 implements fz1 {
    public final int a;

    public er0(int i) {
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
        fr0 fr0Var = fr0.c;
        w1 w1Var = c5.a;
        return new wo1(fr0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "dc12d41dc9d31680797b04047cd65cb2dbd6f21beb15ce29065ec53925d92bb3";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchLibraryLikedTracks($limit: Int!) { me { library { tracks(limit: $limit) { __typename ... on UserLibraryTrackPage { items { __typename ... on UserLibraryTrackResponse { track { __typename ...trackWrapper } } } } } } } }  fragment image on Image { sources { url width height } }  fragment contentRating on ContentRating { label }  fragment track on Track { artists { items { profile { name } } } albumOfTrack { coverArt { __typename ...image } } contentRating { __typename ...contentRating } name uri trackNumber discNumber playability { playable } duration { totalMilliseconds } }  fragment trackWrapper on TrackResponseWrapper { data { __typename ... on Error { message } ... on Track { __typename ...track } } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof er0) && this.a == ((er0) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    @Override // p.tq1
    public final String name() {
        return "fetchLibraryLikedTracks";
    }

    public final String toString() {
        return u4.k(new StringBuilder("FetchLibraryLikedTracksQuery(limit="), this.a, ')');
    }
}
