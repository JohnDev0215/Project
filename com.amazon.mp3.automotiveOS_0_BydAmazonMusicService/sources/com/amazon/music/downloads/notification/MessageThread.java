package com.amazon.music.downloads.notification;

import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class MessageThread implements DownloadCallbacks {
    private static final String TAG = DownloadCallbacks.class.getSimpleName();
    private Logger mLog = LoggerFactory.getLogger(TAG);
    private final ExecutorService mMessengerThreadService = new ThreadPoolExecutor(0, 1, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());
    private final CopyOnWriteArrayList<DownloadListener> mListenerList = new CopyOnWriteArrayList<>();

    MessageThread() {
    }

    public void registerDownloadListener(DownloadListener downloadListener) {
        this.mListenerList.add(downloadListener);
    }

    public void unregisterDownloadListener(DownloadListener downloadListener) {
        this.mListenerList.remove(downloadListener);
    }

    public void onStatusUpdate(final DownloadState downloadState, final String str, final Group group) {
        this.mLog.debug("Download status update for groupID " + str + "download state: " + downloadState);
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.notification.MessageThread.1
            @Override // java.lang.Runnable
            public void run() {
                for (DownloadListener downloadListener : MessageThread.this.mListenerList) {
                    if (downloadListener.hasRequestId(str)) {
                        downloadListener.onStatusUpdate(downloadState, str, group);
                    }
                }
            }
        });
    }

    public void onStatusUpdate(final DownloadState downloadState, final String str, final Item item) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.notification.MessageThread.2
            @Override // java.lang.Runnable
            public void run() {
                for (DownloadListener downloadListener : MessageThread.this.mListenerList) {
                    if (downloadListener.hasRequestId(str)) {
                        downloadListener.onStatusUpdate(downloadState, str, item);
                    }
                }
            }
        });
    }

    public void onProgressUpdate(final String str, final Group group, final float f) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.notification.MessageThread.3
            @Override // java.lang.Runnable
            public void run() {
                for (DownloadListener downloadListener : MessageThread.this.mListenerList) {
                    if (downloadListener.hasRequestId(str)) {
                        downloadListener.onProgressUpdate(str, group, f);
                    }
                }
            }
        });
    }

    public void onProgressUpdate(final String str, final Item item, final float f) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.notification.MessageThread.4
            @Override // java.lang.Runnable
            public void run() {
                for (DownloadListener downloadListener : MessageThread.this.mListenerList) {
                    if (downloadListener.hasRequestId(str)) {
                        downloadListener.onProgressUpdate(str, item, f);
                    }
                }
            }
        });
    }
}
