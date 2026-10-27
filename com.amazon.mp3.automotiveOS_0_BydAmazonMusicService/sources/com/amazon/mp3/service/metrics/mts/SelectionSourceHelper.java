package com.amazon.mp3.service.metrics.mts;

import android.net.Uri;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.util.EntityValidatorUtil;
import com.amazon.mp3.prime.station.StationTrack;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.metrics.mts.event.types.SelectionSourceInfo;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.subscription.BrowseMode;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class SelectionSourceHelper {
    private static final String TAG = "SelectionSourceHelper";

    public static SelectionSourceType getSelectionSourceType(Uri uri, AbstractItem item) {
        if (uri != null) {
            if (MediaProvider.PrimePlaylists.isPrimePlaylist(uri)) {
                BrowseMode.Mode browseMode = new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode();
                boolean zEquals = browseMode.equals(BrowseMode.Mode.PRIME);
                boolean zEquals2 = browseMode.equals(BrowseMode.Mode.SONIC_RUSH);
                boolean zEquals3 = browseMode.equals(BrowseMode.Mode.HAWKFIRE);
                boolean zEquals4 = browseMode.equals(BrowseMode.Mode.NIGHTWING);
                SelectionSourceType selectionSourceType = SelectionSourceType.UNLIMITED_PLAYLIST;
                return item == null ? zEquals3 ? selectionSourceType : SelectionSourceType.PRIME_PLAYLIST : EntityValidatorUtil.isPersonalizedPlaylistId(item.getId()) ? SelectionSourceType.PERSONALIZE_PLAYLIST : (zEquals2 && item.isSonicRushGolden()) ? SelectionSourceType.GOLDEN_PLAYLIST : (zEquals4 && item.isFreeOnDemand()) ? SelectionSourceType.GOLDEN_PLAYLIST : (zEquals || (zEquals2 && !item.isSonicRushGolden())) ? SelectionSourceType.PRIME_PLAYLIST : selectionSourceType;
            }
            if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(uri)) {
                return SelectionSourceType.SHARED_PLAYLIST;
            }
            if (MediaProvider.UdoPlaylists.isUdoPlaylist(uri)) {
                return SelectionSourceType.USER_PLAYLIST;
            }
            if (MediaProvider.SmartPlaylists.isSmartPlaylist(uri)) {
                return SelectionSourceType.AUTO_PLAYLIST;
            }
            if (MediaProvider.Albums.isAlbum(uri)) {
                return SelectionSourceType.ALBUM;
            }
            if (MediaProvider.Genres.isGenre(uri)) {
                return SelectionSourceType.GENRE;
            }
            if (MediaProvider.Artists.isArtist(uri)) {
                return SelectionSourceType.ARTIST;
            }
            if (MediaProvider.Station.isStation(uri)) {
                return getStationSelectionSourceType(uri);
            }
            if (MediaProvider.PrimeBrowseTracksCollection.FirstTrack.isCollectionFirstTrack(uri)) {
                return SelectionSourceType.SONGS;
            }
        } else {
            Log.error(TAG, "Null uri used to determine SelectionSourceType -- defaulting to SONGS");
        }
        return SelectionSourceType.SONGS;
    }

    public static SelectionSourceType getSelectionSourceType(Uri uri) {
        return getSelectionSourceType(uri, null);
    }

    private static SelectionSourceType getStationSelectionSourceType(Uri uri) {
        boolean zEquals = new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode().equals(BrowseMode.Mode.PRIME);
        return MediaProvider.Station.isStationFromAnything(uri) ? zEquals ? SelectionSourceType.PRIME_STATION_SEED : SelectionSourceType.UNLIMITED_STATION_SEED : zEquals ? SelectionSourceType.PRIME_STATION : SelectionSourceType.UNLIMITED_STATION;
    }

    public static String getSelectionSourceId(final Uri uri, final MediaItem mediaItem, final String collectionName) {
        return getSelectionSourceId(uri, MediaItemHelper.getAlbumAsin(mediaItem), MediaItemHelper.getArtistAsin(mediaItem), collectionName);
    }

    public static String getSelectionSourceId(Uri uri, String albumAsin, String artistAsin, String collectionName) {
        if (uri != null) {
            if (MediaProvider.PrimePlaylists.isPrimePlaylist(uri)) {
                return MediaProvider.PrimePlaylists.getPlaylistAsin(uri);
            }
            if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(uri)) {
                return String.valueOf(MediaProvider.SharedUserPlaylists.getPlaylistKey(uri));
            }
            if (MediaProvider.UdoPlaylists.isUdoPlaylist(uri)) {
                return String.valueOf(MediaProvider.Playlists.getPlaylistLuid(uri));
            }
            if (MediaProvider.SmartPlaylists.isSmartPlaylist(uri)) {
                long playlistId = MediaProvider.SmartPlaylists.getPlaylistId(uri);
                return playlistId == 0 ? "RECENTLY_PURCHASED" : playlistId == 1 ? "RECENTLY_ADDED" : playlistId == 2 ? "RECENTLY_DOWNLOADED" : playlistId == 3 ? "RECENTLY_PLAYED_SONGS" : String.valueOf(playlistId);
            }
            if (MediaProvider.Albums.isAlbum(uri)) {
                return albumAsin;
            }
            if (MediaProvider.Artists.isArtist(uri)) {
                return artistAsin;
            }
            if (MediaProvider.Genres.isGenre(uri)) {
                return collectionName;
            }
            if (MediaProvider.Station.isStation(uri)) {
                return MediaProvider.Station.getKey(uri);
            }
            return null;
        }
        Log.error(TAG, "Null uri used to determine SelectionSourceId -- defaulting to null");
        return null;
    }

    public static SelectionSourceInfo getSelectionSourceInfo(final Uri uri, final MusicTrack track, final String collectionName) {
        Map<String, String> metricsContext;
        String str = null;
        if ((track instanceof StationTrack) && (metricsContext = ((StationTrack) track).getTrackItem().getMetricsContext()) != null) {
            str = metricsContext.get("selectionSourceSessionId");
        }
        return getSelectionSourceInfo(uri, track.getAlbumAsin(), track.getArtistAsin(), collectionName, str);
    }

    public static SelectionSourceInfo getSelectionSourceInfo(final Uri uri, final String albumAsin, final String artistAsin, final String collectionName) {
        return new SelectionSourceInfo(getSelectionSourceType(uri), getSelectionSourceId(uri, albumAsin, artistAsin, collectionName));
    }

    public static SelectionSourceInfo getSelectionSourceInfo(final Uri uri, final String albumAsin, final String artistAsin, final String collectionName, final String sessionId) {
        return new SelectionSourceInfo(getSelectionSourceType(uri), getSelectionSourceId(uri, albumAsin, artistAsin, collectionName), sessionId);
    }
}
