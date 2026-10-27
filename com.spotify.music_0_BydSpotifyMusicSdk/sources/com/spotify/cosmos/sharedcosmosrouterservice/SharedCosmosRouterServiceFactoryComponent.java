package com.spotify.cosmos.sharedcosmosrouterservice;

/* JADX INFO: loaded from: classes.dex */
interface SharedCosmosRouterServiceFactoryComponent {

    public interface Factory {
        SharedCosmosRouterServiceFactoryComponent create(SharedCosmosRouterServiceDependencies sharedCosmosRouterServiceDependencies);
    }

    SharedCosmosRouterService sharedCosmosRouterService();
}
