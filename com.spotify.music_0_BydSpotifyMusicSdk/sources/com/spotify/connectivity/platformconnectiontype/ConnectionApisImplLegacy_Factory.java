package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.InternetMonitor;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionApisImplLegacy_Factory implements ul0 {
    private final xy1 connectivityListenerProvider;
    private final xy1 flightModeEnabledMonitorProvider;
    private final xy1 internetMonitorProvider;
    private final xy1 mobileDataDisabledMonitorProvider;

    public ConnectionApisImplLegacy_Factory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3, xy1 xy1Var4) {
        this.connectivityListenerProvider = xy1Var;
        this.flightModeEnabledMonitorProvider = xy1Var2;
        this.mobileDataDisabledMonitorProvider = xy1Var3;
        this.internetMonitorProvider = xy1Var4;
    }

    public static ConnectionApisImplLegacy_Factory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3, xy1 xy1Var4) {
        return new ConnectionApisImplLegacy_Factory(xy1Var, xy1Var2, xy1Var3, xy1Var4);
    }

    public static ConnectionApisImplLegacy newInstance(ConnectivityListener connectivityListener, FlightModeEnabledMonitor flightModeEnabledMonitor, MobileDataDisabledMonitor mobileDataDisabledMonitor, InternetMonitor internetMonitor) {
        return new ConnectionApisImplLegacy(connectivityListener, flightModeEnabledMonitor, mobileDataDisabledMonitor, internetMonitor);
    }

    @Override // p.xy1
    public ConnectionApisImplLegacy get() {
        return newInstance((ConnectivityListener) this.connectivityListenerProvider.get(), (FlightModeEnabledMonitor) this.flightModeEnabledMonitorProvider.get(), (MobileDataDisabledMonitor) this.mobileDataDisabledMonitorProvider.get(), (InternetMonitor) this.internetMonitorProvider.get());
    }
}
