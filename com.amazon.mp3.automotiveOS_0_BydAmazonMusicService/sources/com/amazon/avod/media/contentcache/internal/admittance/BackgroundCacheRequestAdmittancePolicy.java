package com.amazon.avod.media.contentcache.internal.admittance;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.contentcache.internal.JitterCacheConfig;
import com.amazon.avod.media.contentcache.internal.WritableCacheRecord;

/* JADX INFO: loaded from: classes2.dex */
public class BackgroundCacheRequestAdmittancePolicy implements AdmittancePolicy {
    private final JitterCacheConfig mJitterConfig;

    public BackgroundCacheRequestAdmittancePolicy(JitterCacheConfig jitterCacheConfig) {
        this.mJitterConfig = jitterCacheConfig;
    }

    @Override // com.amazon.avod.media.contentcache.internal.admittance.AdmittancePolicy
    public AdmittancePolicyResult canAdmit(WritableCacheRecord writableCacheRecord) {
        if (!writableCacheRecord.isDeferrable() || !this.mJitterConfig.shouldAddJitterToBackgroundCacheRequests()) {
            return AdmittancePolicyResult.ALLOWED;
        }
        TimeSpan timeSpanNow = TimeSpan.now();
        if (timeSpanNow.lessThan(this.mJitterConfig.getNextScheduledExecution())) {
            return AdmittancePolicyResult.DENIED_DEFERRED;
        }
        if (timeSpanNow.greaterThanEquals(TimeSpan.add(this.mJitterConfig.getLastBackgroundCacheRequestPerformed(), this.mJitterConfig.getSlidingWindowLength()))) {
            this.mJitterConfig.setLastBackgroundCacheRequestPerformed(timeSpanNow);
            this.mJitterConfig.setBackgroundCacheRequestCount(1);
            return AdmittancePolicyResult.ALLOWED;
        }
        int backgroundCacheRequestCount = this.mJitterConfig.getBackgroundCacheRequestCount();
        if (backgroundCacheRequestCount < this.mJitterConfig.getSlidingWindowThreshold()) {
            this.mJitterConfig.setBackgroundCacheRequestCount(backgroundCacheRequestCount + 1);
            return AdmittancePolicyResult.ALLOWED;
        }
        return AdmittancePolicyResult.DENIED_DEFERRED;
    }
}
