package p;

/* JADX INFO: loaded from: classes.dex */
public final class ss0 implements fz1 {
    public final int a;

    public ss0(int i) {
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
        ts0 ts0Var = ts0.c;
        w1 w1Var = c5.a;
        return new wo1(ts0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "c7f2ecf39b9aa2fd04ba67d4c1227a66d43a397b683db3632b46c972d315f848";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchLibraryPodcasts($limit: Int!) { me { library { podcasts(limit: $limit) { __typename ... on UserLibraryPodcastPage { items { __typename ... on UserLibraryPodcastResponse { podcast { __typename ...podcastWrapper } } } } } episodes(limit: 1) { __typename ... on UserLibraryEpisodePage { totalCount } } } } }  fragment image on Image { sources { url width height } }  fragment podcastInfo on Podcast { coverArt { __typename ...image } mediaType name publisher { name } uri }  fragment podcastWrapper on PodcastResponseWrapper { data { __typename ... on Error { message } ...podcastInfo } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ss0) && this.a == ((ss0) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    @Override // p.tq1
    public final String name() {
        return "fetchLibraryPodcasts";
    }

    public final String toString() {
        return u4.k(new StringBuilder("FetchLibraryPodcastsQuery(limit="), this.a, ')');
    }
}
