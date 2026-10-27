package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.explore.providers.WakeWordProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideWakeWordProviderFactory implements Factory<WakeWordProvider> {
    private final Provider<Context> contextProvider;
    private final ExploreModule module;

    public ExploreModule_ProvideWakeWordProviderFactory(ExploreModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public WakeWordProvider get() {
        return provideWakeWordProvider(this.module, this.contextProvider.get());
    }

    public static ExploreModule_ProvideWakeWordProviderFactory create(ExploreModule module, Provider<Context> contextProvider) {
        return new ExploreModule_ProvideWakeWordProviderFactory(module, contextProvider);
    }

    public static WakeWordProvider provideWakeWordProvider(ExploreModule instance, Context context) {
        return (WakeWordProvider) Preconditions.checkNotNullFromProvides(instance.provideWakeWordProvider(context));
    }
}
