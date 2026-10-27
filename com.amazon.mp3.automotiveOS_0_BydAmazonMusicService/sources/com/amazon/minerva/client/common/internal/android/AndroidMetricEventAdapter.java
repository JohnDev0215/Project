package com.amazon.minerva.client.common.internal.android;

import com.amazon.minerva.client.common.internal.MetricEventAdapter;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidMetricEventAdapter implements MetricEventAdapter {
    private MetricEvent mAndroidMetricEvent;

    public AndroidMetricEventAdapter(String str, String str2) {
        this.mAndroidMetricEvent = new MetricEvent(str, str2);
    }

    protected AndroidMetricEventAdapter() {
    }

    @Override // com.amazon.minerva.client.common.internal.MetricEventAdapter
    public void addDouble(String str, double d) {
        this.mAndroidMetricEvent.addDouble(str, d);
    }

    @Override // com.amazon.minerva.client.common.internal.MetricEventAdapter
    public void addString(String str, String str2) {
        this.mAndroidMetricEvent.addString(str, str2);
    }

    public MetricEvent getAndroidMetricEvent() {
        return this.mAndroidMetricEvent;
    }
}
