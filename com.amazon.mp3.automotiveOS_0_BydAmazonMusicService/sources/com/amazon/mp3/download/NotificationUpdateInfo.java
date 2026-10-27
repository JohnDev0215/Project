package com.amazon.mp3.download;

/* JADX INFO: loaded from: classes3.dex */
public class NotificationUpdateInfo {
    private float mLastPercentageDownloaded;
    private String mRequestId;

    public String getRequestId() {
        return this.mRequestId;
    }

    public void setRequestId(final String requestId) {
        this.mRequestId = requestId;
    }

    public float getLastPercentageDownloaded() {
        return this.mLastPercentageDownloaded;
    }

    public void setLastPercentageDownloaded(final float percentageDownloaded) {
        this.mLastPercentageDownloaded = percentageDownloaded;
    }
}
