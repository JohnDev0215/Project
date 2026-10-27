package com.amazon.podcast.downloads;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.amazon.music.downloads.worker.DownloadEventListener;
import com.amazon.music.downloads.worker.DownloadableItem;
import com.amazon.music.downloads.worker.Downloader;
import com.amazon.music.downloads.worker.ErrorReason;
import com.amazon.podcast.Podcast;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadService extends Service implements DownloadEventListener {
    private static final Logger logger = LoggerFactory.getLogger("DownloadService");
    private Context context;
    private Set<String> currentDownloads = new HashSet();
    private long downloadFileSize;
    private Downloader downloader;
    private Downloads downloads;
    private long fileSize;
    private DownloadNotifications notifications;

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        this.downloads = Podcast.getDownloads();
        Downloader downloader = new Downloader(new DownloadConfiguration());
        this.downloader = downloader;
        downloader.registerDownloadEventListener(this);
        this.notifications = new DownloadNotifications(this.context);
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        if (intent == null) {
            return 2;
        }
        String stringExtra = intent.getStringExtra("action");
        Logger logger2 = logger;
        logger2.debug("Received action {}", stringExtra);
        if (AsmString.METHOD_FIREBASE_PERF_TRACE_START.equals(stringExtra)) {
            String stringExtra2 = intent.getStringExtra("id");
            String stringExtra3 = intent.getStringExtra("destinationUri");
            String stringExtra4 = intent.getStringExtra("audioUri");
            logger2.debug("requesting download for {}", stringExtra2);
            logger2.debug("destination uri {}", stringExtra3);
            logger2.debug("audio uri {}", stringExtra4);
            DownloadableItem downloadableItem = new DownloadableItem(stringExtra4, stringExtra3);
            logger2.debug("Adding download {} to the queue", stringExtra2);
            this.downloader.add(stringExtra2, downloadableItem);
        } else if ("cancel".equals(stringExtra)) {
            String stringExtra5 = intent.getStringExtra("id");
            logger2.debug("Cancelling download {}", stringExtra5);
            this.downloader.cancel(stringExtra5);
        } else if ("pause".equals(stringExtra)) {
            this.downloader.pause(intent.getStringExtra("id"));
        } else if ("resume".equals(stringExtra)) {
            this.downloader.resume(intent.getStringExtra("id"));
        }
        return 2;
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onAdded(final String id) {
        this.downloads.onAdded(id);
        this.currentDownloads.add(id);
        int ongoingNotificationId = this.notifications.getOngoingNotificationId();
        DownloadNotifications downloadNotifications = this.notifications;
        startForeground(ongoingNotificationId, downloadNotifications.getDownloadNotification(id, downloadNotifications.createLoadingNotificationView()));
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onProgress(final String id, final long bytesDownloaded, final long fileSizeInBytes) {
        this.downloads.onProgress(id, bytesDownloaded, fileSizeInBytes);
        this.notifications.notifyOnProgress(id, bytesDownloaded, fileSizeInBytes);
        this.downloadFileSize = bytesDownloaded;
        this.fileSize = fileSizeInBytes;
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onPaused(final String id) {
        this.downloads.onPaused(id);
        this.notifications.notifyOnPause(id, this.downloadFileSize, this.fileSize);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onCanceled(final String id) {
        this.downloads.onCanceled(id);
        this.currentDownloads.remove(id);
        stopForeground(false);
        this.notifications.clear();
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onResumed(final String id) {
        this.downloads.onResumed(id);
        this.notifications.notifyOnProgress(id, this.downloadFileSize, this.fileSize);
        int ongoingNotificationId = this.notifications.getOngoingNotificationId();
        DownloadNotifications downloadNotifications = this.notifications;
        startForeground(ongoingNotificationId, downloadNotifications.getDownloadNotification(id, downloadNotifications.createLoadingNotificationView()));
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onError(final String id, final ErrorReason errorReason) {
        this.downloads.onError(id, errorReason);
        this.currentDownloads.remove(id);
        stopForeground(false);
        this.notifications.notifyOnError(id);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onError(final String id, final ErrorReason errorReason, final int errorCode) {
        this.downloads.onError(id, errorReason, errorCode);
        this.currentDownloads.remove(id);
        stopForeground(false);
        this.notifications.notifyOnError(id);
    }

    @Override // com.amazon.music.downloads.worker.DownloadEventListener
    public void onDownloaded(final String id) {
        this.downloads.onDownloaded(id);
        this.currentDownloads.remove(id);
        if (this.currentDownloads.isEmpty()) {
            stopForeground(false);
        }
        this.notifications.notifyCompleted();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.downloader.unregisterDownloadEventListener(this);
        this.notifications.clear();
    }
}
