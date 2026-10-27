package com.amazon.music.metrics.mts.event.types.uiinteraction;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum InteractionType implements MetricValueType {
    TAP("TAP"),
    SWIPE("SWIPE"),
    PINCH("PINCH"),
    LONG_PRESS("LONG_PRESS"),
    DRAG("DRAG"),
    DROP("DROP"),
    THREE_D_TOUCH("3D_TOUCH"),
    DOUBLE_TAP("DOUBLE_TAP"),
    REMOTE("REMOTE"),
    VOICE("VOICE"),
    THREE_P_VOICE("3P_VOICE"),
    TOGGLE("TOGGLE");

    private final String mMetricValue;

    InteractionType(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
