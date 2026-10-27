package com.facebook.cache.disk;

import com.facebook.cache.common.CacheEvent;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheKey;
import java.io.IOException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SettableCacheEvent implements CacheEvent {
    private static final Object RECYCLER_LOCK = new Object();

    @Nullable
    private static SettableCacheEvent sFirstRecycledEvent;
    private static int sRecycledCount;

    @Nullable
    private CacheKey mCacheKey;
    private long mCacheLimit;
    private long mCacheSize;

    @Nullable
    private CacheEventListener.EvictionReason mEvictionReason;

    @Nullable
    private IOException mException;
    private long mItemSize;

    @Nullable
    private SettableCacheEvent mNextRecycledEvent;

    @Nullable
    private String mResourceId;

    public static SettableCacheEvent obtain() {
        synchronized (RECYCLER_LOCK) {
            SettableCacheEvent settableCacheEvent = sFirstRecycledEvent;
            if (settableCacheEvent != null) {
                sFirstRecycledEvent = settableCacheEvent.mNextRecycledEvent;
                settableCacheEvent.mNextRecycledEvent = null;
                sRecycledCount--;
                return settableCacheEvent;
            }
            return new SettableCacheEvent();
        }
    }

    private SettableCacheEvent() {
    }

    public SettableCacheEvent setCacheKey(CacheKey cacheKey) {
        this.mCacheKey = cacheKey;
        return this;
    }

    public SettableCacheEvent setResourceId(String resourceId) {
        this.mResourceId = resourceId;
        return this;
    }

    public SettableCacheEvent setItemSize(long itemSize) {
        this.mItemSize = itemSize;
        return this;
    }

    public SettableCacheEvent setCacheSize(long cacheSize) {
        this.mCacheSize = cacheSize;
        return this;
    }

    public SettableCacheEvent setCacheLimit(long cacheLimit) {
        this.mCacheLimit = cacheLimit;
        return this;
    }

    public SettableCacheEvent setException(IOException exception) {
        this.mException = exception;
        return this;
    }

    public SettableCacheEvent setEvictionReason(CacheEventListener.EvictionReason evictionReason) {
        this.mEvictionReason = evictionReason;
        return this;
    }

    public void recycle() {
        synchronized (RECYCLER_LOCK) {
            if (sRecycledCount < 5) {
                reset();
                sRecycledCount++;
                SettableCacheEvent settableCacheEvent = sFirstRecycledEvent;
                if (settableCacheEvent != null) {
                    this.mNextRecycledEvent = settableCacheEvent;
                }
                sFirstRecycledEvent = this;
            }
        }
    }

    private void reset() {
        this.mCacheKey = null;
        this.mResourceId = null;
        this.mItemSize = 0L;
        this.mCacheLimit = 0L;
        this.mCacheSize = 0L;
        this.mException = null;
        this.mEvictionReason = null;
    }
}
