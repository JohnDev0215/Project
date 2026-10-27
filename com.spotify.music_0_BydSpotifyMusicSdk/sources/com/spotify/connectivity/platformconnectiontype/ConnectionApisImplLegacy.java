package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionApis;
import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.connectiontype.InternetMonitor;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.operators.observable.m3;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionApisImplLegacy implements ConnectionApis {
    private final ConnectivityListener connectivityListener;
    private final FlightModeEnabledMonitor flightModeEnabledMonitor;
    private final InternetConnectivityObservableImpl internetConnectivityObservable;
    private final InternetMonitor internetMonitor;
    private final MobileDataDisabledMonitor mobileDataDisabledMonitor;

    public ConnectionApisImplLegacy(ConnectivityListener connectivityListener, FlightModeEnabledMonitor flightModeEnabledMonitor, MobileDataDisabledMonitor mobileDataDisabledMonitor, InternetMonitor internetMonitor) {
        v03.j(connectivityListener, "connectivityListener");
        v03.j(flightModeEnabledMonitor, "flightModeEnabledMonitor");
        v03.j(mobileDataDisabledMonitor, "mobileDataDisabledMonitor");
        v03.j(internetMonitor, "internetMonitor");
        this.connectivityListener = connectivityListener;
        this.flightModeEnabledMonitor = flightModeEnabledMonitor;
        this.mobileDataDisabledMonitor = mobileDataDisabledMonitor;
        this.internetMonitor = internetMonitor;
        this.internetConnectivityObservable = new InternetConnectivityObservableImpl(connectivityListener);
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public ConnectionType getConnectionType() {
        return this.internetConnectivityObservable.getNetworkConnectivity();
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public Observable<ConnectionType> getConnectionTypeObservable() {
        io.reactivex.rxjava3.observables.a aVarReplay = this.internetConnectivityObservable.createConnectionType().distinctUntilChanged().replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }

    public final ConnectivityListener getConnectivityListener() {
        return this.connectivityListener;
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

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public boolean isConnected() {
        return this.internetConnectivityObservable.isConnected();
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public Observable<Boolean> isConnectedObservable() {
        io.reactivex.rxjava3.observables.a aVarReplay = this.internetConnectivityObservable.create().distinctUntilChanged().replay(1);
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
        InternetMonitor internetMonitor = this.internetMonitor;
        ConnectionType connectionType = getConnectionType();
        v03.i(connectionType, "getConnectionType()");
        return internetMonitor.isPermanentOffline(connectionType, isMobileDataDisabled(), isFlightModeEnabled());
    }

    @Override // com.spotify.connectivity.connectiontype.ConnectionApis
    public Observable<Boolean> isPermanentlyOfflineObservable() {
        io.reactivex.rxjava3.observables.a aVarReplay = this.internetMonitor.getPermanentOfflineState().distinctUntilChanged().replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }
}
