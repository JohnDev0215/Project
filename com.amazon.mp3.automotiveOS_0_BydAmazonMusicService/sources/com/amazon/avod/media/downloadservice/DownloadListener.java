package com.amazon.avod.media.downloadservice;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadListener {
    void onCancel(DownloadRequest downloadRequest);

    void onDownloadProgress(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics, long j);

    void onFailure(DownloadRequest downloadRequest, Exception exc, DownloadStatistics downloadStatistics);

    void onSuccess(DownloadRequest downloadRequest, DownloadStatistics downloadStatistics);
}
