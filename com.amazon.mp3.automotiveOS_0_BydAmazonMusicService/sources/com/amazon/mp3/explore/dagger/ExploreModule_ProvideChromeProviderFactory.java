package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideChromeProviderFactory implements Factory<ChromeProvider> {
    private final ExploreModule module;

    public ExploreModule_ProvideChromeProviderFactory(ExploreModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public ChromeProvider get() {
        return provideChromeProvider(this.module);
    }

    public static ExploreModule_ProvideChromeProviderFactory create(ExploreModule module) {
        return new ExploreModule_ProvideChromeProviderFactory(module);
    }

    public static ChromeProvider provideChromeProvider(ExploreModule instance) {
        return (ChromeProvider) Preconditions.checkNotNullFromProvides(instance.provideChromeProvider());
    }
}
