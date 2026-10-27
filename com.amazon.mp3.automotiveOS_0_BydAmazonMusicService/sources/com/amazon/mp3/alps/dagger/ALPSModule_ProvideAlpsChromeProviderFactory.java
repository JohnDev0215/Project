package com.amazon.mp3.alps.dagger;

import com.amazon.music.alps.providers.ChromeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ALPSModule_ProvideAlpsChromeProviderFactory implements Factory<ChromeProvider> {
    private final Provider<com.amazon.music.skyfire.ui.providers.ChromeProvider> chromeProvider;
    private final ALPSModule module;

    public ALPSModule_ProvideAlpsChromeProviderFactory(ALPSModule module, Provider<com.amazon.music.skyfire.ui.providers.ChromeProvider> chromeProvider) {
        this.module = module;
        this.chromeProvider = chromeProvider;
    }

    @Override // javax.inject.Provider
    public ChromeProvider get() {
        return provideAlpsChromeProvider(this.module, this.chromeProvider.get());
    }

    public static ALPSModule_ProvideAlpsChromeProviderFactory create(ALPSModule module, Provider<com.amazon.music.skyfire.ui.providers.ChromeProvider> chromeProvider) {
        return new ALPSModule_ProvideAlpsChromeProviderFactory(module, chromeProvider);
    }

    public static ChromeProvider provideAlpsChromeProvider(ALPSModule instance, com.amazon.music.skyfire.ui.providers.ChromeProvider chromeProvider) {
        return (ChromeProvider) Preconditions.checkNotNullFromProvides(instance.provideAlpsChromeProvider(chromeProvider));
    }
}
