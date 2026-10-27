package com.amazon.mp3.explore.dagger;

import com.amazon.music.explore.providers.ExplicitFilterProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideExplicitFilterProviderFactory implements Factory<ExplicitFilterProvider> {
    private final ExploreModule module;

    public ExploreModule_ProvideExplicitFilterProviderFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public ExplicitFilterProvider get() {
        return provideExplicitFilterProvider(this.module);
    }

    public static ExploreModule_ProvideExplicitFilterProviderFactory create(ExploreModule module) {
        return new ExploreModule_ProvideExplicitFilterProviderFactory(module);
    }

    public static ExplicitFilterProvider provideExplicitFilterProvider(ExploreModule instance) {
        return (ExplicitFilterProvider) Preconditions.checkNotNullFromProvides(instance.provideExplicitFilterProvider());
    }
}
