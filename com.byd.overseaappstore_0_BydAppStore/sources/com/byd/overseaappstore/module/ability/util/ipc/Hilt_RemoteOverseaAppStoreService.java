package com.byd.overseaappstore.module.ability.util.ipc;

import android.app.Service;
import androidx.annotation.CallSuper;
import spi.gm;
import spi.xc;
import spi.yo;

/* JADX INFO: loaded from: classes.dex */
public abstract class Hilt_RemoteOverseaAppStoreService extends Service implements xc {
    public volatile yo a;
    public final Object b = new Object();
    public boolean c = false;

    @Override // spi.xc
    public final Object a() {
        if (this.a == null) {
            synchronized (this.b) {
                if (this.a == null) {
                    this.a = new yo(this);
                }
            }
        }
        return this.a.a();
    }

    @Override // android.app.Service
    @CallSuper
    public void onCreate() {
        if (!this.c) {
            this.c = true;
            ((gm) a()).a((RemoteOverseaAppStoreService) this);
        }
        super.onCreate();
    }
}
