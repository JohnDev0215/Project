package io.sentry.android.core;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import io.sentry.C1457d;
import io.sentry.EnumC1495p1;
import io.sentry.InterfaceC1456c1;

/* JADX INFO: loaded from: classes.dex */
public final class O extends ConnectivityManager.NetworkCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.E f16083a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final B f16084b;
    public Network c;
    public NetworkCapabilities d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f16085e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final InterfaceC1456c1 f16086f;

    public O(B b10, InterfaceC1456c1 interfaceC1456c1) {
        io.sentry.E e10 = io.sentry.E.f15834a;
        this.c = null;
        this.d = null;
        this.f16085e = 0L;
        this.f16083a = e10;
        h6.e.q(b10, "BuildInfoProvider is required");
        this.f16084b = b10;
        h6.e.q(interfaceC1456c1, "SentryDateProvider is required");
        this.f16086f = interfaceC1456c1;
    }

    public static C1457d a(String str) {
        C1457d c1457d = new C1457d();
        c1457d.o = "system";
        c1457d.f16515q = "network.event";
        c1457d.c(str, "action");
        c1457d.f16517s = EnumC1495p1.INFO;
        return c1457d;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        if (network.equals(this.c)) {
            return;
        }
        this.f16083a.e(a("NETWORK_AVAILABLE"));
        this.c = network;
        this.d = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0100  */
    @Override // android.net.ConnectivityManager.NetworkCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onCapabilitiesChanged(android.net.Network r21, android.net.NetworkCapabilities r22) {
        /*
            Method dump skipped, instruction units count: 341
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.O.onCapabilitiesChanged(android.net.Network, android.net.NetworkCapabilities):void");
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        if (network.equals(this.c)) {
            this.f16083a.e(a("NETWORK_LOST"));
            this.c = null;
            this.d = null;
        }
    }
}
