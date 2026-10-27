package com.amazon.mp3.service.metrics;

/* JADX INFO: loaded from: classes4.dex */
public interface IMetricsRecorder {
    ExternalStorageMetrics getExternalStorageMetrics();

    FirstTimeUseMetrics getFirstTimeUseMetrics();

    LoginMetrics getLoginMetrics();

    RecurrentUseMetrics getRecurrentUseMetrics();

    ReferralAndCampaignMetrics getReferralAndCampaignMetrics();

    void tabSelectedEvent(int titleId);
}
