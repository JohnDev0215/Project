package com.byd.overseaappstore.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.CallSuper;
import spi.f2;
import spi.wp;

/* JADX INFO: loaded from: classes.dex */
public abstract class Hilt_SoftwareBroadcastReceiver extends BroadcastReceiver {
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
                ((wp) f2.a(context)).e((SoftwareBroadcastReceiver) this);
                this.a = true;
            }
        }
    }
}
