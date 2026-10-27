package com.amazon.mp3.playback.harley;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.net.cirrus.AlbumArtworkUrlCache;
import com.amazon.mp3.net.cirrus.AlbumArtworkUrlRequest;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.msautils.MsaUrl;

/* JADX INFO: loaded from: classes3.dex */
public class GeneralImageUriProvider extends MediaItem.SimpleImageUriProvider {
    private static final String TAG = "GeneralImageUriProvider";
    private final Context context;

    public GeneralImageUriProvider(final Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.media.playback.MediaItem.SimpleImageUriProvider, com.amazon.music.media.playback.MediaItem.ImageUriProvider
    public Uri getImageUri(final MediaItem.ImageInfo info) {
        Uri imageUri = super.getImageUri(info);
        if (imageUri == null) {
            return null;
        }
        try {
            String url = MsaUrl.with(imageUri.toString()).clear().toUrl();
            Log.debug(TAG, "albumArtUrl: " + url);
            return Uri.parse(url);
        } catch (Exception e) {
            Log.debug(TAG, "Failed to retrieve album artwork URL via track information.", e);
            return imageUri;
        }
    }

    @Override // com.amazon.music.media.playback.MediaItem.SimpleImageUriProvider, com.amazon.music.media.playback.MediaItem.ImageUriProvider
    public Uri createImageUri(final MediaItem.ImageInfo info) {
        info.getMediaItem();
        Uri imageUri = getImageUri(info);
        String albumArtUrlFromPrimeCache = getAlbumArtUrlFromPrimeCache(info);
        if (albumArtUrlFromPrimeCache == null) {
            albumArtUrlFromPrimeCache = getAlbumArtUrlFromPrimeDatabase(info);
        }
        if (albumArtUrlFromPrimeCache == null && imageUri != null) {
            albumArtUrlFromPrimeCache = imageUri.toString();
        }
        if (albumArtUrlFromPrimeCache == null) {
            albumArtUrlFromPrimeCache = getAlbumArtUrlFromRequest(info);
        }
        Log.debug(TAG, "albumArtUrl: " + albumArtUrlFromPrimeCache);
        if (albumArtUrlFromPrimeCache == null) {
            return null;
        }
        return Uri.parse(albumArtUrlFromPrimeCache);
    }

    private String getAlbumArtUrlFromRequest(final MediaItem.ImageInfo info) {
        String str = TAG;
        Log.debug(str, "getAlbumArtUrlFromRequest() " + info.getMediaItem().getName());
        AlbumArtworkUrlCache cache = AlbumArtworkUrlCache.getCache(this.context);
        String mediaCollectionIdString = MediaCollectionInfo.getMediaCollectionIdString(info.getMediaItem().getMediaCollectionInfo(MediaCollectionType.ALBUM, null), MediaCollectionId.Type.ASIN, null);
        String mediaItemIdString = MediaItem.getMediaItemIdString(info.getMediaItem(), MediaItemId.Type.OWNED, null);
        Log.debug(str, "coid: " + mediaItemIdString + " albumAsin: " + mediaCollectionIdString);
        AlbumArtworkUrlRequest.AlbumMetadata albumMetadata = cache.get(mediaCollectionIdString);
        if (albumMetadata == null) {
            try {
                AlbumArtworkUrlRequest.Response responseRequestAlbumArtworkUrls = new AlbumArtworkUrlRequest().requestAlbumArtworkUrls(mediaItemIdString, mediaCollectionIdString, cache, AlbumArtworkUrlCache.AlbumArtworkUrlCacheType.LIGHT);
                if (!responseRequestAlbumArtworkUrls.getAlbumMetadataList().isEmpty()) {
                    albumMetadata = responseRequestAlbumArtworkUrls.getAlbumMetadataList().get(0);
                }
            } catch (Exception e) {
                Log.debug(TAG, "Failed to retrieve album artwork URL via request.", e);
            }
        }
        if (albumMetadata == null) {
            return null;
        }
        if (info.getWidth() >= 1400) {
            return albumMetadata.getAlbumUrlXL();
        }
        if (info.getWidth() >= 1000) {
            return albumMetadata.getAlbumUrlLarge();
        }
        return albumMetadata.getAlbumUrlMedium();
    }

    private String getAlbumArtUrlFromPrimeCache(final MediaItem.ImageInfo info) {
        Log.debug(TAG, "getAlbumArtUrlFromPrimeCache() " + info.getMediaItem().getName());
        try {
            CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(this.context);
            String mediaCollectionIdString = MediaCollectionInfo.getMediaCollectionIdString(info.getMediaItem().getMediaCollectionInfo(MediaCollectionType.ALBUM, null), MediaCollectionId.Type.ASIN, null);
            if (mediaCollectionIdString == null) {
                return null;
            }
            return cTAPrimeCache.getArtworkUrlForAsin(mediaCollectionIdString);
        } catch (Exception unused) {
            Log.debug(TAG, "Failed to retrieve album artwork URL via Prime cache.");
            return null;
        }
    }

    private String getAlbumArtUrlFromPrimeDatabase(final MediaItem.ImageInfo info) {
        String string;
        String largeAlbumArtUrl;
        String largeAlbumArtUrl2;
        Log.debug(TAG, "getAlbumArtUrlFromPrimeDatabase() " + info.getMediaItem().getName());
        try {
            if (MediaItem.getMediaItemIdString(info.getMediaItem(), MediaItemId.Type.ASIN, null) != null && (string = Long.toString(r1.hashCode())) != null) {
                PrimePlaylistDatabaseManager primePlaylistDatabaseManager = new PrimePlaylistDatabaseManager();
                CatalogPlaylistTrack trackByArtworkId = primePlaylistDatabaseManager.getTrackByArtworkId(string, 0);
                if (trackByArtworkId != null && (largeAlbumArtUrl2 = trackByArtworkId.getLargeAlbumArtUrl()) != null) {
                    return MsaUrl.with(largeAlbumArtUrl2).clear().scaleToWidth(info.getWidth()).toUrl();
                }
                CatalogPlaylistTrack trackByArtworkId2 = primePlaylistDatabaseManager.getTrackByArtworkId(string, 1);
                if (trackByArtworkId2 != null && (largeAlbumArtUrl = trackByArtworkId2.getLargeAlbumArtUrl()) != null) {
                    return MsaUrl.with(largeAlbumArtUrl).clear().scaleToWidth(info.getWidth()).toUrl();
                }
            }
        } catch (Exception unused) {
            Log.debug(TAG, "Failed to retrieve album artwork URL via Prime database.");
        }
        return null;
    }
}
