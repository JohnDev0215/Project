package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.InternetMonitor;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeModule_ProvideInternetMonitorFactory implements ul0 {
    private final xy1 connectivityListenerProvider;
    private final xy1 flightModeEnabledMonitorProvider;
    private final xy1 mobileDataDisabledMonitorProvider;

    public ConnectionTypeModule_ProvideInternetMonitorFactory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        this.connectivityListenerProvider = xy1Var;
        this.flightModeEnabledMonitorProvider = xy1Var2;
        this.mobileDataDisabledMonitorProvider = xy1Var3;
    }

    public static ConnectionTypeModule_ProvideInternetMonitorFactory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        return new ConnectionTypeModule_ProvideInternetMonitorFactory(xy1Var, xy1Var2, xy1Var3);
    }

    public static InternetMonitor provideInternetMonitor(ConnectivityListener connectivityListener, FlightModeEnabledMonitor flightModeEnabledMonitor, MobileDataDisabledMonitor mobileDataDisabledMonitor) {
        InternetMonitor internetMonitorProvideInternetMonitor = ConnectionTypeModule.provideInternetMonitor(connectivityListener, flightModeEnabledMonitor, mobileDataDisabledMonitor);
        vr2.b(internetMonitorProvideInternetMonitor);
        return internetMonitorProvideInternetMonitor;
    }

    @Override // p.xy1
    public InternetMonitor get() {
        return provideInternetMonitor((ConnectivityListener) this.connectivityListenerProvider.get(), (FlightModeEnabledMonitor) this.flightModeEnabledMonitorProvider.get(), (MobileDataDisabledMonitor) this.mobileDataDisabledMonitorProvider.get());
    }
}
