package p;

/* JADX INFO: loaded from: classes.dex */
public final class nq0 implements fz1 {
    public final int a;

    public nq0(int i) {
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
        oq0 oq0Var = oq0.c;
        w1 w1Var = c5.a;
        return new wo1(oq0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "dcbed06dabc27707c3d9b6cda2549f228c66457a4f77fce2b66c8b2f26dba653";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchLibraryLikedEpisodes($limit: Int!) { me { library { episodes(limit: $limit) { __typename ... on UserLibraryEpisodePage { items { __typename ... on UserLibraryEpisodeResponse { episode { __typename ...episodeWrapper } } } } } } } }  fragment image on Image { sources { url width height } }  fragment contentRating on ContentRating { label }  fragment episode on Episode { name uri coverArt { __typename ...image } releaseDate { isoString } duration { totalMilliseconds } contentRating { __typename ...contentRating } playability { playable } playedState { playPositionMilliseconds state } podcastV2 { data { __typename ... on Podcast { name } } } }  fragment episodeWrapper on EpisodeResponseWrapper { data { __typename ... on Error { message } ... on Episode { __typename ...episode } } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof nq0) && this.a == ((nq0) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    @Override // p.tq1
    public final String name() {
        return "fetchLibraryLikedEpisodes";
    }

    public final String toString() {
        return u4.k(new StringBuilder("FetchLibraryLikedEpisodesQuery(limit="), this.a, ')');
    }
}
