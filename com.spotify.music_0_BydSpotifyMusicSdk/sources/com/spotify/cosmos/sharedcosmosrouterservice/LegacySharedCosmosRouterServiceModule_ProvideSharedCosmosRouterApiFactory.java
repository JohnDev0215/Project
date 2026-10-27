package com.spotify.cosmos.sharedcosmosrouterservice;

import com.spotify.cosmos.sharedcosmosrouterapi.SharedCosmosRouterApi;
import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class LegacySharedCosmosRouterServiceModule_ProvideSharedCosmosRouterApiFactory implements ul0 {
    private final xy1 sharedCosmosRouterServiceProvider;

    public LegacySharedCosmosRouterServiceModule_ProvideSharedCosmosRouterApiFactory(xy1 xy1Var) {
        this.sharedCosmosRouterServiceProvider = xy1Var;
    }

    public static LegacySharedCosmosRouterServiceModule_ProvideSharedCosmosRouterApiFactory create(xy1 xy1Var) {
        return new LegacySharedCosmosRouterServiceModule_ProvideSharedCosmosRouterApiFactory(xy1Var);
    }

    public static SharedCosmosRouterApi provideSharedCosmosRouterApi(SharedCosmosRouterService sharedCosmosRouterService) {
        SharedCosmosRouterApi sharedCosmosRouterApiProvideSharedCosmosRouterApi = LegacySharedCosmosRouterServiceModule.INSTANCE.provideSharedCosmosRouterApi(sharedCosmosRouterService);
        vr2.b(sharedCosmosRouterApiProvideSharedCosmosRouterApi);
        return sharedCosmosRouterApiProvideSharedCosmosRouterApi;
    }

    @Override // p.xy1
    public SharedCosmosRouterApi get() {
        return provideSharedCosmosRouterApi((SharedCosmosRouterService) this.sharedCosmosRouterServiceProvider.get());
    }
}
