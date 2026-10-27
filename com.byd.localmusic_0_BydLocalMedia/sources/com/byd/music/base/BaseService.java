package com.byd.music.base;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.byd.music.R;
import com.byd.music.utils.StringResource;

/* JADX INFO: loaded from: classes5.dex */
public class BaseService extends Service {
    public static final String CHANNEL_ID_STRING = "BYDMusicService";

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        initServiceCompat();
    }

    protected void initServiceCompat() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("BYDMusicService", StringResource.getString(R.string.app_name), 4));
            Notification notificationBuild = new Notification.Builder(this, "BYDMusicService").setContentTitle("正在运行").setAutoCancel(true).setOngoing(true).setSmallIcon(R.drawable.icon_media_fm_48).build();
            notificationBuild.flags = 64;
            startForeground(1, notificationBuild);
        }
    }

    public static void startCompatService(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (Build.VERSION.SDK_INT >= 26) {
            stopForeground(true);
        }
        super.onDestroy();
    }
}
