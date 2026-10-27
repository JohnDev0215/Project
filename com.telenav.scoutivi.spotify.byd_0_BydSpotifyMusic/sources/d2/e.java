package d2;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import d2.c;

/* JADX INFO: compiled from: DefaultConnectivityMonitor.java */
/* JADX INFO: loaded from: classes.dex */
final class e implements c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Context f5620e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final c.a f5621g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    boolean f5622h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f5623i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final BroadcastReceiver f5624j = new a();

    /* JADX INFO: compiled from: DefaultConnectivityMonitor.java */
    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            e eVar = e.this;
            boolean z6 = eVar.f5622h;
            eVar.f5622h = eVar.b(context);
            if (z6 != e.this.f5622h) {
                if (Log.isLoggable("ConnectivityMonitor", 3)) {
                    Log.d("ConnectivityMonitor", "connectivity changed, isConnected: " + e.this.f5622h);
                }
                e eVar2 = e.this;
                eVar2.f5621g.a(eVar2.f5622h);
            }
        }
    }

    e(Context context, c.a aVar) {
        this.f5620e = context.getApplicationContext();
        this.f5621g = aVar;
    }

    private void c() {
        if (this.f5623i) {
            return;
        }
        this.f5622h = b(this.f5620e);
        try {
            this.f5620e.registerReceiver(this.f5624j, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.f5623i = true;
        } catch (SecurityException e7) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to register", e7);
            }
        }
    }

    private void m() {
        if (this.f5623i) {
            this.f5620e.unregisterReceiver(this.f5624j);
            this.f5623i = false;
        }
    }

    @Override // d2.i
    public void a() {
        c();
    }

    @SuppressLint({"MissingPermission"})
    boolean b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) k2.j.d((ConnectivityManager) context.getSystemService("connectivity"))).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (RuntimeException e7) {
            if (Log.isLoggable("ConnectivityMonitor", 5)) {
                Log.w("ConnectivityMonitor", "Failed to determine connectivity status when connectivity changed", e7);
            }
            return true;
        }
    }

    @Override // d2.i
    public void g() {
        m();
    }

    @Override // d2.i
    public void onDestroy() {
    }
}
