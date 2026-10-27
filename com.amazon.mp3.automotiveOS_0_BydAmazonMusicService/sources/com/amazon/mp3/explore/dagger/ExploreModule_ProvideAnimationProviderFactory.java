package com.amazon.mp3.explore.dagger;

import com.amazon.music.explore.providers.AnimationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideAnimationProviderFactory implements Factory<AnimationProvider> {
    private final ExploreModule module;

    public ExploreModule_ProvideAnimationProviderFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public AnimationProvider get() {
        return provideAnimationProvider(this.module);
    }

    public static ExploreModule_ProvideAnimationProviderFactory create(ExploreModule module) {
        return new ExploreModule_ProvideAnimationProviderFactory(module);
    }

    public static AnimationProvider provideAnimationProvider(ExploreModule instance) {
        return (AnimationProvider) Preconditions.checkNotNullFromProvides(instance.provideAnimationProvider());
    }
}
