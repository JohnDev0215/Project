package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideEnvironmentProviderFactory implements Factory<EnvironmentProvider> {
    private final Provider<Context> contextProvider;
    private final ExploreModule module;

    public ExploreModule_ProvideEnvironmentProviderFactory(ExploreModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public EnvironmentProvider get() {
        return provideEnvironmentProvider(this.module, this.contextProvider.get());
    }

    public static ExploreModule_ProvideEnvironmentProviderFactory create(ExploreModule module, Provider<Context> contextProvider) {
        return new ExploreModule_ProvideEnvironmentProviderFactory(module, contextProvider);
    }

    public static EnvironmentProvider provideEnvironmentProvider(ExploreModule instance, Context context) {
        return (EnvironmentProvider) Preconditions.checkNotNullFromProvides(instance.provideEnvironmentProvider(context));
    }
}
