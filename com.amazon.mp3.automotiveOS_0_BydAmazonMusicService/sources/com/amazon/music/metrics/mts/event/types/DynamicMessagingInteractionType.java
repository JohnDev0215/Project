package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum DynamicMessagingInteractionType implements MetricValueType {
    DYNAMIC_MESSAGES_REQUESTED("dynamicMessagesRequested"),
    DYNAMIC_MESSAGES_RECEIVED("dynamicMessagesReceived"),
    DYNAMIC_MESSAGING_ERROR("dynamicMessagingError"),
    DYNAMIC_MESSAGING_API_LATENCY("dynamicMessagingApiLatency"),
    DYNAMIC_MESSAGING_RENDER_LATENCY("dynamicMessagingRenderLatency");

    private final String metricValue;

    DynamicMessagingInteractionType(String str) {
        this.metricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.metricValue;
    }
}
