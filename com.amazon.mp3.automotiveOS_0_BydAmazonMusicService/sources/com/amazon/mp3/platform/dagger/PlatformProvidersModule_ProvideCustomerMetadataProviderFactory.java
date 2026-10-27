package com.amazon.mp3.platform.dagger;

import com.amazon.music.platform.providers.CustomerMetadataProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class PlatformProvidersModule_ProvideCustomerMetadataProviderFactory implements Factory<CustomerMetadataProvider> {
    private final PlatformProvidersModule module;

    public PlatformProvidersModule_ProvideCustomerMetadataProviderFactory(PlatformProvidersModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public CustomerMetadataProvider get() {
        return provideCustomerMetadataProvider(this.module);
    }

    public static PlatformProvidersModule_ProvideCustomerMetadataProviderFactory create(PlatformProvidersModule module) {
        return new PlatformProvidersModule_ProvideCustomerMetadataProviderFactory(module);
    }

    public static CustomerMetadataProvider provideCustomerMetadataProvider(PlatformProvidersModule instance) {
        return (CustomerMetadataProvider) Preconditions.checkNotNullFromProvides(instance.provideCustomerMetadataProvider());
    }
}
