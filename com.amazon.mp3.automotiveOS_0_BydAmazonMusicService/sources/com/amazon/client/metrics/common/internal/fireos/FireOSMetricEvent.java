package com.amazon.client.metrics.common.internal.fireos;

import com.amazon.client.metrics.common.MetricEvent;

/* JADX INFO: loaded from: classes2.dex */
public class FireOSMetricEvent implements MetricEvent {
    private final com.amazon.client.metrics.MetricEvent mDelegateFirstPartyMetricEvent;

    public FireOSMetricEvent(com.amazon.client.metrics.MetricEvent metricEvent) {
        if (metricEvent == null) {
            throw new NullPointerException("DelegateMetricEvent may not be null");
        }
        this.mDelegateFirstPartyMetricEvent = metricEvent;
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addCounter(String str, double d) {
        this.mDelegateFirstPartyMetricEvent.addCounter(str, d);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void incrementCounter(String str, double d) {
        this.mDelegateFirstPartyMetricEvent.incrementCounter(str, d);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void startTimer(String str) {
        this.mDelegateFirstPartyMetricEvent.startTimer(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void stopTimer(String str) {
        this.mDelegateFirstPartyMetricEvent.stopTimer(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addString(String str, String str2) {
        this.mDelegateFirstPartyMetricEvent.addString(str, str2);
    }

    public String toString() {
        return this.mDelegateFirstPartyMetricEvent.toString();
    }

    public com.amazon.client.metrics.MetricEvent getDelegateMetricEvent() {
        return this.mDelegateFirstPartyMetricEvent;
    }
}
