package com.amazon.mp3.service.metrics;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseMetricsRecorder implements IMetricsRecorder {
    private static IMetricsRecorder sMetricsRecorder;
    private final String SIDELOADED_TRACKS_DETECTED_COUNTER_NAME = "NumberOfTracks";
    private final String SIDELOADED_TRACKS_DETECTED_METRIC_NAME = "SideLoadedContent";
    private ExternalStorageMetrics mExternalStorageMetrics = new NoOpExternalStorageMetrics();
    private FirstTimeUseMetrics mFirstTimeUseMetrics = new NoOpFirstTimeUseMetrics();
    private RecurrentUseMetrics mRecurrentUseMetrics = new NoOpRecurrentUseMetrics();
    private LoginMetrics mLoginMetrics = new NoOpLoginMetrics();
    private ReferralAndCampaignMetrics mReferralAndCampaignMetrics = new NoOpReferralMetrics();

    public interface MetricEventWrapper {
        void incrementCounter(String name, int value);
    }

    public interface MetricsFactoryWrapper {
        MetricEventWrapper createMetricEvent(String programName, String sourceName);

        void record(MetricEventWrapper metric);
    }

    protected abstract MetricsFactoryWrapper getMetricsFactory();

    public String getMetricsProgramName() {
        return "Amazon_MP3_Android";
    }

    @Override // com.amazon.mp3.service.metrics.IMetricsRecorder
    public ExternalStorageMetrics getExternalStorageMetrics() {
        return this.mExternalStorageMetrics;
    }

    @Override // com.amazon.mp3.service.metrics.IMetricsRecorder
    public FirstTimeUseMetrics getFirstTimeUseMetrics() {
        return this.mFirstTimeUseMetrics;
    }

    @Override // com.amazon.mp3.service.metrics.IMetricsRecorder
    public RecurrentUseMetrics getRecurrentUseMetrics() {
        return this.mRecurrentUseMetrics;
    }

    @Override // com.amazon.mp3.service.metrics.IMetricsRecorder
    public LoginMetrics getLoginMetrics() {
        return this.mLoginMetrics;
    }

    @Override // com.amazon.mp3.service.metrics.IMetricsRecorder
    public ReferralAndCampaignMetrics getReferralAndCampaignMetrics() {
        return this.mReferralAndCampaignMetrics;
    }

    protected void setExternalStorageMetrics(ExternalStorageMetrics externalStorageMetrics) {
        this.mExternalStorageMetrics = externalStorageMetrics;
    }

    protected void setFirstTimeUseMetrics(FirstTimeUseMetrics firstTimeUseMetrics) {
        this.mFirstTimeUseMetrics = firstTimeUseMetrics;
    }

    protected void setRecurrentUseMetrics(RecurrentUseMetrics recurrentUseMetrics) {
        this.mRecurrentUseMetrics = recurrentUseMetrics;
    }

    protected void setLoginMetrics(LoginMetrics loginMetrics) {
        this.mLoginMetrics = loginMetrics;
    }

    public static synchronized IMetricsRecorder getMetricsRecorder(Context context) {
        if (sMetricsRecorder == null) {
            sMetricsRecorder = new MetricsRecorderMarket(context);
        }
        return sMetricsRecorder;
    }
}
