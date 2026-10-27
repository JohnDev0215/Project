package com.amazon.mp3.library.cache.image.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.AbstractImageLoader;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.task.JobContext;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistImageLoader extends AbstractImageLoader implements ImageLoaderFactory.ImageLoader {
    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public int getMaxImageSize() {
        return 0;
    }

    public PrimePlaylistImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> jobContext) throws Throwable {
        ImageMetadata imageMetadata = (ImageMetadata) jobContext.getMetadata();
        if (imageMetadata == null) {
            return jobContext;
        }
        Bitmap imageForMetadata = getImageForMetadata(imageMetadata);
        if (imageForMetadata == null) {
            ImageLoaderFactory.getInstance().getDefaultImageLoader(this.mItemType).getImage(jobContext, true);
        } else {
            imageMetadata.setImage(new BitmapDrawable(AmazonApplication.getContext().getResources(), imageForMetadata));
        }
        jobContext.setStatus(JobContext.Status.FINISHED);
        return jobContext;
    }

    protected Bitmap getImageForMetadata(final ImageMetadata metadata) throws Throwable {
        String source = metadata.getSource();
        ImageLoaderFactory.ItemType type = metadata.getType();
        int size = metadata.getSize();
        String id = metadata.getId();
        if (id != null && source != null && !source.isEmpty() && type != null) {
            Bitmap localExactSizeImage = getLocalExactSizeImage(source, type, size, id);
            if (localExactSizeImage != null) {
                metadata.addFlags(2);
                return localExactSizeImage;
            }
            Bitmap bitmapCreateLocalImageByResizing = createLocalImageByResizing(source, type, size, id, false);
            if (bitmapCreateLocalImageByResizing != null) {
                metadata.addFlags(2);
                saveToFile(bitmapCreateLocalImageByResizing, source, type, id);
                return bitmapCreateLocalImageByResizing;
            }
            Bitmap remoteImage = getRemoteImage(source, type, size, id);
            if (remoteImage != null) {
                saveToFile(remoteImage, source, type, id);
                return loadAndScaleFromFile(getCacheFileName(type, source, size, id), type, size);
            }
            Bitmap bitmapCreateLocalImageByResizing2 = createLocalImageByResizing(source, type, size, id, true);
            if (bitmapCreateLocalImageByResizing2 != null) {
                metadata.addFlags(2);
                saveToFile(bitmapCreateLocalImageByResizing2, source, type, id);
                return bitmapCreateLocalImageByResizing2;
            }
        }
        return null;
    }

    private Bitmap getLocalExactSizeImage(final String source, final ImageLoaderFactory.ItemType type, final int size, final String imageId) {
        String cacheFileName = getCacheFileName(type, source, size, imageId);
        if (cacheFileName == null || !new File(cacheFileName).exists()) {
            return null;
        }
        return loadAndScaleFromFile(cacheFileName, type, size);
    }

    private Bitmap createLocalImageByResizing(final String source, final ImageLoaderFactory.ItemType type, final int newSize, final String imageId, final boolean forceUpsize) {
        int downloadedImageSizeByType = CacheManager.getDownloadedImageSizeByType(type);
        if ((downloadedImageSizeByType <= newSize && !forceUpsize) || downloadedImageSizeByType == 0) {
            return null;
        }
        String cacheFileName = getCacheFileName(type, source, downloadedImageSizeByType, imageId);
        if ((cacheFileName == null || !new File(cacheFileName).exists()) && "cirrus".equals(source)) {
            cacheFileName = getCacheFileName(type, "cirrus-local", downloadedImageSizeByType, imageId);
        }
        if (cacheFileName == null || !new File(cacheFileName).exists()) {
            return null;
        }
        return loadAndScaleFromFile(cacheFileName, type, newSize);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected android.graphics.Bitmap getRemoteImage(java.lang.String r5, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ItemType r6, int r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            r4 = this;
            java.lang.String r0 = "cirrus"
            boolean r5 = r0.equals(r5)
            r0 = 1
            r5 = r5 ^ r0
            com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager r1 = new com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager
            r1.<init>()
            int[] r2 = com.amazon.mp3.library.cache.image.loader.PrimePlaylistImageLoader.AnonymousClass1.$SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType
            int r3 = r6.ordinal()
            r2 = r2[r3]
            r3 = 0
            if (r2 == r0) goto L42
            r0 = 2
            if (r2 == r0) goto L42
            r6 = 3
            if (r2 == r6) goto L37
            r5 = 4
            if (r2 == r5) goto L2e
            r5 = 5
            if (r2 == r5) goto L25
            goto L5a
        L25:
            android.content.Context r5 = com.amazon.mp3.AmazonApplication.getContext()
            java.lang.String r5 = com.amazon.mp3.library.util.PlaylistUtil.getPlaylistBannerArtUrl(r5, r8)
            goto L5b
        L2e:
            android.content.Context r5 = com.amazon.mp3.AmazonApplication.getContext()
            java.lang.String r5 = com.amazon.mp3.library.util.PlaylistUtil.getPlaylistThumbnailArtUrl(r5, r8)
            goto L5b
        L37:
            com.amazon.mp3.playlist.CatalogPlaylistTrack r5 = r1.getTrackByArtworkId(r8, r5)
            if (r5 == 0) goto L5a
            java.lang.String r5 = r5.getLargeAlbumArtUrl()
            goto L5b
        L42:
            com.amazon.mp3.playlist.CatalogPlaylist r5 = r1.getPlaylist(r8, r5)
            if (r5 == 0) goto L5a
            com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory$ItemType r8 = com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ItemType.PRIME_PLAYLIST_BANNER
            if (r8 != r6) goto L51
            java.lang.String r5 = r5.getBannerArtUrl()
            goto L5b
        L51:
            com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory$ItemType r8 = com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL
            if (r8 != r6) goto L5a
            java.lang.String r5 = r5.getThumbnailArtUrl()
            goto L5b
        L5a:
            r5 = r3
        L5b:
            if (r5 == 0) goto L72
            com.amazon.music.msautils.MsaUrl r5 = com.amazon.music.msautils.MsaUrl.with(r5)
            com.amazon.music.msautils.MsaUrl r5 = r5.scaleToLongest(r7)
            java.lang.String r5 = r5.toUrl()
            android.graphics.Bitmap r5 = com.amazon.mp3.net.BitmapHttpClient.bitmapFromUrl(r5)
            android.graphics.Bitmap r5 = r4.resample(r5, r7)
            return r5
        L72:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.cache.image.loader.PrimePlaylistImageLoader.getRemoteImage(java.lang.String, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory$ItemType, int, java.lang.String):android.graphics.Bitmap");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.cache.image.loader.PrimePlaylistImageLoader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType;

        static {
            int[] iArr = new int[ImageLoaderFactory.ItemType.values().length];
            $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType = iArr;
            try {
                iArr[ImageLoaderFactory.ItemType.PRIME_PLAYLIST_BANNER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ImageLoaderFactory.ItemType.PLAYLIST_UDO_THUMBNAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ImageLoaderFactory.ItemType.PLAYLIST_UDO_BANNER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    protected Bitmap loadAndScaleFromFile(String filename, final ImageLoaderFactory.ItemType type, final int size) {
        AbstractImageLoader.ScaledImageLoader scaledImageLoader = new AbstractImageLoader.ScaledImageLoader(filename);
        if (ImageLoaderFactory.ItemType.PRIME_PLAYLIST_BANNER == type || ImageLoaderFactory.ItemType.PLAYLIST_UDO_BANNER == type) {
            return scaledImageLoader.loadAndScale(size, Math.round((size * 8) / 16.0f), true);
        }
        if (ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL == type || ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK == type || ImageLoaderFactory.ItemType.PLAYLIST_UDO_THUMBNAIL == type) {
            return scaledImageLoader.loadAndScale(size, true);
        }
        return null;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.AbstractImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public String getCacheFileName(final ImageLoaderFactory.ItemType type, final String source, final int size, final String id) {
        return super.getCacheFileName(type, source, size, id);
    }

    @Override // com.amazon.mp3.library.cache.image.loader.AbstractImageLoader
    public String saveToFile(final Bitmap image, final String source, final ImageLoaderFactory.ItemType type, final String imageId) {
        return super.saveToFile(image, source, type, imageId);
    }
}
