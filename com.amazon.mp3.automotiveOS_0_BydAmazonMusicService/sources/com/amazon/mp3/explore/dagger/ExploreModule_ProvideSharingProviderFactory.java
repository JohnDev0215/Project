package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.ui.providers.SharingProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideSharingProviderFactory implements Factory<SharingProvider> {
    private final ExploreModule module;

    public ExploreModule_ProvideSharingProviderFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public SharingProvider get() {
        return provideSharingProvider(this.module);
    }

    public static ExploreModule_ProvideSharingProviderFactory create(ExploreModule module) {
        return new ExploreModule_ProvideSharingProviderFactory(module);
    }

    public static SharingProvider provideSharingProvider(ExploreModule instance) {
        return (SharingProvider) Preconditions.checkNotNullFromProvides(instance.provideSharingProvider());
    }
}
