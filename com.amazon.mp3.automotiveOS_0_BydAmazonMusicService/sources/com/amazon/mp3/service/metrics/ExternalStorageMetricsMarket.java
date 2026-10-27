package com.amazon.mp3.service.metrics;

import com.amazon.mp3.service.metrics.ExternalStorageMetrics;

/* JADX INFO: loaded from: classes4.dex */
public class ExternalStorageMetricsMarket implements ExternalStorageMetrics {
    private final BaseMetricsRecorder mRecorder;

    @Override // com.amazon.mp3.service.metrics.ExternalStorageMetrics
    public void recordDeviceChosen() {
    }

    @Override // com.amazon.mp3.service.metrics.ExternalStorageMetrics
    public void recordDeviceDownload(ExternalStorageMetrics.TrackType type) {
    }

    @Override // com.amazon.mp3.service.metrics.ExternalStorageMetrics
    public void recordSDCardChosen() {
    }

    @Override // com.amazon.mp3.service.metrics.ExternalStorageMetrics
    public void recordSDCardDownload(ExternalStorageMetrics.TrackType type) {
    }

    @Override // com.amazon.mp3.service.metrics.ExternalStorageMetrics
    public void recordSDCardInserted() {
    }

    @Override // com.amazon.mp3.service.metrics.ExternalStorageMetrics
    public void recordSDCardRemoved() {
    }

    public ExternalStorageMetricsMarket(final BaseMetricsRecorder recorder) {
        this.mRecorder = recorder;
    }
}
