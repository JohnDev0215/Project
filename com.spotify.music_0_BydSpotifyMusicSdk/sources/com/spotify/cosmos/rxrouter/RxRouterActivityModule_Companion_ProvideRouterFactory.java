package com.spotify.cosmos.rxrouter;

import p.ul0;
import p.vr2;
import p.xy1;
import p.zw0;

/* JADX INFO: loaded from: classes.dex */
public final class RxRouterActivityModule_Companion_ProvideRouterFactory implements ul0 {
    private final xy1 activityProvider;
    private final xy1 providerProvider;

    public RxRouterActivityModule_Companion_ProvideRouterFactory(xy1 xy1Var, xy1 xy1Var2) {
        this.providerProvider = xy1Var;
        this.activityProvider = xy1Var2;
    }

    public static RxRouterActivityModule_Companion_ProvideRouterFactory create(xy1 xy1Var, xy1 xy1Var2) {
        return new RxRouterActivityModule_Companion_ProvideRouterFactory(xy1Var, xy1Var2);
    }

    public static RxRouter provideRouter(RxRouterProvider rxRouterProvider, zw0 zw0Var) {
        RxRouter rxRouterProvideRouter = RxRouterActivityModule.Companion.provideRouter(rxRouterProvider, zw0Var);
        vr2.b(rxRouterProvideRouter);
        return rxRouterProvideRouter;
    }

    @Override // p.xy1
    public RxRouter get() {
        return provideRouter((RxRouterProvider) this.providerProvider.get(), (zw0) this.activityProvider.get());
    }
}
