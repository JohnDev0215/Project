package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSkyFireConfigurationProviderFactory implements Factory<SkyFireConfigurationProvider> {
    private final Provider<Context> contextProvider;
    private final FindModule module;

    public FindModule_ProvideSkyFireConfigurationProviderFactory(FindModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public SkyFireConfigurationProvider get() {
        return provideSkyFireConfigurationProvider(this.module, this.contextProvider.get());
    }

    public static FindModule_ProvideSkyFireConfigurationProviderFactory create(FindModule module, Provider<Context> contextProvider) {
        return new FindModule_ProvideSkyFireConfigurationProviderFactory(module, contextProvider);
    }

    public static SkyFireConfigurationProvider provideSkyFireConfigurationProvider(FindModule instance, Context context) {
        return (SkyFireConfigurationProvider) Preconditions.checkNotNullFromProvides(instance.provideSkyFireConfigurationProvider(context));
    }
}
