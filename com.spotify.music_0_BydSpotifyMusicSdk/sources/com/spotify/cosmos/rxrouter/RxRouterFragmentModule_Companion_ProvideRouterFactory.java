package com.spotify.cosmos.rxrouter;

import p.ul0;
import p.vr2;
import p.ww0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class RxRouterFragmentModule_Companion_ProvideRouterFactory implements ul0 {
    private final xy1 fragmentProvider;
    private final xy1 providerProvider;

    public RxRouterFragmentModule_Companion_ProvideRouterFactory(xy1 xy1Var, xy1 xy1Var2) {
        this.providerProvider = xy1Var;
        this.fragmentProvider = xy1Var2;
    }

    public static RxRouterFragmentModule_Companion_ProvideRouterFactory create(xy1 xy1Var, xy1 xy1Var2) {
        return new RxRouterFragmentModule_Companion_ProvideRouterFactory(xy1Var, xy1Var2);
    }

    public static RxRouter provideRouter(RxRouterProvider rxRouterProvider, ww0 ww0Var) {
        RxRouter rxRouterProvideRouter = RxRouterFragmentModule.Companion.provideRouter(rxRouterProvider, ww0Var);
        vr2.b(rxRouterProvideRouter);
        return rxRouterProvideRouter;
    }

    @Override // p.xy1
    public RxRouter get() {
        return provideRouter((RxRouterProvider) this.providerProvider.get(), (ww0) this.fragmentProvider.get());
    }
}
