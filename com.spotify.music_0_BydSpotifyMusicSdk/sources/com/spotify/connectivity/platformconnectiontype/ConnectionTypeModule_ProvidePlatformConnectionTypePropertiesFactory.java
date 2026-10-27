package com.spotify.connectivity.platformconnectiontype;

import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeModule_ProvidePlatformConnectionTypePropertiesFactory implements ul0 {
    private final xy1 netCapabilitiesValidatedDisabledProvider;
    private final xy1 shouldUseSingleThreadProvider;

    public ConnectionTypeModule_ProvidePlatformConnectionTypePropertiesFactory(xy1 xy1Var, xy1 xy1Var2) {
        this.netCapabilitiesValidatedDisabledProvider = xy1Var;
        this.shouldUseSingleThreadProvider = xy1Var2;
    }

    public static ConnectionTypeModule_ProvidePlatformConnectionTypePropertiesFactory create(xy1 xy1Var, xy1 xy1Var2) {
        return new ConnectionTypeModule_ProvidePlatformConnectionTypePropertiesFactory(xy1Var, xy1Var2);
    }

    public static PlatformConnectionTypeProperties providePlatformConnectionTypeProperties(boolean z, boolean z2) {
        PlatformConnectionTypeProperties platformConnectionTypePropertiesProvidePlatformConnectionTypeProperties = ConnectionTypeModule.providePlatformConnectionTypeProperties(z, z2);
        vr2.b(platformConnectionTypePropertiesProvidePlatformConnectionTypeProperties);
        return platformConnectionTypePropertiesProvidePlatformConnectionTypeProperties;
    }

    @Override // p.xy1
    public PlatformConnectionTypeProperties get() {
        return providePlatformConnectionTypeProperties(((Boolean) this.netCapabilitiesValidatedDisabledProvider.get()).booleanValue(), ((Boolean) this.shouldUseSingleThreadProvider.get()).booleanValue());
    }
}
