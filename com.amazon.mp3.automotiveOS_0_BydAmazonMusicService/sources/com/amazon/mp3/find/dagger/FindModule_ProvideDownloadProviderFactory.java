package com.amazon.mp3.find.dagger;

import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideDownloadProviderFactory implements Factory<DownloadProvider> {
    private final FindModule module;

    public FindModule_ProvideDownloadProviderFactory(FindModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public DownloadProvider get() {
        return provideDownloadProvider(this.module);
    }

    public static FindModule_ProvideDownloadProviderFactory create(FindModule module) {
        return new FindModule_ProvideDownloadProviderFactory(module);
    }

    public static DownloadProvider provideDownloadProvider(FindModule instance) {
        return (DownloadProvider) Preconditions.checkNotNullFromProvides(instance.provideDownloadProvider());
    }
}
