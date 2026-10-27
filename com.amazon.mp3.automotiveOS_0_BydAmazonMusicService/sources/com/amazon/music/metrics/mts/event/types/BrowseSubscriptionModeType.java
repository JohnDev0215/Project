package com.amazon.music.metrics.mts.event.types;

import UpsellInterface.v1_0.UpsellType;
import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum BrowseSubscriptionModeType implements MetricValueType {
    NO_BROWSE("NO_BROWSE"),
    PRIME(UpsellType.PRIME),
    UNLIMITED(UpsellType.UNLIMITED),
    UNKNOWN("");

    private final String mMetricValue;

    BrowseSubscriptionModeType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
