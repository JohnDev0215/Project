package com.byd.data.common.http;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import com.byd.data.common.i.f;

/* JADX INFO: compiled from: NetWorkState.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends ConnectivityManager.NetworkCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3411a = "NetWorkReceiver";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ConnectivityManager f3412b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.byd.data.common.server.e.b f3413c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile byte f3414d;

    private void a(boolean z) {
    }

    private void b(NetworkCapabilities networkCapabilities) {
        com.byd.data.common.server.e.b bVar;
        if (networkCapabilities == null || this.f3413c == null) {
            if (networkCapabilities == null) {
                this.f3414d = (byte) 0;
                return;
            }
            return;
        }
        if (!networkCapabilities.hasCapability(12)) {
            this.f3414d = (byte) 0;
            a(false);
            return;
        }
        if (this.f3414d == 0 && (bVar = this.f3413c) != null) {
            bVar.b(true);
        }
        if (networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(5)) {
            if (this.f3414d != 1) {
                f.g("NetWorkReceiver", "WIFI network state =" + ((int) this.f3414d));
                this.f3414d = (byte) 1;
                return;
            }
            return;
        }
        if (networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(3)) {
            if (this.f3414d != 1) {
                this.f3414d = (byte) 1;
                f.b("NetWorkReceiver", "mobile network");
                return;
            }
            return;
        }
        f.b("NetWorkReceiver", "unknown network eg bluetooth vpn apn3");
        if (this.f3414d != 1) {
            this.f3414d = (byte) 1;
        }
    }

    public boolean c() {
        return this.f3414d != 0;
    }

    public void d(com.byd.data.common.server.e.b bVar) {
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = (ConnectivityManager) com.byd.data.common.g.a.i().d().getSystemService("connectivity");
        this.f3412b = connectivityManager;
        if (connectivityManager == null) {
            return;
        }
        this.f3413c = bVar;
        connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), this);
        Network activeNetwork = this.f3412b.getActiveNetwork();
        if (activeNetwork != null && (networkCapabilities = this.f3412b.getNetworkCapabilities(activeNetwork)) != null && networkCapabilities.hasCapability(16)) {
            this.f3414d = (byte) 1;
        }
        f.g("NetWorkReceiver", "registerNet is end state =" + ((int) this.f3414d));
    }

    public void e() {
        ConnectivityManager connectivityManager = this.f3412b;
        if (connectivityManager != null) {
            connectivityManager.unregisterNetworkCallback(this);
        }
        this.f3413c = null;
        this.f3412b = null;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(Network network) {
        super.onAvailable(network);
        f.g("NetWorkReceiver", "caesar onAvailable 网络连接成功 state =" + ((int) this.f3414d));
        a(true);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        b(networkCapabilities);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLosing(Network network, int i2) {
        super.onLosing(network, i2);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLost(Network network) {
        if (this.f3412b == null) {
            return;
        }
        super.onLost(network);
        Network activeNetwork = this.f3412b.getActiveNetwork();
        if (activeNetwork == null) {
            a(false);
            return;
        }
        b(this.f3412b.getNetworkCapabilities(activeNetwork));
        f.g("NetWorkReceiver", "onLost network is lost connect end state =" + ((int) this.f3414d));
        a(false);
    }
}
