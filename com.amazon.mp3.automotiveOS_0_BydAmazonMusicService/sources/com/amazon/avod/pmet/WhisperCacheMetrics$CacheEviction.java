package com.amazon.avod.pmet;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum WhisperCacheMetrics$CacheEviction implements MetricParameter {
    TTL_EXPIRE("CacheEvictionTTLExpire"),
    CONTENT_REPUBLISHED("CacheEvictionContentRepublished"),
    LIVE_ORIGIN_FAILOVER("CacheEvictionLiveOriginFailover"),
    LICENSE_ERROR("CacheEvictionLicenseError"),
    CLEAR_CACHE("CacheEvictionClearCache"),
    CACHE_FULL("CacheEvictionCacheOrDiskFull");

    private final String mQosString;

    WhisperCacheMetrics$CacheEviction(@Nonnull String str) {
        this.mQosString = (String) Preconditions.checkNotNull(str, "qosString");
    }

    @Nonnull
    public String getQosString() {
        return this.mQosString;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return name();
    }
}
