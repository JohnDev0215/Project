package com.amazon.music.downloads.worker;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes4.dex */
class EventThread implements DownloadEventListener {
    private final ExecutorService mMessengerThreadService = Executors.newSingleThreadExecutor();
    private final List<DownloadEventListener> mListenerList = new CopyOnWriteArrayList();

    EventThread() {
    }

    public void registerDownloadEventListener(DownloadEventListener downloadEventListener) {
        this.mListenerList.add(downloadEventListener);
    }

    public void unregisterDownloadEventListener(DownloadEventListener downloadEventListener) {
        this.mListenerList.remove(downloadEventListener);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onAdded(final String str) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.worker.EventThread.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = EventThread.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((DownloadEventListener) it.next()).onAdded(str);
                }
            }
        });
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onProgress(final String str, final long j, final long j2) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.worker.EventThread.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = EventThread.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((DownloadEventListener) it.next()).onProgress(str, j, j2);
                }
            }
        });
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onPaused(final String str) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.worker.EventThread.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = EventThread.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((DownloadEventListener) it.next()).onPaused(str);
                }
            }
        });
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onCanceled(final String str) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.worker.EventThread.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = EventThread.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((DownloadEventListener) it.next()).onCanceled(str);
                }
            }
        });
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onResumed(final String str) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.worker.EventThread.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = EventThread.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((DownloadEventListener) it.next()).onResumed(str);
                }
            }
        });
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onError(final String str, final ErrorReason errorReason) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.worker.EventThread.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = EventThread.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((DownloadEventListener) it.next()).onError(str, errorReason);
                }
            }
        });
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onError(final String str, final ErrorReason errorReason, final int i) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.worker.EventThread.7
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = EventThread.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((DownloadEventListener) it.next()).onError(str, errorReason, i);
                }
            }
        });
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onDownloaded(final String str) {
        if (this.mListenerList.isEmpty()) {
            return;
        }
        this.mMessengerThreadService.execute(new Runnable() { // from class: com.amazon.music.downloads.worker.EventThread.8
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = EventThread.this.mListenerList.iterator();
                while (it.hasNext()) {
                    ((DownloadEventListener) it.next()).onDownloaded(str);
                }
            }
        });
    }
}
