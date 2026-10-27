package com.spotify.cosmos.sharedcosmosrouterservice;

import com.spotify.cosmos.servicebasedrouter.RemoteNativeRouter;
import p.em1;
import p.r10;
import p.u4;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class LegacySharedCosmosRouterServiceModule_ProvideSharedCosmosRouterServiceFactory implements ul0 {
    private final xy1 coreThreadingApiProvider;
    private final xy1 nativeLibraryProvider;
    private final xy1 remoteNativeRouterProvider;

    public LegacySharedCosmosRouterServiceModule_ProvideSharedCosmosRouterServiceFactory(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        this.nativeLibraryProvider = xy1Var;
        this.coreThreadingApiProvider = xy1Var2;
        this.remoteNativeRouterProvider = xy1Var3;
    }

    public static LegacySharedCosmosRouterServiceModule_ProvideSharedCosmosRouterServiceFactory create(xy1 xy1Var, xy1 xy1Var2, xy1 xy1Var3) {
        return new LegacySharedCosmosRouterServiceModule_ProvideSharedCosmosRouterServiceFactory(xy1Var, xy1Var2, xy1Var3);
    }

    public static SharedCosmosRouterService provideSharedCosmosRouterService(em1 em1Var, r10 r10Var, RemoteNativeRouter remoteNativeRouter) {
        SharedCosmosRouterService sharedCosmosRouterServiceProvideSharedCosmosRouterService = LegacySharedCosmosRouterServiceModule.INSTANCE.provideSharedCosmosRouterService(em1Var, r10Var, remoteNativeRouter);
        vr2.b(sharedCosmosRouterServiceProvideSharedCosmosRouterService);
        return sharedCosmosRouterServiceProvideSharedCosmosRouterService;
    }

    @Override // p.xy1
    public SharedCosmosRouterService get() {
        u4.z(this.nativeLibraryProvider.get());
        return provideSharedCosmosRouterService(null, (r10) this.coreThreadingApiProvider.get(), (RemoteNativeRouter) this.remoteNativeRouterProvider.get());
    }
}
