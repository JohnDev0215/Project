package com.amazon.mp3.find.dagger;

import com.amazon.music.skyfire.ui.providers.SharingProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSharingProviderFactory implements Factory<SharingProvider> {
    private final FindModule module;

    public FindModule_ProvideSharingProviderFactory(FindModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public SharingProvider get() {
        return provideSharingProvider(this.module);
    }

    public static FindModule_ProvideSharingProviderFactory create(FindModule module) {
        return new FindModule_ProvideSharingProviderFactory(module);
    }

    public static SharingProvider provideSharingProvider(FindModule instance) {
        return (SharingProvider) Preconditions.checkNotNullFromProvides(instance.provideSharingProvider());
    }
}
