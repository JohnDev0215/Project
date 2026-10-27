package com.spotify.cosmos.sharedcosmosrouterservice;

import p.r10;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SharedCosmosRouterServiceDependencies {
    private final r10 coreThreadingApi;

    public SharedCosmosRouterServiceDependencies(r10 r10Var) {
        v03.j(r10Var, "coreThreadingApi");
        this.coreThreadingApi = r10Var;
    }

    public final r10 getCoreThreadingApi() {
        return this.coreThreadingApi;
    }
}
