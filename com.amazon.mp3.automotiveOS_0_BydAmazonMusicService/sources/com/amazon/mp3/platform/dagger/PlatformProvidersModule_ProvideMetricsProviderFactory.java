package com.amazon.mp3.platform.dagger;

import com.amazon.music.platform.providers.MetricsProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class PlatformProvidersModule_ProvideMetricsProviderFactory implements Factory<MetricsProvider> {
    private final PlatformProvidersModule module;

    public PlatformProvidersModule_ProvideMetricsProviderFactory(PlatformProvidersModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public MetricsProvider get() {
        return provideMetricsProvider(this.module);
    }

    public static PlatformProvidersModule_ProvideMetricsProviderFactory create(PlatformProvidersModule module) {
        return new PlatformProvidersModule_ProvideMetricsProviderFactory(module);
    }

    public static MetricsProvider provideMetricsProvider(PlatformProvidersModule instance) {
        return (MetricsProvider) Preconditions.checkNotNullFromProvides(instance.provideMetricsProvider());
    }
}
