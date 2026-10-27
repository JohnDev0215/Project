package com.byd.vrsettings.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.receiver.NotificationClickReceiver;
import com.byd.vrsettings.utils.LanguageUtil;
import com.byd.vrsettings.utils.LogUtils;
import com.chad.library.BuildConfig;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class NotifyManager {
    public static String channelId = "BYD_CHANNEL_ID";
    public static String channelName = "BYD_CHANNEL_NAME";
    private static NotifyManager instance = null;
    public static String notifyAction = "com.notify.action";
    NotificationCompat.Builder mBuilder;
    private final NotificationManager mNotificationManager;
    private RemoteViews mRemoteViews;
    Map<Integer, Map<String, String>> cacheMap = new ArrayMap();
    Map<String, String> map1 = new ArrayMap();
    Map<String, String> map2 = new ArrayMap();
    Map<String, String> map3 = new ArrayMap();
    Map<String, String> map4 = new ArrayMap();

    public static NotifyManager getInstance() {
        if (instance == null) {
            synchronized (AidlServiceManager.class) {
                if (instance == null) {
                    instance = new NotifyManager();
                }
            }
        }
        return instance;
    }

    public NotifyManager() {
        NotificationManager notificationManager = (NotificationManager) BydVoiceSettingApplication.getContext().getSystemService("notification");
        this.mNotificationManager = notificationManager;
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, 3);
        notificationChannel.setShowBadge(true);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    public NotificationCompat.Builder createNotify(int i, String str) {
        LogUtils.i("NotifyManager", "createNotify:" + i);
        Map<String, String> map = this.cacheMap.get(Integer.valueOf(i));
        String strConvertAll = BuildConfig.FLAVOR;
        int i2 = 0;
        for (String str2 : map.keySet()) {
            if (i2 == 0) {
                strConvertAll = LanguageUtil.convertAll(BydVoiceSettingApplication.getContext(), str2);
            }
            i2++;
        }
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(BydVoiceSettingApplication.getContext(), channelId).setSmallIcon(R.mipmap.icon_voice_language_light).setDefaults(3).setPriority(3).setContentTitle(getContentTitle(i)).setContentText(getNotificationContent(i, strConvertAll, map.size())).setWhen(System.currentTimeMillis()).setContentIntent(getPendingIntent(i));
        this.mBuilder = contentIntent;
        Notification notificationBuild = contentIntent.build();
        notificationBuild.flags = 16;
        this.mNotificationManager.notify(i, notificationBuild);
        return this.mBuilder;
    }

    public void cancelSingleNotify(int i, String str) {
        if (isNotifyActive(i)) {
            Map<String, String> map = this.cacheMap.get(Integer.valueOf(i));
            if (map != null && map.containsKey(str)) {
                map.remove(str);
            }
            String strConvertAll = BuildConfig.FLAVOR;
            int i2 = 0;
            for (String str2 : map.keySet()) {
                if (i2 == 0) {
                    strConvertAll = LanguageUtil.convertAll(BydVoiceSettingApplication.getContext(), str2);
                }
                i2++;
            }
            if (i == -3) {
                this.map3 = map;
            } else if (i == -2) {
                this.map2 = map;
            } else if (i == -1) {
                this.map1 = map;
            }
            if (this.mBuilder == null || this.mNotificationManager == null) {
                return;
            }
            if (map != null && map.size() > 0) {
                this.mBuilder.setContentText(getNotificationContent(i, strConvertAll, i2));
                this.mNotificationManager.notify(i, this.mBuilder.build());
            } else {
                this.mNotificationManager.cancel(i);
            }
        }
    }

    public PendingIntent getPendingIntent(int i) {
        Intent intent = new Intent(BydVoiceSettingApplication.getContext(), (Class<?>) NotificationClickReceiver.class);
        intent.putExtra("NotifyManager", "source_notify");
        intent.putExtra("notifyid", i);
        return PendingIntent.getBroadcast(BydVoiceSettingApplication.getContext(), 0, intent, 0);
    }

    public NotificationManager getNotificationManager() {
        return this.mNotificationManager;
    }

    public void notifyChange() {
        RemoteViews remoteViews = this.mRemoteViews;
        if (remoteViews == null) {
            return;
        }
        this.mBuilder.setCustomContentView(remoteViews);
        this.mBuilder.setDefaults(1).setPriority(1);
        for (StatusBarNotification statusBarNotification : this.mNotificationManager.getActiveNotifications()) {
            this.mNotificationManager.notify(statusBarNotification.getId(), this.mBuilder.build());
        }
    }

    public void removeCache(int i) {
        if (isNotifyActive(i)) {
            return;
        }
        if (this.cacheMap.containsKey(Integer.valueOf(i))) {
            this.cacheMap.remove(Integer.valueOf(i));
        }
        if (i == -1) {
            this.map1.clear();
        }
        if (i == -2) {
            this.map2.clear();
        }
        if (i == -3) {
            this.map3.clear();
        }
        if (i == 100) {
            this.map4.clear();
        }
    }

    public void removeByName(String str) {
        if (this.map1.containsKey(str)) {
            this.map1.remove(str);
        }
        if (this.map2.containsKey(str)) {
            this.map2.remove(str);
        }
        if (this.map3.containsKey(str)) {
            this.map3.remove(str);
        }
        if (this.map4.containsKey(str)) {
            this.map4.remove(str);
        }
    }

    public NotifyManager saveCache(int i, String str) {
        removeCache(i);
        if (i == -2) {
            this.map2.put(str, null);
            this.cacheMap.put(Integer.valueOf(i), this.map2);
        }
        if (i == -3) {
            this.map3.put(str, null);
            this.cacheMap.put(Integer.valueOf(i), this.map3);
        }
        if (i == -1) {
            this.map1.put(str, null);
            this.cacheMap.put(Integer.valueOf(i), this.map1);
        }
        if (i == 100) {
            this.map4.put(str, null);
            this.cacheMap.put(Integer.valueOf(i), this.map4);
        }
        return this;
    }

    private boolean isNotifyActive(int i) {
        for (StatusBarNotification statusBarNotification : this.mNotificationManager.getActiveNotifications()) {
            if (statusBarNotification.getId() == i) {
                return true;
            }
        }
        return false;
    }

    private String getNotificationContent(int i, String str, int i2) {
        if (i == -1) {
            String string = BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_notify_download_fail_tip);
            if (i2 > 1) {
                string = BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_notify_download_fail_multi_tip);
            }
            return String.format(string, str);
        }
        if (i == -3) {
            String string2 = BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_notification_download_complete);
            if (i2 > 1) {
                string2 = BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_notification_download_complete_multi);
            }
            return String.format(string2, str);
        }
        if (i != -2) {
            return i == 100 ? BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_notify_update_tip) : BuildConfig.FLAVOR;
        }
        String string3 = BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_notify_suspend_tip);
        if (i2 > 1) {
            string3 = BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_notify_suspend_multi_tip);
        }
        return String.format(string3, str);
    }

    private String getContentTitle(int i) {
        if (i == -1) {
            return BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_download_fail);
        }
        if (i == -3) {
            return BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_download_complete);
        }
        if (i == -2) {
            return BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_download_paused);
        }
        return i == 100 ? BydVoiceSettingApplication.getContext().getResources().getString(R.string.text_update_enable) : BuildConfig.FLAVOR;
    }

    public void cancelAll() {
        removeCache(-1);
        removeCache(-2);
        removeCache(-3);
        removeCache(100);
        NotificationManager notificationManager = this.mNotificationManager;
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }
}
