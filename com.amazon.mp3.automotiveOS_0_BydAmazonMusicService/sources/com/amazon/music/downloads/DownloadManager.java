package com.amazon.music.downloads;

import com.amazon.music.downloads.notification.DownloadListener;

/* JADX INFO: loaded from: classes4.dex */
public interface DownloadManager {
    void cancel(String str);

    RequestProvider getRequestProvider();

    void pause(String str);

    void registerDownloadListener(DownloadListener downloadListener);

    void resume(String str);

    void unregisterDownloadListener(DownloadListener downloadListener);
}
