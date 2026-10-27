package com.spotify.connectivity.platformconnectiontype;

import android.app.Application;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeModuleNoRcProps_ProvideConnectivityListenerFactory implements ul0 {
    private final xy1 applicationProvider;
    private final xy1 connectivityUtilProvider;
    private final xy1 propertiesProvider;

    public ConnectionTypeModuleNoRcProps_ProvideConnectivityListenerFactory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        this.applicationProvider = xy1Var;
        this.connectivityUtilProvider = xy1Var2;
        this.propertiesProvider = xy1Var3;
    }

    public static ConnectionTypeModuleNoRcProps_ProvideConnectivityListenerFactory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        return new ConnectionTypeModuleNoRcProps_ProvideConnectivityListenerFactory(xy1Var, xy1Var2, xy1Var3);
    }

    public static ConnectivityListener provideConnectivityListener(Application application, ConnectivityUtil connectivityUtil, PlatformConnectionTypeProperties platformConnectionTypeProperties) {
        ConnectivityListener connectivityListenerProvideConnectivityListener = ConnectionTypeModuleNoRcProps.provideConnectivityListener(application, connectivityUtil, platformConnectionTypeProperties);
        vr2.b(connectivityListenerProvideConnectivityListener);
        return connectivityListenerProvideConnectivityListener;
    }

    @Override // p.xy1
    public ConnectivityListener get() {
        return provideConnectivityListener((Application) this.applicationProvider.get(), (ConnectivityUtil) this.connectivityUtilProvider.get(), (PlatformConnectionTypeProperties) this.propertiesProvider.get());
    }
}
