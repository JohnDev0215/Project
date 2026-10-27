package com.android.packageinstaller;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes2.dex */
class PackageInstalledNotificationUtils {
    private static final float DEFAULT_MAX_LABEL_SIZE_PX = 500.0f;
    private static final String META_DATA_INSTALLER_NOTIFICATION_COLOR_KEY = "com.android.packageinstaller.notification.color";
    private static final String META_DATA_INSTALLER_NOTIFICATION_SMALL_ICON_KEY = "com.android.packageinstaller.notification.smallIcon";
    private static final String NEW_APP_INSTALLED_CHANNEL_ID_PREFIX = "INSTALLER:";
    private static final String TAG = PackageInstalledNotificationUtils.class.getSimpleName();
    private final String mChannelId;
    private final Context mContext;
    private final String mInstalledAppLabel;
    private final Icon mInstalledAppLargeIcon;
    private final String mInstalledPackage;
    private final Integer mInstallerAppColor;
    private final String mInstallerAppLabel;
    private final Icon mInstallerAppSmallIcon;
    private final String mInstallerPackage;
    private final NotificationManager mNotificationManager;

    PackageInstalledNotificationUtils(Context context, String installerPackage, String installedPackage) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        try {
            ApplicationInfo installerAppInfo = context.getPackageManager().getApplicationInfo(installerPackage, 128);
            try {
                ApplicationInfo installedAppInfo = context.getPackageManager().getApplicationInfo(installedPackage, 128);
                this.mInstallerPackage = installerPackage;
                this.mInstallerAppLabel = getAppLabel(context, installerAppInfo, installerPackage);
                this.mInstallerAppSmallIcon = getAppNotificationIcon(context, installerAppInfo);
                this.mInstallerAppColor = getAppNotificationColor(context, installerAppInfo);
                this.mInstalledPackage = installedPackage;
                this.mInstalledAppLabel = getAppLabel(context, installedAppInfo, installerPackage);
                this.mInstalledAppLargeIcon = getAppLargeIcon(installedAppInfo);
                this.mChannelId = NEW_APP_INSTALLED_CHANNEL_ID_PREFIX + installerPackage;
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalStateException("Unable to get application info: " + installedPackage);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            throw new IllegalStateException("Unable to get application info: " + installerPackage);
        }
    }

    private static String getAppLabel(Context context, ApplicationInfo appInfo, String packageName) {
        CharSequence label = appInfo.loadSafeLabel(context.getPackageManager(), DEFAULT_MAX_LABEL_SIZE_PX, 5).toString();
        if (label != null) {
            return label.toString();
        }
        return packageName;
    }

    private static Icon getAppLargeIcon(ApplicationInfo appInfo) {
        if (appInfo.icon != 0) {
            return Icon.createWithResource(appInfo.packageName, appInfo.icon);
        }
        return Icon.createWithResource("android", android.R.drawable.sym_def_app_icon);
    }

    private static Icon getAppNotificationIcon(Context context, ApplicationInfo appInfo) {
        if (appInfo.metaData == null) {
            return Icon.createWithResource(context, R.drawable.ic_file_download);
        }
        int iconResId = appInfo.metaData.getInt(META_DATA_INSTALLER_NOTIFICATION_SMALL_ICON_KEY, 0);
        if (iconResId != 0) {
            return Icon.createWithResource(appInfo.packageName, iconResId);
        }
        return Icon.createWithResource(context, R.drawable.ic_file_download);
    }

    private static Integer getAppNotificationColor(Context context, ApplicationInfo appInfo) {
        int colorResId;
        if (appInfo.metaData != null && (colorResId = appInfo.metaData.getInt(META_DATA_INSTALLER_NOTIFICATION_COLOR_KEY, 0)) != 0) {
            try {
                PackageManager pm = context.getPackageManager();
                Resources resources = pm.getResourcesForApplication(appInfo.packageName);
                return Integer.valueOf(resources.getColor(colorResId, context.getTheme()));
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Error while loading notification color: " + colorResId + " for " + appInfo.packageName);
            }
        }
        return null;
    }

    private static Intent getAppDetailIntent(String packageName) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", packageName, null));
        return intent;
    }

    private static Intent resolveIntent(Context context, Intent i) {
        ResolveInfo result = context.getPackageManager().resolveActivity(i, 0);
        if (result == null) {
            return null;
        }
        return new Intent(i.getAction()).setClassName(result.activityInfo.packageName, result.activityInfo.name);
    }

    private static Intent getAppStoreLink(Context context, String installerPackageName, String packageName) {
        Intent intent = new Intent("android.intent.action.SHOW_APP_INFO").setPackage(installerPackageName);
        Intent result = resolveIntent(context, intent);
        if (result != null) {
            result.putExtra("android.intent.extra.PACKAGE_NAME", packageName);
            return result;
        }
        return null;
    }

    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(this.mChannelId, this.mInstallerAppLabel, 3);
        channel.setDescription(this.mContext.getString(R.string.app_installed_notification_channel_description));
        channel.enableVibration(false);
        channel.setSound(null, null);
        channel.setLockscreenVisibility(0);
        channel.setBlockable(true);
        this.mNotificationManager.createNotificationChannel(channel);
    }

    private PendingIntent getInstalledAppLaunchIntent() {
        Intent intent = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mInstalledPackage);
        if (intent == null) {
            intent = getAppStoreLink(this.mContext, this.mInstallerPackage, this.mInstalledPackage);
        }
        if (intent == null) {
            intent = getAppDetailIntent(this.mInstalledPackage);
        }
        intent.setFlags(268435456);
        return PendingIntent.getActivity(this.mContext, 0, intent, 134217728);
    }

    private PendingIntent getInstallerEntranceIntent() {
        Intent intent = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mInstallerPackage);
        if (intent == null) {
            intent = getAppDetailIntent(this.mInstallerPackage);
        }
        intent.setFlags(268435456);
        return PendingIntent.getActivity(this.mContext, 0, intent, 134217728);
    }

    private Notification.Builder getGroupNotificationBuilder() {
        PendingIntent contentIntent = getInstallerEntranceIntent();
        Bundle extras = new Bundle();
        extras.putString("android.substName", this.mInstallerAppLabel);
        Notification.Builder builder = new Notification.Builder(this.mContext, this.mChannelId).setSmallIcon(this.mInstallerAppSmallIcon).setGroup(this.mChannelId).setExtras(extras).setLocalOnly(true).setCategory(NotificationCompat.CATEGORY_STATUS).setContentIntent(contentIntent).setGroupSummary(true);
        Integer num = this.mInstallerAppColor;
        if (num != null) {
            builder.setColor(num.intValue());
        }
        return builder;
    }

    private Notification.Builder getAppInstalledNotificationBuilder() {
        PendingIntent contentIntent = getInstalledAppLaunchIntent();
        Bundle extras = new Bundle();
        extras.putString("android.substName", this.mInstallerAppLabel);
        String tickerText = String.format(this.mContext.getString(R.string.notification_installation_success_status), this.mInstalledAppLabel);
        Notification.Builder builder = new Notification.Builder(this.mContext, this.mChannelId).setAutoCancel(true).setSmallIcon(this.mInstallerAppSmallIcon).setContentTitle(this.mInstalledAppLabel).setContentText(this.mContext.getString(R.string.notification_installation_success_message)).setContentIntent(contentIntent).setTicker(tickerText).setCategory(NotificationCompat.CATEGORY_STATUS).setShowWhen(true).setWhen(System.currentTimeMillis()).setLocalOnly(true).setGroup(this.mChannelId).addExtras(extras).setStyle(new Notification.BigTextStyle());
        Icon icon = this.mInstalledAppLargeIcon;
        if (icon != null) {
            builder.setLargeIcon(icon);
        }
        Integer num = this.mInstallerAppColor;
        if (num != null) {
            builder.setColor(num.intValue());
        }
        return builder;
    }

    void postAppInstalledNotification() {
        createChannel();
        Notification.Builder appNotificationBuilder = getAppInstalledNotificationBuilder();
        NotificationManager notificationManager = this.mNotificationManager;
        String str = this.mInstalledPackage;
        notificationManager.notify(str, str.hashCode(), appNotificationBuilder.build());
        Notification.Builder groupNotificationBuilder = getGroupNotificationBuilder();
        NotificationManager notificationManager2 = this.mNotificationManager;
        String str2 = this.mInstallerPackage;
        notificationManager2.notify(str2, str2.hashCode(), groupNotificationBuilder.build());
    }
}
