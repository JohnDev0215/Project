package com.amazon.music.metrics.mts.event.types;

import androidx.core.os.EnvironmentCompat;
import com.amazon.music.metrics.mts.event.types.core.MetricValueType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public enum PlaybackPageType implements MetricValueType {
    ALBUM_DETAIL("albumDetail"),
    ALBUMS_LIST("albumsList"),
    ARTIST_DETAIL("artistDetail"),
    ARTISTS_LIST("artistsList"),
    CLOUD_LIBRARY_SEARCH("cloudLibrarySearch"),
    EXTERNAL("external"),
    FORD_SYNC("fordSync"),
    GENRE_DETAIL("genreDetail"),
    GENRES_LIST("genresList"),
    GENRE_STATIONS_LIST("genreStationsList"),
    HOME_SCREEN_WIDGET("homeScreenWidget"),
    MOBILE_WEB("mobileWeb"),
    OFFLINE_LIBRARY_SEARCH("offlineLibrarySearch"),
    PLAYLIST_DETAIL("playlistDetail"),
    PLAYLISTS_LIST("playlistsList"),
    PRIME_BROWSE_ALBUMS_LIST("primeBrowseAlbumsList"),
    PRIME_BROWSE_ARTISTS_LIST("primeBrowseArtistsList"),
    PRIME_BROWSE_PLAYLISTS_LIST("primeBrowsePlaylistsList"),
    PRIME_BROWSE_SEARCH("primeBrowseSearch"),
    PRIME_BROWSE_SEARCH_ALBUMS("primeBrowseSearchAlbums"),
    PRIME_BROWSE_SEARCH_ARTISTS("primeBrowseSearchArtists"),
    PRIME_BROWSE_SEARCH_STATIONS("primeBrowseSearchStations"),
    PRIME_BROWSE_SEARCH_PLAYLISTS("primeBrowseSearchPlaylists"),
    PRIME_BROWSE_SEARCH_SONGS("primeBrowseSearchSongs"),
    PRIME_BROWSE_STATIONS_LIST("primeBrowseStationsList"),
    PRIME_BROWSE_SONGS_LIST("primeBrowseSongsList"),
    PRIME_PLAYLIST_DETAIL("primePlaylistDetail"),
    RECENTLY_PLAYED("recentlyPlayed"),
    SONGS_LIST("songsList"),
    STORE("store"),
    TRACK_DETAIL("trackDetail"),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN),
    SFA("stationFromAnything"),
    GOLDEN_PLAYLIST("goldenPlaylist"),
    MY_SOUNDTRACK("mySoundtrack"),
    CAR_MODE_PRESET_LIST("carModePresetList"),
    CAR_MODE_NOW_PLAYING("carModeNowPlaying"),
    CAR_MODE_PLAY_QUEUE("carModePlayQueue"),
    CAR_MODE_HOME("carModeHome"),
    CAR_MODE_DETAIL("carModeDetail"),
    CAR_MODE_PODCAST_EPISODE_LIST("carModePodcastEpisodeList"),
    MY_LIBRARY_FRONT_PAGE_RECENTLY_PLAYED("myLibraryFrontPageRecentlyPlayed"),
    MY_LIBRARY_FRONT_PAGE_MADE_FOR_YOU("myLibraryFrontPageMdeForYou"),
    AUTOMOTIVE_OS_BROWSE_HOME("automotiveOsBrowseHome"),
    CAR_MODE_BROWSE_SEARCH("carModeBrowseSearch"),
    REACT_NATIVE("reactNative");

    private static final Logger logger = LoggerFactory.getLogger(PlaybackPageType.class.getSimpleName());
    private final String mMetricValue;

    PlaybackPageType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }

    public static PlaybackPageType fromValue(String str) {
        for (PlaybackPageType playbackPageType : values()) {
            if (str.equals(playbackPageType.getMetricValue())) {
                return playbackPageType;
            }
        }
        logger.warn("Invalid playbackPageType " + str + ", default to UNKNOWN");
        return UNKNOWN;
    }
}
