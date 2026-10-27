package com.bumptech.glide.o;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.bumptech.glide.o.c;

/* JADX INFO: loaded from: classes.dex */
final class e implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f2461b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a f2462c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f2463d;
    private boolean e;
    private final BroadcastReceiver f = new a();

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            e eVar = e.this;
            boolean z = eVar.f2463d;
            eVar.f2463d = eVar.l(context);
            if (z != e.this.f2463d) {
                if (Log.isLoggable("ConnectivityMonitor", 3)) {
                    Log.d("ConnectivityMonitor", "connectivity changed, isConnected: " + e.this.f2463d);
                }
                e eVar2 = e.this;
                eVar2.f2462c.a(eVar2.f2463d);
            }
        }
    }

    e(Context context, c.a aVar) {
        this.f2461b = context.getApplicationContext();
        this.f2462c = aVar;
    }

    private void m() {
        if (this.e) {
            return;
        }
        this.f2463d = l(this.f2461b);
        try {
            this.f2461b.registerReceiver(this.f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.e = true;
        } catch (SecurityException e) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to register", e);
            }
        }
    }

    private void n() {
        if (this.e) {
            this.f2461b.unregisterReceiver(this.f);
            this.e = false;
        }
    }

    @Override // com.bumptech.glide.o.i
    public void a() {
        m();
    }

    @Override // com.bumptech.glide.o.i
    public void f() {
        n();
    }

    @Override // com.bumptech.glide.o.i
    public void k() {
    }

    @SuppressLint({"MissingPermission"})
    boolean l(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) com.bumptech.glide.t.j.d((ConnectivityManager) context.getSystemService("connectivity"))).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (RuntimeException e) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to determine connectivity status when connectivity changed", e);
            }
            return true;
        }
    }
}
