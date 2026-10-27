package com.amazon.avod.content.backgrounddownload;

/* JADX INFO: loaded from: classes2.dex */
interface DownloadTask {
    long getDownloadedSizeBytes();

    float getPlayableDownloadRatio();

    long getTotalSizeBytes();

    void start();

    void stop();
}
