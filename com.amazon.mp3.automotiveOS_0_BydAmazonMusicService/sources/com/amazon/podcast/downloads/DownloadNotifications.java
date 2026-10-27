package com.amazon.podcast.downloads;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;

/* JADX INFO: loaded from: classes5.dex */
final class DownloadNotifications {
    private final Context context;
    private NotificationManager notificationManager;

    public int getOngoingNotificationId() {
        return 9475796;
    }

    public DownloadNotifications(final Context context) {
        this.context = context;
        this.notificationManager = (NotificationManager) context.getSystemService("notification");
        createNotificationChannel();
    }

    public Notification getDownloadNotification(final String episodeId, final RemoteViews notifView) {
        return new NotificationCompat.Builder(this.context, "android_podcast_download_notification").setSmallIcon(R$drawable.podcast_notification_icn_downloading).setContentTitle(getTitle(episodeId)).setContent(notifView).setWhen(System.currentTimeMillis()).build();
    }

    public void notifyOnProgress(final String episodeId, final long bytesDownloaded, final long fileSizeInBytes) {
        RemoteViews remoteViewsCreateBasicNotificationView = createBasicNotificationView(episodeId, bytesDownloaded, fileSizeInBytes);
        remoteViewsCreateBasicNotificationView.setViewVisibility(R$id.podcast_resume_button, 8);
        PendingIntent pausePendingIntent = getPausePendingIntent(episodeId);
        int i = R$id.podcast_pause_button;
        remoteViewsCreateBasicNotificationView.setViewVisibility(i, 0);
        remoteViewsCreateBasicNotificationView.setOnClickPendingIntent(i, pausePendingIntent);
        PendingIntent cancelPendingIntent = getCancelPendingIntent(episodeId);
        int i2 = R$id.podcast_cancel_button;
        remoteViewsCreateBasicNotificationView.setOnClickPendingIntent(i2, cancelPendingIntent);
        remoteViewsCreateBasicNotificationView.setViewVisibility(i2, 0);
        this.notificationManager.notify(getOngoingNotificationId(), getDownloadNotification(remoteViewsCreateBasicNotificationView));
    }

    public void notifyOnPause(final String episodeId, final long bytesDownloaded, final long fileSizeInBytes) {
        RemoteViews remoteViewsCreateBasicNotificationView = createBasicNotificationView(episodeId, bytesDownloaded, fileSizeInBytes);
        remoteViewsCreateBasicNotificationView.setViewVisibility(R$id.podcast_pause_button, 8);
        PendingIntent resumePendingIntent = getResumePendingIntent(episodeId);
        int i = R$id.podcast_resume_button;
        remoteViewsCreateBasicNotificationView.setViewVisibility(i, 0);
        remoteViewsCreateBasicNotificationView.setOnClickPendingIntent(i, resumePendingIntent);
        this.notificationManager.notify(getOngoingNotificationId(), getDownloadNotification(remoteViewsCreateBasicNotificationView));
    }

    public void notifyOnError(final String episodeId) {
        RemoteViews remoteViews = new RemoteViews(this.context.getPackageName(), R$layout.podcast_error_download_notif_layout);
        remoteViews.setTextViewText(R$id.podcast_download_title, this.context.getString(R$string.podcast_download_failed_notif_title));
        this.notificationManager.notify(getOngoingNotificationId(), getDownloadNotification(episodeId, remoteViews));
    }

    public void notifyCompleted() {
        this.notificationManager.notify(getOngoingNotificationId(), getDownloadCompletedNotification(new RemoteViews(this.context.getPackageName(), R$layout.podcast_download_completed_notif_layout)));
    }

    public void clear() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.notificationManager.cancelAll();
        }
    }

    public RemoteViews createLoadingNotificationView() {
        RemoteViews remoteViews = new RemoteViews(this.context.getPackageName(), R$layout.podcast_download_notif_layout);
        remoteViews.setViewVisibility(R$id.podcast_progress_bar, 8);
        remoteViews.setViewVisibility(R$id.podcast_progress_percent, 8);
        remoteViews.setViewVisibility(R$id.podcast_download_notification_progressbar, 0);
        remoteViews.setTextViewText(R$id.podcast_download_title, this.context.getString(R$string.podcast_downloading_text));
        remoteViews.setViewVisibility(R$id.podcast_resume_button, 8);
        remoteViews.setViewVisibility(R$id.podcast_pause_button, 8);
        return remoteViews;
    }

    private RemoteViews createBasicNotificationView(final String episodeId, final long bytesDownloaded, final long fileSizeInBytes) {
        RemoteViews remoteViews = new RemoteViews(this.context.getPackageName(), R$layout.podcast_download_notif_layout);
        int i = R$id.podcast_progress_bar;
        remoteViews.setProgressBar(i, (int) fileSizeInBytes, (int) bytesDownloaded, false);
        remoteViews.setViewVisibility(i, 0);
        remoteViews.setViewVisibility(R$id.podcast_download_notification_progressbar, 8);
        int i2 = R$id.podcast_progress_percent;
        remoteViews.setViewVisibility(i2, 0);
        remoteViews.setTextViewText(R$id.podcast_progress_status, this.context.getString(R$string.podcast_downloading_text));
        remoteViews.setTextViewText(i2, this.context.getString(R$string.podcast_download_percent, Integer.valueOf((int) ((bytesDownloaded / fileSizeInBytes) * 100.0d))));
        remoteViews.setTextViewText(R$id.podcast_download_title, getTitle(episodeId));
        return remoteViews;
    }

    private Notification getDownloadNotification(final RemoteViews remoteView) {
        return new NotificationCompat.Builder(this.context, "android_podcast_download_notification").setSmallIcon(R$drawable.podcast_notification_icn_downloading).setContent(remoteView).setWhen(System.currentTimeMillis()).build();
    }

    private Notification getDownloadCompletedNotification(final RemoteViews notifView) {
        return new NotificationCompat.Builder(this.context, "android_podcast_download_notification").setSmallIcon(R$drawable.podcast_notification_icn_complete).setContent(notifView).setWhen(System.currentTimeMillis()).build();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("android_podcast_download_notification", "Downloads", 2);
            this.notificationManager.createNotificationChannel(notificationChannel);
            notificationChannel.setLockscreenVisibility(1);
        }
    }

    private PendingIntent getPausePendingIntent(final String episodeId) {
        return PendingIntent.getService(this.context, 0, new Intent(this.context, (Class<?>) DownloadService.class).putExtra("action", "pause").putExtra("id", episodeId), 201326592);
    }

    private PendingIntent getCancelPendingIntent(final String episodeId) {
        return PendingIntent.getService(this.context, 1, new Intent(this.context, (Class<?>) DownloadService.class).putExtra("action", "cancel").putExtra("id", episodeId), 201326592);
    }

    private PendingIntent getResumePendingIntent(final String episodeId) {
        return PendingIntent.getService(this.context, 2, new Intent(this.context, (Class<?>) DownloadService.class).putExtra("action", "resume").putExtra("id", episodeId), 201326592);
    }

    private String getTitle(final String id) {
        Download download;
        if (id == null || (download = Podcast.getAppSync().download().read(this.context, id)) == null) {
            return null;
        }
        return download.getTitle();
    }
}
