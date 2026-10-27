package com.amazon.client.metrics.thirdparty;

/* JADX INFO: loaded from: classes2.dex */
public enum MetricEventType {
    AGGREGATING,
    AVERAGING;

    public static MetricEventType getDefault() {
        return AGGREGATING;
    }
}
