package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.InternetMonitor;
import com.spotify.connectivity.connectiontype.SpotifyConnectivityManager;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionApisImpl_Factory implements ul0 {
    private final xy1 flightModeEnabledMonitorProvider;
    private final xy1 internetMonitorProvider;
    private final xy1 mobileDataDisabledMonitorProvider;
    private final xy1 spotifyConnectivityManagerProvider;

    public ConnectionApisImpl_Factory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3, xy1 xy1Var4) {
        this.flightModeEnabledMonitorProvider = xy1Var;
        this.mobileDataDisabledMonitorProvider = xy1Var2;
        this.internetMonitorProvider = xy1Var3;
        this.spotifyConnectivityManagerProvider = xy1Var4;
    }

    public static ConnectionApisImpl_Factory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3, xy1 xy1Var4) {
        return new ConnectionApisImpl_Factory(xy1Var, xy1Var2, xy1Var3, xy1Var4);
    }

    public static ConnectionApisImpl newInstance(FlightModeEnabledMonitor flightModeEnabledMonitor, MobileDataDisabledMonitor mobileDataDisabledMonitor, InternetMonitor internetMonitor, SpotifyConnectivityManager spotifyConnectivityManager) {
        return new ConnectionApisImpl(flightModeEnabledMonitor, mobileDataDisabledMonitor, internetMonitor, spotifyConnectivityManager);
    }

    @Override // p.xy1
    public ConnectionApisImpl get() {
        return newInstance((FlightModeEnabledMonitor) this.flightModeEnabledMonitorProvider.get(), (MobileDataDisabledMonitor) this.mobileDataDisabledMonitorProvider.get(), (InternetMonitor) this.internetMonitorProvider.get(), (SpotifyConnectivityManager) this.spotifyConnectivityManagerProvider.get());
    }
}
