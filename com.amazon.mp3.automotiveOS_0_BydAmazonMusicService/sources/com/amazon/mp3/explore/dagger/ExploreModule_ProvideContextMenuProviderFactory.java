package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.ui.providers.ContextMenuProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideContextMenuProviderFactory implements Factory<ContextMenuProvider> {
    private final ExploreModule module;

    public ExploreModule_ProvideContextMenuProviderFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public ContextMenuProvider get() {
        return provideContextMenuProvider(this.module);
    }

    public static ExploreModule_ProvideContextMenuProviderFactory create(ExploreModule module) {
        return new ExploreModule_ProvideContextMenuProviderFactory(module);
    }

    public static ContextMenuProvider provideContextMenuProvider(ExploreModule instance) {
        return (ContextMenuProvider) Preconditions.checkNotNullFromProvides(instance.provideContextMenuProvider());
    }
}
