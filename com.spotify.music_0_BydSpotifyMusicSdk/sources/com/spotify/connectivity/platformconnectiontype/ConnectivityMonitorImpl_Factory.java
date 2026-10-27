package com.spotify.connectivity.platformconnectiontype;

import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectivityMonitorImpl_Factory implements ul0 {
    private final xy1 connectivityListenerProvider;

    public ConnectivityMonitorImpl_Factory(xy1 xy1Var) {
        this.connectivityListenerProvider = xy1Var;
    }

    public static ConnectivityMonitorImpl_Factory create(xy1 xy1Var) {
        return new ConnectivityMonitorImpl_Factory(xy1Var);
    }

    public static ConnectivityMonitorImpl newInstance(ConnectivityListener connectivityListener) {
        return new ConnectivityMonitorImpl(connectivityListener);
    }

    @Override // p.xy1
    public ConnectivityMonitorImpl get() {
        return newInstance((ConnectivityListener) this.connectivityListenerProvider.get());
    }
}
