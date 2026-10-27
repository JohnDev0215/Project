package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionApisServiceScopeModule_Companion_ProvideFlightModeEnabledMonitorFactory implements ul0 {
    private final xy1 contextProvider;

    public ConnectionApisServiceScopeModule_Companion_ProvideFlightModeEnabledMonitorFactory(xy1 xy1Var) {
        this.contextProvider = xy1Var;
    }

    public static ConnectionApisServiceScopeModule_Companion_ProvideFlightModeEnabledMonitorFactory create(xy1 xy1Var) {
        return new ConnectionApisServiceScopeModule_Companion_ProvideFlightModeEnabledMonitorFactory(xy1Var);
    }

    public static FlightModeEnabledMonitor provideFlightModeEnabledMonitor(Context context) {
        FlightModeEnabledMonitor flightModeEnabledMonitorProvideFlightModeEnabledMonitor = ConnectionApisServiceScopeModule.Companion.provideFlightModeEnabledMonitor(context);
        vr2.b(flightModeEnabledMonitorProvideFlightModeEnabledMonitor);
        return flightModeEnabledMonitorProvideFlightModeEnabledMonitor;
    }

    @Override // p.xy1
    public FlightModeEnabledMonitor get() {
        return provideFlightModeEnabledMonitor((Context) this.contextProvider.get());
    }
}
