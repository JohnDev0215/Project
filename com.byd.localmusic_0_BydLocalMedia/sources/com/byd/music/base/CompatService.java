package com.byd.music.base;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.blankj.utilcode.util.Utils;
import com.byd.music.R;
import io.reactivex.disposables.CompositeDisposable;

/* JADX INFO: loaded from: classes5.dex */
public class CompatService extends Service {
    public static final String CHANNEL_ID_STRING = "BYDMusicService";
    private CompositeDisposable compositeDisposable;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void clearCompositeDisposable() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (compositeDisposable != null) {
            if (!compositeDisposable.isDisposed()) {
                this.compositeDisposable.dispose();
            }
            this.compositeDisposable.clear();
            this.compositeDisposable = null;
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        initServiceCompat();
        if (this.compositeDisposable == null) {
            this.compositeDisposable = new CompositeDisposable();
        }
    }

    protected void initServiceCompat() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("BYDMusicService", Utils.getApp().getString(R.string.app_name), 4));
            Notification notificationBuild = new Notification.Builder(this, "BYDMusicService").setContentTitle("running").setAutoCancel(true).setOngoing(true).setSmallIcon(R.drawable.icon_media_fm_48).build();
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
        clearCompositeDisposable();
        super.onDestroy();
    }
}
