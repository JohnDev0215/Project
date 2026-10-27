package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum ActivityIdType implements MetricValueType {
    SEARCH_SUGGESTION_ID("searchSuggestionId");

    private final String metricValue;

    ActivityIdType(String str) {
        this.metricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.metricValue;
    }

    public static ActivityIdType fromString(String str) {
        if (str == null) {
            return null;
        }
        for (ActivityIdType activityIdType : values()) {
            if (str.equals(activityIdType.metricValue)) {
                return activityIdType;
            }
        }
        return null;
    }
}
