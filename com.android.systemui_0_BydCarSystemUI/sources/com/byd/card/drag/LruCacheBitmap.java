package com.byd.card.drag;

import android.graphics.Bitmap;
import android.util.LruCache;

/* JADX INFO: loaded from: classes.dex */
public class LruCacheBitmap {
    private static volatile LruCacheBitmap sInstance;
    private LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8) { // from class: com.byd.card.drag.LruCacheBitmap.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public int sizeOf(String key, Bitmap value) {
            return (value.getRowBytes() * value.getHeight()) / 1024;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
            super.entryRemoved(evicted, key, oldValue, newValue);
            oldValue.recycle();
        }
    };

    private LruCacheBitmap() {
    }

    public static LruCacheBitmap getInstance() {
        if (sInstance == null) {
            synchronized (LruCacheBitmap.class) {
                if (sInstance == null) {
                    sInstance = new LruCacheBitmap();
                }
            }
        }
        return sInstance;
    }

    public Bitmap getDataFromCache(String key) {
        if (this.mCache.size() != 0) {
            return this.mCache.get(key);
        }
        return null;
    }

    public void putDataToCache(String key, Bitmap bitmap) {
        if (getDataFromCache(key) == null) {
            this.mCache.put(key, bitmap);
        }
    }

    public void clearCache() {
        this.mCache.evictAll();
    }
}
