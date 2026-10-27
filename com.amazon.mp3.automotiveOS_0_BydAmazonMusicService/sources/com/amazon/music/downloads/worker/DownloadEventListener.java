package com.amazon.music.downloads.worker;

/* JADX INFO: loaded from: classes4.dex */
public interface DownloadEventListener {
    void onAdded(String str);

    void onCanceled(String str);

    void onDownloaded(String str);

    void onError(String str, ErrorReason errorReason);

    void onError(String str, ErrorReason errorReason, int i);

    void onPaused(String str);

    void onProgress(String str, long j, long j2);

    void onResumed(String str);
}
