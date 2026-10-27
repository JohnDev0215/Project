package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideDownloadProviderFactory implements Factory<DownloadProvider> {
    private final ExploreModule module;

    public ExploreModule_ProvideDownloadProviderFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public DownloadProvider get() {
        return provideDownloadProvider(this.module);
    }

    public static ExploreModule_ProvideDownloadProviderFactory create(ExploreModule module) {
        return new ExploreModule_ProvideDownloadProviderFactory(module);
    }

    public static DownloadProvider provideDownloadProvider(ExploreModule instance) {
        return (DownloadProvider) Preconditions.checkNotNullFromProvides(instance.provideDownloadProvider());
    }
}
