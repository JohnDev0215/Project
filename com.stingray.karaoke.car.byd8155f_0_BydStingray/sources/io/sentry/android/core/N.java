package io.sentry.android.core;

import android.net.NetworkCapabilities;

/* JADX INFO: loaded from: classes.dex */
public final class N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f16073a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f16074b;
    public final int c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f16075e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f16076f;

    public N(NetworkCapabilities networkCapabilities, B b10, long j3) {
        h6.e.q(networkCapabilities, "NetworkCapabilities is required");
        h6.e.q(b10, "BuildInfoProvider is required");
        this.f16073a = networkCapabilities.getLinkDownstreamBandwidthKbps();
        this.f16074b = networkCapabilities.getLinkUpstreamBandwidthKbps();
        int signalStrength = networkCapabilities.getSignalStrength();
        this.c = signalStrength <= -100 ? 0 : signalStrength;
        this.f16075e = networkCapabilities.hasTransport(4);
        String str = networkCapabilities.hasTransport(3) ? "ethernet" : networkCapabilities.hasTransport(1) ? "wifi" : networkCapabilities.hasTransport(0) ? "cellular" : null;
        this.f16076f = str == null ? "" : str;
        this.d = j3;
    }
}
