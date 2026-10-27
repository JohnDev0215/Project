package p;

/* JADX INFO: loaded from: classes.dex */
public final class vm0 implements fz1 {
    public final String a;
    public final int b;

    public vm0(String str, int i) {
        this.a = str;
        this.b = i;
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
        xm0 xm0Var = xm0.c;
        w1 w1Var = c5.a;
        return new wo1(xm0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "8eb4164ed57ec1143783f1f8c7834f4d7ac75704926d45b888248ce13ab44675";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchAlbumTracks($uri: ID!, $limit: Int!) { albumUnion(uri: $uri) { __typename ... on Album { tracks(limit: $limit) { items { track { __typename ...track } } } } } }  fragment image on Image { sources { url width height } }  fragment contentRating on ContentRating { label }  fragment track on Track { artists { items { profile { name } } } albumOfTrack { coverArt { __typename ...image } } contentRating { __typename ...contentRating } name uri trackNumber discNumber playability { playable } duration { totalMilliseconds } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof vm0)) {
            return false;
        }
        vm0 vm0Var = (vm0) obj;
        return v03.b(this.a, vm0Var.a) && this.b == vm0Var.b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    @Override // p.tq1
    public final String name() {
        return "fetchAlbumTracks";
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FetchAlbumTracksQuery(uri=");
        sb.append(this.a);
        sb.append(", limit=");
        return u4.k(sb, this.b, ')');
    }
}
