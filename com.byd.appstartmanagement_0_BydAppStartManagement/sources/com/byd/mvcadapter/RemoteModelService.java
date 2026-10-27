package com.byd.mvcadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import b.a.c.q;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteModelService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f631b = new q(this);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f631b;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f631b.D();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}
