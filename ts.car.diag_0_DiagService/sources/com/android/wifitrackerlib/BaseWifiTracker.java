package com.android.wifitrackerlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkKey;
import android.net.NetworkRequest;
import android.net.NetworkScoreManager;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class BaseWifiTracker implements androidx.lifecycle.e {
    private static boolean w;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f1417c;
    private final BroadcastReceiver d;
    private final a e;
    protected final Context f;
    protected final WifiManager g;
    protected final ConnectivityManager h;
    protected final NetworkScoreManager i;
    protected final Handler j;
    protected final Handler k;
    protected final long l;
    protected final long m;
    protected final d0 n;
    protected final WifiNetworkScoreCache o;
    protected boolean p;
    protected boolean q;
    protected boolean r;
    private final Set<NetworkKey> s;
    private final NetworkRequest t;
    private final ConnectivityManager.NetworkCallback u;
    private final ConnectivityManager.NetworkCallback v;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: caradapter-bug */
    @WorkerThread
    class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1418a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ BaseWifiTracker f1419b;

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            if (this.f1419b.g.startScan()) {
                this.f1418a = 0;
            } else {
                int i = this.f1418a + 1;
                this.f1418a = i;
                if (i >= 3) {
                    if (BaseWifiTracker.g()) {
                        Log.v(this.f1419b.f1417c, "Scanner failed to start scan " + this.f1418a + " times!");
                    }
                    this.f1418a = 0;
                    return;
                }
            }
            postDelayed(new Runnable() { // from class: com.android.wifitrackerlib.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1457c.d();
                }
            }, this.f1419b.m);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            if (BaseWifiTracker.g()) {
                Log.v(this.f1419b.f1417c, "Scanner start");
            }
            d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            if (BaseWifiTracker.g()) {
                Log.v(this.f1419b.f1417c, "Scanner stop");
            }
            this.f1418a = 0;
            removeCallbacksAndMessages(null);
        }
    }

    public static boolean g() {
        return w;
    }

    @WorkerThread
    protected void c() {
    }

    @MainThread
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.net.wifi.CONFIGURED_NETWORKS_CHANGE");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        this.f.registerReceiver(this.d, intentFilter, null, this.k);
        this.h.registerNetworkCallback(this.t, this.u, this.k);
        this.h.registerDefaultNetworkCallback(this.v, this.k);
        ConnectivityManager connectivityManager = this.h;
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities != null) {
            this.q = networkCapabilities.hasTransport(1);
            this.r = networkCapabilities.hasTransport(0);
        } else {
            this.q = false;
            this.r = false;
        }
        if (g()) {
            Log.v(this.f1417c, "Wifi is the default route: " + this.q);
            Log.v(this.f1417c, "Cell is the default route: " + this.r);
        }
        this.i.registerNetworkScoreCache(1, this.o, 2);
        if (this.g.getWifiState() == 3) {
            Handler handler = this.k;
            final a aVar = this.e;
            Objects.requireNonNull(aVar);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.e
                @Override // java.lang.Runnable
                public final void run() {
                    aVar.e();
                }
            });
        } else {
            Handler handler2 = this.k;
            final a aVar2 = this.e;
            Objects.requireNonNull(aVar2);
            handler2.post(new Runnable() { // from class: com.android.wifitrackerlib.b
                @Override // java.lang.Runnable
                public final void run() {
                    aVar2.f();
                }
            });
        }
        this.k.post(new Runnable() { // from class: com.android.wifitrackerlib.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f1462c.c();
            }
        });
    }

    @MainThread
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Handler handler = this.k;
        final a aVar = this.e;
        Objects.requireNonNull(aVar);
        handler.post(new Runnable() { // from class: com.android.wifitrackerlib.c
            @Override // java.lang.Runnable
            public final void run() {
                aVar.f();
            }
        });
        this.f.unregisterReceiver(this.d);
        this.h.unregisterNetworkCallback(this.u);
        this.h.unregisterNetworkCallback(this.v);
        this.i.unregisterNetworkScoreCache(1, this.o);
        Handler handler2 = this.k;
        final Set<NetworkKey> set = this.s;
        Objects.requireNonNull(set);
        handler2.post(new Runnable() { // from class: com.android.wifitrackerlib.Z
            @Override // java.lang.Runnable
            public final void run() {
                set.clear();
            }
        });
    }
}
