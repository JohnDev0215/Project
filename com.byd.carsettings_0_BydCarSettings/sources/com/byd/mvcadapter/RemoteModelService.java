package com.byd.mvcadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import b.b.l.q;

/* JADX INFO: loaded from: classes2.dex */
public class RemoteModelService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f3682a = new q(this);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f3682a;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f3682a.f0();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}
