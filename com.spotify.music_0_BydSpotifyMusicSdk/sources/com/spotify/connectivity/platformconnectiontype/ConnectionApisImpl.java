package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionApis;
import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.connectiontype.InternetMonitor;
import com.spotify.connectivity.connectiontype.SpotifyConnectivityManager;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.operators.observable.m3;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionApisImpl implements ConnectionApis {
    private final FlightModeEnabledMonitor flightModeEnabledMonitor;
    private final InternetMonitor internetMonitor;
    private final MobileDataDisabledMonitor mobileDataDisabledMonitor;
    private final SpotifyConnectivityManager spotifyConnectivityManager;

    public ConnectionApisImpl(FlightModeEnabledMonitor flightModeEnabledMonitor, MobileDataDisabledMonitor mobileDataDisabledMonitor, InternetMonitor internetMonitor, SpotifyConnectivityManager spotifyConnectivityManager) {
        v03.j(flightModeEnabledMonitor, "flightModeEnabledMonitor");
        v03.j(mobileDataDisabledMonitor, "mobileDataDisabledMonitor");
        v03.j(internetMonitor, "internetMonitor");
        v03.j(spotifyConnectivityManager, "spotifyConnectivityManager");
        this.flightModeEnabledMonitor = flightModeEnabledMonitor;
        this.mobileDataDisabledMonitor = mobileDataDisabledMonitor;
        this.internetMonitor = internetMonitor;
        this.spotifyConnectivityManager = spotifyConnectivityManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isConnected(ConnectionType connectionType) {
        return connectionType != ConnectionType.CONNECTION_TYPE_NONE;
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public ConnectionType getConnectionType() {
        ConnectionType connectionType = this.spotifyConnectivityManager.getConnectionType();
        v03.i(connectionType, "spotifyConnectivityManager.connectionType");
        return connectionType;
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public Observable<ConnectionType> getConnectionTypeObservable() {
        Observable<ConnectionType> connectionTypeObservable = this.spotifyConnectivityManager.getConnectionTypeObservable();
        v03.i(connectionTypeObservable, "spotifyConnectivityManag….connectionTypeObservable");
        return connectionTypeObservable;
    }

    public final FlightModeEnabledMonitor getFlightModeEnabledMonitor() {
        return this.flightModeEnabledMonitor;
    }

    public final InternetMonitor getInternetMonitor() {
        return this.internetMonitor;
    }

    public final MobileDataDisabledMonitor getMobileDataDisabledMonitor() {
        return this.mobileDataDisabledMonitor;
    }

    public final SpotifyConnectivityManager getSpotifyConnectivityManager() {
        return this.spotifyConnectivityManager;
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public Observable<Boolean> isConnectedObservable() {
        io.reactivex.rxjava3.observables.a aVarReplay = getConnectionTypeObservable().map(new o() { // from class: com.spotify.connectivity.platformconnectiontype.ConnectionApisImpl.isConnectedObservable.1
            @Override // io.reactivex.rxjava3.functions.o
            public final Boolean apply(ConnectionType connectionType) {
                ConnectionApisImpl connectionApisImpl = ConnectionApisImpl.this;
                v03.i(connectionType, "it");
                return Boolean.valueOf(connectionApisImpl.isConnected(connectionType));
            }
        }).distinctUntilChanged().replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public boolean isFlightModeEnabled() {
        return this.flightModeEnabledMonitor.isFlightModeEnabledCurrently();
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public Observable<Boolean> isFlightModeEnabledObservable() {
        io.reactivex.rxjava3.observables.a aVarReplay = this.flightModeEnabledMonitor.isFlightModeEnabled().distinctUntilChanged().replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public boolean isMobileDataDisabled() {
        return this.mobileDataDisabledMonitor.getMobileDataDisabled();
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public Observable<Boolean> isMobileDataDisabledObservable() {
        io.reactivex.rxjava3.observables.a aVarReplay = this.mobileDataDisabledMonitor.isMobileDataDisabled().distinctUntilChanged().replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public boolean isPermanentlyOffline() {
        return this.internetMonitor.isPermanentOffline(getConnectionType(), isMobileDataDisabled(), isFlightModeEnabled());
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public Observable<Boolean> isPermanentlyOfflineObservable() {
        io.reactivex.rxjava3.observables.a aVarReplay = this.internetMonitor.getPermanentOfflineState().distinctUntilChanged().replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public boolean isConnected() {
        return isConnected(getConnectionType());
    }
}
