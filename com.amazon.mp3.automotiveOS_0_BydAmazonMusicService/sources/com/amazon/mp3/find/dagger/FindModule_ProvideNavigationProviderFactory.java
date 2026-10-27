package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideNavigationProviderFactory implements Factory<NavigationProvider> {
    private final Provider<Context> contextProvider;
    private final FindModule module;

    public FindModule_ProvideNavigationProviderFactory(FindModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public NavigationProvider get() {
        return provideNavigationProvider(this.module, this.contextProvider.get());
    }

    public static FindModule_ProvideNavigationProviderFactory create(FindModule module, Provider<Context> contextProvider) {
        return new FindModule_ProvideNavigationProviderFactory(module, contextProvider);
    }

    public static NavigationProvider provideNavigationProvider(FindModule instance, Context context) {
        return (NavigationProvider) Preconditions.checkNotNullFromProvides(instance.provideNavigationProvider(context));
    }
}
