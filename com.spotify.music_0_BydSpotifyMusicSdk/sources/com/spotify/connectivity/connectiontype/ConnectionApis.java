package com.spotify.connectivity.connectiontype;

import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectionApis {
    ConnectionType getConnectionType();

    Observable<ConnectionType> getConnectionTypeObservable();

    boolean isConnected();

    Observable<Boolean> isConnectedObservable();

    boolean isFlightModeEnabled();

    Observable<Boolean> isFlightModeEnabledObservable();

    boolean isMobileDataDisabled();

    Observable<Boolean> isMobileDataDisabledObservable();

    boolean isPermanentlyOffline();

    Observable<Boolean> isPermanentlyOfflineObservable();
}
