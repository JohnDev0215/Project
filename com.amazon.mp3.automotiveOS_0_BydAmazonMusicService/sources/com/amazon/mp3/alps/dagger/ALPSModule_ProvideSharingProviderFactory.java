package com.amazon.mp3.alps.dagger;

import com.amazon.music.skyfire.ui.providers.SharingProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ALPSModule_ProvideSharingProviderFactory implements Factory<SharingProvider> {
    private final ALPSModule module;

    public ALPSModule_ProvideSharingProviderFactory(ALPSModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public SharingProvider get() {
        return provideSharingProvider(this.module);
    }

    public static ALPSModule_ProvideSharingProviderFactory create(ALPSModule module) {
        return new ALPSModule_ProvideSharingProviderFactory(module);
    }

    public static SharingProvider provideSharingProvider(ALPSModule instance) {
        return (SharingProvider) Preconditions.checkNotNullFromProvides(instance.provideSharingProvider());
    }
}
