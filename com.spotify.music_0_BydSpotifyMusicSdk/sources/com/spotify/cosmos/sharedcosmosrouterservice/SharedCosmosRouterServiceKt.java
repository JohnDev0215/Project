package com.spotify.cosmos.sharedcosmosrouterservice;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SharedCosmosRouterServiceKt {
    public static final SharedCosmosRouterService createSharedCosmosRouterService(SharedCosmosRouterServiceDependencies sharedCosmosRouterServiceDependencies) {
        v03.j(sharedCosmosRouterServiceDependencies, "dependencies");
        return DaggerSharedCosmosRouterServiceFactoryComponent.factory().create(sharedCosmosRouterServiceDependencies).sharedCosmosRouterService();
    }
}
