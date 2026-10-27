package com.qtxa.clean;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.ServiceManager;
import com.qtxa.clean.sysmonitor.SysMonitor;
import com.qtxa.clean.sysmonitor.d;

/* JADX INFO: loaded from: classes.dex */
public class SysCleanerService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b f17a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SysMonitor f18b = null;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f17a;
    }

    @Override // android.app.Service
    public void onCreate() {
        d.b("QtSysMonitor", "SysCleanerImpl");
        super.onCreate();
        b bVar = new b(this);
        this.f17a = bVar;
        ServiceManager.addService(b.g, bVar);
        SysMonitor sysMonitorH = SysMonitor.h(this);
        this.f18b = sysMonitorH;
        sysMonitorH.w();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (this.f17a != null) {
            this.f17a = null;
        }
    }
}
