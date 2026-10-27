package com.spotify.connectivity.platformconnectiontype;

import io.reactivex.rxjava3.core.Observable;
import p.dv0;

/* JADX INFO: loaded from: classes.dex */
public interface FlightModeEnabledMonitor {
    dv0 flightModeEnabledFlow();

    Observable<Boolean> isFlightModeEnabled();

    boolean isFlightModeEnabledCurrently();
}
