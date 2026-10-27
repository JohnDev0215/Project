package com.amazon.mp3.library.cache.image;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.CachePolicy;
import com.amazon.mp3.library.cache.LruCachePolicy;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.task.JobContext;
import com.amazon.mp3.task.JobListener;
import com.amazon.mp3.task.JobStack;
import com.amazon.mp3.task.JobThread;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import com.amazon.mp3.util.ScreenUtil;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class CacheManager implements JobListener<ImageMetadata> {
    public static final int ALBUM_ART_THUMBNAIL_SIZE;
    public static final int CMS_SMALL_THUMB_GRID_SIZE;
    public static final int DEFAULT_EXPLORE_IMAGE_SIZE;
    public static final int DEFAULT_HERO_IMAGE_SIZE;
    public static final int GRID_VIEW_IMAGE_SIZE;
    private static final int LOAD_THREAD_COUNT = Math.round(Runtime.getRuntime().availableProcessors() * 1.5f);
    public static final int MAX_UNSCALED_SIZE;
    private static final String TAG;
    private static int sCacheComputedImageSize;
    private static CacheManager sInstance;
    private static final LibraryItemFactory sLibraryFactory;
    private static int sMaxPrefetchSize;
    private static int sNextThreadId;
    private final CachePolicy<String, Drawable> mCachePolicy;
    private final CachePolicy<String, Drawable> mLargeCachePolicy;
    private BroadcastReceiver mSyncEventReceiver;
    private boolean mIsPaused = false;
    private Set<Integer> mCachedSizes = new HashSet();
    private JobStack<ImageMetadata> mImageLoadStack = new JobStack<>();
    private HashSet<ImageLoadThread> mLoadThreads = new HashSet<>();
    private JobStack<ImageMetadata> mCompositeLoadStack = new JobStack<>();
    private ImageLoadThread mCompositeLoadThread = new ImageLoadThread(this.mCompositeLoadStack);
    private ImageLoaderFactory mImageLoaderFactory = ImageLoaderFactory.getInstance();
    private Set<String> mForceDefaults = Collections.newSetFromMap(new ConcurrentHashMap());
    private Runnable mMoveAllItemsToWeakCache = new Runnable() { // from class: com.amazon.mp3.library.cache.image.CacheManager.3
        @Override // java.lang.Runnable
        public void run() {
            if (!ConnectivityUtil.isWifiConnected() && !CacheManager.this.mIsPaused) {
                CacheManager.this.pause();
            }
            CacheManager.this.weakenCache();
        }
    };

    static /* synthetic */ int access$008() {
        int i = sNextThreadId;
        sNextThreadId = i + 1;
        return i;
    }

    static {
        if (ScreenUtil.getDeviceWidth() < 256) {
            MAX_UNSCALED_SIZE = 256;
        } else {
            MAX_UNSCALED_SIZE = 500;
        }
        sInstance = null;
        DEFAULT_EXPLORE_IMAGE_SIZE = AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.cms_small_image_size);
        DEFAULT_HERO_IMAGE_SIZE = AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.artist_detail_hero_width);
        ALBUM_ART_THUMBNAIL_SIZE = AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_listview_art_size);
        GRID_VIEW_IMAGE_SIZE = AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_gridview_art_size);
        CMS_SMALL_THUMB_GRID_SIZE = AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_gridview_art_size);
        sLibraryFactory = AmazonApplication.getLibraryItemFactory();
        sCacheComputedImageSize = -1;
        TAG = CacheManager.class.getSimpleName();
        sMaxPrefetchSize = 64;
        sNextThreadId = 0;
    }

    private CachePolicy<String, Drawable> getCachePolicy(int size) {
        synchronized (this.mCachedSizes) {
            this.mCachedSizes.add(Integer.valueOf(size));
        }
        if (size > sCacheComputedImageSize) {
            return this.mLargeCachePolicy;
        }
        return this.mCachePolicy;
    }

    public class ImageLoadThread extends JobThread<ImageMetadata> {
        private int mThreadId;

        public ImageLoadThread(JobStack<ImageMetadata> jobStack) {
            super(jobStack);
            synchronized (ImageLoadThread.class) {
                this.mThreadId = CacheManager.access$008();
            }
        }

        @Override // com.amazon.mp3.task.JobThread
        public void execute(JobContext<ImageMetadata> imageLoadContext) {
            CacheManager.this.mImageLoaderFactory.getImageLoader(((ImageMetadata) imageLoadContext.getMetadata()).getType()).getImage(imageLoadContext);
        }
    }

    private void initializeImageLoader() {
        synchronized (this.mLoadThreads) {
            for (int i = 0; i < LOAD_THREAD_COUNT; i++) {
                this.mLoadThreads.add(new ImageLoadThread(this.mImageLoadStack));
            }
            for (ImageLoadThread imageLoadThread : this.mLoadThreads) {
                imageLoadThread.setPriority(1);
                imageLoadThread.start();
            }
        }
        this.mCompositeLoadThread.setPriority(1);
        this.mCompositeLoadThread.start();
    }

    private void registerSyncListener() {
        this.mSyncEventReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.cache.image.CacheManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if ("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STOPPED".equals(intent.getAction())) {
                    CacheManager.this.mForceDefaults.clear();
                    return;
                }
                int intExtra = intent.getIntExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_EVENT_TYPE", 0);
                if (intExtra == 3 || intExtra == 5) {
                    CacheManager.this.mForceDefaults.clear();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("com.amazon.mp3.library.service.sync.ACTION_SYNC_EVENT");
        intentFilter.addAction("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STOPPED");
        AmazonApplication.getContext().registerReceiver(this.mSyncEventReceiver, intentFilter);
    }

    private static void setCacheComputedImageSize(int size) {
        sCacheComputedImageSize = size;
    }

    private CacheManager() {
        ActivityManager activityManager = (ActivityManager) AmazonApplication.getContext().getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        boolean z = (AmazonApplication.getAppInfo() == null || (AmazonApplication.getAppInfo().flags & 1048576) == 0) ? false : true;
        float largeMemoryClass = (z ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass()) / 16.0f;
        float fMin = Math.min((2.0f + largeMemoryClass) * largeMemoryClass, z ? 58.0f : 24.0f);
        int iMax = Math.max(AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_gridview_art_size), AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_listview_art_size));
        setCacheComputedImageSize(iMax);
        this.mCachePolicy = new CacheManagerLruCachePolicy((((int) (fMin * 1048576.0f)) / ((iMax * iMax) * 2)) + 1, AmazonApplication.getCapabilities().isMemoryConstrainted());
        this.mLargeCachePolicy = new LruCachePolicy(3, false);
        initializeImageLoader();
        registerSyncListener();
    }

    public static synchronized CacheManager getInstance() {
        if (sInstance == null) {
            sInstance = new CacheManager();
        }
        return sInstance;
    }

    public void pause() {
        synchronized (this.mLoadThreads) {
            Iterator<ImageLoadThread> it = this.mLoadThreads.iterator();
            while (it.hasNext()) {
                it.next().pause();
            }
            this.mIsPaused = true;
            this.mCompositeLoadThread.pause();
        }
    }

    public void unpause() {
        synchronized (this.mLoadThreads) {
            Iterator<ImageLoadThread> it = this.mLoadThreads.iterator();
            while (it.hasNext()) {
                it.next().unpause();
            }
            this.mCompositeLoadThread.unpause();
            this.mIsPaused = false;
        }
    }

    public boolean hasOnDisk(ImageLoaderFactory.ItemType type, int size, String id) {
        return hasOnDisk(type, null, size, id);
    }

    public boolean hasOnDisk(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        ImageLoaderFactory.ImageLoader imageLoader = ImageLoaderFactory.getInstance().getImageLoader(type);
        if (imageLoader != null) {
            return new File(imageLoader.getCacheFileName(type, source, size, id)).exists() || new File(imageLoader.getCacheFileName(type, source, imageLoader.getMaxImageSize(), id)).exists();
        }
        return false;
    }

    public boolean hasExactOnDisk(ImageLoaderFactory.ItemType type, int size, String id) {
        return hasExactOnDisk(type, null, size, id);
    }

    public boolean hasExactOnDisk(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        ImageLoaderFactory.ImageLoader imageLoader = ImageLoaderFactory.getInstance().getImageLoader(type);
        if (imageLoader != null) {
            return new File(imageLoader.getCacheFileName(type, source, size, id)).exists();
        }
        return false;
    }

    public boolean contains(ImageLoaderFactory.ItemType type, int size, String id) {
        return contains(type, null, size, id);
    }

    public boolean contains(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        boolean zContains;
        synchronized (this.mCachePolicy) {
            zContains = getCachePolicy(size).contains("TheNewImageCache", ImageMetadata.makeKey(type, source, size, id));
        }
        return zContains;
    }

    public Drawable getDefault(ImageLoaderFactory.ItemType type, int size) {
        return getDefault(type, null, size);
    }

    public Drawable getDefault(ImageLoaderFactory.ItemType type, String source, int size) {
        JobContext<ImageMetadata> image = this.mImageLoaderFactory.getDefaultImageLoader(type).getImage(new JobContext<>(new ImageMetadata(type, source, size, "")));
        if (image == null || image.getMetadata() == null) {
            return null;
        }
        return ((ImageMetadata) image.getMetadata()).getImage();
    }

    public Drawable get(ImageLoaderFactory.ItemType type, int size, String id) {
        return get(type, null, size, id);
    }

    public Drawable get(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        Drawable drawable;
        String strMakeKey = ImageMetadata.makeKey(type, source, size, id);
        synchronized (this.mCachePolicy) {
            drawable = getCachePolicy(size).get("TheNewImageCache", strMakeKey);
        }
        if (drawable != null) {
            return drawable;
        }
        ImageMetadata imageMetadata = new ImageMetadata(type, source, size, id);
        if (this.mForceDefaults.contains(strMakeKey)) {
            imageMetadata.setUpdateOnly();
        }
        if (type.isComposite()) {
            this.mCompositeLoadStack.put(imageMetadata, this);
        } else {
            this.mImageLoadStack.put(imageMetadata, this);
        }
        this.mForceDefaults.add(strMakeKey);
        return getDefault(type, source, size);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.library.cache.image.CacheManager$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType;

        static {
            int[] iArr = new int[ImageLoaderFactory.ItemType.values().length];
            $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType = iArr;
            try {
                iArr[ImageLoaderFactory.ItemType.PLAYLIST_UDO_THUMBNAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ImageLoaderFactory.ItemType.PLAYLIST_UDO_BANNER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ImageLoaderFactory.ItemType.PRIME_PLAYLIST_BANNER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static int getDownloadedImageSizeByType(final ImageLoaderFactory.ItemType type) {
        int i = AnonymousClass4.$SwitchMap$com$amazon$mp3$library$cache$image$loader$ImageLoaderFactory$ItemType[type.ordinal()];
        if (i == 1 || i == 2) {
            return ALBUM_ART_THUMBNAIL_SIZE;
        }
        if (i == 3 || i == 4 || i == 5) {
            return AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.now_playing_album_art_size);
        }
        Log.error(TAG, "called getDownloadedImageSizeByType with currently unsupported type: " + type.name());
        return 0;
    }

    public Drawable getOnCurrentThread(ImageLoaderFactory.ItemType type, int size, String id, boolean keepInCache) {
        return getOnCurrentThread(type, null, size, id, keepInCache);
    }

    public Drawable getOnCurrentThread(ImageLoaderFactory.ItemType type, String source, int size, String id, boolean keepInCache) {
        return getOnCurrentThread(type, source, size, id, keepInCache, true);
    }

    public Drawable getOnCurrentThread(ImageLoaderFactory.ItemType type, String source, int size, String id, boolean keepInCache, boolean usePlaceHolder) {
        Drawable drawable;
        JobContext<ImageMetadata> image;
        synchronized (this.mCachePolicy) {
            drawable = getCachePolicy(size).get("TheNewImageCache", ImageMetadata.makeKey(type, source, size, id));
        }
        if (drawable != null) {
            return drawable;
        }
        ImageMetadata imageMetadata = new ImageMetadata(type, source, size, id);
        if (!keepInCache) {
            imageMetadata.setLowPriority(true);
        }
        JobContext<ImageMetadata> image2 = ImageLoaderFactory.getInstance().getImageLoader(type).getImage(new JobContext<>(imageMetadata));
        if (image2 == null || image2.getMetadata() == null) {
            return drawable;
        }
        if (((ImageMetadata) image2.getMetadata()).getImage() == null) {
            return (((ImageMetadata) image2.getMetadata()).getType() == ImageLoaderFactory.ItemType.ARTIST_HERO || !usePlaceHolder || (image = this.mImageLoaderFactory.getDefaultImageLoader(type).getImage(image2)) == null || image.getMetadata() == null) ? drawable : ((ImageMetadata) image.getMetadata()).getImage();
        }
        Drawable image3 = ((ImageMetadata) image2.getMetadata()).getImage();
        onComplete(image2);
        return image3;
    }

    public void precache(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        boolean zContains;
        if (AmazonApplication.getCapabilities().isMemoryConstrainted() && SyncService.isRunning()) {
            return;
        }
        String strMakeKey = ImageMetadata.makeKey(type, source, size, id);
        synchronized (this.mCachePolicy) {
            zContains = getCachePolicy(size).contains("TheNewImageCache", strMakeKey);
        }
        if (zContains || this.mForceDefaults.contains(strMakeKey)) {
            return;
        }
        ImageMetadata imageMetadata = new ImageMetadata(type, source, size, id);
        imageMetadata.setPrecacheRequest(true);
        imageMetadata.setLowPriority(true);
        this.mForceDefaults.add(strMakeKey);
        if (type.isComposite()) {
            this.mCompositeLoadStack.put(imageMetadata, this);
        } else {
            this.mImageLoadStack.put(imageMetadata, this);
        }
    }

    public void prefetch(ImageLoaderFactory.ItemType type, int size, String id, boolean expelCached) {
        prefetch(type, null, size, id, expelCached);
    }

    public void prefetch(ImageLoaderFactory.ItemType type, String source, int size, String id, boolean expelCached) {
        prefetch(type, source, size, id, expelCached, true);
    }

    private void prefetch(ImageLoaderFactory.ItemType type, String source, int size, String id, boolean expelCached, boolean lowPriority) {
        Drawable drawable;
        if (AmazonApplication.getCapabilities().isMemoryConstrainted()) {
            return;
        }
        if (expelCached) {
            synchronized (this.mCachedSizes) {
                Iterator<Integer> it = this.mCachedSizes.iterator();
                while (it.hasNext()) {
                    int iIntValue = it.next().intValue();
                    getCachePolicy(iIntValue).remove("TheNewImageCache", ImageMetadata.makeKey(type, source, iIntValue, id));
                }
            }
        } else {
            synchronized (this.mCachePolicy) {
                drawable = getCachePolicy(size).get("TheNewImageCache", ImageMetadata.makeKey(type, source, size, id));
            }
            if (drawable != null) {
                return;
            }
        }
        if (hasExactOnDisk(type, source, size, id)) {
            return;
        }
        ImageMetadata imageMetadata = new ImageMetadata(type, source, size, id);
        imageMetadata.setLowPriority(lowPriority);
        if (type.isComposite()) {
            this.mCompositeLoadStack.put(imageMetadata, this);
        } else {
            this.mImageLoadStack.put(imageMetadata, this);
        }
    }

    public void refreshArtwork(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        clearFromDiskCache(type, source, id, true);
        prefetch(type, source, size, id, true, false);
    }

    public String getCacheFileName(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        Profiler.begin("getCacheFilename");
        String cacheFileName = this.mImageLoaderFactory.getImageLoader(type).getCacheFileName(type, source, size, id);
        if (cacheFileName != null && !new File(cacheFileName).exists()) {
            cacheFileName = this.mImageLoaderFactory.getDefaultImageLoader(type).getCacheFileName(type, null, size, id);
        }
        Profiler.end();
        return cacheFileName;
    }

    private void broadcastLoaded(ImageMetadata imageMetadata) {
        Intent intent = new Intent("com.amazon.mp3.library.cache.image.CacheManager.ACTION_ALBUM_ARTWORK_CACHED");
        intent.putExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_ID", imageMetadata.getId());
        if (imageMetadata.getSource() != null) {
            intent.putExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_SOURCE", imageMetadata.getSource());
        }
        intent.putExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_TYPE", imageMetadata.getType().intValue());
        intent.putExtra("com.amazon.mp3.library.cache.image.CacheManager.EXTRA_IMAGE_SIZE", imageMetadata.getSize());
        AmazonApplication.getContext().sendBroadcast(intent);
    }

    @Override // com.amazon.mp3.task.JobListener
    public void onComplete(JobContext<ImageMetadata> taskContext) {
        ImageMetadata imageMetadata = (ImageMetadata) taskContext.getMetadata();
        Drawable image = imageMetadata.getImage();
        if (image == null) {
            if (imageMetadata.isLowPriority()) {
                return;
            }
            Log.warning(TAG, "Null image loaded for type: %s size: %s id: %s", imageMetadata.getType(), Integer.valueOf(imageMetadata.getSize()), imageMetadata.getId());
            return;
        }
        this.mForceDefaults.remove(imageMetadata.getKey());
        if (!imageMetadata.isLowPriority() || imageMetadata.isPrecacheRequest()) {
            synchronized (this.mCachePolicy) {
                getCachePolicy(imageMetadata.getSize()).put("TheNewImageCache", imageMetadata.getKey(), image);
            }
        }
        if (imageMetadata.isLowPriority()) {
            return;
        }
        broadcastLoaded(imageMetadata);
    }

    @Override // com.amazon.mp3.task.JobListener
    public void onFailure(JobContext<ImageMetadata> taskContext) {
        ImageMetadata imageMetadata = (ImageMetadata) taskContext.getMetadata();
        if (imageMetadata == null) {
            Log.warning(TAG, "Image load failed: metadata returned by loader is null");
        } else {
            Log.warning(TAG, "Image load failed for type: %s size: %s id: %s", imageMetadata.getType(), Integer.valueOf(imageMetadata.getSize()), imageMetadata.getId());
        }
    }

    public void regenerateDefaultImages() {
        this.mImageLoaderFactory.regenerateDefaultImages();
    }

    public void clearImages() {
        synchronized (this.mCachePolicy) {
            this.mCachePolicy.clear();
            this.mLargeCachePolicy.clear();
        }
        regenerateDefaultImages();
    }

    public void clearPendingJobs() {
        this.mImageLoadStack.clearStack();
        this.mCompositeLoadStack.clearStack();
    }

    public void reset() {
        clearPendingJobs();
        clearImages();
    }

    public void clearFromDiskCache(ImageLoaderFactory.ItemType type, String source, String id, boolean skipCms) {
        HashSet hashSet = new HashSet();
        hashSet.add(id);
        clearFromDiskCache(type, source, hashSet, skipCms);
    }

    public void clearFromDiskCache(ImageLoaderFactory.ItemType type, Collection<String> ids, boolean skipCms) {
        clearFromDiskCache(type, (String) null, ids, skipCms);
    }

    public void clearFromDiskCache(ImageLoaderFactory.ItemType type, String source, Collection<String> ids, boolean skipCms) {
        ImageLoaderFactory.ImageLoader imageLoader = this.mImageLoaderFactory.getImageLoader(type);
        if (imageLoader != null) {
            imageLoader.removeIds(type, source, ids, skipCms);
        }
    }

    public void clearAllFromDiskCache() {
        reset();
        for (ImageLoaderFactory.ItemType itemType : ImageLoaderFactory.ItemType.types()) {
            ImageLoaderFactory.ImageLoader imageLoader = this.mImageLoaderFactory.getImageLoader(itemType);
            if (imageLoader != null) {
                imageLoader.removeAll(itemType, null);
            }
        }
        regenerateDefaultImages();
    }

    public void onActivityPaused() {
        AmazonApplication.getDefaultHandler().postDelayed(this.mMoveAllItemsToWeakCache, 10000L);
    }

    public void onActivityResumed() {
        AmazonApplication.getDefaultHandler().removeCallbacks(this.mMoveAllItemsToWeakCache);
        if (this.mIsPaused) {
            unpause();
        }
    }

    public void weakenCache() {
        if (AmazonApplication.getDefaultHandler() != null) {
            AmazonApplication.getDefaultHandler().removeCallbacks(this.mMoveAllItemsToWeakCache);
        }
        new Thread("Clear hard cache") { // from class: com.amazon.mp3.library.cache.image.CacheManager.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                synchronized (CacheManager.this.mCachePolicy) {
                    CacheManager.this.mLargeCachePolicy.clear();
                    CacheManager.this.mCachePolicy.backgroundCache();
                }
            }
        }.start();
    }

    public static int getMaxPrefetchCount() {
        return sMaxPrefetchSize;
    }

    private class CacheManagerLruCachePolicy<K, V> extends LruCachePolicy<K, V> {
        public CacheManagerLruCachePolicy(int maxSize, boolean enableSoftCache) {
            super(maxSize, enableSoftCache);
        }

        @Override // com.amazon.mp3.library.cache.LruCachePolicy
        protected void onElementRemoved(String sourceId, K key, V value) {
            super.onElementRemoved(sourceId, key, value);
            CacheManager.this.mForceDefaults.remove(key);
        }
    }
}
