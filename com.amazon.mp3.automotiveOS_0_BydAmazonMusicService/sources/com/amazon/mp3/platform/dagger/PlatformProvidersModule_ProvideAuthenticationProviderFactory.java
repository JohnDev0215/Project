package com.amazon.mp3.platform.dagger;

import com.amazon.music.platform.providers.AuthenticationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class PlatformProvidersModule_ProvideAuthenticationProviderFactory implements Factory<AuthenticationProvider> {
    private final PlatformProvidersModule module;

    public PlatformProvidersModule_ProvideAuthenticationProviderFactory(PlatformProvidersModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public AuthenticationProvider get() {
        return provideAuthenticationProvider(this.module);
    }

    public static PlatformProvidersModule_ProvideAuthenticationProviderFactory create(PlatformProvidersModule module) {
        return new PlatformProvidersModule_ProvideAuthenticationProviderFactory(module);
    }

    public static AuthenticationProvider provideAuthenticationProvider(PlatformProvidersModule instance) {
        return (AuthenticationProvider) Preconditions.checkNotNullFromProvides(instance.provideAuthenticationProvider());
    }
}
