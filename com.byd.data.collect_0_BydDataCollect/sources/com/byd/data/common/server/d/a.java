package com.byd.data.common.server.d;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.byd.data.common.a;
import com.byd.data.common.c.c.b;
import com.byd.data.common.i.f;

/* JADX INFO: compiled from: CloudServiceConnect.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements ServiceConnection {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    com.byd.data.common.a f3469b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3470c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3468a = "CloudServiceConnect";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IBinder.DeathRecipient f3471d = new C0069a();

    /* JADX INFO: renamed from: com.byd.data.common.server.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CloudServiceConnect.java */
    class C0069a implements IBinder.DeathRecipient {
        C0069a() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            f.g("CloudServiceConnect", "DeathRecipient,binderDied");
            a.this.f3470c = false;
        }
    }

    public boolean b() {
        return this.f3470c;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        com.byd.data.common.a aVarR = a.AbstractBinderC0063a.r(iBinder);
        this.f3469b = aVarR;
        this.f3470c = aVarR != null;
        com.byd.data.common.g.a.i().g().A(this.f3469b);
        if (iBinder != null) {
            try {
                iBinder.linkToDeath(this.f3471d, 0);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        this.f3469b = null;
        this.f3470c = false;
        b bVarG = com.byd.data.common.g.a.i().g();
        if (bVarG != null) {
            bVarG.A(null);
        }
    }
}
