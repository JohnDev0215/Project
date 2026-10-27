package com.spotify.cosmos.sharedcosmosrouterservice;

import p.r10;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class SharedCosmosRouterService_Factory implements ul0 {
    private final xy1 coreThreadingApiProvider;
    private final xy1 remoteRouterFactoryProvider;

    public SharedCosmosRouterService_Factory(xy1 xy1Var, xy1 xy1Var2) {
        this.coreThreadingApiProvider = xy1Var;
        this.remoteRouterFactoryProvider = xy1Var2;
    }

    public static SharedCosmosRouterService_Factory create(xy1 xy1Var, xy1 xy1Var2) {
        return new SharedCosmosRouterService_Factory(xy1Var, xy1Var2);
    }

    public static SharedCosmosRouterService newInstance(r10 r10Var, RemoteRouterFactory remoteRouterFactory) {
        return new SharedCosmosRouterService(r10Var, remoteRouterFactory);
    }

    @Override // p.xy1
    public SharedCosmosRouterService get() {
        return newInstance((r10) this.coreThreadingApiProvider.get(), (RemoteRouterFactory) this.remoteRouterFactoryProvider.get());
    }
}
