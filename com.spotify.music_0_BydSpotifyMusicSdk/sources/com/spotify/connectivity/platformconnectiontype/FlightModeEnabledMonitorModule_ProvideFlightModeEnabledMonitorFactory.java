package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class FlightModeEnabledMonitorModule_ProvideFlightModeEnabledMonitorFactory implements ul0 {
    private final xy1 contextProvider;

    public FlightModeEnabledMonitorModule_ProvideFlightModeEnabledMonitorFactory(xy1 xy1Var) {
        this.contextProvider = xy1Var;
    }

    public static FlightModeEnabledMonitorModule_ProvideFlightModeEnabledMonitorFactory create(xy1 xy1Var) {
        return new FlightModeEnabledMonitorModule_ProvideFlightModeEnabledMonitorFactory(xy1Var);
    }

    public static FlightModeEnabledMonitor provideFlightModeEnabledMonitor(Context context) {
        FlightModeEnabledMonitor flightModeEnabledMonitorProvideFlightModeEnabledMonitor = FlightModeEnabledMonitorModule.INSTANCE.provideFlightModeEnabledMonitor(context);
        vr2.b(flightModeEnabledMonitorProvideFlightModeEnabledMonitor);
        return flightModeEnabledMonitorProvideFlightModeEnabledMonitor;
    }

    @Override // p.xy1
    public FlightModeEnabledMonitor get() {
        return provideFlightModeEnabledMonitor((Context) this.contextProvider.get());
    }
}
