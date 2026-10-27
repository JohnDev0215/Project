package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideStyleSheetProviderFactory implements Factory<StyleSheetProvider> {
    private final ExploreModule module;

    public ExploreModule_ProvideStyleSheetProviderFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public StyleSheetProvider get() {
        return provideStyleSheetProvider(this.module);
    }

    public static ExploreModule_ProvideStyleSheetProviderFactory create(ExploreModule module) {
        return new ExploreModule_ProvideStyleSheetProviderFactory(module);
    }

    public static StyleSheetProvider provideStyleSheetProvider(ExploreModule instance) {
        return (StyleSheetProvider) Preconditions.checkNotNullFromProvides(instance.provideStyleSheetProvider());
    }
}
