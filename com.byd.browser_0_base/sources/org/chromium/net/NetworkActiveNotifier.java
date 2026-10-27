package org.chromium.net;

import J.N;
import android.net.ConnectivityManager;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class NetworkActiveNotifier implements ConnectivityManager.OnNetworkActiveListener {
    public final ConnectivityManager a = (ConnectivityManager) xz.a.getSystemService("connectivity");
    public final long b;
    public boolean c;

    public NetworkActiveNotifier(long j) {
        this.b = j;
    }

    @CalledByNative
    public static NetworkActiveNotifier build(long j) {
        return new NetworkActiveNotifier(j);
    }

    @CalledByNative
    public void disableNotifications() {
        this.c = false;
        this.a.removeDefaultNetworkActiveListener(this);
    }

    @CalledByNative
    public void enableNotifications() {
        this.c = true;
        this.a.addDefaultNetworkActiveListener(this);
    }

    @CalledByNative
    public void fakeDefaultNetworkActive() {
        if (this.c) {
            onNetworkActive();
        }
    }

    @CalledByNative
    public boolean isDefaultNetworkActive() {
        return this.a.isDefaultNetworkActive();
    }

    @Override // android.net.ConnectivityManager.OnNetworkActiveListener
    public final void onNetworkActive() {
        N.MSZPA7qE(this.b);
    }
}
