package com.amazon.mp3.find.dagger;

import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideChromeProviderFactory implements Factory<ChromeProvider> {
    private final FindModule module;

    public FindModule_ProvideChromeProviderFactory(FindModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public ChromeProvider get() {
        return provideChromeProvider(this.module);
    }

    public static FindModule_ProvideChromeProviderFactory create(FindModule module) {
        return new FindModule_ProvideChromeProviderFactory(module);
    }

    public static ChromeProvider provideChromeProvider(FindModule instance) {
        return (ChromeProvider) Preconditions.checkNotNullFromProvides(instance.provideChromeProvider());
    }
}
