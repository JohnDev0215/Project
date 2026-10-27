package com.amazon.mp3.library.cache.image.loader;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Size;
import androidx.annotation.RequiresApi;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.data.impl.ContributorManagerImpl;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.local.LocalMediaSource;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.BitmapHttpClient;
import com.amazon.mp3.net.cirrus.AlbumArtworkUrlCache;
import com.amazon.mp3.net.cirrus.AlbumArtworkUrlRequest;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumImageLoader extends AbstractImageLoader implements ImageLoaderFactory.ImageLoader {
    public static final int ARTWORK_SIZE_LARGE = CacheManager.MAX_UNSCALED_SIZE;
    private static final String TAG = "AlbumImageLoader";

    AlbumImageLoader(ImageLoaderFactory.ItemType mType) {
        super(mType);
    }

    private Bitmap loadFromLocalStore(String id) {
        Context context = AmazonApplication.getContext();
        Bitmap albumThumbnail = null;
        if (!GrantPermissionActivity.areAllGranted(context, AbstractImageLoader.PERMISSIONS_FOR_LOCAL_ARTWORK)) {
            return null;
        }
        Profiler.begin("Loading from local store");
        Cursor cursorQuery = context.getContentResolver().query(ItemWrapper$ItemType.ALBUM.getUriForLcid(id), new String[]{"album_art_id"}, null, null, null);
        try {
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        long j = cursorQuery.getInt(0);
                        if (Build.VERSION.SDK_INT <= 28) {
                            cursorQuery = context.getContentResolver().query(Uri.parse("content://media/external/audio/albumart/" + j), null, null, null, null);
                            if (cursorQuery != null) {
                                try {
                                    if (cursorQuery.moveToFirst()) {
                                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("_data"));
                                        if (!TextUtils.isEmpty(string)) {
                                            albumThumbnail = loadAndScaleFromFile(string, ARTWORK_SIZE_LARGE);
                                        }
                                    }
                                } finally {
                                }
                            }
                            DbUtil.closeCursor(cursorQuery);
                        } else {
                            albumThumbnail = getAlbumThumbnail(j, context);
                        }
                        albumThumbnail = resample(albumThumbnail, ARTWORK_SIZE_LARGE);
                    }
                } catch (UnsupportedOperationException unused) {
                    Log.warning(TAG, "An UnSupportedOperationException was happened when loading bitmap for the album with id " + id + " in local store.");
                }
            }
            DbUtil.closeCursor(cursorQuery);
            Profiler.end();
            return albumThumbnail;
        } finally {
        }
    }

    @RequiresApi(29)
    private Bitmap getAlbumThumbnail(long albumId, Context context) {
        Uri uriWithAppendedId = ContentUris.withAppendedId(LocalMediaSource.BASE_ALBUM_URI, albumId);
        try {
            int i = ARTWORK_SIZE_LARGE;
            return context.getContentResolver().loadThumbnail(uriWithAppendedId, new Size(i, i), null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap loadFromCirrus(String id) {
        String albumUrlLarge;
        Bitmap bitmapBitmapFromUrl = null;
        if (AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            Profiler.begin("Loading image from Cirrus");
            AlbumArtworkUrlRequest.AlbumMetadata artworkMetadataForAlbum = getArtworkMetadataForAlbum(id);
            if (artworkMetadataForAlbum == null) {
                albumUrlLarge = null;
            } else if (ARTWORK_SIZE_LARGE == 256) {
                albumUrlLarge = artworkMetadataForAlbum.getAlbumUrlMedium();
            } else {
                albumUrlLarge = artworkMetadataForAlbum.getAlbumUrlLarge();
            }
            if (albumUrlLarge != null && albumUrlLarge.startsWith("http")) {
                bitmapBitmapFromUrl = BitmapHttpClient.bitmapFromUrl(albumUrlLarge);
            }
            Profiler.end();
        } else {
            Log.verbose(TAG, "Could not load album artwork from Cirrus. Cloud is not supported.");
        }
        return bitmapBitmapFromUrl;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.graphics.Bitmap loadFromCTA(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = "Loading image from CTA"
            com.amazon.mp3.util.Profiler.begin(r0)
            android.content.Context r0 = com.amazon.mp3.AmazonApplication.getContext()
            java.lang.String r1 = "album_asin"
            java.lang.String[] r4 = new java.lang.String[]{r1}
            r2 = 2
            java.lang.String[] r6 = new java.lang.String[r2]
            r2 = 0
            java.lang.String r3 = java.lang.String.valueOf(r2)
            r6[r2] = r3
            r2 = 1
            r6[r2] = r11
            android.database.sqlite.SQLiteDatabase r2 = com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase.getWritableDatabase(r0)
            java.lang.String r3 = "Track"
            java.lang.String r5 = "source=? AND album_id=?"
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9)
            if (r11 == 0) goto L46
            boolean r2 = r11.moveToFirst()     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L46
            int r1 = r11.getColumnIndexOrThrow(r1)     // Catch: java.lang.Throwable -> L41
            java.lang.String r1 = r11.getString(r1)     // Catch: java.lang.Throwable -> L41
            android.graphics.Bitmap r0 = r10.loadAlbumArtworkFromCTAByAsin(r0, r1)     // Catch: java.lang.Throwable -> L41
            goto L47
        L41:
            r0 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r11)
            throw r0
        L46:
            r0 = 0
        L47:
            com.amazon.mp3.util.DbUtil.closeCursor(r11)
            com.amazon.mp3.util.Profiler.end()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.cache.image.loader.AlbumImageLoader.loadFromCTA(java.lang.String):android.graphics.Bitmap");
    }

    private Bitmap loadXLargeFromCirrus(String id) {
        Bitmap bitmapBitmapFromUrl = null;
        if (AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            Profiler.begin("Loading XL image from cirrus");
            AlbumArtworkUrlRequest.AlbumMetadata artworkMetadataForAlbum = getArtworkMetadataForAlbum(id);
            String albumUrlXL = artworkMetadataForAlbum != null ? artworkMetadataForAlbum.getAlbumUrlXL() : null;
            if (albumUrlXL != null && albumUrlXL.startsWith("http")) {
                bitmapBitmapFromUrl = BitmapHttpClient.bitmapFromUrl(albumUrlXL);
            }
            Profiler.end();
        } else {
            Log.verbose(TAG, "Could not load album artwork from Cirrus. Cloud is not supported.");
        }
        return bitmapBitmapFromUrl;
    }

    private AlbumArtworkUrlRequest.AlbumMetadata getArtworkMetadataForAlbum(String id) {
        AlbumArtworkUrlCache cache = AlbumArtworkUrlCache.getCache(AmazonApplication.getContext());
        AlbumArtworkUrlRequest.AlbumMetadata albumMetadata = cache.get(id);
        if (albumMetadata != null) {
            return albumMetadata;
        }
        String luidForAlbum = getLuidForAlbum(id);
        if (TextUtils.isEmpty(luidForAlbum) || IdGenerator.isPrimeLuid(luidForAlbum) || PrimePlaylistUtil.isAsin(luidForAlbum)) {
            return albumMetadata;
        }
        Log.verbose(TAG, "Artwork urls not cached, creating new request");
        try {
            AlbumArtworkUrlRequest.Response responseRequestAlbumArtworkUrls = new AlbumArtworkUrlRequest().requestAlbumArtworkUrls(luidForAlbum, id, cache, AlbumArtworkUrlCache.AlbumArtworkUrlCacheType.LIGHT);
            return !responseRequestAlbumArtworkUrls.getAlbumMetadataList().isEmpty() ? responseRequestAlbumArtworkUrls.getAlbumMetadataList().get(0) : albumMetadata;
        } catch (AbstractHttpClient.HttpClientException e) {
            e = e;
            Log.error(TAG, "Failed to request album artwork url for album ID " + id, e);
            return albumMetadata;
        } catch (CirrusExceptions.CirrusException e2) {
            Log.error(TAG, "Failed to request album artwork url for album ID " + id + " because " + e2.getMessage() + ", " + e2.getAwsErrorMessage(), e2);
            return albumMetadata;
        } catch (ServiceException e3) {
            e = e3;
            Log.error(TAG, "Failed to request album artwork url for album ID " + id, e);
            return albumMetadata;
        } catch (JSONException e4) {
            e = e4;
            Log.error(TAG, "Failed to request album artwork url for album ID " + id, e);
            return albumMetadata;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getLuidForAlbum(java.lang.String r11) {
        /*
            r10 = this;
            android.content.Context r0 = com.amazon.mp3.AmazonApplication.getContext()
            java.lang.String r1 = "luid"
            java.lang.String[] r4 = new java.lang.String[]{r1}
            r2 = 2
            java.lang.String[] r6 = new java.lang.String[r2]
            r2 = 0
            java.lang.String r3 = java.lang.String.valueOf(r2)
            r6[r2] = r3
            r2 = 1
            r6[r2] = r11
            android.database.sqlite.SQLiteDatabase r2 = com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase.getWritableDatabase(r0)
            java.lang.String r3 = "Track"
            java.lang.String r5 = "source=? AND album_id=? AND luid NOT NULL"
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9)
            if (r11 == 0) goto L3d
            boolean r0 = r11.moveToFirst()     // Catch: java.lang.Throwable -> L38
            if (r0 == 0) goto L3d
            int r0 = r11.getColumnIndexOrThrow(r1)     // Catch: java.lang.Throwable -> L38
            java.lang.String r0 = r11.getString(r0)     // Catch: java.lang.Throwable -> L38
            goto L3e
        L38:
            r0 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r11)
            throw r0
        L3d:
            r0 = 0
        L3e:
            com.amazon.mp3.util.DbUtil.closeCursor(r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.cache.image.loader.AlbumImageLoader.getLuidForAlbum(java.lang.String):java.lang.String");
    }

    private void scheduleArtistAndContributorImageGeneration(Uri albumUri, int size) {
        List<ContributorAccessObject.Contributor> contributors = ContributorManagerImpl.INSTANCE.getContributors(albumUri.toString());
        HashSet hashSet = new HashSet();
        for (ContributorAccessObject.Contributor contributor : contributors) {
            hashSet.addAll(ContributorManagerImpl.INSTANCE.getArtistIdsForContributor(contributor.getAsin()));
            getCacheManager().prefetch(ImageLoaderFactory.ItemType.CONTRIBUTOR, size, contributor.getAsin(), false);
        }
        Album album = AmazonApplication.getLibraryItemFactory().getAlbum(albumUri);
        if (album != null) {
            hashSet.add(Long.toString(album.getArtistId()));
            Iterator<MusicTrack> it = album.getTracks().iterator();
            while (it.hasNext()) {
                hashSet.add(Long.toString(it.next().getArtistId()));
            }
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                getCacheManager().prefetch(ImageLoaderFactory.ItemType.ARTIST, size, (String) it2.next(), false);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00c6  */
    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.mp3.task.JobContext<com.amazon.mp3.library.cache.image.ImageMetadata> getImage(com.amazon.mp3.task.JobContext<com.amazon.mp3.library.cache.image.ImageMetadata> r9) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.cache.image.loader.AlbumImageLoader.getImage(com.amazon.mp3.task.JobContext):com.amazon.mp3.task.JobContext");
    }

    private Bitmap getXLLargeImage(ImageMetadata metadata) {
        Bitmap bitmapLoadXLargeFromCirrus;
        String str = TAG;
        Log.debug(str, "Getting XL image: %s", metadata.getId());
        String cacheFileName = getCacheFileName(metadata.getType(), null, 1000, metadata.getId());
        if (cacheFileName != null && new File(cacheFileName).exists()) {
            bitmapLoadXLargeFromCirrus = loadAndScaleFromFile(cacheFileName, 1000);
            Log.debug(str, "loaded from disk: %s", metadata.getId());
        } else {
            bitmapLoadXLargeFromCirrus = loadXLargeFromCirrus(metadata.getId());
            Log.debug(str, "loaded from Cirrus: %s", metadata.getId());
            if (bitmapLoadXLargeFromCirrus != null) {
                bitmapLoadXLargeFromCirrus = resample(bitmapLoadXLargeFromCirrus, 1000);
                saveToFile(bitmapLoadXLargeFromCirrus, metadata.getType(), metadata.getId());
                Log.debug(str, "Saved XL image: %s", metadata.getId());
            }
        }
        if (bitmapLoadXLargeFromCirrus == null) {
            return bitmapLoadXLargeFromCirrus;
        }
        Bitmap bitmapResample = resample(bitmapLoadXLargeFromCirrus, metadata.getSize());
        metadata.setUri(saveToFile(bitmapResample, null, metadata.getType(), metadata.getId()));
        Log.debug(str, "Resized and Saved from XL to required size: %s", metadata.getId());
        return bitmapResample;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public int getMaxImageSize() {
        return ARTWORK_SIZE_LARGE;
    }
}
