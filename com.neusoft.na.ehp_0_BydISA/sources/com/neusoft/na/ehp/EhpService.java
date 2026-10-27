package com.neusoft.na.ehp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import com.neusoft.dlt.LogService;
import com.neusoft.na.ehp.dbu.DialogActivity;
import com.neusoft.na.ehp.manager.NativeManager;
import com.neusoft.na.ehp.utils.ConfigUtil;
import com.neusoft.permission.check.PermissionChecker;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class EhpService extends Service {
    private static final String TAG = "NeuEhpService";

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate : 8.0.0.186.1-G_NEU_ISA");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager.getNotificationChannel(TAG) == null) {
                notificationManager.createNotificationChannel(new NotificationChannel(TAG, getString(R.string.app_name), 2));
            }
            Intent intent = new Intent(this, (Class<?>) DialogActivity.class);
            intent.setClassName(BuildConfig.APPLICATION_ID, "com.neusoft.na.ehp.dbu.DialogActivity");
            intent.addFlags(268435456);
            startForeground(((int) (System.currentTimeMillis() % 10000)) + 1, new Notification.Builder(this, TAG).setSmallIcon(R.mipmap.logo).setContentTitle(getString(R.string.app_name)).setContentText("service is running").setOngoing(true).setFullScreenIntent(PendingIntent.getActivity(this, 123, intent, 67108864), true).build());
        }
        if (getSharedPreferences("dltLogStatus", 0).getBoolean("openDlt", false)) {
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(new Intent(this, (Class<?>) LogService.class));
            } else {
                startService(new Intent(this, (Class<?>) LogService.class));
            }
        }
        Executors.newScheduledThreadPool(1).schedule(new Runnable() { // from class: com.neusoft.na.ehp.EhpService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m39lambda$onCreate$0$comneusoftnaehpEhpService();
            }
        }, 5L, TimeUnit.SECONDS);
        File file = new File("/sdcard/naviexternconfig/permission_check.ini");
        if (file.exists()) {
            new PermissionChecker().start(file.getAbsolutePath(), new ConfigUtil(this).getDbPath());
            return;
        }
        Log.i(TAG, "${permissionCheckerIniFile.absoluteFile} not exists");
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-neusoft-na-ehp-EhpService, reason: not valid java name */
    /* synthetic */ void m39lambda$onCreate$0$comneusoftnaehpEhpService() {
        try {
            NativeManager.getManager(getApplicationContext()).init(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        Process.killProcess(Process.myPid());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return NativeManager.getManager(getApplicationContext());
    }
}
