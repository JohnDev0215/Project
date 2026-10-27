package com.amazon.mp3.find.dagger;

import com.amazon.music.skyfire.ui.providers.UpsellProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideUpsellProviderFactory implements Factory<UpsellProvider> {
    private final FindModule module;

    public FindModule_ProvideUpsellProviderFactory(FindModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public UpsellProvider get() {
        return provideUpsellProvider(this.module);
    }

    public static FindModule_ProvideUpsellProviderFactory create(FindModule module) {
        return new FindModule_ProvideUpsellProviderFactory(module);
    }

    public static UpsellProvider provideUpsellProvider(FindModule instance) {
        return (UpsellProvider) Preconditions.checkNotNullFromProvides(instance.provideUpsellProvider());
    }
}
