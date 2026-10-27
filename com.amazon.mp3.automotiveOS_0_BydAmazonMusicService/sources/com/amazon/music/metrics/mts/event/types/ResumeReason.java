package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum ResumeReason implements MetricValueType {
    USER_START("userStart"),
    UNKNOWN_START("unknownStart"),
    SYSTEM_START("systemStart"),
    TRACK_SCRUB("trackScrub");

    private final String mReason;

    ResumeReason(String str) {
        this.mReason = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mReason;
    }
}
