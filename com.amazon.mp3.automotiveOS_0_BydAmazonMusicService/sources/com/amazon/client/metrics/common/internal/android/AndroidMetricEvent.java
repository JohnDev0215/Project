package com.amazon.client.metrics.common.internal.android;

import com.amazon.client.metrics.common.MetricEvent;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidMetricEvent implements MetricEvent {
    private final com.amazon.client.metrics.thirdparty.MetricEvent mThirdPartyDelegateMetricEvent;

    public AndroidMetricEvent(com.amazon.client.metrics.thirdparty.MetricEvent metricEvent) {
        if (metricEvent == null) {
            throw new NullPointerException("DelegateMetricEvent may not be null");
        }
        this.mThirdPartyDelegateMetricEvent = metricEvent;
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addCounter(String str, double d) {
        this.mThirdPartyDelegateMetricEvent.addCounter(str, d);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void incrementCounter(String str, double d) {
        this.mThirdPartyDelegateMetricEvent.incrementCounter(str, d);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void startTimer(String str) {
        this.mThirdPartyDelegateMetricEvent.startTimer(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void stopTimer(String str) {
        this.mThirdPartyDelegateMetricEvent.stopTimer(str);
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addString(String str, String str2) {
        this.mThirdPartyDelegateMetricEvent.addString(str, str2);
    }

    public String toString() {
        return this.mThirdPartyDelegateMetricEvent.toString();
    }

    public com.amazon.client.metrics.thirdparty.MetricEvent getDelegateMetricEvent() {
        return this.mThirdPartyDelegateMetricEvent;
    }
}
