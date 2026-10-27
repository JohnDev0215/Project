package com.spotify.connectivity.platformconnectiontype;

import com.google.common.base.Optional;
import com.spotify.connectivity.connectiontype.ConnectionApis;
import com.spotify.connectivity.connectiontype.InternetMonitor;
import com.spotify.connectivity.connectiontype.SpotifyConnectivityManager;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionApisModule_Companion_ProvideConnectionApisFactory implements ul0 {
    private final xy1 connectivityListenerProvider;
    private final xy1 flightModeEnabledMonitorProvider;
    private final xy1 internetMonitorProvider;
    private final xy1 mobileDataDisabledMonitorProvider;
    private final xy1 spotifyConnectivityManagerProvider;

    public ConnectionApisModule_Companion_ProvideConnectionApisFactory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3, xy1 xy1Var4, xy1 xy1Var5) {
        this.connectivityListenerProvider = xy1Var;
        this.flightModeEnabledMonitorProvider = xy1Var2;
        this.mobileDataDisabledMonitorProvider = xy1Var3;
        this.internetMonitorProvider = xy1Var4;
        this.spotifyConnectivityManagerProvider = xy1Var5;
    }

    public static ConnectionApisModule_Companion_ProvideConnectionApisFactory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3, xy1 xy1Var4, xy1 xy1Var5) {
        return new ConnectionApisModule_Companion_ProvideConnectionApisFactory(xy1Var, xy1Var2, xy1Var3, xy1Var4, xy1Var5);
    }

    public static ConnectionApis provideConnectionApis(ConnectivityListener connectivityListener, FlightModeEnabledMonitor flightModeEnabledMonitor, MobileDataDisabledMonitor mobileDataDisabledMonitor, InternetMonitor internetMonitor, Optional<SpotifyConnectivityManager> optional) {
        ConnectionApis connectionApisProvideConnectionApis = ConnectionApisModule.Companion.provideConnectionApis(connectivityListener, flightModeEnabledMonitor, mobileDataDisabledMonitor, internetMonitor, optional);
        vr2.b(connectionApisProvideConnectionApis);
        return connectionApisProvideConnectionApis;
    }

    @Override // p.xy1
    public ConnectionApis get() {
        return provideConnectionApis((ConnectivityListener) this.connectivityListenerProvider.get(), (FlightModeEnabledMonitor) this.flightModeEnabledMonitorProvider.get(), (MobileDataDisabledMonitor) this.mobileDataDisabledMonitorProvider.get(), (InternetMonitor) this.internetMonitorProvider.get(), (Optional) this.spotifyConnectivityManagerProvider.get());
    }
}
