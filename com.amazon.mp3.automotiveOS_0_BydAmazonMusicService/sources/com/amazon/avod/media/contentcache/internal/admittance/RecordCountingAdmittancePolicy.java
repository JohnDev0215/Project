package com.amazon.avod.media.contentcache.internal.admittance;

import com.amazon.avod.download.Downloadable;
import com.amazon.avod.media.contentcache.internal.CacheConfig;
import com.amazon.avod.media.contentcache.internal.CachedContentPersistence;
import com.amazon.avod.media.contentcache.internal.WritableCacheRecord;

/* JADX INFO: loaded from: classes2.dex */
public class RecordCountingAdmittancePolicy implements AdmittancePolicy {
    private final CacheConfig mConfig;
    private final CachedContentPersistence mPersistence;

    public RecordCountingAdmittancePolicy(CacheConfig cacheConfig, CachedContentPersistence cachedContentPersistence) {
        this.mConfig = cacheConfig;
        this.mPersistence = cachedContentPersistence;
    }

    @Override // com.amazon.avod.media.contentcache.internal.admittance.AdmittancePolicy
    public AdmittancePolicyResult canAdmit(WritableCacheRecord writableCacheRecord) {
        if (this.mPersistence.getTotalRecords() - this.mPersistence.getNumRecordsByState(Downloadable.DownloadableState.QUEUED) < this.mConfig.getMaxNumberOfCacheRecords()) {
            return AdmittancePolicyResult.ALLOWED;
        }
        return AdmittancePolicyResult.DENIED_CACHE_FULL;
    }
}
