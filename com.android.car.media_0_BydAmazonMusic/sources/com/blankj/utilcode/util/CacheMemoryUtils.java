package com.blankj.utilcode.util;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;
import com.blankj.utilcode.constant.CacheConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class CacheMemoryUtils implements CacheConstants {
    private static final Map<String, CacheMemoryUtils> CACHE_MAP = new HashMap();
    private static final int DEFAULT_MAX_COUNT = 256;
    private final String mCacheKey;
    private final LruCache<String, a> mMemoryCache;

    public static final class a {
        public long a;
        public Object b;

        public a(long j, Object obj) {
            this.a = j;
            this.b = obj;
        }
    }

    private CacheMemoryUtils(String str, LruCache<String, a> lruCache) {
        this.mCacheKey = str;
        this.mMemoryCache = lruCache;
    }

    public static CacheMemoryUtils getInstance() {
        return getInstance(256);
    }

    public void clear() {
        this.mMemoryCache.evictAll();
    }

    public <T> T get(@NonNull String str) {
        return (T) get(str, null);
    }

    public int getCacheCount() {
        return this.mMemoryCache.size();
    }

    public void put(@NonNull String str, Object obj) {
        put(str, obj, -1);
    }

    public Object remove(@NonNull String str) {
        a aVarRemove = this.mMemoryCache.remove(str);
        if (aVarRemove == null) {
            return null;
        }
        return aVarRemove.b;
    }

    public String toString() {
        return this.mCacheKey + "@" + Integer.toHexString(hashCode());
    }

    public static CacheMemoryUtils getInstance(int i) {
        return getInstance(String.valueOf(i), i);
    }

    public <T> T get(@NonNull String str, T t) {
        a aVar = this.mMemoryCache.get(str);
        if (aVar == null) {
            return t;
        }
        long j = aVar.a;
        if (j == -1 || j >= System.currentTimeMillis()) {
            return (T) aVar.b;
        }
        this.mMemoryCache.remove(str);
        return t;
    }

    public void put(@NonNull String str, Object obj, int i) {
        if (obj == null) {
            return;
        }
        this.mMemoryCache.put(str, new a(i < 0 ? -1L : System.currentTimeMillis() + ((long) (i * 1000)), obj));
    }

    public static CacheMemoryUtils getInstance(String str, int i) {
        Map<String, CacheMemoryUtils> map = CACHE_MAP;
        CacheMemoryUtils cacheMemoryUtils = map.get(str);
        if (cacheMemoryUtils == null) {
            synchronized (CacheMemoryUtils.class) {
                cacheMemoryUtils = map.get(str);
                if (cacheMemoryUtils == null) {
                    cacheMemoryUtils = new CacheMemoryUtils(str, new LruCache(i));
                    map.put(str, cacheMemoryUtils);
                }
            }
        }
        return cacheMemoryUtils;
    }
}
