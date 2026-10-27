package com.amazon.mp3.find.dagger;

import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideStyleSheetProviderFactory implements Factory<StyleSheetProvider> {
    private final FindModule module;

    public FindModule_ProvideStyleSheetProviderFactory(FindModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public StyleSheetProvider get() {
        return provideStyleSheetProvider(this.module);
    }

    public static FindModule_ProvideStyleSheetProviderFactory create(FindModule module) {
        return new FindModule_ProvideStyleSheetProviderFactory(module);
    }

    public static StyleSheetProvider provideStyleSheetProvider(FindModule instance) {
        return (StyleSheetProvider) Preconditions.checkNotNullFromProvides(instance.provideStyleSheetProvider());
    }
}
