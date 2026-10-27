package com.vivid.carcontroller.byd.dx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: Hilt_DeviceBroadcastReceiver.java */
/* JADX INFO: loaded from: classes.dex */
abstract class q extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile boolean f5161a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f5162b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f5163c = false;

    q() {
    }

    protected void a(Context context) {
        if (this.f5161a) {
            return;
        }
        synchronized (this.f5162b) {
            if (!this.f5161a) {
                ((o) dagger.hilt.android.internal.managers.e.a(context)).b((DeviceBroadcastReceiver) o4.e.a(this));
                this.f5161a = true;
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a(context);
    }
}
