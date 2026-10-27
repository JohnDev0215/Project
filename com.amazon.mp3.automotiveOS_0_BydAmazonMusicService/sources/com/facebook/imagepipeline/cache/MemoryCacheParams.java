package com.facebook.imagepipeline.cache;

/* JADX INFO: loaded from: classes3.dex */
public class MemoryCacheParams {
    public final int maxCacheEntries;
    public final int maxCacheEntrySize;
    public final int maxCacheSize;
    public final int maxEvictionQueueEntries;
    public final int maxEvictionQueueSize;
    public final long paramsCheckIntervalMs;

    public MemoryCacheParams(int maxCacheSize, int maxCacheEntries, int maxEvictionQueueSize, int maxEvictionQueueEntries, int maxCacheEntrySize, long paramsCheckIntervalMs) {
        this.maxCacheSize = maxCacheSize;
        this.maxCacheEntries = maxCacheEntries;
        this.maxEvictionQueueSize = maxEvictionQueueSize;
        this.maxEvictionQueueEntries = maxEvictionQueueEntries;
        this.maxCacheEntrySize = maxCacheEntrySize;
        this.paramsCheckIntervalMs = paramsCheckIntervalMs;
    }
}
