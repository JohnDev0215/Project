package com.byd.vrdownload.model;

/* JADX INFO: loaded from: classes.dex */
public interface IProfile {
    void onDownload(ProfileDownloadTask profileDownloadTask);

    void onDownloadComplete(ProfileDownloadTask profileDownloadTask);

    void onProgress(ProfileDownloadTask profileDownloadTask, long j, long j2);
}
