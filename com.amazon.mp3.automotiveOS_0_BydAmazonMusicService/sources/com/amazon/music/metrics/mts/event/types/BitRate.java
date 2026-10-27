package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum BitRate implements MetricValueType {
    LOW("Low"),
    AUTO_LOW("AutoLow"),
    MEDIUM("Medium"),
    AUTO_MEDIUM("AutoMedium"),
    HIGH("High"),
    AUTO_HIGH("AutoHigh"),
    PROG("Prog"),
    UNKNOWN("Unknown");

    private final String mMetricValue;

    BitRate(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
