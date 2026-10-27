package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionApis;
import com.spotify.connectivity.connectiontype.ConnectionType;
import io.reactivex.rxjava3.core.Observable;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionApisModule_ProvideConnectionTypeObservableFactory implements ul0 {
    private final xy1 connectionApisProvider;

    public ConnectionApisModule_ProvideConnectionTypeObservableFactory(xy1 xy1Var) {
        this.connectionApisProvider = xy1Var;
    }

    public static ConnectionApisModule_ProvideConnectionTypeObservableFactory create(xy1 xy1Var) {
        return new ConnectionApisModule_ProvideConnectionTypeObservableFactory(xy1Var);
    }

    public static Observable<ConnectionType> provideConnectionTypeObservable(ConnectionApis connectionApis) {
        Observable<ConnectionType> observableProvideConnectionTypeObservable = ConnectionApisModule.provideConnectionTypeObservable(connectionApis);
        vr2.b(observableProvideConnectionTypeObservable);
        return observableProvideConnectionTypeObservable;
    }

    @Override // p.xy1
    public Observable<ConnectionType> get() {
        return provideConnectionTypeObservable((ConnectionApis) this.connectionApisProvider.get());
    }
}
