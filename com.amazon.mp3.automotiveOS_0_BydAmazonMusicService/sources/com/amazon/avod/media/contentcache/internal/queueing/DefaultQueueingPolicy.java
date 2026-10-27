package com.amazon.avod.media.contentcache.internal.queueing;

import com.amazon.avod.media.contentcache.CacheRequest;
import com.amazon.avod.media.contentcache.internal.CacheConfig;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultQueueingPolicy implements QueueingPolicy {
    private final CacheConfig mConfig;

    public DefaultQueueingPolicy(CacheConfig cacheConfig) {
        this.mConfig = cacheConfig;
    }

    @Override // com.amazon.avod.media.contentcache.internal.queueing.QueueingPolicy
    public boolean canQueue(CacheRequest cacheRequest) {
        return !this.mConfig.shouldBlockCachingOfUnownedContent() || cacheRequest.getVideoSpecification().isTrailer() || cacheRequest.isEntitledToWatch();
    }
}
