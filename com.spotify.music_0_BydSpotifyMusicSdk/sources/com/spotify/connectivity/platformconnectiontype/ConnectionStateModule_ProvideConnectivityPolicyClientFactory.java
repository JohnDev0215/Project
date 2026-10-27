package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.esperanto.proto.ConnectivityPolicyClient;
import com.spotify.cosmos.rxrouter.RxRouter;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionStateModule_ProvideConnectivityPolicyClientFactory implements ul0 {
    private final xy1 rxRouterProvider;

    public ConnectionStateModule_ProvideConnectivityPolicyClientFactory(xy1 xy1Var) {
        this.rxRouterProvider = xy1Var;
    }

    public static ConnectionStateModule_ProvideConnectivityPolicyClientFactory create(xy1 xy1Var) {
        return new ConnectionStateModule_ProvideConnectivityPolicyClientFactory(xy1Var);
    }

    public static ConnectivityPolicyClient provideConnectivityPolicyClient(RxRouter rxRouter) {
        ConnectivityPolicyClient connectivityPolicyClientProvideConnectivityPolicyClient = ConnectionStateModule.provideConnectivityPolicyClient(rxRouter);
        vr2.b(connectivityPolicyClientProvideConnectivityPolicyClient);
        return connectivityPolicyClientProvideConnectivityPolicyClient;
    }

    @Override // p.xy1
    public ConnectivityPolicyClient get() {
        return provideConnectivityPolicyClient((RxRouter) this.rxRouterProvider.get());
    }
}
