package com.amazon.mp3.find.dagger;

import com.amazon.music.skyfire.ui.providers.LibraryProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideLibraryProviderFactory implements Factory<LibraryProvider> {
    private final FindModule module;

    public FindModule_ProvideLibraryProviderFactory(FindModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public LibraryProvider get() {
        return provideLibraryProvider(this.module);
    }

    public static FindModule_ProvideLibraryProviderFactory create(FindModule module) {
        return new FindModule_ProvideLibraryProviderFactory(module);
    }

    public static LibraryProvider provideLibraryProvider(FindModule instance) {
        return (LibraryProvider) Preconditions.checkNotNullFromProvides(instance.provideLibraryProvider());
    }
}
