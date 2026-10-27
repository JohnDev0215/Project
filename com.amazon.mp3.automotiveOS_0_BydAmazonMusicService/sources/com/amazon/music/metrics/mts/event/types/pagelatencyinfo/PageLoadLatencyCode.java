package com.amazon.music.metrics.mts.event.types.pagelatencyinfo;

import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.api.RemoteSearchApi;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.common.Scopes;
import kotlin.Metadata;

/* JADX INFO: compiled from: PageLoadLatencyCode.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b_\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0003X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\ba¨\u0006b"}, d2 = {"Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "", "latencyCode", "", "subtype", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getLatencyCode", "()Ljava/lang/String;", "operation", "getOperation", "getSubtype", "HOME", "FIND", "FIND_V2", "FIND_CONTENT", "LIBRARY", "ALEXA", "ARTIST_DETAIL", "ARTIST_DETAIL_V2", "ALBUM_DETAIL", "ALBUM_DETAIL_FREE_TIER", "SEE_MORE", "PROFILE", "SEARCH_SUGGESTION", "SEARCH_HISTORY", "SEARCH_RESULTS", "MY_MUSIC_SEARCH_RESULTS", "OFFLINE_SEARCH_RESULTS", "PLAYLISTS", "STATIONS", "CHARTS", "NEW_RELEASES", "NOW_PLAYING", "VIDEOS", "PLAY_QUEUE", "AUTOMOTIVE_OS_HOME", "CLOUD_QUEUE_CREATE_QUEUE", "CLOUD_QUEUE_INITIATE_QUEUE", "CLOUD_QUEUE_GET_NEXT_ENTITIES", "CLOUD_QUEUE_GET_QUEUE_VIEW", "GENRE", "THREE_D", RemoteSearchApi.UHD_ASSET_QUALITY, RemoteSearchApi.RA360_ASSET_QUALITY, RemoteSearchApi.ATMOS_ASSET_QUALITY, "PLAYLIST_DETAIL", "PLAYLISTS_LANDING", "PLAYLIST_BROWSE_BRUSH_V3", "LIBRARY_ALBUM_DETAIL", "LIBRARY_ARTIST_DETAIL", "LIBRARY_PLAYLISTS", "LIBRARY_TRACKS", "LIBRARY_ALBUMS", "LIBRARY_ARTISTS", "LIBRARY_ARTISTS_V2", "LIBRARY_FOLLOWED_ARTISTS", "CAR_MODE", "CAR_MODE_HOME", "CAR_MODE_DETAIL", "CAR_MODE_PODCAST_EPISODE_LIST", "CAR_MODE_PRESETS_BRUSH_V3", "CAR_MODE_PLAY_QUEUE", "CAR_PLAY_HOME", "CAR_PLAY_EXPLORE", "CAR_PLAY_GENRE", "CAR_PLAY_LIBRARY", "CAR_PLAY_PODCAST_LATEST", "CAR_PLAY_PODCAST_SHOW", "CASTING_TRANSFER_QUEUE", "MEDIA_BROWSER_HOME", "MEDIA_BROWSER_LIBRARY", "CUSTOMER_PROFILE", "LIVE_STREAMS", "POPULAR", "MUSIC_HOME", "MERCH_ALL", "MERCH_DETAIL", "MERCH_SHOP", "LIVE_EVENT_DETAIL", "RELEASES_DETAIL", "PODCASTS_HOME", "PODCAST_SHOW_ABOUT", "PODCASTS_LIBRARY_SHOWS", "PODCASTS_LIBRARY_DOWNLOADS", "PODCASTS_LIBRARY_PLAYLIST", "PODCASTS_LIBRARY_LATEST", "PODCASTS_FOLLOWED_SHOWS", "PODCASTS_SAVED_EPISODES", "PODCASTS_JUMP_BACK_IN_EPISODES", "PODCAST_SHOW_DETAIL", "PODCAST_EPISODE_DETAIL", "PODCASTS_SEE_ALL_SHOWS", "PODCASTS_SEE_ALL_EPISODES", "PODCAST_NOW_PLAYING", "PODCASTS_CURATED_PAGE", "PODCASTS_TASTE_CATEGORY_SELECTION", "PODCASTS_TASTE_PODCAST_SELECTION", "PODCASTS_TASTE_SUGGESTED_EPISODES", "DMMMetricsMTSEvents_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum PageLoadLatencyCode {
    HOME("pageLoadHome", "home"),
    FIND("pageLoadFind", "find"),
    FIND_V2("pageLoadFindV2", "findV2"),
    FIND_CONTENT("pageLoadFindContent", "findContent"),
    LIBRARY("pageLoadLibrary", "library"),
    ALEXA("pageLoadAlexa", "alexa"),
    ARTIST_DETAIL("pageLoadArtistDetail", "artistDetail"),
    ARTIST_DETAIL_V2("pageLoadArtistDetailV2", "artistDetailV2"),
    ALBUM_DETAIL("pageLoadAlbumDetail", "albumDetail"),
    ALBUM_DETAIL_FREE_TIER("pageLoadAlbumDetailFreeTier", "albumDetailFreeTier"),
    SEE_MORE("pageLoadSeeMore", PageUriUtils.DO_SEE_MORE_VALUE),
    PROFILE("pageLoadProfile", Scopes.PROFILE),
    SEARCH_SUGGESTION("pageLoadSearchSuggestion", "searchSuggestion"),
    SEARCH_HISTORY("pageLoadSearchHistory", SearchMetricsServiceDefault.CONTENT_NAME_SEARCH_HISTORY),
    SEARCH_RESULTS("pageLoadSearchResults", "searchResults"),
    MY_MUSIC_SEARCH_RESULTS("pageLoadMyMusicSearchResults", "myMusicSearchResults"),
    OFFLINE_SEARCH_RESULTS("pageLoadOfflineSearchResults", SearchMetricsServiceDefault.CONTENT_NAME_BASE_OFFLINE_LIBRARY_SEARCH),
    PLAYLISTS("pageLoadPlaylists", "playlists"),
    STATIONS("pageLoadStations", "stations"),
    CHARTS("pageLoadCharts", "charts"),
    NEW_RELEASES("pageLoadNewReleases", "newReleases"),
    NOW_PLAYING("pageLoadNowPlaying", "nowPlaying"),
    VIDEOS("pageVideos", "videos"),
    PLAY_QUEUE("pageLoadPlayQueue", "playQueue"),
    AUTOMOTIVE_OS_HOME("pageLoadAutomotiveOSHome", "automotiveOSHome"),
    CLOUD_QUEUE_CREATE_QUEUE("pageLoadCloudQueueCreateQueue", "cloudQueueCreateQueue"),
    CLOUD_QUEUE_INITIATE_QUEUE("pageLoadCloudQueueInitiateQueue", "cloudQueueInitiateQueue"),
    CLOUD_QUEUE_GET_NEXT_ENTITIES("pageLoadCloudQueueGetNextEntities", "cloudQueueGetNextEntities"),
    CLOUD_QUEUE_GET_QUEUE_VIEW("pageLoadCloudQueueGetQueueView", "cloudQueueGetQueueView"),
    GENRE("pageLoadGenre", ParserUtil.GENRE_SEGMENT_NAME),
    THREE_D("pageLoadThreeD", "3D"),
    UHD("pageLoadUhd", "uhd"),
    RA360("pageLoadRA360", "ra360"),
    ATMOS("pageLoadAtmos", "atmos"),
    PLAYLIST_DETAIL("pageLoadPlaylistDetail", "playlistDetail"),
    PLAYLISTS_LANDING("pageLoadPlaylistsLanding", "playlistsLanding"),
    PLAYLIST_BROWSE_BRUSH_V3("pageLoadPlaylistBrowseBrushV3", "playlistBrowseBrushV3"),
    LIBRARY_ALBUM_DETAIL("pageLoadLibraryAlbumDetail", "libraryAlbumDetail"),
    LIBRARY_ARTIST_DETAIL("pageLoadLibraryArtistDetail", "libraryArtistDetail"),
    LIBRARY_PLAYLISTS("pageLoadLibraryPlaylists", "libraryPlaylists"),
    LIBRARY_TRACKS("pageLoadLibraryTracks", "libraryTracks"),
    LIBRARY_ALBUMS("pageLoadLibraryAlbums", "libraryAlbums"),
    LIBRARY_ARTISTS("pageLoadLibraryArtists", "libraryArtists"),
    LIBRARY_ARTISTS_V2("pageLoadLibraryArtistsV2", "libraryArtistsV2"),
    LIBRARY_FOLLOWED_ARTISTS("pageLoadLibraryFollowedArtists", "libraryFollowedArtists"),
    CAR_MODE("pageLoadCarModePresets", "carModePresets"),
    CAR_MODE_HOME("pageLoadCarModeHome", "carModeHome"),
    CAR_MODE_DETAIL("pageLoadCarModeDetail", "carModeDetail"),
    CAR_MODE_PODCAST_EPISODE_LIST("pageLoadCarModePodcastEpisodeList", "carModePodcastEpisodeList"),
    CAR_MODE_PRESETS_BRUSH_V3("pageLoadCarModePresetsBrushV3", "carModePresetsBrushV3"),
    CAR_MODE_PLAY_QUEUE("pageLoadCarModePlayQueue", "carModePlayQueue"),
    CAR_PLAY_HOME("pageLoadCarPlayHome", "carPlayHome"),
    CAR_PLAY_EXPLORE("pageLoadCarPlayExplore", "carPlayExplore"),
    CAR_PLAY_GENRE("pageLoadCarPlayGenre", "carPlayGenre"),
    CAR_PLAY_LIBRARY("pageLoadCarPlayLibrary", "carPlayLibrary"),
    CAR_PLAY_PODCAST_LATEST("pageLoadCarPlayPodcastLatest", "carPlayPodcastLatest"),
    CAR_PLAY_PODCAST_SHOW("pageLoadCarPlayPodcastShow", "carPlayPodcastShow"),
    CASTING_TRANSFER_QUEUE("pageLoadCastingTransferQueue", "castingTransferQueue"),
    MEDIA_BROWSER_HOME("pageLoadMediaBrowserHome", "mediaBrowserHome"),
    MEDIA_BROWSER_LIBRARY("pageLoadMediaBrowserLibrary", "mediaBrowserLibrary"),
    CUSTOMER_PROFILE("pageLoadCustomerProfile", "customerProfile"),
    LIVE_STREAMS("pageLoadLiveStreams", "liveStreams"),
    POPULAR("pageLoadPopular", "popular"),
    MUSIC_HOME("pageLoadMusicHome", "musicHome"),
    MERCH_ALL("pageLoadMerchAll", "merchAll"),
    MERCH_DETAIL("pageLoadMerchDetail", "merchDetail"),
    MERCH_SHOP("pageLoadMerchShop", "merchShop"),
    LIVE_EVENT_DETAIL("pageLoadLiveEventDetail", "liveEventDetail"),
    RELEASES_DETAIL("pageLoadReleasesDetail", "releasesDetail"),
    PODCASTS_HOME("pageLoadPodcastsHomePage", "podcastsHomePage"),
    PODCAST_SHOW_ABOUT("pageLoadPodcastShowAbout", "podcastShowAbout"),
    PODCASTS_LIBRARY_SHOWS("pageLoadPodcastsLibraryShows", "podcastsLibraryShows"),
    PODCASTS_LIBRARY_DOWNLOADS("pageLoadPodcastsLibraryDownloads", "podcastsLibraryDownloads"),
    PODCASTS_LIBRARY_PLAYLIST("pageLoadPodcastsLibraryPlaylist", "podcastsLibraryPlaylist"),
    PODCASTS_LIBRARY_LATEST("pageLoadPodcastsLibraryLatest", "podcastsLibraryLatest"),
    PODCASTS_FOLLOWED_SHOWS("pageLoadPodcastsFollowedShows", "podcastsFollowedShows"),
    PODCASTS_SAVED_EPISODES("pageLoadPodcastsSavedEpisodes", "podcastsSavedEpisodes"),
    PODCASTS_JUMP_BACK_IN_EPISODES("pageLoadPodcastsJumpBackInEpisodes", "podcastsJumpBackInEpisodes"),
    PODCAST_SHOW_DETAIL("pageLoadPodcastShowDetail", "podcastShowDetail"),
    PODCAST_EPISODE_DETAIL("pageLoadPodcastEpisodeDetail", "podcastEpisodeDetail"),
    PODCASTS_SEE_ALL_SHOWS("pageLoadPodcastsSeeAllShows", "podcastsSeeAllShows"),
    PODCASTS_SEE_ALL_EPISODES("pageLoadPodcastsSeeAllEpisodes", "podcastsSeeAllEpisodes"),
    PODCAST_NOW_PLAYING("pageLoadPodcastNowPlaying", "podcastNowPlaying"),
    PODCASTS_CURATED_PAGE("pageLoadPodcastsCuratedPage", "podcastsCuratedPage"),
    PODCASTS_TASTE_CATEGORY_SELECTION("pageLoadPodcastsTasteCategorySelection", "podcastsTasteCategorySelection"),
    PODCASTS_TASTE_PODCAST_SELECTION("pageLoadPodcastsTastePodcastSelection", "podcastsTastePodcastSelection"),
    PODCASTS_TASTE_SUGGESTED_EPISODES("pageLoadPodcastsTasteSuggestedEpisodes", "podcastsTasteSuggestedEpisodes");

    private final String latencyCode;
    private final String operation = "pageLoad";
    private final String subtype;

    PageLoadLatencyCode(String str, String str2) {
        this.latencyCode = str;
        this.subtype = str2;
    }

    public final String getLatencyCode() {
        return this.latencyCode;
    }

    public final String getSubtype() {
        return this.subtype;
    }

    public final String getOperation() {
        return this.operation;
    }
}
