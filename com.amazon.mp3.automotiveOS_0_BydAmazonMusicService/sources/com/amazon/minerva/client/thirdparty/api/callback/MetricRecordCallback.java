package com.amazon.minerva.client.thirdparty.api.callback;

import com.amazon.minerva.client.thirdparty.api.MetricEvent;

/* JADX INFO: loaded from: classes3.dex */
public interface MetricRecordCallback {
    void onError(MetricRecordStatus metricRecordStatus, MetricEvent metricEvent);
}
