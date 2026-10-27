package com.amazon.mp3.service.metrics;

import com.amazon.mp3.service.metrics.ExternalStorageMetrics;

/* JADX INFO: loaded from: classes4.dex */
public class NoOpExternalStorageMetrics implements ExternalStorageMetrics {
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
}
