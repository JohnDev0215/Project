package io.sentry.android.core.internal.util;

import B8.t;
import android.net.ConnectivityManager;
import android.net.Network;
import io.sentry.G;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ConnectivityManager.NetworkCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ G f16207a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ t f16208b;

    public a(t tVar, G g7) {
        this.f16208b = tVar;
        this.f16207a = g7;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        this.f16207a.i(this.f16208b.e());
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLosing(Network network, int i7) {
        this.f16207a.i(this.f16208b.e());
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        this.f16207a.i(this.f16208b.e());
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onUnavailable() {
        this.f16207a.i(this.f16208b.e());
    }
}
