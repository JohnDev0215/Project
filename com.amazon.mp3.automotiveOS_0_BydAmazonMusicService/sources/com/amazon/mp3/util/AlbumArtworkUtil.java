package com.amazon.mp3.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.store.util.ImageCache;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;

/* JADX INFO: loaded from: classes4.dex */
public class AlbumArtworkUtil {
    private static final String TAG = "AlbumArtworkUtil";
    private final Context mContext;

    public AlbumArtworkUtil(final Context context) {
        this.mContext = context;
    }

    public Drawable getAlbumArtworkByAsin(final MediaItem mediaItem, final int artworkSize) {
        if (mediaItem == null) {
            Log.error(TAG, "Media item is null, cannot retrieve album artwork");
            return null;
        }
        MediaCollectionType mediaCollectionType = MediaCollectionType.ALBUM;
        MediaCollectionId.Type type = MediaCollectionId.Type.ASIN;
        MediaCollectionInfo mediaCollectionInfo = mediaItem.getMediaCollectionInfo(mediaCollectionType, type);
        if (mediaCollectionInfo == null) {
            Log.error(TAG, "album media collection info is null, unable to retrieve artwork");
            return null;
        }
        MediaCollectionId id = mediaCollectionInfo.getId(type);
        if (id == null) {
            Log.error(TAG, "album media collection id is null, unable to retrieve artwork");
            return null;
        }
        String id2 = id.getId();
        if (id2.isEmpty()) {
            Log.error(TAG, "Album asin is empty, unable to retrieve artwork");
            return null;
        }
        return new ImageCache(this.mContext, artworkSize, artworkSize, Bitmap.Config.RGB_565).cacheOnCurrentThread(new CTAPrimeCache(this.mContext).getArtworkUrlForAsin(id2));
    }

    public Drawable getAlbumArtworkForLibraryMediaItem(final MediaItem mediaItem, final int artworkSize) {
        MediaCollectionId id;
        long albumId = MediaItemHelper.getAlbumId(mediaItem);
        if (albumId == 3) {
            Log.error(TAG, "Error retrieving album id, unable to retrieve album artwork");
            return null;
        }
        if (albumId == -1 || mediaItem == null) {
            return null;
        }
        MediaItemId mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.LUID);
        if (mediaItemId != null) {
            if (TextUtils.isEmpty(mediaItemId.getId())) {
                CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(this.mContext);
                MediaCollectionType mediaCollectionType = MediaCollectionType.ALBUM;
                MediaCollectionId.Type type = MediaCollectionId.Type.ASIN;
                MediaCollectionInfo mediaCollectionInfo = mediaItem.getMediaCollectionInfo(mediaCollectionType, type);
                if (mediaCollectionInfo == null || (id = mediaCollectionInfo.getId(type)) == null) {
                    return null;
                }
                String id2 = id.getId();
                if (id2.isEmpty()) {
                    return null;
                }
                return new ImageCache(this.mContext, artworkSize, artworkSize, Bitmap.Config.RGB_565).cacheOnCurrentThread(cTAPrimeCache.getArtworkUrlForAsin(id2));
            }
            return loadArtworkFromCache(Long.toString(albumId), artworkSize);
        }
        return loadArtworkFromCache(Long.toString(albumId), artworkSize);
    }

    private Drawable loadArtworkFromCache(final String albumId, final int artworkSize) {
        if (artworkSize > 0) {
            return CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, artworkSize, albumId, true);
        }
        return null;
    }

    public Drawable getAlbumArtworkForPrimePlaylistMediaItem(final MediaItem mediaItem, final int artworkSize) {
        if (mediaItem == null) {
            return null;
        }
        MediaItemId.Type type = MediaItemId.Type.ASIN;
        if (mediaItem.getMediaItemId(type) == null) {
            return null;
        }
        String source = MediaItemHelper.getSource(mediaItem);
        if (mediaItem.getMediaItemId(type).getId() == null || artworkSize <= 0) {
            return null;
        }
        return CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK, source, artworkSize, Long.toString(r10.hashCode()), true);
    }
}
