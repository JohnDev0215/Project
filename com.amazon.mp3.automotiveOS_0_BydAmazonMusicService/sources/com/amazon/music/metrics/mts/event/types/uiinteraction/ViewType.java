package com.amazon.music.metrics.mts.event.types.uiinteraction;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum ViewType implements MetricValueType {
    LANDSCAPE("LANDSCAPE"),
    PORTRAIT("PORTRAIT"),
    SQUARE("SQUARE"),
    UNDEFINED("UNDEFINED");

    private final String mMetricValue;

    ViewType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
