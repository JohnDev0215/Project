package com.amazon.mp3.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.DownloadNotificationService;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;

/* JADX INFO: loaded from: classes4.dex */
public class ServiceUtil {
    private static final String TAG = "ServiceUtil";

    public static void startForegroundService(final Context context, final Intent intent) {
        if (PlatformUtil.isVersionOrGreater(31)) {
            if (AmazonApplication.isApplicationForeground()) {
                Log.debug(TAG, "Starting foreground service for: " + getIntentName(intent));
                context.startForegroundService(intent);
                return;
            }
            return;
        }
        if (PlatformUtil.isVersionOrGreater(26) && AmazonApplication.isApplicationBackground()) {
            Log.debug(TAG, "Starting foreground service for: " + getIntentName(intent));
            context.startForegroundService(intent);
            return;
        }
        startService(context, intent);
    }

    public static void startService(final Context context, final Intent intent) {
        try {
            context.startService(intent);
        } catch (Exception unused) {
            Log.error(TAG, "Start service failed for: " + getIntentName(intent));
        }
    }

    public static void startNotification(final Context context, final Service service) {
        if (PlatformUtil.isVersionOrGreater(26)) {
            Log.debug(TAG, "Starting foreground notification for: " + service.getClass().getSimpleName());
            service.startForeground(3817, createNotification(context));
        }
    }

    public static void startNotification(final Context context, final Service service, final int notificationId, final Notification notification) {
        if (PlatformUtil.isVersionOrGreater(26)) {
            Log.debug(TAG, "Starting foreground notification for: " + service.getClass().getSimpleName());
            service.startForeground(notificationId, notification);
        }
    }

    public static void wakeUpDownloadNotificationService(Context mContext) {
        Intent intent = new Intent(mContext.getApplicationContext(), (Class<?>) DownloadNotificationService.class);
        intent.setAction("com.amazon.music.downloads.notification.SHOW");
        startForegroundService(mContext, intent);
    }

    private static String getIntentName(final Intent intent) {
        return (intent == null || intent.getComponent() == null) ? "Null Intent" : intent.getComponent().getShortClassName();
    }

    private static Notification createNotification(final Context context) {
        createNotificationChannel(context);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "android_music_background_notification");
        builder.setVisibility(-1).setSmallIcon(R.drawable.statusbar_icon).setColor(ViewCompat.MEASURED_STATE_MASK).setShowWhen(false).setPriority(-2).setOngoing(false).setCategory(NotificationCompat.CATEGORY_SERVICE).setAutoCancel(true);
        return builder.build();
    }

    private static void createNotificationChannel(final Context context) {
        if (PlatformUtil.isVersionOrGreater(26)) {
            NotificationChannel notificationChannel = new NotificationChannel("android_music_background_notification", "Background Updates", 0);
            notificationChannel.setShowBadge(false);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}
