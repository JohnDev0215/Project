package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum InitiationReason implements MetricValueType {
    USER_START("userStart");

    private final String metricValue;

    InitiationReason(String str) {
        this.metricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.metricValue;
    }
}
