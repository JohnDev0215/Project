package com.spotify.cosmos.sharedcosmosrouterservice;

import p.ul0;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class RemoteRouterFactoryModule_ProvideRemoteRouterFactoryFactory implements ul0 {

    public static final class InstanceHolder {
        private static final RemoteRouterFactoryModule_ProvideRemoteRouterFactoryFactory INSTANCE = new RemoteRouterFactoryModule_ProvideRemoteRouterFactoryFactory();

        private InstanceHolder() {
        }
    }

    public static RemoteRouterFactoryModule_ProvideRemoteRouterFactoryFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static RemoteRouterFactory provideRemoteRouterFactory() {
        RemoteRouterFactory remoteRouterFactoryProvideRemoteRouterFactory = RemoteRouterFactoryModule.INSTANCE.provideRemoteRouterFactory();
        vr2.b(remoteRouterFactoryProvideRemoteRouterFactory);
        return remoteRouterFactoryProvideRemoteRouterFactory;
    }

    @Override // p.xy1
    public RemoteRouterFactory get() {
        return provideRemoteRouterFactory();
    }
}
