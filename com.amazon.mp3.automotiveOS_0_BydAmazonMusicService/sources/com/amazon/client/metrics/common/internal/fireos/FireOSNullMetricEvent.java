package com.amazon.client.metrics.common.internal.fireos;

import com.amazon.client.metrics.NullMetricEvent;

/* JADX INFO: loaded from: classes2.dex */
public class FireOSNullMetricEvent extends FireOSMetricEvent {
    private final NullMetricEvent mDelegateFirstPartyNullMetricEvent;

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void incrementCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void startTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void stopTimer(String str) {
    }

    public FireOSNullMetricEvent(NullMetricEvent nullMetricEvent) {
        super(nullMetricEvent);
        this.mDelegateFirstPartyNullMetricEvent = nullMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent
    public NullMetricEvent getDelegateMetricEvent() {
        return this.mDelegateFirstPartyNullMetricEvent;
    }
}
