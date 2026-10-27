package com.amazon.mp3.alps.dagger;

import com.amazon.music.alps.providers.SharingProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ALPSModule_ProvideAlpsSharingProviderFactory implements Factory<SharingProvider> {
    private final ALPSModule module;
    private final Provider<com.amazon.music.skyfire.ui.providers.SharingProvider> sharingProvider;

    public ALPSModule_ProvideAlpsSharingProviderFactory(ALPSModule module, Provider<com.amazon.music.skyfire.ui.providers.SharingProvider> sharingProvider) {
        this.module = module;
        this.sharingProvider = sharingProvider;
    }

    @Override // javax.inject.Provider
    public SharingProvider get() {
        return provideAlpsSharingProvider(this.module, this.sharingProvider.get());
    }

    public static ALPSModule_ProvideAlpsSharingProviderFactory create(ALPSModule module, Provider<com.amazon.music.skyfire.ui.providers.SharingProvider> sharingProvider) {
        return new ALPSModule_ProvideAlpsSharingProviderFactory(module, sharingProvider);
    }

    public static SharingProvider provideAlpsSharingProvider(ALPSModule instance, com.amazon.music.skyfire.ui.providers.SharingProvider sharingProvider) {
        return (SharingProvider) Preconditions.checkNotNullFromProvides(instance.provideAlpsSharingProvider(sharingProvider));
    }
}
