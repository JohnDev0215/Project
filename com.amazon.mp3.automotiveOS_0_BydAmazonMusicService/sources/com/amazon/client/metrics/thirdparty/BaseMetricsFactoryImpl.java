package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.internal.BasicMetricEvent;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseMetricsFactoryImpl implements MetricsFactory {
    protected abstract boolean shouldRecordMetrics();

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2) {
        return createMetricEvent(str, str2, MetricEventType.getDefault(), false);
    }

    public MetricEvent createMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        if (shouldRecordMetrics()) {
            return new BasicMetricEvent(str, str2, metricEventType, z);
        }
        return new NullMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2) {
        return createConcurrentMetricEvent(str, str2, MetricEventType.getDefault(), false);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public MetricEvent createConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        if (shouldRecordMetrics()) {
            return new ConcurrentMetricEvent(str, str2, metricEventType, z);
        }
        return new NullMetricEvent(str, str2, metricEventType);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public void record(MetricEvent metricEvent) {
        record(metricEvent, Priority.NORMAL);
    }

    public void record(MetricEvent metricEvent, Priority priority) {
        record(metricEvent, priority, Channel.ANONYMOUS);
    }
}
