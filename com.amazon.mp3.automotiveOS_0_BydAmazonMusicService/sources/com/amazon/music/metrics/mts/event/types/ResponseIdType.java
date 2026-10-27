package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum ResponseIdType implements MetricValueType {
    INSTANT_SEARCH_ID("instantSearchId");

    private final String metricValue;

    ResponseIdType(String str) {
        this.metricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.metricValue;
    }

    public static ResponseIdType fromString(String str) {
        if (str == null) {
            return null;
        }
        for (ResponseIdType responseIdType : values()) {
            if (str.equals(responseIdType.metricValue)) {
                return responseIdType;
            }
        }
        return null;
    }
}
