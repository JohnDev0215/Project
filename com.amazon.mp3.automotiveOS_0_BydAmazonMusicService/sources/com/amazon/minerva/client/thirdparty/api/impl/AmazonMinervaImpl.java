package com.amazon.minerva.client.thirdparty.api.impl;

import android.content.Context;
import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.AmazonMinerva;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;

/* JADX INFO: loaded from: classes3.dex */
public class AmazonMinervaImpl implements AmazonMinerva {
    protected AmazonMinervaAndroidClient mAmazonMinervaClient;

    public AmazonMinervaImpl(Context context, MinervaServiceAndroidAdapter minervaServiceAndroidAdapter, String str, String str2) {
        this.mAmazonMinervaClient = new AmazonMinervaAndroidClient(context, minervaServiceAndroidAdapter, str, str2);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AmazonMinerva
    public void record(MetricEvent metricEvent) {
        this.mAmazonMinervaClient.record(metricEvent);
    }

    @Override // com.amazon.minerva.client.thirdparty.api.AmazonMinerva
    public void shutdown() {
        this.mAmazonMinervaClient.shutdown();
    }
}
