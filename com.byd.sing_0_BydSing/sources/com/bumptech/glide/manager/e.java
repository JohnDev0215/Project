package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.bumptech.glide.manager.c;

/* JADX INFO: compiled from: DefaultConnectivityMonitor.java */
/* JADX INFO: loaded from: classes.dex */
final class e implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f3202b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a f3203c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f3204d;
    private boolean e;
    private final BroadcastReceiver f = new a();

    /* JADX INFO: compiled from: DefaultConnectivityMonitor.java */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            e eVar = e.this;
            boolean z = eVar.f3204d;
            eVar.f3204d = eVar.l(context);
            if (z != e.this.f3204d) {
                if (Log.isLoggable("ConnectivityMonitor", 3)) {
                    Log.d("ConnectivityMonitor", "connectivity changed, isConnected: " + e.this.f3204d);
                }
                e eVar2 = e.this;
                eVar2.f3203c.a(eVar2.f3204d);
            }
        }
    }

    e(Context context, c.a aVar) {
        this.f3202b = context.getApplicationContext();
        this.f3203c = aVar;
    }

    private void m() {
        if (this.e) {
            return;
        }
        this.f3204d = l(this.f3202b);
        try {
            this.f3202b.registerReceiver(this.f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.e = true;
        } catch (SecurityException e) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to register", e);
            }
        }
    }

    private void n() {
        if (this.e) {
            this.f3202b.unregisterReceiver(this.f);
            this.e = false;
        }
    }

    @Override // com.bumptech.glide.manager.i
    public void a() {
        m();
    }

    @Override // com.bumptech.glide.manager.i
    public void e() {
        n();
    }

    @Override // com.bumptech.glide.manager.i
    public void k() {
    }

    @SuppressLint({"MissingPermission"})
    boolean l(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) com.bumptech.glide.m.j.d((ConnectivityManager) context.getSystemService("connectivity"))).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (RuntimeException e) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to determine connectivity status when connectivity changed", e);
            }
            return true;
        }
    }
}
