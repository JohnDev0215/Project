package com.spotify.connectivity.platformconnectiontype;

import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeModuleNoRcProps_ProvideConnectivityUtilFactory implements ul0 {
    private final xy1 propertiesProvider;

    public ConnectionTypeModuleNoRcProps_ProvideConnectivityUtilFactory(xy1 xy1Var) {
        this.propertiesProvider = xy1Var;
    }

    public static ConnectionTypeModuleNoRcProps_ProvideConnectivityUtilFactory create(xy1 xy1Var) {
        return new ConnectionTypeModuleNoRcProps_ProvideConnectivityUtilFactory(xy1Var);
    }

    public static ConnectivityUtil provideConnectivityUtil(PlatformConnectionTypeProperties platformConnectionTypeProperties) {
        ConnectivityUtil connectivityUtilProvideConnectivityUtil = ConnectionTypeModuleNoRcProps.provideConnectivityUtil(platformConnectionTypeProperties);
        vr2.b(connectivityUtilProvideConnectivityUtil);
        return connectivityUtilProvideConnectivityUtil;
    }

    @Override // p.xy1
    public ConnectivityUtil get() {
        return provideConnectivityUtil((PlatformConnectionTypeProperties) this.propertiesProvider.get());
    }
}
