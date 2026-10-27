package com.amazon.mp3.service.metrics;

/* JADX INFO: loaded from: classes4.dex */
public interface ExternalStorageMetrics {

    public enum TrackType {
        PRIME,
        OWNED
    }

    void recordDeviceChosen();

    void recordDeviceDownload(TrackType type);

    void recordSDCardChosen();

    void recordSDCardDownload(TrackType type);

    void recordSDCardInserted();

    void recordSDCardRemoved();
}
