package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class FlightModeEnabledMonitorModule {
    public static final FlightModeEnabledMonitorModule INSTANCE = new FlightModeEnabledMonitorModule();

    private FlightModeEnabledMonitorModule() {
    }

    public final FlightModeEnabledMonitor provideFlightModeEnabledMonitor(Context context) {
        v03.j(context, "context");
        return new FlightModeEnabledMonitorImpl(context);
    }
}
