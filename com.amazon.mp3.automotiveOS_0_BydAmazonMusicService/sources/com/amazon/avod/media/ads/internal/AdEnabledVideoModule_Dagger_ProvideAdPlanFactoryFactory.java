package com.amazon.avod.media.ads.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AdEnabledVideoModule_Dagger_ProvideAdPlanFactoryFactory implements Factory<AdPlanFactory> {
    private final Provider<AdManagerBasedAdPlanFactory> factoryProvider;
    private final AdEnabledVideoModule_Dagger module;

    public AdEnabledVideoModule_Dagger_ProvideAdPlanFactoryFactory(AdEnabledVideoModule_Dagger adEnabledVideoModule_Dagger, Provider<AdManagerBasedAdPlanFactory> provider) {
        this.module = adEnabledVideoModule_Dagger;
        this.factoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public AdPlanFactory get() {
        return (AdPlanFactory) Preconditions.checkNotNull(this.module.provideAdPlanFactory(this.factoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AdPlanFactory> create(AdEnabledVideoModule_Dagger adEnabledVideoModule_Dagger, Provider<AdManagerBasedAdPlanFactory> provider) {
        return new AdEnabledVideoModule_Dagger_ProvideAdPlanFactoryFactory(adEnabledVideoModule_Dagger, provider);
    }
}
