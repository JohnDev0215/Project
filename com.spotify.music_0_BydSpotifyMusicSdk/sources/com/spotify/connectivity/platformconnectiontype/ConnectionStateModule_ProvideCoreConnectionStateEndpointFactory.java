package com.spotify.connectivity.platformconnectiontype;

import com.spotify.cosmos.cosmonaut.Cosmonaut;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionStateModule_ProvideCoreConnectionStateEndpointFactory implements ul0 {
    private final xy1 cosmonautProvider;

    public ConnectionStateModule_ProvideCoreConnectionStateEndpointFactory(xy1 xy1Var) {
        this.cosmonautProvider = xy1Var;
    }

    public static ConnectionStateModule_ProvideCoreConnectionStateEndpointFactory create(xy1 xy1Var) {
        return new ConnectionStateModule_ProvideCoreConnectionStateEndpointFactory(xy1Var);
    }

    public static CoreConnectionState provideCoreConnectionStateEndpoint(Cosmonaut cosmonaut) {
        CoreConnectionState coreConnectionStateProvideCoreConnectionStateEndpoint = ConnectionStateModule.provideCoreConnectionStateEndpoint(cosmonaut);
        vr2.b(coreConnectionStateProvideCoreConnectionStateEndpoint);
        return coreConnectionStateProvideCoreConnectionStateEndpoint;
    }

    @Override // p.xy1
    public CoreConnectionState get() {
        return provideCoreConnectionStateEndpoint((Cosmonaut) this.cosmonautProvider.get());
    }
}
