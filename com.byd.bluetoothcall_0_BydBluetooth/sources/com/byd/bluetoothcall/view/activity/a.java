package com.byd.bluetoothcall.view.activity;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.IProcessObserver;
import android.app.TaskStackListener;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.byd.bluetoothcall.f.f;

/* JADX INFO: compiled from: SystemActivityMonitoringService.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a */
    private static Context f3318a;

    /* JADX INFO: renamed from: b */
    private static IActivityManager f3319b;

    /* JADX INFO: renamed from: c */
    private static b f3320c;

    /* JADX INFO: renamed from: d */
    private static c f3321d;

    /* JADX INFO: renamed from: e */
    private String f3322e = "SystemActivityMonitoringService";
    private final SparseArray<?> f = new SparseArray<>();

    /* JADX INFO: compiled from: SystemActivityMonitoringService.java */
    private class b extends IProcessObserver.Stub {
        private b() {
        }

        public void onForegroundActivitiesChanged(int i, int i2, boolean z) {
        }

        public void onForegroundServicesChanged(int i, int i2, int i3) throws RemoteException {
        }

        public void onProcessDied(int i, int i2) {
        }

        /* synthetic */ b(a aVar, C0082a c0082a) {
            this();
        }
    }

    /* JADX INFO: compiled from: SystemActivityMonitoringService.java */
    private class c extends TaskStackListener {
        private c() {
        }

        public void onTaskStackChanged() {
            c.a.b.b.f0().h(f.h(a.f3318a));
        }

        /* synthetic */ c(a aVar, C0082a c0082a) {
            this();
        }
    }

    public a(Context context) {
        f3318a = context;
        f3320c = new b();
        f3321d = new c();
        IActivityManager service = ActivityManager.getService();
        f3319b = service;
        try {
            service.registerProcessObserver(f3320c);
            f3319b.registerTaskStackListener(f3321d);
        } catch (Exception e2) {
            Log.e(this.f3322e, "cannot register activity monitoring", e2);
        }
    }
}
