package com.amazon.music.downloads.worker;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class Downloader {
    public static final String TAG = "Downloader";
    private final Configuration mConfiguration;
    private final ThreadPoolExecutor mThreadPoolExecutor;
    Logger LOG = LoggerFactory.getLogger(TAG);
    private final Map<String, DownloadableItem> mDownloadMap = Collections.synchronizedMap(new HashMap());
    private final EventThread mEventThread = new EventThread();
    private final DownloadEventListener mInternalListener = new DownloadEventListener() { // from class: com.amazon.music.downloads.worker.Downloader.1
        @Override // com.amazon.music.downloads.worker.DownloadEventListener
        public void onAdded(String str) {
            Downloader.this.mEventThread.onAdded(str);
        }

        @Override // com.amazon.music.downloads.worker.DownloadEventListener
        public void onProgress(String str, long j, long j2) {
            Downloader.this.mEventThread.onProgress(str, j, j2);
        }

        @Override // com.amazon.music.downloads.worker.DownloadEventListener
        public void onPaused(String str) {
            Downloader.this.mEventThread.onPaused(str);
        }

        @Override // com.amazon.music.downloads.worker.DownloadEventListener
        public void onCanceled(String str) {
            Downloader.this.mDownloadMap.remove(str);
            Downloader.this.mEventThread.onCanceled(str);
        }

        @Override // com.amazon.music.downloads.worker.DownloadEventListener
        public void onResumed(String str) {
            Downloader.this.mEventThread.onResumed(str);
        }

        @Override // com.amazon.music.downloads.worker.DownloadEventListener
        public void onError(String str, ErrorReason errorReason) {
            Downloader.this.mDownloadMap.remove(str);
            Downloader.this.mEventThread.onError(str, errorReason);
        }

        @Override // com.amazon.music.downloads.worker.DownloadEventListener
        public void onError(String str, ErrorReason errorReason, int i) {
            Downloader.this.mDownloadMap.remove(str);
            Downloader.this.mEventThread.onError(str, errorReason);
        }

        @Override // com.amazon.music.downloads.worker.DownloadEventListener
        public void onDownloaded(String str) {
            Downloader.this.mDownloadMap.remove(str);
            Downloader.this.mEventThread.onDownloaded(str);
        }
    };

    public Downloader(Configuration configuration) {
        this.mConfiguration = configuration;
        int parallelDownloadsCount = configuration.getParallelDownloadsCount();
        if (parallelDownloadsCount < 1) {
            throw new IllegalArgumentException("Parallel download count should be at least 1");
        }
        this.mThreadPoolExecutor = new ThreadPoolExecutor(parallelDownloadsCount, parallelDownloadsCount, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public synchronized void add(String str, DownloadableItem downloadableItem) {
        if (str != null && downloadableItem != null) {
            if (!this.mDownloadMap.containsKey(str)) {
                this.mDownloadMap.put(str, downloadableItem);
                addToQueue(str, downloadableItem);
                this.mInternalListener.onAdded(str);
            }
        }
    }

    public synchronized void cancel(String str) {
        DownloadableItem downloadableItemRemove = this.mDownloadMap.remove(str);
        if (downloadableItemRemove == null) {
            return;
        }
        if (downloadableItemRemove.isPaused()) {
            File file = new File(downloadableItemRemove.getDestinationURI());
            if (file.exists() && !file.delete()) {
                this.LOG.info("Unable to delete paused file");
            }
        }
        downloadableItemRemove.setIsCanceled(true);
        this.mInternalListener.onCanceled(str);
    }

    public synchronized void pause(String str) {
        DownloadableItem downloadableItem = this.mDownloadMap.get(str);
        if (downloadableItem == null) {
            return;
        }
        downloadableItem.setIsPaused(true);
        this.mInternalListener.onPaused(str);
    }

    public synchronized void resume(String str) {
        DownloadableItem downloadableItem = this.mDownloadMap.get(str);
        if (downloadableItem != null && downloadableItem.isPaused()) {
            downloadableItem.setIsPaused(false);
            if (!downloadableItem.isQueued() && !downloadableItem.isProcessing()) {
                addToQueue(str, downloadableItem);
            }
            this.mInternalListener.onResumed(str);
        }
    }

    public void registerDownloadEventListener(DownloadEventListener downloadEventListener) {
        this.mEventThread.registerDownloadEventListener(downloadEventListener);
    }

    public void unregisterDownloadEventListener(DownloadEventListener downloadEventListener) {
        this.mEventThread.unregisterDownloadEventListener(downloadEventListener);
    }

    private void addToQueue(String str, DownloadableItem downloadableItem) {
        DownloadRunnable downloadRunnable = new DownloadRunnable(this.mConfiguration, str, downloadableItem, this.mInternalListener);
        downloadableItem.setIsQueued(true);
        try {
            this.mThreadPoolExecutor.execute(downloadRunnable);
        } catch (RejectedExecutionException unused) {
            this.LOG.error("Unable to add download request with id {}", str);
        }
    }
}
