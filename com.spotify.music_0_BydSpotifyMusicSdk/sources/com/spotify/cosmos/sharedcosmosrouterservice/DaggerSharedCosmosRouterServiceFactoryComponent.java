package com.spotify.cosmos.sharedcosmosrouterservice;

import com.spotify.cosmos.sharedcosmosrouterservice.SharedCosmosRouterServiceFactoryComponent;
import p.r10;

/* JADX INFO: loaded from: classes.dex */
final class DaggerSharedCosmosRouterServiceFactoryComponent {

    public static final class Factory implements SharedCosmosRouterServiceFactoryComponent.Factory {
        private Factory() {
        }

        @Override // com.spotify.cosmos.sharedcosmosrouterservice.SharedCosmosRouterServiceFactoryComponent.Factory
        public SharedCosmosRouterServiceFactoryComponent create(SharedCosmosRouterServiceDependencies sharedCosmosRouterServiceDependencies) {
            sharedCosmosRouterServiceDependencies.getClass();
            return new SharedCosmosRouterServiceFactoryComponentImpl(sharedCosmosRouterServiceDependencies);
        }
    }

    public static final class SharedCosmosRouterServiceFactoryComponentImpl implements SharedCosmosRouterServiceFactoryComponent {
        private final SharedCosmosRouterServiceDependencies sharedCosmosRouterServiceDependencies;
        private final SharedCosmosRouterServiceFactoryComponentImpl sharedCosmosRouterServiceFactoryComponentImpl;

        @Override // com.spotify.cosmos.sharedcosmosrouterservice.SharedCosmosRouterServiceFactoryComponent
        public SharedCosmosRouterService sharedCosmosRouterService() {
            r10 coreThreadingApi = this.sharedCosmosRouterServiceDependencies.getCoreThreadingApi();
            if (coreThreadingApi != null) {
                return new SharedCosmosRouterService(coreThreadingApi, RemoteRouterFactoryModule_ProvideRemoteRouterFactoryFactory.provideRemoteRouterFactory());
            }
            throw new NullPointerException("Cannot return null from a non-@Nullable component method");
        }

        private SharedCosmosRouterServiceFactoryComponentImpl(SharedCosmosRouterServiceDependencies sharedCosmosRouterServiceDependencies) {
            this.sharedCosmosRouterServiceFactoryComponentImpl = this;
            this.sharedCosmosRouterServiceDependencies = sharedCosmosRouterServiceDependencies;
        }
    }

    private DaggerSharedCosmosRouterServiceFactoryComponent() {
    }

    public static SharedCosmosRouterServiceFactoryComponent.Factory factory() {
        return new Factory();
    }
}
