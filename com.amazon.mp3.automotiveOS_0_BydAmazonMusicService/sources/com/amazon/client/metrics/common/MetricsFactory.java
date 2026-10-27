package com.amazon.client.metrics.common;

/* JADX INFO: loaded from: classes2.dex */
public interface MetricsFactory {
    MetricEvent createMetricEvent(String str, String str2);

    void record(MetricEvent metricEvent);
}
