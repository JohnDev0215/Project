package com.amazon.mp3.alps.dagger;

import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class ALPSModule_ProvideChromeProviderFactory implements Factory<ChromeProvider> {
    private final ALPSModule module;

    public ALPSModule_ProvideChromeProviderFactory(ALPSModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public ChromeProvider get() {
        return provideChromeProvider(this.module);
    }

    public static ALPSModule_ProvideChromeProviderFactory create(ALPSModule module) {
        return new ALPSModule_ProvideChromeProviderFactory(module);
    }

    public static ChromeProvider provideChromeProvider(ALPSModule instance) {
        return (ChromeProvider) Preconditions.checkNotNullFromProvides(instance.provideChromeProvider());
    }
}
