package com.amazon.mp3.sharing.dagger;

import com.amazon.music.share.menu.providers.StyleSheetProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class ShareModule_ProvideStyleSheetProviderFactory implements Factory<StyleSheetProvider> {
    private final ShareModule module;

    public ShareModule_ProvideStyleSheetProviderFactory(ShareModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public StyleSheetProvider get() {
        return provideStyleSheetProvider(this.module);
    }

    public static ShareModule_ProvideStyleSheetProviderFactory create(ShareModule module) {
        return new ShareModule_ProvideStyleSheetProviderFactory(module);
    }

    public static StyleSheetProvider provideStyleSheetProvider(ShareModule instance) {
        return (StyleSheetProvider) Preconditions.checkNotNullFromProvides(instance.provideStyleSheetProvider());
    }
}
