package com.amazon.avod.media.contentcache.internal.admittance;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.media.contentcache.internal.LiveCache;
import com.amazon.avod.media.contentcache.internal.WritableCacheRecord;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LiveCacheAdmittancePolicy implements AdmittancePolicy {
    private final LiveCache mLiveCache;

    public LiveCacheAdmittancePolicy(@Nonnull LiveCache liveCache) {
        this.mLiveCache = (LiveCache) Preconditions.checkNotNull(liveCache, "liveCache");
    }

    @Override // com.amazon.avod.media.contentcache.internal.admittance.AdmittancePolicy
    @Nonnull
    public AdmittancePolicyResult canAdmit(@Nonnull WritableCacheRecord writableCacheRecord) {
        if (writableCacheRecord.getSessionType() != ContentSessionType.LIVE_CACHE) {
            return AdmittancePolicyResult.ALLOWED;
        }
        String titleId = writableCacheRecord.getSpecification().getTitleId();
        if (this.mLiveCache.isTitleCachedOrPrepared(titleId)) {
            DLog.logf("LiveCache: Denied admittance as titleId: %s is already cached or prepared", titleId);
            return AdmittancePolicyResult.DENIED_CACHING_NOT_PERMITTED;
        }
        DLog.logf("LiveCache: Admitted titleId: %s", titleId);
        return AdmittancePolicyResult.ALLOWED;
    }
}
