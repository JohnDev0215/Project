package com.spotify.cosmos.sharedcosmosrouterservice;

/* JADX INFO: loaded from: classes.dex */
public final class RemoteRouterFactoryModule {
    public static final RemoteRouterFactoryModule INSTANCE = new RemoteRouterFactoryModule();

    private RemoteRouterFactoryModule() {
    }

    public final RemoteRouterFactory provideRemoteRouterFactory() {
        return new RemoteRouterFactory();
    }
}
