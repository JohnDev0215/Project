package com.byd.overseaappstore.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.CallSuper;
import spi.f2;
import spi.rj;

/* JADX INFO: loaded from: classes.dex */
public abstract class Hilt_NetWorkStateBroadcastReceiver extends BroadcastReceiver {
    public volatile boolean a = false;
    public final Object b = new Object();

    @Override // android.content.BroadcastReceiver
    @CallSuper
    public void onReceive(Context context, Intent intent) {
        if (this.a) {
            return;
        }
        synchronized (this.b) {
            if (!this.a) {
                ((rj) f2.a(context)).f();
                this.a = true;
            }
        }
    }
}
