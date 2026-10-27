package com.amazon.mp3.library.cache.image.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.task.JobContext;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultGenericImageLoader extends AbstractDefaultImageLoader {
    private static final String TAG = "DefaultGenericImageLoader";
    static HashMap<String, String> sCachePathCache;
    private static int[] sOtherSupportedSizes;
    private static int[] sPreCachedImageSizes;
    static HashMap<Integer, HashMap<Integer, Bitmap>> sSharedImageStores;
    private static HashSet<Integer> sSizesToCacheInMemory;
    static HashMap<Integer, HashMap<Integer, SoftReference<Bitmap>>> sUnsupportedImageStores;
    private int mCachedMaxSize;
    HashMap<Integer, Bitmap> mDefaultImageStore;
    HashMap<Integer, SoftReference<Bitmap>> mUnsupportedImageStore;

    protected int getDefaultImageId() {
        return R.drawable.placeholder_sm;
    }

    static {
        int i = CacheManager.MAX_UNSCALED_SIZE;
        int i2 = CacheManager.ALBUM_ART_THUMBNAIL_SIZE;
        sOtherSupportedSizes = new int[]{i, i2, AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_gridview_art_size), AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_listview_art_size), AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.now_playing_album_art_size)};
        sPreCachedImageSizes = new int[]{AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_gridview_art_size), AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_listview_art_size)};
        HashSet<Integer> hashSet = new HashSet<>();
        sSizesToCacheInMemory = hashSet;
        hashSet.add(Integer.valueOf(i));
        sSizesToCacheInMemory.add(Integer.valueOf(i2));
        sSizesToCacheInMemory.add(Integer.valueOf(AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_gridview_art_size)));
        sSizesToCacheInMemory.add(Integer.valueOf(AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_listview_art_size)));
        sSizesToCacheInMemory.add(Integer.valueOf(AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.now_playing_album_art_size)));
        sSharedImageStores = new HashMap<>();
        sUnsupportedImageStores = new HashMap<>();
        sCachePathCache = new HashMap<>();
    }

    DefaultGenericImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
        this.mCachedMaxSize = -1;
        this.mDefaultImageStore = null;
        this.mUnsupportedImageStore = null;
        HashMap<Integer, Bitmap> map = sSharedImageStores.get(Integer.valueOf(getDefaultImageId()));
        this.mDefaultImageStore = map;
        if (map == null) {
            this.mDefaultImageStore = new HashMap<>(sOtherSupportedSizes.length + 1);
            sSharedImageStores.put(Integer.valueOf(getDefaultImageId()), this.mDefaultImageStore);
        }
        HashMap<Integer, SoftReference<Bitmap>> map2 = sUnsupportedImageStores.get(Integer.valueOf(getDefaultImageId()));
        this.mUnsupportedImageStore = map2;
        if (map2 == null) {
            this.mUnsupportedImageStore = new HashMap<>();
            sUnsupportedImageStores.put(Integer.valueOf(getDefaultImageId()), this.mUnsupportedImageStore);
        }
        generateImages();
    }

    protected Bitmap getDefaultBitmap() {
        return ((BitmapDrawable) AmazonApplication.getContext().getResources().getDrawable(getDefaultImageId())).getBitmap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getScaledDefaultImage(int size) {
        int maxSupportedSize = getMaxSupportedSize(size);
        SoftReference<Bitmap> softReference = this.mUnsupportedImageStore.get(Integer.valueOf(maxSupportedSize));
        Bitmap bitmapResample = softReference == null ? null : softReference.get();
        if (bitmapResample == null) {
            bitmapResample = resample(Bitmap.createScaledBitmap(getDefaultBitmap(), maxSupportedSize, maxSupportedSize, true), maxSupportedSize);
            if (sSizesToCacheInMemory.contains(Integer.valueOf(maxSupportedSize)) && !this.mDefaultImageStore.containsKey(Integer.valueOf(maxSupportedSize))) {
                this.mDefaultImageStore.put(Integer.valueOf(maxSupportedSize), bitmapResample);
            }
            bitmapResample.setDensity(0);
            this.mUnsupportedImageStore.put(Integer.valueOf(maxSupportedSize), new SoftReference<>(bitmapResample));
        }
        return bitmapResample;
    }

    private int getMaxSupportedSize(final int originalSize) {
        int i;
        if (PlatformUtil.isVersionOrGreater(23) || originalSize <= (i = CacheManager.MAX_UNSCALED_SIZE)) {
            i = originalSize;
        }
        Log.debug(TAG, "Original Size: " + originalSize + " Scaled Size: " + i);
        return i;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.DefaultImageLoader
    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> taskContext, boolean forceLoad) {
        ImageMetadata imageMetadata = (ImageMetadata) taskContext.getMetadata();
        Bitmap scaledDefaultImage = this.mDefaultImageStore.get(Integer.valueOf(imageMetadata.getSize()));
        if (scaledDefaultImage == null) {
            scaledDefaultImage = getScaledDefaultImage(imageMetadata.getSize());
        }
        imageMetadata.setImage(new BitmapDrawable(AmazonApplication.getContext().getResources(), scaledDefaultImage));
        return taskContext;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.AbstractImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public String getCacheFileName(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(type.toString());
        sb.append(source == null ? "" : source);
        sb.append(size);
        sb.append("-");
        sb.append(id);
        String string = sb.toString();
        String str = sCachePathCache.get(string);
        if (str != null) {
            return str;
        }
        String cacheFileName = super.getCacheFileName(type, source, size, "default");
        sCachePathCache.put(string, cacheFileName);
        return cacheFileName;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.DefaultImageLoader
    public void generateImages() {
        String cacheFileName = getCacheFileName(this.mItemType, null, CacheManager.ALBUM_ART_THUMBNAIL_SIZE, "default");
        if (cacheFileName == null || new File(cacheFileName).exists()) {
            return;
        }
        if (this.mDefaultImageStore.size() == 0) {
            Bitmap bitmap = ((BitmapDrawable) AmazonApplication.getContext().getResources().getDrawable(getDefaultImageId())).getBitmap();
            for (int i : sPreCachedImageSizes) {
                this.mDefaultImageStore.put(Integer.valueOf(i), resample(Bitmap.createScaledBitmap(bitmap, i, i, true), i));
            }
        }
        new Thread(new Runnable() { // from class: com.amazon.mp3.library.cache.image.loader.DefaultGenericImageLoader.1
            @Override // java.lang.Runnable
            public void run() {
                for (int i2 : DefaultGenericImageLoader.sOtherSupportedSizes) {
                    Bitmap scaledDefaultImage = DefaultGenericImageLoader.this.mDefaultImageStore.get(Integer.valueOf(i2));
                    if (scaledDefaultImage == null) {
                        scaledDefaultImage = DefaultGenericImageLoader.this.getScaledDefaultImage(i2);
                    }
                    DefaultGenericImageLoader defaultGenericImageLoader = DefaultGenericImageLoader.this;
                    defaultGenericImageLoader.saveToFile(scaledDefaultImage, null, defaultGenericImageLoader.mItemType, "default");
                }
            }
        }, "Default image generator thread").start();
    }

    @Override // com.amazon.mp3.library.cache.image.loader.AbstractDefaultImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.DefaultImageLoader, com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.ImageLoader
    public int getMaxImageSize() {
        if (this.mCachedMaxSize == -1) {
            for (int i : sOtherSupportedSizes) {
                if (i > this.mCachedMaxSize) {
                    this.mCachedMaxSize = i;
                }
            }
        }
        return this.mCachedMaxSize;
    }
}
