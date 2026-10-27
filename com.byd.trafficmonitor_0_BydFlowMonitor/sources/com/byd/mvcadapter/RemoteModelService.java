package com.byd.mvcadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import c.a.b.n;

/* JADX INFO: loaded from: classes.dex */
public class RemoteModelService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f4359b = new n(this);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f4359b;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f4359b.K();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}
