package com.spotify.connectivity.connectiontypeflags;

import com.spotify.remoteconfig.b;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeFlagsServiceKt {
    public static final ConnectionTypeFlagsService createConnectionTypeFlagsService(ConnectionTypeFlagsServiceDependencies connectionTypeFlagsServiceDependencies) {
        v03.j(connectionTypeFlagsServiceDependencies, "dependencies");
        connectionTypeFlagsServiceDependencies.getRemoteConfigUnauthResolverApi();
        v03.j(null, "configProvider");
        return new ConnectionTypeFlagsService(new b(false, false), new ConnectionTypePropertiesWriter(connectionTypeFlagsServiceDependencies.getSharedPrefs()));
    }
}
