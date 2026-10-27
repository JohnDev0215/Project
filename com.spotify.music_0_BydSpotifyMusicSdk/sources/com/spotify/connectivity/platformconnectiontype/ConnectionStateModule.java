package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionApis;
import com.spotify.connectivity.connectiontype.ConnectionState;
import com.spotify.connectivity.connectiontype.ConnectivityManager;
import com.spotify.connectivity.esperanto.proto.ConnectivityPolicyClient;
import com.spotify.connectivity.esperanto.proto.EsConnectivityPolicyEsperantoKt;
import com.spotify.cosmos.cosmonaut.Cosmonaut;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.esperanto.esperantocosmos.CosmosTransport;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.operators.observable.m3;
import java.util.Objects;
import p.ed1;
import p.sx;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectionStateModule {
    static Observable<ConnectionState> provideConnectionState(CoreConnectionState coreConnectionState, ConnectionApis connectionApis, Scheduler scheduler) {
        io.reactivex.rxjava3.observables.a aVarReplay = ConnectionStateObservable.create(coreConnectionState.connection().map(new ed1(26)), connectionApis.isConnectedObservable()).observeOn(scheduler).replay(1);
        aVarReplay.getClass();
        return new m3(aVarReplay);
    }

    static ConnectivityPolicyClient provideConnectivityPolicyClient(RxRouter rxRouter) {
        Objects.requireNonNull(rxRouter);
        return EsConnectivityPolicyEsperantoKt.createConnectivityPolicyClient(new CosmosTransport(new sx(rxRouter, 0)));
    }

    static CoreConnectionState provideCoreConnectionStateEndpoint(Cosmonaut cosmonaut) {
        return (CoreConnectionState) cosmonaut.createCosmosService(CoreConnectionState.class);
    }

    ConnectivityManager bindConnectivityManager(ConnectivityManagerEsperanto connectivityManagerEsperanto);
}
