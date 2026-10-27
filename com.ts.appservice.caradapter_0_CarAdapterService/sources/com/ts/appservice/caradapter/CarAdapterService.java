package com.ts.appservice.caradapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import com.ts.appservice.caradapter.c.e;
import com.ts.lib.caradapter.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarAdapterService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private e f8a;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f8a;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtil.checkLogLogger();
        LogUtil.info("CarAdapterService", "car adapter service start");
        if (this.f8a == null) {
            this.f8a = new e(this);
        }
        this.f8a.f3();
        Settings.System.putInt(getContentResolver(), "car.bodywork.acc.on", 0);
        LogUtil.info("CarAdapterService", "car adapter service end");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.f8a.b3();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
