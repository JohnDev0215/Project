package com.amazon.minerva.client.common.internal.fireos;

import com.amazon.minerva.client.api.MetricEvent;
import com.amazon.minerva.client.common.internal.MetricEventAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class FireOSMetricEventAdapter implements MetricEventAdapter {
    private MetricEvent mFireOsMetricEvent;

    public FireOSMetricEventAdapter(String str, String str2) {
        this.mFireOsMetricEvent = new MetricEvent(str, str2);
    }

    public FireOSMetricEventAdapter() {
    }

    @Override // com.amazon.minerva.client.common.internal.MetricEventAdapter
    public void addDouble(String str, double d) {
        this.mFireOsMetricEvent.addDouble(str, d);
    }

    @Override // com.amazon.minerva.client.common.internal.MetricEventAdapter
    public void addString(String str, String str2) {
        this.mFireOsMetricEvent.addString(str, str2);
    }

    public MetricEvent getFireOsMetricEvent() {
        return this.mFireOsMetricEvent;
    }
}
