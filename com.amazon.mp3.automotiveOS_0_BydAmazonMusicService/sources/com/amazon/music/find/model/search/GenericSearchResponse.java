package com.amazon.music.find.model.search;

import com.amazon.music.find.model.filter.FilterMetadata;
import com.amazon.music.search.SectionType;
import com.amazon.music.search.SpellCorrections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenericSearchResponse.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b)\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R0\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R0\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00110\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\b\"\u0004\b\u0014\u0010\nR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000e\"\u0004\b\u0017\u0010\u0010R0\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00180\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\b\"\u0004\b\u001b\u0010\nR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010R\u001c\u0010\u001f\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u000e\"\u0004\b!\u0010\u0010R\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010*R\u0016\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010/\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00101\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00103\u001a\n\u0012\u0004\u0012\u000204\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00105\u001a\n\u0012\u0004\u0012\u000206\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00107\u001a\n\u0012\u0004\u0012\u000208\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00109\u001a\n\u0012\u0004\u0012\u00020:\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010<\u001a\n\u0012\u0004\u0012\u00020:\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010=\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010@R\u0014\u0010C\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010@R\u0014\u0010E\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010@R\u0014\u0010G\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bH\u0010@R\u0014\u0010I\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010@R\u0014\u0010K\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bL\u0010@R\u0014\u0010M\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u0010@R\u0014\u0010O\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bP\u0010@R\u0014\u0010Q\u001a\u00020>8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bR\u0010@R\u001a\u0010S\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010\u000e\"\u0004\bU\u0010\u0010R0\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00180\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00180\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bW\u0010\b\"\u0004\bX\u0010\nR\u001c\u0010Y\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010\u000e\"\u0004\b[\u0010\u0010R0\u0010\\\u001a\b\u0012\u0004\u0012\u0002000\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002000\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b]\u0010\b\"\u0004\b^\u0010\nR\u001c\u0010_\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010\u000e\"\u0004\ba\u0010\u0010R0\u0010b\u001a\b\u0012\u0004\u0012\u0002020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002020\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bc\u0010\b\"\u0004\bd\u0010\nR\u001c\u0010e\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010\u000e\"\u0004\bg\u0010\u0010R\"\u0010h\u001a\n\u0012\u0004\u0012\u00020i\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010\b\"\u0004\bk\u0010\nR\u001c\u0010l\u001a\u0004\u0018\u00010mX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010o\"\u0004\bp\u0010qR\u001c\u0010r\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010\u000e\"\u0004\bt\u0010\u0010R0\u0010u\u001a\b\u0012\u0004\u0012\u0002040\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002040\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bv\u0010\b\"\u0004\bw\u0010\nR0\u0010x\u001a\b\u0012\u0004\u0012\u0002060\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002060\u00048V@VX\u0096\u000e¢\u0006\f\u001a\u0004\by\u0010\b\"\u0004\bz\u0010\nR\u001c\u0010{\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010\u000e\"\u0004\b}\u0010\u0010R1\u0010~\u001a\b\u0012\u0004\u0012\u0002080\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002080\u00048V@VX\u0096\u000e¢\u0006\r\u001a\u0004\b\u007f\u0010\b\"\u0005\b\u0080\u0001\u0010\nR\u001f\u0010\u0081\u0001\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u000e\"\u0005\b\u0083\u0001\u0010\u0010R3\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020:0\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020:0\u00048V@VX\u0096\u000e¢\u0006\u000e\u001a\u0005\b\u0085\u0001\u0010\b\"\u0005\b\u0086\u0001\u0010\nR\u001f\u0010\u0087\u0001\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010\u000e\"\u0005\b\u0089\u0001\u0010\u0010R3\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020\u00180\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00180\u00048V@VX\u0096\u000e¢\u0006\u000e\u001a\u0005\b\u008b\u0001\u0010\b\"\u0005\b\u008c\u0001\u0010\nR\u001f\u0010\u008d\u0001\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010\u000e\"\u0005\b\u008f\u0001\u0010\u0010R3\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u00020:0\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020:0\u00048V@VX\u0096\u000e¢\u0006\u000e\u001a\u0005\b\u0091\u0001\u0010\b\"\u0005\b\u0092\u0001\u0010\nR\u001f\u0010\u0093\u0001\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010\u000e\"\u0005\b\u0095\u0001\u0010\u0010¨\u0006\u0096\u0001"}, d2 = {"Lcom/amazon/music/find/model/search/GenericSearchResponse;", "Lcom/amazon/music/find/model/search/SearchResponse;", "()V", "value", "", "Lcom/amazon/music/find/model/search/AlbumSearchItem;", "albums", "getAlbums", "()Ljava/util/List;", "setAlbums", "(Ljava/util/List;)V", "albumsBlockRef", "", "getAlbumsBlockRef", "()Ljava/lang/String;", "setAlbumsBlockRef", "(Ljava/lang/String;)V", "Lcom/amazon/music/find/model/search/ArtistSearchItem;", "artists", "getArtists", "setArtists", "artistsBlockRef", "getArtistsBlockRef", "setArtistsBlockRef", "Lcom/amazon/music/find/model/search/PlaylistSearchItem;", "communityPlaylists", "getCommunityPlaylists", "setCommunityPlaylists", "communityPlaylistsBlockRef", "getCommunityPlaylistsBlockRef", "setCommunityPlaylistsBlockRef", "executedTerm", "getExecutedTerm", "setExecutedTerm", "filterMetadata", "Lcom/amazon/music/find/model/filter/FilterMetadata;", "getFilterMetadata", "()Lcom/amazon/music/find/model/filter/FilterMetadata;", "setFilterMetadata", "(Lcom/amazon/music/find/model/filter/FilterMetadata;)V", "isNothingFound", "", "()Z", "mAlbums", "mArtists", "mCommunityPlaylist", "mPlaylists", "mPodcastEpisodes", "Lcom/amazon/music/find/model/search/PodcastEpisodeSearchItem;", "mPodcastShows", "Lcom/amazon/music/find/model/search/PodcastShowSearchItem;", "mSoccerItems", "Lcom/amazon/music/find/model/search/SportsSearchItem;", "mStations", "Lcom/amazon/music/find/model/search/StationSearchItem;", "mTopHitItems", "Lcom/amazon/music/find/model/search/SearchItem;", "mTracks", "Lcom/amazon/music/find/model/search/TrackSearchItem;", "mVideoPlaylists", "mVideos", "numberOfAlbumsAvailable", "", "getNumberOfAlbumsAvailable", "()I", "numberOfArtistsAvailable", "getNumberOfArtistsAvailable", "numberOfCommunityPlaylistsAvailable", "getNumberOfCommunityPlaylistsAvailable", "numberOfPlaylistsAvailable", "getNumberOfPlaylistsAvailable", "numberOfPodcastEpisodesAvailable", "getNumberOfPodcastEpisodesAvailable", "numberOfPodcastShowsAvailable", "getNumberOfPodcastShowsAvailable", "numberOfStationsAvailable", "getNumberOfStationsAvailable", "numberOfTracksAvailable", "getNumberOfTracksAvailable", "numberOfVideoPlaylistsAvailable", "getNumberOfVideoPlaylistsAvailable", "numberOfVideosAvailable", "getNumberOfVideosAvailable", "originalQuery", "getOriginalQuery", "setOriginalQuery", "playlists", "getPlaylists", "setPlaylists", "playlistsBlockRef", "getPlaylistsBlockRef", "setPlaylistsBlockRef", "podcastEpisodes", "getPodcastEpisodes", "setPodcastEpisodes", "podcastEpisodesBlockRef", "getPodcastEpisodesBlockRef", "setPodcastEpisodesBlockRef", "podcastShows", "getPodcastShows", "setPodcastShows", "podcastShowsBlockRef", "getPodcastShowsBlockRef", "setPodcastShowsBlockRef", "sectionOrdering", "Lcom/amazon/music/search/SectionType;", "getSectionOrdering", "setSectionOrdering", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "getSpellCorrections", "()Lcom/amazon/music/search/SpellCorrections;", "setSpellCorrections", "(Lcom/amazon/music/search/SpellCorrections;)V", "sportsBlockRef", "getSportsBlockRef", "setSportsBlockRef", "sportsItems", "getSportsItems", "setSportsItems", "stations", "getStations", "setStations", "stationsBlockRef", "getStationsBlockRef", "setStationsBlockRef", "topHitItems", "getTopHitItems", "setTopHitItems", "topHitsBlockRef", "getTopHitsBlockRef", "setTopHitsBlockRef", "tracks", "getTracks", "setTracks", "tracksBlockRef", "getTracksBlockRef", "setTracksBlockRef", "videoPlaylists", "getVideoPlaylists", "setVideoPlaylists", "videoPlaylistsBlockRef", "getVideoPlaylistsBlockRef", "setVideoPlaylistsBlockRef", "videos", "getVideos", "setVideos", "videosBlockRef", "getVideosBlockRef", "setVideosBlockRef", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GenericSearchResponse implements SearchResponse {
    private String albumsBlockRef;
    private String artistsBlockRef;
    private String communityPlaylistsBlockRef;
    private String executedTerm;
    private FilterMetadata filterMetadata;
    private List<? extends AlbumSearchItem> mAlbums;
    private List<? extends ArtistSearchItem> mArtists;
    private List<? extends PlaylistSearchItem> mCommunityPlaylist;
    private List<? extends PlaylistSearchItem> mPlaylists;
    private List<? extends PodcastEpisodeSearchItem> mPodcastEpisodes;
    private List<? extends PodcastShowSearchItem> mPodcastShows;
    private List<? extends SportsSearchItem> mSoccerItems;
    private List<? extends StationSearchItem> mStations;
    private List<? extends SearchItem> mTopHitItems;
    private List<? extends TrackSearchItem> mTracks;
    private List<? extends PlaylistSearchItem> mVideoPlaylists;
    private List<? extends TrackSearchItem> mVideos;
    private String originalQuery = "";
    private String playlistsBlockRef;
    private String podcastEpisodesBlockRef;
    private String podcastShowsBlockRef;
    private List<? extends SectionType> sectionOrdering;
    private SpellCorrections spellCorrections;
    private String sportsBlockRef;
    private String stationsBlockRef;
    private String topHitsBlockRef;
    private String tracksBlockRef;
    private String videoPlaylistsBlockRef;
    private String videosBlockRef;

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<SectionType> getSectionOrdering() {
        return this.sectionOrdering;
    }

    public void setSectionOrdering(List<? extends SectionType> list) {
        this.sectionOrdering = list;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public SpellCorrections getSpellCorrections() {
        return this.spellCorrections;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public void setSpellCorrections(SpellCorrections spellCorrections) {
        this.spellCorrections = spellCorrections;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getExecutedTerm() {
        return this.executedTerm;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public void setExecutedTerm(String str) {
        this.executedTerm = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getOriginalQuery() {
        return this.originalQuery;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public void setOriginalQuery(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.originalQuery = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public FilterMetadata getFilterMetadata() {
        return this.filterMetadata;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public void setFilterMetadata(FilterMetadata filterMetadata) {
        this.filterMetadata = filterMetadata;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<TrackSearchItem> getTracks() {
        if (this.mTracks == null) {
            this.mTracks = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mTracks;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setTracks(List<? extends TrackSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mTracks = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<ArtistSearchItem> getArtists() {
        if (this.mArtists == null) {
            this.mArtists = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mArtists;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setArtists(List<? extends ArtistSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mArtists = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<AlbumSearchItem> getAlbums() {
        if (this.mAlbums == null) {
            this.mAlbums = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mAlbums;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setAlbums(List<? extends AlbumSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mAlbums = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<PlaylistSearchItem> getPlaylists() {
        if (this.mPlaylists == null) {
            this.mPlaylists = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mPlaylists;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setPlaylists(List<? extends PlaylistSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mPlaylists = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<StationSearchItem> getStations() {
        if (this.mStations == null) {
            this.mStations = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mStations;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setStations(List<? extends StationSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mStations = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<PodcastShowSearchItem> getPodcastShows() {
        if (this.mPodcastShows == null) {
            this.mPodcastShows = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mPodcastShows;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setPodcastShows(List<? extends PodcastShowSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mPodcastShows = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<PodcastEpisodeSearchItem> getPodcastEpisodes() {
        if (this.mPodcastEpisodes == null) {
            this.mPodcastEpisodes = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mPodcastEpisodes;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setPodcastEpisodes(List<? extends PodcastEpisodeSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mPodcastEpisodes = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<SportsSearchItem> getSportsItems() {
        if (this.mSoccerItems == null) {
            this.mSoccerItems = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mSoccerItems;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setSportsItems(List<? extends SportsSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mSoccerItems = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<TrackSearchItem> getVideos() {
        if (this.mVideos == null) {
            this.mVideos = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mVideos;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setVideos(List<? extends TrackSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mVideos = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<PlaylistSearchItem> getVideoPlaylists() {
        if (this.mVideoPlaylists == null) {
            this.mVideoPlaylists = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mVideoPlaylists;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setVideoPlaylists(List<? extends PlaylistSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mVideoPlaylists = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<SearchItem> getTopHitItems() {
        if (this.mTopHitItems == null) {
            this.mTopHitItems = CollectionsKt__CollectionsKt.emptyList();
        }
        List list = this.mTopHitItems;
        Intrinsics.checkNotNull(list);
        return list;
    }

    public void setTopHitItems(List<? extends SearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mTopHitItems = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public List<PlaylistSearchItem> getCommunityPlaylists() {
        List list = this.mCommunityPlaylist;
        return list == null ? CollectionsKt__CollectionsKt.emptyList() : list;
    }

    public void setCommunityPlaylists(List<? extends PlaylistSearchItem> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.mCommunityPlaylist = value;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public int getNumberOfTracksAvailable() {
        return getTracks().size();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public int getNumberOfAlbumsAvailable() {
        return getAlbums().size();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public int getNumberOfArtistsAvailable() {
        return getArtists().size();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public int getNumberOfPlaylistsAvailable() {
        return getPlaylists().size();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public int getNumberOfStationsAvailable() {
        return getStations().size();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public int getNumberOfVideosAvailable() {
        return getVideos().size();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public int getNumberOfVideoPlaylistsAvailable() {
        return getVideoPlaylists().size();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public int getNumberOfPodcastShowsAvailable() {
        return getPodcastShows().size();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public int getNumberOfPodcastEpisodesAvailable() {
        return getPodcastEpisodes().size();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public int getNumberOfCommunityPlaylistsAvailable() {
        return getCommunityPlaylists().size();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public boolean isNothingFound() {
        return getArtists().isEmpty() && getTracks().isEmpty() && getAlbums().isEmpty() && getPlaylists().isEmpty() && getStations().isEmpty() && getVideos().isEmpty() && getVideoPlaylists().isEmpty() && getPodcastShows().isEmpty() && getPodcastEpisodes().isEmpty() && getSportsItems().isEmpty() && getTopHitItems().isEmpty() && getCommunityPlaylists().isEmpty();
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getTracksBlockRef() {
        return this.tracksBlockRef;
    }

    public void setTracksBlockRef(String str) {
        this.tracksBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getArtistsBlockRef() {
        return this.artistsBlockRef;
    }

    public void setArtistsBlockRef(String str) {
        this.artistsBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getAlbumsBlockRef() {
        return this.albumsBlockRef;
    }

    public void setAlbumsBlockRef(String str) {
        this.albumsBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getPlaylistsBlockRef() {
        return this.playlistsBlockRef;
    }

    public void setPlaylistsBlockRef(String str) {
        this.playlistsBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getStationsBlockRef() {
        return this.stationsBlockRef;
    }

    public void setStationsBlockRef(String str) {
        this.stationsBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getSportsBlockRef() {
        return this.sportsBlockRef;
    }

    public void setSportsBlockRef(String str) {
        this.sportsBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getPodcastShowsBlockRef() {
        return this.podcastShowsBlockRef;
    }

    public void setPodcastShowsBlockRef(String str) {
        this.podcastShowsBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getPodcastEpisodesBlockRef() {
        return this.podcastEpisodesBlockRef;
    }

    public void setPodcastEpisodesBlockRef(String str) {
        this.podcastEpisodesBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getTopHitsBlockRef() {
        return this.topHitsBlockRef;
    }

    public void setTopHitsBlockRef(String str) {
        this.topHitsBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getVideosBlockRef() {
        return this.videosBlockRef;
    }

    public void setVideosBlockRef(String str) {
        this.videosBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getVideoPlaylistsBlockRef() {
        return this.videoPlaylistsBlockRef;
    }

    public void setVideoPlaylistsBlockRef(String str) {
        this.videoPlaylistsBlockRef = str;
    }

    @Override // com.amazon.music.find.model.search.SearchResponse
    public String getCommunityPlaylistsBlockRef() {
        return this.communityPlaylistsBlockRef;
    }

    public void setCommunityPlaylistsBlockRef(String str) {
        this.communityPlaylistsBlockRef = str;
    }
}
