package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;
import com.visualon.OSMPUtils.voMimeTypes;

/* JADX INFO: loaded from: classes4.dex */
public enum PlaybackEntityType implements MetricValueType {
    VIDEO(voMimeTypes.VOBASE_TYPE_VIDEO);

    private final String mReason;

    PlaybackEntityType(String str) {
        this.mReason = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mReason;
    }
}
