package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionApis;
import com.spotify.connectivity.connectiontype.ConnectionState;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionStateModule_ProvideConnectionStateFactory implements ul0 {
    private final xy1 connectionApisProvider;
    private final xy1 endpointProvider;
    private final xy1 ioSchedulerProvider;

    public ConnectionStateModule_ProvideConnectionStateFactory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        this.endpointProvider = xy1Var;
        this.connectionApisProvider = xy1Var2;
        this.ioSchedulerProvider = xy1Var3;
    }

    public static ConnectionStateModule_ProvideConnectionStateFactory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        return new ConnectionStateModule_ProvideConnectionStateFactory(xy1Var, xy1Var2, xy1Var3);
    }

    public static Observable<ConnectionState> provideConnectionState(CoreConnectionState coreConnectionState, ConnectionApis connectionApis, Scheduler scheduler) {
        Observable<ConnectionState> observableProvideConnectionState = ConnectionStateModule.provideConnectionState(coreConnectionState, connectionApis, scheduler);
        vr2.b(observableProvideConnectionState);
        return observableProvideConnectionState;
    }

    @Override // p.xy1
    public Observable<ConnectionState> get() {
        return provideConnectionState((CoreConnectionState) this.endpointProvider.get(), (ConnectionApis) this.connectionApisProvider.get(), (Scheduler) this.ioSchedulerProvider.get());
    }
}
