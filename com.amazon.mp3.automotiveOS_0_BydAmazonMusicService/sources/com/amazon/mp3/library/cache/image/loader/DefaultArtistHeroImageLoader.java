package com.amazon.mp3.library.cache.image.loader;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.image.ImageMetadata;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.task.JobContext;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultArtistHeroImageLoader extends AbstractDefaultImageLoader {
    static HashMap<String, String> sCachePathCache;
    private static int[] sOtherSupportedSizes = {AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.artist_detail_hero_width), AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.library_playlist_listview_art_row_height)};
    static HashMap<Integer, HashMap<Integer, Drawable>> sSharedImageStores;
    private static HashSet<Integer> sSizesToCacheInMemory;
    static HashMap<Integer, HashMap<Integer, SoftReference<BitmapDrawable>>> sUnsupportedImageStores;
    private int mCachedMaxSize;
    HashMap<Integer, Drawable> mDefaultImageStore;
    HashMap<Integer, SoftReference<BitmapDrawable>> mUnsupportedImageStore;

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.DefaultImageLoader
    public void generateImages() {
    }

    protected int getDefaultImageId() {
        return R.drawable.artist_placeholder_detail;
    }

    static {
        HashSet<Integer> hashSet = new HashSet<>();
        sSizesToCacheInMemory = hashSet;
        hashSet.add(Integer.valueOf(AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.artist_detail_hero_width)));
        sSizesToCacheInMemory.add(Integer.valueOf(AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.library_playlist_listview_art_row_height)));
        sSharedImageStores = new HashMap<>();
        sUnsupportedImageStores = new HashMap<>();
        sCachePathCache = new HashMap<>();
    }

    DefaultArtistHeroImageLoader(ImageLoaderFactory.ItemType itemType) {
        super(itemType);
        this.mCachedMaxSize = -1;
        this.mDefaultImageStore = null;
        this.mUnsupportedImageStore = null;
        HashMap<Integer, Drawable> map = sSharedImageStores.get(Integer.valueOf(getDefaultImageId()));
        this.mDefaultImageStore = map;
        if (map == null) {
            this.mDefaultImageStore = new HashMap<>(sOtherSupportedSizes.length + 1);
            sSharedImageStores.put(Integer.valueOf(getDefaultImageId()), this.mDefaultImageStore);
        }
        HashMap<Integer, SoftReference<BitmapDrawable>> map2 = sUnsupportedImageStores.get(Integer.valueOf(getDefaultImageId()));
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

    private BitmapDrawable getScaledDefaultImage(int size) {
        SoftReference<BitmapDrawable> softReference = this.mUnsupportedImageStore.get(Integer.valueOf(size));
        BitmapDrawable bitmapDrawable = softReference == null ? null : softReference.get();
        if (bitmapDrawable != null) {
            return bitmapDrawable;
        }
        float f = size * 0.5625f;
        Bitmap bitmapResample = resample(Bitmap.createScaledBitmap(getDefaultBitmap(), size, Math.round(f), true), size, Math.round(f));
        if (sSizesToCacheInMemory.contains(Integer.valueOf(size)) && !this.mDefaultImageStore.containsKey(Integer.valueOf(size))) {
            this.mDefaultImageStore.put(Integer.valueOf(size), new BitmapDrawable(AmazonApplication.getContext().getResources(), bitmapResample));
        }
        bitmapResample.setDensity(0);
        BitmapDrawable bitmapDrawable2 = new BitmapDrawable(bitmapResample);
        this.mUnsupportedImageStore.put(Integer.valueOf(size), new SoftReference<>(bitmapDrawable2));
        return bitmapDrawable2;
    }

    @Override // com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory.DefaultImageLoader
    public JobContext<ImageMetadata> getImage(JobContext<ImageMetadata> taskContext, boolean forceLoad) {
        ImageMetadata imageMetadata = (ImageMetadata) taskContext.getMetadata();
        Drawable scaledDefaultImage = this.mDefaultImageStore.get(Integer.valueOf(imageMetadata.getSize()));
        if (scaledDefaultImage == null) {
            scaledDefaultImage = getScaledDefaultImage(imageMetadata.getSize());
        }
        imageMetadata.setImage(scaledDefaultImage);
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
