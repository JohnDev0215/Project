package com.amazon.mp3.library.cache.image.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.AbstractImageLoader;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.data.ContributorManager;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.data.impl.ContributorManagerImpl;
import com.amazon.mp3.library.item.ItemWrapper$IdType;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.task.JobContext;
import com.amazon.music.find.featuregating.FindFeatureGating;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ArtistImageLoader extends ContributorImageLoader {
    private static final int ARTWORK_SIZE_LARGE = CacheManager.MAX_UNSCALED_SIZE;
    private static final String TAG = "ArtistImageLoader";
    private ContributorManager mService;

    ArtistImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
        this.mService = ContributorManagerImpl.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0028 A[Catch: all -> 0x008e, TryCatch #1 {all -> 0x008e, blocks: (B:5:0x0022, B:9:0x0048, B:11:0x004e, B:13:0x0055, B:15:0x0067, B:16:0x0070, B:7:0x0028), top: B:33:0x0022 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getFullSizedAlbumImageForArtist(java.lang.String r11, com.amazon.mp3.library.cache.image.ImageMetadata r12) {
        /*
            r10 = this;
            java.lang.String r12 = "date_created"
            java.lang.String r0 = "_id"
            r1 = 0
            r2 = 0
            com.amazon.mp3.library.provider.MediaProvider r3 = com.amazon.mp3.library.provider.MediaProvider.getInstance()     // Catch: java.lang.NullPointerException -> L93
            java.lang.String r4 = "cirrus"
            long r5 = java.lang.Long.parseLong(r11)     // Catch: java.lang.NullPointerException -> L93
            android.net.Uri r4 = com.amazon.mp3.library.provider.MediaProvider.Artists.Albums.getContentUri(r4, r5)     // Catch: java.lang.NullPointerException -> L93
            java.lang.String[] r5 = new java.lang.String[]{r0, r12}     // Catch: java.lang.NullPointerException -> L93
            r6 = 0
            r7 = 0
            java.lang.String r8 = "date_created"
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.NullPointerException -> L93
            if (r3 == 0) goto L28
            boolean r4 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L8e
            if (r4 != 0) goto L46
        L28:
            com.amazon.mp3.util.DbUtil.closeCursor(r3)     // Catch: java.lang.Throwable -> L8e
            com.amazon.mp3.library.provider.MediaProvider r4 = com.amazon.mp3.library.provider.MediaProvider.getInstance()     // Catch: java.lang.Throwable -> L8e
            java.lang.String r5 = "cirrus-local"
            long r6 = java.lang.Long.parseLong(r11)     // Catch: java.lang.Throwable -> L8e
            android.net.Uri r5 = com.amazon.mp3.library.provider.MediaProvider.Artists.Albums.getContentUri(r5, r6)     // Catch: java.lang.Throwable -> L8e
            java.lang.String[] r6 = new java.lang.String[]{r0, r12}     // Catch: java.lang.Throwable -> L8e
            r7 = 0
            r8 = 0
            java.lang.String r9 = "date_created"
            android.database.Cursor r12 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L8e
            r3 = r12
        L46:
            if (r3 == 0) goto L77
            boolean r12 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L8e
            if (r12 == 0) goto L77
            int r12 = r3.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L8e
            r0 = -1
            if (r12 <= r0) goto L77
            java.lang.String r12 = r3.getString(r12)     // Catch: java.lang.Throwable -> L8e
            com.amazon.mp3.library.cache.image.CacheManager r0 = r10.getCacheManager()     // Catch: java.lang.Throwable -> L8e
            com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory$ItemType r4 = com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ItemType.ALBUM     // Catch: java.lang.Throwable -> L8e
            int r5 = com.amazon.mp3.library.cache.image.loader.AlbumImageLoader.ARTWORK_SIZE_LARGE     // Catch: java.lang.Throwable -> L8e
            boolean r0 = r0.hasExactOnDisk(r4, r5, r12)     // Catch: java.lang.Throwable -> L8e
            if (r0 == 0) goto L70
            com.amazon.mp3.library.cache.image.CacheManager r0 = r10.getCacheManager()     // Catch: java.lang.Throwable -> L8e
            java.lang.String r12 = r0.getCacheFileName(r4, r2, r5, r12)     // Catch: java.lang.Throwable -> L8e
            goto L78
        L70:
            com.amazon.mp3.library.cache.image.CacheManager r0 = r10.getCacheManager()     // Catch: java.lang.Throwable -> L8e
            r0.prefetch(r4, r5, r12, r1)     // Catch: java.lang.Throwable -> L8e
        L77:
            r12 = r2
        L78:
            com.amazon.mp3.util.DbUtil.closeCursor(r3)
            if (r12 == 0) goto L8c
            int r0 = com.amazon.mp3.library.cache.image.loader.ArtistImageLoader.ARTWORK_SIZE_LARGE
            android.graphics.Bitmap r12 = r10.loadAndScaleFromFile(r12, r0)
            if (r12 == 0) goto L8d
            com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory$ItemType r0 = com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ItemType.ARTIST
            java.lang.String r2 = r10.saveToFile(r12, r0, r11)
            goto L8d
        L8c:
            r2 = r12
        L8d:
            return r2
        L8e:
            r11 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r3)
            throw r11
        L93:
            r11 = move-exception
            java.lang.String r12 = com.amazon.mp3.library.cache.image.loader.ArtistImageLoader.TAG
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r11 = r11.toString()
            r0[r1] = r11
            java.lang.String r11 = "Caught NPE in getFullSizedAlbumImageForArtist: %s"
            com.amazon.mp3.util.Log.warning(r12, r11, r0)
            java.lang.String r11 = "Recovering..."
            com.amazon.mp3.util.Log.warning(r12, r11)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.cache.image.loader.ArtistImageLoader.getFullSizedAlbumImageForArtist(java.lang.String, com.amazon.mp3.library.cache.image.ImageMetadata):java.lang.String");
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ContributorImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> imageLoadContext) {
        ContributorAccessObject.Contributor contributor;
        Bitmap bitmapLoadAndScale;
        ImageMetadata imageMetadata = (ImageMetadata) imageLoadContext.getMetadata();
        int size = imageMetadata.getSize();
        String id = imageMetadata.getId();
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.ARTIST;
        String cacheFileName = getCacheFileName(itemType, imageMetadata.getSource(), size, id);
        if (cacheFileName != null && new File(cacheFileName).exists()) {
            bitmapLoadAndScale = loadAndScaleFromFile(cacheFileName, size);
            imageMetadata.addFlags(2);
            imageMetadata.setUri(cacheFileName);
        } else {
            List<ContributorAccessObject.Contributor> contributors = this.mService.getContributors(ItemWrapper$IdType.MERGED_ID.getUri(ItemWrapper$ItemType.ARTIST, id).toString());
            if (contributors.isEmpty()) {
                contributor = null;
            } else {
                contributor = contributors.get(0);
                imageMetadata.setExtraData(contributor.getAsin());
            }
            if (contributor == null || contributor.getImageURL() == null) {
                bitmapLoadAndScale = null;
            } else {
                ImageLoaderFactory.ItemType itemType2 = ImageLoaderFactory.ItemType.CONTRIBUTOR;
                String cacheFileName2 = getCacheFileName(itemType2, imageMetadata.getSource(), size, contributor.getAsin());
                if (new File(cacheFileName2).exists()) {
                    bitmapLoadAndScale = loadAndScaleFromFile(cacheFileName2, size);
                    imageMetadata.addFlags(2);
                    imageMetadata.setUri(cacheFileName2);
                } else {
                    String strDownloadUnscaledImage = downloadUnscaledImage(contributor, imageMetadata);
                    getCacheManager().clearFromDiskCache(itemType, imageMetadata.getSource(), id, true);
                    bitmapLoadAndScale = new AbstractImageLoader.ScaledImageLoader(strDownloadUnscaledImage).loadAndScale(size, true);
                    saveToFile(bitmapLoadAndScale, itemType2, contributor.getAsin());
                    imageMetadata.setUri(saveToFile(bitmapLoadAndScale, itemType, id));
                }
            }
            if (bitmapLoadAndScale == null) {
                String cacheFileName3 = getCacheFileName(itemType, imageMetadata.getSource(), ARTWORK_SIZE_LARGE, id);
                if (cacheFileName3 != null && !new File(cacheFileName3).exists()) {
                    cacheFileName3 = getFullSizedAlbumImageForArtist(id, imageMetadata);
                }
                if (cacheFileName3 != null) {
                    bitmapLoadAndScale = new AbstractImageLoader.ScaledImageLoader(cacheFileName3).loadAndScale(imageMetadata.getSize(), true);
                    imageMetadata.setUri(saveToFile(bitmapLoadAndScale, itemType, id));
                }
            }
        }
        if (bitmapLoadAndScale == null) {
            ImageLoaderFactory.getInstance().getDefaultImageLoader(this.mItemType).getImage(imageLoadContext, true);
            imageMetadata.setImage(new BitmapDrawable(AmazonApplication.getContext().getResources(), ((BitmapDrawable) imageMetadata.getImage()).getBitmap()));
            imageMetadata.setUri(null);
        } else {
            imageMetadata.setImage(new BitmapDrawable(AmazonApplication.getContext().getResources(), bitmapLoadAndScale));
        }
        imageLoadContext.setStatus(JobContext.Status.FINISHED);
        return imageLoadContext;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ContributorImageLoader, com.amazon.mp3.library.cache.image.loader.AbstractImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public String getCacheFileName(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        String cacheFileName;
        if (id == null || type == null) {
            return null;
        }
        ImageLoaderFactory.ItemType itemType = ImageLoaderFactory.ItemType.CONTRIBUTOR;
        if (type == itemType) {
            return super.getCacheFileName(type, source, size, id);
        }
        List<ContributorAccessObject.Contributor> contributors = this.mService.getContributors(ItemWrapper$IdType.MERGED_ID.getUri(ItemWrapper$ItemType.ARTIST, id).toString());
        boolean z = (contributors == null || contributors.isEmpty() || contributors.get(0).getImageURL() == null) ? false : true;
        if (z) {
            ContributorAccessObject.Contributor contributor = contributors.get(0);
            String cacheFileName2 = super.getCacheFileName(itemType, source, size, contributor.getAsin());
            cacheFileName = (cacheFileName2 == null || !new File(cacheFileName2).exists()) ? super.getCacheFileName(itemType, null, size, contributor.getAsin()) : cacheFileName2;
        } else {
            cacheFileName = null;
        }
        if (FindFeatureGating.ARTIST_IMAGE_CACHING_IMPROVEMENT.isEnabled()) {
            if (z) {
                return cacheFileName;
            }
            String cacheFileName3 = super.getCacheFileName(this.mItemType, source, size, id);
            return (cacheFileName3 == null || !new File(cacheFileName3).exists()) ? super.getCacheFileName(this.mItemType, null, size, id) : cacheFileName3;
        }
        if (cacheFileName == null || !new File(cacheFileName).exists()) {
            cacheFileName = super.getCacheFileName(this.mItemType, source, size, id);
        }
        return (cacheFileName == null || !new File(cacheFileName).exists()) ? super.getCacheFileName(this.mItemType, null, size, id) : cacheFileName;
    }
}
