package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideSkyFireConfigurationProviderFactory implements Factory<SkyFireConfigurationProvider> {
    private final Provider<Context> contextProvider;
    private final ExploreModule module;

    public ExploreModule_ProvideSkyFireConfigurationProviderFactory(ExploreModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public SkyFireConfigurationProvider get() {
        return provideSkyFireConfigurationProvider(this.module, this.contextProvider.get());
    }

    public static ExploreModule_ProvideSkyFireConfigurationProviderFactory create(ExploreModule module, Provider<Context> contextProvider) {
        return new ExploreModule_ProvideSkyFireConfigurationProviderFactory(module, contextProvider);
    }

    public static SkyFireConfigurationProvider provideSkyFireConfigurationProvider(ExploreModule instance, Context context) {
        return (SkyFireConfigurationProvider) Preconditions.checkNotNullFromProvides(instance.provideSkyFireConfigurationProvider(context));
    }
}
