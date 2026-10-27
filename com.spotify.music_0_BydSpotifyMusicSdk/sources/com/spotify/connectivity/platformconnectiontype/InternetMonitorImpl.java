package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionType;
import com.spotify.connectivity.connectiontype.InternetMonitor;
import com.spotify.connectivity.connectiontype.InternetState;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.h;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.functions.q;
import p.c00;
import p.dv0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class InternetMonitorImpl implements InternetMonitor {
    private final Observable<InternetState> observable;

    public InternetMonitorImpl(final ConnectivityMonitorImpl connectivityMonitorImpl, final FlightModeEnabledMonitor flightModeEnabledMonitor, final MobileDataDisabledMonitor mobileDataDisabledMonitor) {
        v03.j(connectivityMonitorImpl, "connectivityMonitor");
        v03.j(flightModeEnabledMonitor, "flightModeEnabledMonitor");
        v03.j(mobileDataDisabledMonitor, "mobileDataDisabledMonitor");
        this.observable = Observable.defer(new q() { // from class: com.spotify.connectivity.platformconnectiontype.InternetMonitorImpl$observable$1
            @Override // io.reactivex.rxjava3.functions.q
            public final ObservableSource<? extends InternetState> get() {
                return Observable.combineLatest(connectivityMonitorImpl.getConnectionTypes(), flightModeEnabledMonitor.isFlightModeEnabled(), mobileDataDisabledMonitor.isMobileDataDisabled(), new h() { // from class: com.spotify.connectivity.platformconnectiontype.InternetMonitorImpl$observable$1.1
                    public final InternetState apply(ConnectionType connectionType, boolean z, boolean z2) {
                        return InternetState.create(connectionType, z, z2);
                    }

                    @Override // io.reactivex.rxjava3.functions.h
                    public /* bridge */ /* synthetic */ Object apply(Object obj, Object obj2, Object obj3) {
                        return apply((ConnectionType) obj, ((Boolean) obj2).booleanValue(), ((Boolean) obj3).booleanValue());
                    }
                });
            }
        });
    }

    @Override // com.spotify.connectivity.connectiontype.InternetMonitor
    public Observable<InternetState> getInternetState() {
        return this.observable;
    }

    @Override // com.spotify.connectivity.connectiontype.InternetMonitor
    public dv0 getInternetStateFlow() {
        Observable<InternetState> internetState = getInternetState();
        v03.i(internetState, "getInternetState()");
        return kotlinx.coroutines.rx3.a.a(internetState);
    }

    @Override // com.spotify.connectivity.connectiontype.InternetMonitor
    public Observable<Boolean> getPermanentOfflineState() {
        return getInternetState().map(new o() { // from class: com.spotify.connectivity.platformconnectiontype.InternetMonitorImpl.getPermanentOfflineState.1
            @Override // io.reactivex.rxjava3.functions.o
            public final Boolean apply(InternetState internetState) {
                v03.j(internetState, "state");
                InternetMonitorImpl internetMonitorImpl = InternetMonitorImpl.this;
                ConnectionType connectionType = internetState.connectionType();
                v03.i(connectionType, "state.connectionType()");
                return Boolean.valueOf(internetMonitorImpl.isPermanentOffline(connectionType, internetState.mobileDataDisabled(), internetState.flightModeEnabled()));
            }
        });
    }

    @Override // com.spotify.connectivity.connectiontype.InternetMonitor
    public dv0 getPermanentOfflineStateFlow() {
        Observable<Boolean> permanentOfflineState = getPermanentOfflineState();
        v03.i(permanentOfflineState, "getPermanentOfflineState()");
        return kotlinx.coroutines.rx3.a.a(permanentOfflineState);
    }

    @Override // com.spotify.connectivity.connectiontype.InternetMonitor
    public Object isPermanentOffline(c00<? super Boolean> c00Var) {
        Single<Boolean> singleIsPermanentlyOffline = isPermanentlyOffline();
        v03.i(singleIsPermanentlyOffline, "isPermanentlyOffline()");
        return kotlinx.coroutines.rx3.a.c(singleIsPermanentlyOffline, c00Var);
    }

    @Override // com.spotify.connectivity.connectiontype.InternetMonitor
    public Single<Boolean> isPermanentlyOffline() {
        return getPermanentOfflineState().firstOrError();
    }

    @Override // com.spotify.connectivity.connectiontype.InternetMonitor
    public boolean isPermanentOffline(ConnectionType connectionType, boolean z, boolean z2) {
        v03.j(connectionType, "connectionType");
        return !(connectionType == ConnectionType.CONNECTION_TYPE_UNKNOWN || !connectionType.isOffline()) && (z || z2);
    }
}
