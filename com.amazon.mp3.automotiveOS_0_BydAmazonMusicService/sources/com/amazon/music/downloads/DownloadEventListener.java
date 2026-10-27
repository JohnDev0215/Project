package com.amazon.music.downloads;

/* JADX INFO: loaded from: classes4.dex */
public interface DownloadEventListener {
    void onAdded(String str);

    void onCanceled(String str);

    void onDownloaded(String str, String str2);

    void onError(String str, ErrorReason errorReason);

    void onPaused(String str);

    void onProgress(String str, float f);

    void onResumed(String str);
}
