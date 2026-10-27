package com.amazon.mp3.service.metrics.mts;

import android.content.Context;
import com.amazon.mp3.service.metrics.minerva.MinervaMetricsFactory;
import com.amazon.music.metrics.mts.MTSEventHandler;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MTSMinervaMetricsProviderImpl implements MTSEventHandler.MTSMinervaMetricsProvider {
    private final Context mContext;

    public MTSMinervaMetricsProviderImpl(final Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.music.metrics.mts.MTSEventHandler.MTSMinervaMetricsProvider
    public void sendMetrics(final String metricGroupId, final String metricSchema, final List<Object> keyList) {
        MinervaMetricsFactory.record(metricGroupId, metricSchema, keyList.toArray(new Object[0]));
    }
}
