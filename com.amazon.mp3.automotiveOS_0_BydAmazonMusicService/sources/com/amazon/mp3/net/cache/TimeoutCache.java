package com.amazon.mp3.net.cache;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TimeoutCache<K, V> {
    private final long mCacheTimeout;

    protected abstract CacheData<V> getCacheData(K id);

    protected abstract void insert(K id, CacheData<V> data);

    protected abstract void remove(K id);

    public TimeoutCache(long cacheTimeout) {
        this.mCacheTimeout = cacheTimeout;
    }

    protected long getTimeout() {
        return this.mCacheTimeout;
    }

    public synchronized void put(K id, V object) {
        insert(id, new CacheData<>(object));
    }

    public synchronized V get(K id) {
        V data;
        data = null;
        CacheData<V> cacheData = getCacheData(id);
        if (cacheData != null) {
            if (SystemClock.uptimeMillis() - cacheData.getTimeStamp() < getTimeout()) {
                data = cacheData.getData();
            } else {
                remove(id);
            }
        }
        return data;
    }
}
