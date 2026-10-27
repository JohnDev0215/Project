package com.amazon.avod.media.contentcache.internal.retry;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.media.contentcache.internal.CacheConfig;
import com.amazon.avod.media.contentcache.internal.WritableCacheRecord;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AttemptBasedRetryPolicy implements RetryPolicy {
    private final CacheConfig mConfig;

    public AttemptBasedRetryPolicy(CacheConfig cacheConfig) {
        this.mConfig = cacheConfig;
    }

    @Override // com.amazon.avod.media.contentcache.internal.retry.RetryPolicy
    public boolean canRetry(@Nonnull WritableCacheRecord writableCacheRecord) {
        int maxNumberOfTries;
        int numberOfAttempts = writableCacheRecord.getNumberOfAttempts();
        if (writableCacheRecord.getSessionType() == ContentSessionType.LIVE_CACHE) {
            maxNumberOfTries = this.mConfig.getLiveMaxNumberOfTries();
        } else {
            maxNumberOfTries = this.mConfig.getMaxNumberOfTries();
        }
        return numberOfAttempts < maxNumberOfTries;
    }
}
