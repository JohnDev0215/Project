package com.byd.mvcadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import t3.n;

/* JADX INFO: loaded from: classes.dex */
public class RemoteModelService extends Service {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n f2774d = new n(this);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f2774d;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f2774d.A0();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}
