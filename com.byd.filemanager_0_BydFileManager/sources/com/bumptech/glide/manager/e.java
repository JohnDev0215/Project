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
    private final Context f3429b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a f3430c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f3431d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3432e;
    private final BroadcastReceiver f = new a();

    /* JADX INFO: compiled from: DefaultConnectivityMonitor.java */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            e eVar = e.this;
            boolean z = eVar.f3431d;
            eVar.f3431d = eVar.l(context);
            if (z != e.this.f3431d) {
                if (Log.isLoggable("ConnectivityMonitor", 3)) {
                    Log.d("ConnectivityMonitor", "connectivity changed, isConnected: " + e.this.f3431d);
                }
                e eVar2 = e.this;
                eVar2.f3430c.a(eVar2.f3431d);
            }
        }
    }

    e(Context context, c.a aVar) {
        this.f3429b = context.getApplicationContext();
        this.f3430c = aVar;
    }

    private void m() {
        if (this.f3432e) {
            return;
        }
        this.f3431d = l(this.f3429b);
        try {
            this.f3429b.registerReceiver(this.f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.f3432e = true;
        } catch (SecurityException e2) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to register", e2);
            }
        }
    }

    private void n() {
        if (this.f3432e) {
            this.f3429b.unregisterReceiver(this.f);
            this.f3432e = false;
        }
    }

    @Override // com.bumptech.glide.manager.i
    public void a() {
        m();
    }

    @Override // com.bumptech.glide.manager.i
    public void f() {
        n();
    }

    @Override // com.bumptech.glide.manager.i
    public void k() {
    }

    @SuppressLint({"MissingPermission"})
    boolean l(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) com.bumptech.glide.o.j.d((ConnectivityManager) context.getSystemService("connectivity"))).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (RuntimeException e2) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to determine connectivity status when connectivity changed", e2);
            }
            return true;
        }
    }
}
