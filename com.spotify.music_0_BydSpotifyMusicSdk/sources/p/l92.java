package p;

/* JADX INFO: loaded from: classes.dex */
public final class l92 implements fz1 {
    public final String a;
    public final int b;
    public final int c;
    public final boolean d;

    public l92(int i, int i2, String str, boolean z) {
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = z;
    }

    @Override // p.tq1
    public final void a(a81 a81Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(cVar, "customScalarAdapters");
        ci1.F(a81Var, cVar, this);
    }

    @Override // p.tq1
    public final wo1 b() {
        p92 p92Var = p92.c;
        w1 w1Var = c5.a;
        return new wo1(p92Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "9831ecbe8b34d7f7236925ca41470c0af2460407ea2a1b5ab8d33ee1344b8d4a";
    }

    @Override // p.tq1
    public final String d() {
        return "query search($query: String!, $limit: Int!, $topResultsLimit: Int!, $includeTracks: Boolean!) { searchV2(query: $query, limit: $limit, numberOfTopResults: $topResultsLimit) { topResults { featured { __typename ... on PlaylistResponseWrapper { __typename ...playlistWrapper } } itemsV2 { item { __typename ... on AlbumResponseWrapper { __typename ...albumWrapper } ... on ArtistResponseWrapper { __typename ...artistWrapper } ... on AudiobookResponseWrapper { __typename ...audiobookWrapper } ... on EpisodeResponseWrapper { __typename ...episodeWrapper } ... on PlaylistResponseWrapper { __typename ...playlistWrapper } ... on PodcastResponseWrapper { __typename ...podcastWrapper } ... on TrackResponseWrapper { __typename ...trackWrapper @include(if: $includeTracks) } } } } albums { items { __typename ...albumWrapper } } artists { items { __typename ...artistWrapper } } audiobooks { items { __typename ...audiobookWrapper } } episodes { items { __typename ...episodeWrapper } } playlists { items { __typename ...playlistWrapper } } podcasts { items { __typename ...podcastWrapper } } tracksV2 @include(if: $includeTracks) { items { item { __typename ...trackWrapper } } } } }  fragment image on Image { sources { url width height } }  fragment playlistWrapper on PlaylistResponseWrapper { data { __typename ... on Error { message } ... on Playlist { description images { items { __typename ...image } } name ownerV2 { data { __typename ... on Error { message } ... on User { name } } } uri } } }  fragment album on Album { uri name coverArt { __typename ...image } artists { items { profile { name } } } date { year } name uri }  fragment albumWrapper on AlbumResponseWrapper { data { __typename ... on Error { message } ... on Album { __typename ...album } } }  fragment artist on Artist { uri profile { name } visuals { avatarImage { __typename ...image } } }  fragment artistWrapper on ArtistResponseWrapper { data { __typename ... on Error { message } ... on Artist { __typename ...artist } } }  fragment audiobookInfo on Audiobook { accessInfo { isUserMemberOfAtLeastOneGroup } authors { name } coverArt { __typename ...image } name uri }  fragment audiobookWrapper on AudiobookResponseWrapper { data { __typename ... on Error { message } ...audiobookInfo } }  fragment contentRating on ContentRating { label }  fragment episode on Episode { name uri coverArt { __typename ...image } releaseDate { isoString } duration { totalMilliseconds } contentRating { __typename ...contentRating } playability { playable } playedState { playPositionMilliseconds state } podcastV2 { data { __typename ... on Podcast { name } } } }  fragment episodeWrapper on EpisodeResponseWrapper { data { __typename ... on Error { message } ... on Episode { __typename ...episode } } }  fragment podcastInfo on Podcast { coverArt { __typename ...image } mediaType name publisher { name } uri }  fragment podcastWrapper on PodcastResponseWrapper { data { __typename ... on Error { message } ...podcastInfo } }  fragment track on Track { artists { items { profile { name } } } albumOfTrack { coverArt { __typename ...image } } contentRating { __typename ...contentRating } name uri trackNumber discNumber playability { playable } duration { totalMilliseconds } }  fragment trackWrapper on TrackResponseWrapper { data { __typename ... on Error { message } ... on Track { __typename ...track } } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l92)) {
            return false;
        }
        l92 l92Var = (l92) obj;
        return v03.b(this.a, l92Var.a) && this.b == l92Var.b && this.c == l92Var.c && this.d == l92Var.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    public final int hashCode() {
        int iA = hf2.a(this.c, hf2.a(this.b, this.a.hashCode() * 31, 31), 31);
        boolean z = this.d;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iA + r1;
    }

    @Override // p.tq1
    public final String name() {
        return "search";
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SearchQuery(query=");
        sb.append(this.a);
        sb.append(", limit=");
        sb.append(this.b);
        sb.append(", topResultsLimit=");
        sb.append(this.c);
        sb.append(", includeTracks=");
        return u4.n(sb, this.d, ')');
    }
}
