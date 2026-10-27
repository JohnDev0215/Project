package com.amazon.mp3.playback.harley;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.AllTracks;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.service.metrics.mts.SelectionSourceHelper;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.subscription.BrowseMode;

/* JADX INFO: loaded from: classes3.dex */
public class MediaCollectionHelper {
    public static final MediaCollectionInfo ALL_OFFLINE_TRACKS_MEDIA_COLLECTION_INFO;
    public static final MediaCollectionInfo ALL_ONLINE_TRACKS_MEDIA_COLLECTION_INFO;

    static {
        AllTracks allTracks = AllTracks.INSTANCE;
        String title = allTracks.getTitle();
        MediaCollectionType mediaCollectionType = MediaCollectionType.SONGS;
        ALL_ONLINE_TRACKS_MEDIA_COLLECTION_INFO = new MediaCollectionInfo(title, null, null, mediaCollectionType, MediaAccessInfo.fullAccess(true, true), new MediaCollectionId[0]);
        ALL_OFFLINE_TRACKS_MEDIA_COLLECTION_INFO = new MediaCollectionInfo(allTracks.getTitle(), null, null, mediaCollectionType, MediaAccessInfo.fullAccess(true, false), new MediaCollectionId[0]);
    }

    @Nullable
    private static MediaCollectionInfo getSmartPlaylistMediaCollectionInfo(final Uri uri) {
        int i;
        if (!MediaProvider.SmartPlaylists.isSmartPlaylist(uri)) {
            return null;
        }
        long playlistId = MediaProvider.SmartPlaylists.getPlaylistId(uri);
        if (0 == playlistId) {
            i = R.string.dmusic_library_smart_playlist_latest_purchases;
        } else if (1 == playlistId) {
            i = R.string.dmusic_library_smart_playlist_latest_uploads;
        } else if (2 == playlistId) {
            i = R.string.dmusic_library_smart_playlist_recently_added;
        } else {
            if (3 != playlistId) {
                return null;
            }
            i = R.string.dmusic_listening_history;
        }
        boolean zContains = uri.toString().contains("cirrus-local");
        String string = AmazonApplication.getContext().getString(i);
        return new MediaCollectionInfo(string, null, new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, SelectionSourceHelper.getSelectionSourceId(uri, "", "", string)), MediaCollectionType.AUTO_PLAYLIST, MediaAccessInfo.fullAccess(true, !zContains), new MediaCollectionId[0]);
    }

    public static MediaCollectionInfo getMediaCollectionInfo(final Uri uri) {
        MediaCollectionInfo smartPlaylistMediaCollectionInfo = getSmartPlaylistMediaCollectionInfo(uri);
        if (smartPlaylistMediaCollectionInfo != null) {
            return smartPlaylistMediaCollectionInfo;
        }
        MediaCollectionInfo mediaCollectionInfo = getMediaCollectionInfo(getAbstractItem(uri));
        if (mediaCollectionInfo == null) {
            return uri.toString().contains("cirrus-local") ? ALL_OFFLINE_TRACKS_MEDIA_COLLECTION_INFO : ALL_ONLINE_TRACKS_MEDIA_COLLECTION_INFO;
        }
        return mediaCollectionInfo;
    }

    public static MediaCollectionInfo getMediaCollectionInfo(final AbstractItem item) {
        if (item == null) {
            return null;
        }
        boolean zEquals = "cirrus-local".equals(item.getSource());
        if (item instanceof Album) {
            return getAlbumMediaCollectionInfo((Album) item, zEquals);
        }
        if (item instanceof Artist) {
            return getArtistMediaCollectionInfo((Artist) item, zEquals);
        }
        if (item instanceof Playlist) {
            return getPlaylistMediaCollectionInfo((Playlist) item, zEquals);
        }
        if (item instanceof Genre) {
            return getGenreMediaCollectionInfo((Genre) item, zEquals);
        }
        if (item instanceof MusicTrack) {
            return getTrackMediaCollectionInfo((MusicTrack) item, zEquals);
        }
        if (item instanceof CatalogPlaylist) {
            return getCatalogPlaylistMediaCollectionInfo((CatalogPlaylist) item, zEquals);
        }
        if (item instanceof AllTracks) {
            return zEquals ? ALL_OFFLINE_TRACKS_MEDIA_COLLECTION_INFO : ALL_ONLINE_TRACKS_MEDIA_COLLECTION_INFO;
        }
        return null;
    }

    public static MediaAccessInfo getMediaAccessInfo(final AbstractItem item, final boolean offline) {
        ContentCatalogStatus maxNonPreviousContentCatalogStatus = item.getMaxNonPreviousContentCatalogStatus();
        return MediaAccessInfo.forEligibility(item.isExplicit(), item.isCastable(), offline, !offline, !item.isNotOwned(), maxNonPreviousContentCatalogStatus.isPrime(), maxNonPreviousContentCatalogStatus.isSonicRush(), maxNonPreviousContentCatalogStatus.isHawkfire(), maxNonPreviousContentCatalogStatus.isBOP());
    }

    private static MediaCollectionInfo getMediaCollectionInfo(final AbstractItem item, final String title, final String subtitle, final MediaCollectionType type, final boolean offline) {
        MediaCollectionId mediaCollectionId;
        MediaCollectionId mediaCollectionId2;
        MediaCollectionId mediaCollectionId3 = null;
        if (StringUtils.isEmpty(item.getAsin())) {
            mediaCollectionId = null;
            mediaCollectionId2 = null;
        } else {
            mediaCollectionId = new MediaCollectionId(MediaCollectionId.Type.ASIN, item.getAsin());
            mediaCollectionId2 = new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, item.getAsin());
        }
        if (item.getContentUri() != null) {
            mediaCollectionId3 = new MediaCollectionId(MediaCollectionId.Type.LIBRARY_COLLECTION, item.getContentUri().toString());
            if (mediaCollectionId2 == null) {
                mediaCollectionId2 = new MediaCollectionId(MediaCollectionId.Type.SELECTION_SOURCE_ID, SelectionSourceHelper.getSelectionSourceId(item.getContentUri(), "", "", title));
            }
        }
        MediaCollectionInfo mediaCollectionInfo = new MediaCollectionInfo(title, subtitle, mediaCollectionId2, type, getMediaAccessInfo(item, offline), new MediaCollectionId[0]);
        if (mediaCollectionId != null) {
            mediaCollectionInfo.addId(mediaCollectionId);
        }
        if (mediaCollectionId3 != null) {
            mediaCollectionInfo.addId(mediaCollectionId3);
        }
        return mediaCollectionInfo;
    }

    private static MediaCollectionInfo getAlbumMediaCollectionInfo(final Album item, final boolean offline) {
        return getMediaCollectionInfo(item, item.getTitle(), item.getArtistName(), MediaCollectionType.ALBUM, offline);
    }

    private static MediaCollectionInfo getArtistMediaCollectionInfo(final Artist item, final boolean offline) {
        return getMediaCollectionInfo(item, item.getName(), null, MediaCollectionType.ARTIST, offline);
    }

    private static MediaCollectionInfo getPlaylistMediaCollectionInfo(final Playlist item, final boolean offline) {
        return getMediaCollectionInfo(item, item.getName(), null, getMediaCollectionTypeForPlaylist(item), offline);
    }

    private static MediaCollectionInfo getGenreMediaCollectionInfo(final Genre item, final boolean offline) {
        return getMediaCollectionInfo(item, item.getName(), null, MediaCollectionType.GENRE, offline);
    }

    private static MediaCollectionInfo getTrackMediaCollectionInfo(final MusicTrack item, final boolean offline) {
        return new MediaCollectionInfo(item.getAlbumName(), item.getArtistName(), null, MediaCollectionType.SONGS, getMediaAccessInfo(item, offline), new MediaCollectionId[0]);
    }

    private static MediaCollectionInfo getCatalogPlaylistMediaCollectionInfo(final CatalogPlaylist item, final boolean offline) {
        return getMediaCollectionInfo(item, item.getTitle(), null, getMediaCollectionTypeForCatalogPlaylist(item), offline);
    }

    private static MediaCollectionType getMediaCollectionTypeForPlaylist(final Playlist item) {
        String type = item.getType();
        type.hashCode();
        switch (type) {
            case "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist":
                return MediaCollectionType.AUTO_PLAYLIST;
            case "vnd.android.cursor.item/vnd.amazonmp3.shareduserplaylists":
                return MediaCollectionType.SHARED_PLAYLIST;
            case "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist":
                return MediaCollectionType.USER_PLAYLIST;
            case "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist":
                return MediaCollectionType.PRIME_PLAYLIST;
            default:
                return MediaCollectionType.UNLIMITED_PLAYLIST;
        }
    }

    private static MediaCollectionType getMediaCollectionTypeForCatalogPlaylist(final CatalogPlaylist item) {
        if (item.isSharedUserPlaylist()) {
            return MediaCollectionType.SHARED_PLAYLIST;
        }
        if (new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode() == BrowseMode.Mode.PRIME) {
            return MediaCollectionType.PRIME_PLAYLIST;
        }
        return MediaCollectionType.UNLIMITED_PLAYLIST;
    }

    private static AbstractItem getAbstractItem(final Uri uri) {
        int iMatch = DefaultUriMatcher.match(uri);
        if (iMatch != 1) {
            if (iMatch == 2) {
                return AmazonApplication.getLibraryItemFactory().getItem(uri);
            }
            if (iMatch != 5 && iMatch != 9 && iMatch != 26) {
                if (iMatch == 28 || iMatch == 33) {
                    return new PrimePlaylistDatabaseManager().getPlaylist(MediaProvider.Playlists.getCatalogPlaylistId(uri), PrimePlaylistsTable.translateSource(MediaProvider.getSource(uri)));
                }
                if (iMatch != 39 && iMatch != 15 && iMatch != 16) {
                    return null;
                }
            }
        }
        Uri collectionUri = CirrusMediaSource.getCollectionUri(uri);
        if (collectionUri != null) {
            return AmazonApplication.getLibraryItemFactory().getItem(collectionUri);
        }
        return null;
    }
}
