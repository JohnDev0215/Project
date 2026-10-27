package com.android.systemui.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioAttributes;
import android.net.Uri;
import android.provider.Settings;
import com.android.systemui.R;
import com.android.systemui.SystemUI;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class NotificationChannels extends SystemUI {
    public static String ALERTS = "ALR";
    public static String BATTERY = "BAT";
    public static String GENERAL = "GEN";
    public static String HINTS = "HNT";
    public static String SCREENSHOTS_HEADSUP = "SCN_HEADSUP";
    public static String SCREENSHOTS_LEGACY = "SCN";
    public static String STORAGE = "DSK";
    public static String TVPIP = "TPP";

    @Override // com.android.systemui.SystemUI
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    @Override // com.android.systemui.SystemUI
    protected void onBootCompleted() {
    }

    @Override // com.android.systemui.SystemUI
    protected void onConfigurationChanged(Configuration newConfig) {
    }

    public NotificationChannels(Context context) {
    }

    public static void createAll(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        NotificationChannel notificationChannel = new NotificationChannel(BATTERY, context.getString(R.string.notification_channel_battery), 5);
        notificationChannel.setSound(Uri.parse("file://" + Settings.Global.getString(context.getContentResolver(), "low_battery_sound")), new AudioAttributes.Builder().setContentType(4).setUsage(10).build());
        notificationChannel.setBlockable(true);
        notificationManager.createNotificationChannels(Arrays.asList(new NotificationChannel(ALERTS, context.getString(R.string.notification_channel_alerts), 4), new NotificationChannel(GENERAL, context.getString(R.string.notification_channel_general), 1), new NotificationChannel(STORAGE, context.getString(R.string.notification_channel_storage), isTv(context) ? 3 : 2), createScreenshotChannel(context.getString(R.string.notification_channel_screenshot), notificationManager.getNotificationChannel(SCREENSHOTS_LEGACY)), notificationChannel, new NotificationChannel(HINTS, context.getString(R.string.notification_channel_hints), 3)));
        notificationManager.deleteNotificationChannel(SCREENSHOTS_LEGACY);
        if (isTv(context)) {
            notificationManager.createNotificationChannel(new NotificationChannel(TVPIP, context.getString(R.string.notification_channel_tv_pip), 5));
        }
    }

    static NotificationChannel createScreenshotChannel(String name, NotificationChannel legacySS) {
        NotificationChannel notificationChannel = new NotificationChannel(SCREENSHOTS_HEADSUP, name, 4);
        notificationChannel.setSound(null, new AudioAttributes.Builder().setUsage(5).build());
        notificationChannel.setBlockable(true);
        if (legacySS != null) {
            int userLockedFields = legacySS.getUserLockedFields();
            if ((userLockedFields & 4) != 0) {
                notificationChannel.setImportance(legacySS.getImportance());
            }
            if ((userLockedFields & 32) != 0) {
                notificationChannel.setSound(legacySS.getSound(), legacySS.getAudioAttributes());
            }
            if ((userLockedFields & 16) != 0) {
                notificationChannel.setVibrationPattern(legacySS.getVibrationPattern());
            }
            if ((userLockedFields & 8) != 0) {
                notificationChannel.setLightColor(legacySS.getLightColor());
            }
        }
        return notificationChannel;
    }

    @Override // com.android.systemui.SystemUI
    public void start() {
        createAll(this.mContext);
    }

    private static boolean isTv(Context context) {
        return context.getPackageManager().hasSystemFeature("android.software.leanback");
    }
}
