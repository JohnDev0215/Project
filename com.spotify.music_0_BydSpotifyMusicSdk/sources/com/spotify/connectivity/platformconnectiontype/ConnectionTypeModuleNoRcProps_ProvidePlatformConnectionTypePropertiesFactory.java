package com.spotify.connectivity.platformconnectiontype;

import p.ul0;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeModuleNoRcProps_ProvidePlatformConnectionTypePropertiesFactory implements ul0 {

    public static final class InstanceHolder {
        private static final ConnectionTypeModuleNoRcProps_ProvidePlatformConnectionTypePropertiesFactory INSTANCE = new ConnectionTypeModuleNoRcProps_ProvidePlatformConnectionTypePropertiesFactory();

        private InstanceHolder() {
        }
    }

    public static ConnectionTypeModuleNoRcProps_ProvidePlatformConnectionTypePropertiesFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static PlatformConnectionTypeProperties providePlatformConnectionTypeProperties() {
        PlatformConnectionTypeProperties platformConnectionTypePropertiesProvidePlatformConnectionTypeProperties = ConnectionTypeModuleNoRcProps.providePlatformConnectionTypeProperties();
        vr2.b(platformConnectionTypePropertiesProvidePlatformConnectionTypeProperties);
        return platformConnectionTypePropertiesProvidePlatformConnectionTypeProperties;
    }

    @Override // p.xy1
    public PlatformConnectionTypeProperties get() {
        return providePlatformConnectionTypeProperties();
    }
}
