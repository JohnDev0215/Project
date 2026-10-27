package com.amazon.client.metrics.common;

import com.amazon.client.metrics.common.internal.android.AndroidNullMetricEvent;
import com.amazon.client.metrics.common.internal.fireos.FireOSNullMetricEvent;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;

/* JADX INFO: loaded from: classes2.dex */
public class NullMetricEvent implements MetricEvent {
    private final MetricEvent mDelegateMetricEvent;

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void addString(String str, String str2) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void incrementCounter(String str, double d) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void startTimer(String str) {
    }

    @Override // com.amazon.client.metrics.common.MetricEvent
    public void stopTimer(String str) {
    }

    public NullMetricEvent(String str, String str2) {
        this(str, str2, MetricEventType.getDefault());
    }

    public NullMetricEvent(String str, String str2, MetricEventType metricEventType) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegateMetricEvent = new FireOSNullMetricEvent(new com.amazon.client.metrics.NullMetricEvent(str, str2, com.amazon.client.metrics.MetricEventType.valueOf(metricEventType.name())));
        } else {
            this.mDelegateMetricEvent = new AndroidNullMetricEvent(new com.amazon.client.metrics.thirdparty.NullMetricEvent(str, str2, com.amazon.client.metrics.thirdparty.MetricEventType.valueOf(metricEventType.name())));
        }
    }

    MetricEvent getDelegateMetricEvent() {
        return this.mDelegateMetricEvent;
    }
}
