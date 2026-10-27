package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum PlaybackSourceType implements MetricValueType {
    STREAM("stream");

    private final String mReason;

    PlaybackSourceType(String str) {
        this.mReason = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mReason;
    }
}
