package p;

/* JADX INFO: loaded from: classes.dex */
public final class mt0 implements fz1 {
    public final String a;
    public final int b;

    public mt0(String str, int i) {
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
        nt0 nt0Var = nt0.c;
        w1 w1Var = c5.a;
        return new wo1(nt0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "3989fd37400bfa6170e73b71d8bdba29970255dcfe23d39f926c6224eafdf7b9";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchShow($uri: ID!, $limit: Int!) { podcastUnionV2(uri: $uri) { __typename ...audiobook ...podcast } }  fragment image on Image { sources { url width height } }  fragment contentRating on ContentRating { label }  fragment chapterWrapper on ChapterResponseWrapper { data { __typename ... on Error { message } ... on Chapter { name uri coverArt { __typename ...image } releaseDate { isoString } duration { totalMilliseconds } contentRating { __typename ...contentRating } playability { playable } playedState { playPositionMilliseconds state } } } }  fragment audiobook on Audiobook { name accessInfo { isUserMemberOfAtLeastOneGroup } chaptersV2(limit: $limit) { __typename ... on ContextChapterPage { items { entity { __typename ...chapterWrapper } } } } trailerV2 { __typename ...chapterWrapper } }  fragment episode on Episode { name uri coverArt { __typename ...image } releaseDate { isoString } duration { totalMilliseconds } contentRating { __typename ...contentRating } playability { playable } playedState { playPositionMilliseconds state } podcastV2 { data { __typename ... on Podcast { name } } } }  fragment episodeWrapper on EpisodeResponseWrapper { data { __typename ... on Error { message } ... on Episode { __typename ...episode } } }  fragment podcast on Podcast { episodesV2(limit: $limit, mediaTypes: [AUDIO,UNKNOWN]) { __typename ... on ContextEpisodePage { items { __typename ... on ContextEpisodeV2 { entity { __typename ...episodeWrapper } } } } } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof mt0)) {
            return false;
        }
        mt0 mt0Var = (mt0) obj;
        return v03.b(this.a, mt0Var.a) && this.b == mt0Var.b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    @Override // p.tq1
    public final String name() {
        return "fetchShow";
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FetchShowQuery(uri=");
        sb.append(this.a);
        sb.append(", limit=");
        return u4.k(sb, this.b, ')');
    }
}
