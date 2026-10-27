package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.esperanto.proto.ConnectivityPolicyClient;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectivityManagerEsperanto_Factory implements ul0 {
    private final xy1 clientProvider;

    public ConnectivityManagerEsperanto_Factory(xy1 xy1Var) {
        this.clientProvider = xy1Var;
    }

    public static ConnectivityManagerEsperanto_Factory create(xy1 xy1Var) {
        return new ConnectivityManagerEsperanto_Factory(xy1Var);
    }

    public static ConnectivityManagerEsperanto newInstance(ConnectivityPolicyClient connectivityPolicyClient) {
        return new ConnectivityManagerEsperanto(connectivityPolicyClient);
    }

    @Override // p.xy1
    public ConnectivityManagerEsperanto get() {
        return newInstance((ConnectivityPolicyClient) this.clientProvider.get());
    }
}
