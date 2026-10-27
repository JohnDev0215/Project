package com.amazon.mp3.find.dagger;

import com.amazon.music.skyfire.ui.providers.ContextMenuProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideContextMenuProviderFactory implements Factory<ContextMenuProvider> {
    private final FindModule module;

    public FindModule_ProvideContextMenuProviderFactory(FindModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public ContextMenuProvider get() {
        return provideContextMenuProvider(this.module);
    }

    public static FindModule_ProvideContextMenuProviderFactory create(FindModule module) {
        return new FindModule_ProvideContextMenuProviderFactory(module);
    }

    public static ContextMenuProvider provideContextMenuProvider(FindModule instance) {
        return (ContextMenuProvider) Preconditions.checkNotNullFromProvides(instance.provideContextMenuProvider());
    }
}
