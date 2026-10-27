package com.amazon.mp3.platform.dagger;

import com.amazon.music.platform.providers.FeatureGateProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class PlatformProvidersModule_ProvideFeatureGateProviderFactory implements Factory<FeatureGateProvider> {
    public static FeatureGateProvider provideFeatureGateProvider(PlatformProvidersModule instance) {
        return (FeatureGateProvider) Preconditions.checkNotNullFromProvides(instance.provideFeatureGateProvider());
    }
}
