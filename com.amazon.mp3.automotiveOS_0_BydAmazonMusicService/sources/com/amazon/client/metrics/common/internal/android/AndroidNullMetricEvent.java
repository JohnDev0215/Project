package com.amazon.client.metrics.common.internal.android;

import com.amazon.client.metrics.thirdparty.NullMetricEvent;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidNullMetricEvent extends AndroidMetricEvent {
    private final NullMetricEvent mDelegateThirdPartyNullMetricEvent;

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void addString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void incrementCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void startTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent, com.amazon.client.metrics.common.MetricEvent
    public void stopTimer(String str) {
    }

    public AndroidNullMetricEvent(NullMetricEvent nullMetricEvent) {
        super(nullMetricEvent);
        this.mDelegateThirdPartyNullMetricEvent = nullMetricEvent;
    }

    @Override // com.amazon.client.metrics.common.internal.android.AndroidMetricEvent
    public NullMetricEvent getDelegateMetricEvent() {
        return this.mDelegateThirdPartyNullMetricEvent;
    }
}
