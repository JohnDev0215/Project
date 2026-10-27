package com.amazon.client.metrics;

/* JADX INFO: loaded from: classes2.dex */
public class NullMetricEvent implements MetricEvent {
    private final MetricEventType mMetricEventType;
    private final String mProgram;
    private final String mSource;

    @Override // com.amazon.client.metrics.MetricEvent
    public void addCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.MetricEvent
    public void addString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.MetricEvent
    public void addTimer(String str, double d) {
    }

    @Override // com.amazon.client.metrics.MetricEvent
    public void clear() {
    }

    @Override // com.amazon.client.metrics.MetricEvent
    public void incrementCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.MetricEvent
    public void startTimer(String str) {
    }

    @Override // com.amazon.client.metrics.MetricEvent
    public void stopTimer(String str) {
    }

    public NullMetricEvent(String str, String str2, MetricEventType metricEventType) {
        this.mProgram = str;
        this.mSource = str2;
        this.mMetricEventType = metricEventType;
    }
}
