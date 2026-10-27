package com.android.providers.settings;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.android.internal.notification.SystemNotificationChannels;

/* JADX INFO: loaded from: classes.dex */
public class WifiSoftApConfigChangedNotifier {
    public static void notifyUserOfConfigConversion(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(new NotificationChannel(SystemNotificationChannels.NETWORK_STATUS, context.getString(android.R.string.keyboard_shortcut_group_applications_maps), 2));
        notificationManager.notify(50, createConversionNotification(context));
    }

    private static Notification createConversionNotification(Context context) {
        Resources resources = context.getResources();
        CharSequence text = resources.getText(R.string.wifi_softap_config_change);
        CharSequence text2 = resources.getText(R.string.wifi_softap_config_change_summary);
        return new Notification.Builder(context, SystemNotificationChannels.NETWORK_STATUS).setSmallIcon(R.drawable.ic_wifi_settings).setPriority(1).setCategory("sys").setContentTitle(text).setContentText(text2).setContentIntent(getPendingActivity(context)).setTicker(text).setShowWhen(false).setLocalOnly(true).setColor(resources.getColor(android.R.color.system_notification_accent_color, context.getTheme())).setStyle(new Notification.BigTextStyle().setBigContentTitle(text).setSummaryText(text2)).setAutoCancel(true).build();
    }

    private static PendingIntent getPendingActivity(Context context) {
        return PendingIntent.getActivity(context, 0, new Intent("com.android.settings.WIFI_TETHER_SETTINGS").addFlags(268435456), 134217728);
    }
}
