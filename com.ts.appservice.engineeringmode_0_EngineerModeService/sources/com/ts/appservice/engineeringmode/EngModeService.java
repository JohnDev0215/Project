package com.ts.appservice.engineeringmode;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.ts.appservice.engineeringmode.b.a;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class EngModeService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f14a;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        com.ts.appservice.engineeringmode.e.a.g("EngModeService", "onBind()");
        return this.f14a;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f14a = new a(this);
        com.ts.appservice.engineeringmode.e.a.g("EngModeService", "onCreate()");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        com.ts.appservice.engineeringmode.e.a.g("EngModeService", "onDestroy()");
        this.f14a.b();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        com.ts.appservice.engineeringmode.e.a.g("EngModeService", "onStartCommand()");
        return super.onStartCommand(intent, i, i2);
    }
}
