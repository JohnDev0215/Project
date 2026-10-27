package com.vivid.carcontroller.byd.dx;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public class RestoreStateService extends r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    l f5120i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private NotificationManager f5121j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    a f5122k;

    public interface a {
        boolean a();

        Notification b();
    }

    private Notification d() {
        a aVar = this.f5122k;
        if (aVar == null) {
            return null;
        }
        return aVar.b();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.vivid.carcontroller.byd.dx.r, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f5121j = (NotificationManager) getApplication().getSystemService("notification");
        this.f5121j.createNotificationChannel(new NotificationChannel("111", "restore_state_channel", 4));
        startForeground(1, d());
    }

    @Override // android.app.Service
    public void onDestroy() {
        h3.i.b("RestoreStateService").b("onDestroy", new Object[0]);
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i7, int i8) {
        String action = intent.getAction();
        h3.i.b("RestoreStateService").b("onStartCommand", new Object[0]);
        h3.i.b("RestoreStateService").b("action:" + action, new Object[0]);
        a aVar = this.f5122k;
        boolean zA = aVar == null ? false : aVar.a();
        boolean zE = this.f5120i.e();
        h3.i.b("RestoreStateService").b("a: " + this.f5120i.f(), new Object[0]);
        h3.i.b("RestoreStateService").b("b: " + zA, new Object[0]);
        h3.i.b("RestoreStateService").b("c: " + zE, new Object[0]);
        if ((zA && !this.f5120i.f()) || zE) {
            this.f5120i.j();
        }
        stopForeground(true);
        stopSelf();
        return 2;
    }
}
