package com.amazon.client.metrics.thirdparty;

/* JADX INFO: loaded from: classes2.dex */
public interface MetricsFactory {
    MetricEvent createConcurrentMetricEvent(String str, String str2);

    MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z);

    MetricEvent createMetricEvent(String str, String str2);

    void record(MetricEvent metricEvent);

    void record(MetricEvent metricEvent, Priority priority, Channel channel);
}
