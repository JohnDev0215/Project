package com.spotify.cosmos.sharedcosmosrouterservice;

import com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter;
import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import p.em1;
import p.r10;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class LegacySharedCosmosRouterServiceModule {
    public static final LegacySharedCosmosRouterServiceModule INSTANCE = new LegacySharedCosmosRouterServiceModule();

    private LegacySharedCosmosRouterServiceModule() {
    }

    public final SharedCosmosRouterApi provideSharedCosmosRouterApi(SharedCosmosRouterService sharedCosmosRouterService) {
        v03.j(sharedCosmosRouterService, "sharedCosmosRouterService");
        return sharedCosmosRouterService.m195getApi();
    }

    public final SharedCosmosRouterService provideSharedCosmosRouterService(em1 em1Var, r10 r10Var, RemoteNativeRouter remoteNativeRouter) {
        v03.j(em1Var, "nativeLibrary");
        v03.j(r10Var, "coreThreadingApi");
        v03.j(remoteNativeRouter, "remoteNativeRouter");
        em1Var.a();
        return new SharedCosmosRouterService(r10Var, remoteNativeRouter);
    }
}
