package com.amazon.minerva.client.common.internal.fireos;

import android.content.Context;
import com.amazon.minerva.client.api.AmazonMinerva;
import com.amazon.minerva.client.api.AmazonMinervaClientBuilder;
import com.amazon.minerva.client.common.api.MetricEvent;
import com.amazon.minerva.client.common.internal.AmazonMinervaAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class FireOSAmazonMinervaAdapter implements AmazonMinervaAdapter {
    private AmazonMinerva mMinervaClient;

    public FireOSAmazonMinervaAdapter(Context context, String str) {
        this.mMinervaClient = createBuilder(context, str).build();
    }

    protected FireOSAmazonMinervaAdapter() {
    }

    protected AmazonMinervaClientBuilder createBuilder(Context context, String str) {
        return AmazonMinervaClientBuilder.standard(context).withRegion(str);
    }

    @Override // com.amazon.minerva.client.common.internal.AmazonMinervaAdapter
    public void record(MetricEvent metricEvent) {
        this.mMinervaClient.record(((FireOSMetricEventAdapter) metricEvent.getMetricEventDelegate()).getFireOsMetricEvent());
    }
}
