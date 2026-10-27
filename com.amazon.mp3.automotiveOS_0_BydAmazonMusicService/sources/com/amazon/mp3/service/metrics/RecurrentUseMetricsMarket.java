package com.amazon.mp3.service.metrics;

import com.amazon.mp3.service.metrics.minerva.MinervaMetricsFactory;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class RecurrentUseMetricsMarket implements RecurrentUseMetrics {
    private static final String TAG = "RecurrentUseMetricsMarket";
    private final BaseMetricsRecorder mRecorder;

    public RecurrentUseMetricsMarket(BaseMetricsRecorder recorder) {
        this.mRecorder = recorder;
    }

    @Override // com.amazon.mp3.service.metrics.RecurrentUseMetrics
    public void recordUserSignedIn() {
        MinervaMetricsFactory.record("vrzx4wil", "5w32/2/02330400", "Counter", 1L, "MetricName", "UserSignedIn");
        Log.verbose(TAG, "UserSignedIn");
    }

    @Override // com.amazon.mp3.service.metrics.RecurrentUseMetrics
    public void recordUserSignedOut() {
        MinervaMetricsFactory.record("vrzx4wil", "6ad6/2/02330400", "Counter", 1L, "MetricName", "UserSignedOut");
        Log.verbose(TAG, "UserSignedOut");
    }
}
