package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.ui.providers.LibraryProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideLibraryProviderFactory implements Factory<LibraryProvider> {
    private final ExploreModule module;

    public ExploreModule_ProvideLibraryProviderFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public LibraryProvider get() {
        return provideLibraryProvider(this.module);
    }

    public static ExploreModule_ProvideLibraryProviderFactory create(ExploreModule module) {
        return new ExploreModule_ProvideLibraryProviderFactory(module);
    }

    public static LibraryProvider provideLibraryProvider(ExploreModule instance) {
        return (LibraryProvider) Preconditions.checkNotNullFromProvides(instance.provideLibraryProvider());
    }
}
