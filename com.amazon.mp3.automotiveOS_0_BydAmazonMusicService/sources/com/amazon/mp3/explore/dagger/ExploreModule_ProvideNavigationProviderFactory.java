package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideNavigationProviderFactory implements Factory<NavigationProvider> {
    private final Provider<Context> contextProvider;
    private final ExploreModule module;

    public ExploreModule_ProvideNavigationProviderFactory(ExploreModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public NavigationProvider get() {
        return provideNavigationProvider(this.module, this.contextProvider.get());
    }

    public static ExploreModule_ProvideNavigationProviderFactory create(ExploreModule module, Provider<Context> contextProvider) {
        return new ExploreModule_ProvideNavigationProviderFactory(module, contextProvider);
    }

    public static NavigationProvider provideNavigationProvider(ExploreModule instance, Context context) {
        return (NavigationProvider) Preconditions.checkNotNullFromProvides(instance.provideNavigationProvider(context));
    }
}
