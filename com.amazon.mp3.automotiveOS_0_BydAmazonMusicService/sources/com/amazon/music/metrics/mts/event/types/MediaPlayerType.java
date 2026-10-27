package com.amazon.music.metrics.mts.event.types;

import PlaybackInterface.v1_0.AudioStreamFormat;
import UpsellInterface.v1_0.PlaybackMode;
import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum MediaPlayerType implements MetricValueType {
    HLS(AudioStreamFormat.HLS),
    BUY_DRM("BUYDRM"),
    LOCAL(PlaybackMode.NONE),
    PROGRESSIVE("PROG"),
    DASH("DASH"),
    UNKNOWN("UNKNOWN");

    private final String mMetricValue;

    MediaPlayerType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
