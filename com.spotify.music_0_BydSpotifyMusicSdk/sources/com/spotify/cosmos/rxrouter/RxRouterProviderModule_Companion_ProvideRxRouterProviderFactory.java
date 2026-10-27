package com.spotify.cosmos.rxrouter;

import p.ul0;
import p.vr2;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class RxRouterProviderModule_Companion_ProvideRxRouterProviderFactory implements ul0 {
    private final xy1 rxRouterProvider;

    public RxRouterProviderModule_Companion_ProvideRxRouterProviderFactory(xy1 xy1Var) {
        this.rxRouterProvider = xy1Var;
    }

    public static RxRouterProviderModule_Companion_ProvideRxRouterProviderFactory create(xy1 xy1Var) {
        return new RxRouterProviderModule_Companion_ProvideRxRouterProviderFactory(xy1Var);
    }

    public static RxRouterProvider provideRxRouterProvider(RxRouter rxRouter) {
        RxRouterProvider rxRouterProviderProvideRxRouterProvider = RxRouterProviderModule.Companion.provideRxRouterProvider(rxRouter);
        vr2.b(rxRouterProviderProvideRxRouterProvider);
        return rxRouterProviderProvideRxRouterProvider;
    }

    @Override // p.xy1
    public RxRouterProvider get() {
        return provideRxRouterProvider((RxRouter) this.rxRouterProvider.get());
    }
}
