package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.ui.providers.UpsellProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideUpsellProviderFactory implements Factory<UpsellProvider> {
    private final ExploreModule module;

    public ExploreModule_ProvideUpsellProviderFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public UpsellProvider get() {
        return provideUpsellProvider(this.module);
    }

    public static ExploreModule_ProvideUpsellProviderFactory create(ExploreModule module) {
        return new ExploreModule_ProvideUpsellProviderFactory(module);
    }

    public static UpsellProvider provideUpsellProvider(ExploreModule instance) {
        return (UpsellProvider) Preconditions.checkNotNullFromProvides(instance.provideUpsellProvider());
    }
}
