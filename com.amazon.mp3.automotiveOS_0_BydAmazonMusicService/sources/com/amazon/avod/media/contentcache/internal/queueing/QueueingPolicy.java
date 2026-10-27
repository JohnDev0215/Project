package com.amazon.avod.media.contentcache.internal.queueing;

import com.amazon.avod.media.contentcache.CacheRequest;

/* JADX INFO: loaded from: classes2.dex */
public interface QueueingPolicy {
    boolean canQueue(CacheRequest cacheRequest);
}
