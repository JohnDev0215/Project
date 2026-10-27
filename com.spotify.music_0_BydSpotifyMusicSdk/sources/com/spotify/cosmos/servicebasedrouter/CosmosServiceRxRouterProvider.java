package com.spotify.cosmos.servicebasedrouter;

import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.cosmos.rxrouter.RxRouterProvider;
import p.l91;
import p.v03;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosServiceRxRouterProvider implements RxRouterProvider {
    private final xy1 routerFactory;

    public CosmosServiceRxRouterProvider(xy1 xy1Var) {
        v03.j(xy1Var, "routerFactory");
        this.routerFactory = xy1Var;
    }

    @Override // com.spotify.cosmos.rxrouter.RxRouterProvider
    public RxRouter provideWithLifecycle(l91 l91Var) {
        v03.j(l91Var, "lifecycle");
        CosmosServiceRxRouter cosmosServiceRxRouter = (CosmosServiceRxRouter) this.routerFactory.get();
        v03.i(cosmosServiceRxRouter, "router");
        l91Var.a(new RouterLifecycleObserver(cosmosServiceRxRouter));
        return cosmosServiceRxRouter;
    }
}
