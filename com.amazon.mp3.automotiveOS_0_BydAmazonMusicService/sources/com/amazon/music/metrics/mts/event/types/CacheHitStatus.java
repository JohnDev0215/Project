package com.amazon.music.metrics.mts.event.types;

import androidx.core.os.EnvironmentCompat;
import com.amazon.music.metrics.mts.event.types.core.MetricValueType;
import com.google.android.gms.common.internal.ImagesContract;

/* JADX INFO: loaded from: classes4.dex */
public enum CacheHitStatus implements MetricValueType {
    FULL("full"),
    PARTIAL("partial"),
    NONE("none"),
    LOCAL(ImagesContract.LOCAL),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);

    private final String mMetricValue;

    CacheHitStatus(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
