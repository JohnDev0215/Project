package com.amazon.mp3.download.service;

import android.widget.RemoteViews;
import androidx.annotation.DrawableRes;
import com.amazon.mp3.download.NotificationUpdateInfo;
import com.amazon.music.downloads.ErrorReason;
import com.amazon.music.downloads.notification.NotificationInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface NotificationView {
    void clearNotification();

    RemoteViews createCompleteNotificationView();

    RemoteViews createErrorNotificationView(String id, ErrorReason errorReason);

    RemoteViews createProgressNotificationView(String id, NotificationInfo notificationInfo, float percentageDownloaded, boolean isPaused);

    NotificationUpdateInfo getCurrentNotification();

    @DrawableRes
    int getDownloadCompleteResourceId();

    @DrawableRes
    int getDownloadingResourceId();

    @DrawableRes
    int getErrorResourceId();

    boolean isNotificationEnabled();

    void removeNotificationRunnable();

    void showCompletedNotification(RemoteViews notifView, int smallIconResId);

    void showNotification(RemoteViews notifView, int smallIconResId, boolean forceShow);
}
