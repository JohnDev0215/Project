package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum LyricsViewType implements MetricValueType {
    LANDSCAPE("landscape"),
    PORTRAIT_PREVIEW("portrait_preview"),
    PORTRAIT_MAXIMIZED("portrait_maximized"),
    PORTRAIT_CLOSED("portrait_closed"),
    LANDSCAPE_PREVIEW("landscape_preview"),
    LANDSCAPE_CLOSED("landscape_closed"),
    FULL_SCREEN("full_screen");

    private final String mMetricValue;

    LyricsViewType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
