package com.android.volley;

import com.android.volley.Cache;

/* JADX INFO: loaded from: classes5.dex */
public class CacheStateManager {
    private final Cache cache;

    public CacheStateManager(Cache cache) {
        this.cache = cache;
    }

    public boolean hasCacheExpired(String str) {
        Cache.Entry entry;
        if (str == null || (entry = this.cache.get(str)) == null) {
            return false;
        }
        VolleyLog.d("%s", "Time until cache " + str + " expires: " + entry.timeToExpire());
        return entry.isExpired();
    }

    public boolean invalidateCacheEntry(String str) {
        if (str == null || this.cache.get(str) == null) {
            return false;
        }
        this.cache.invalidate(str, true);
        return true;
    }

    public Cache.Entry getCacheEntry(String str) {
        if (str != null) {
            return this.cache.get(str);
        }
        return null;
    }
}
