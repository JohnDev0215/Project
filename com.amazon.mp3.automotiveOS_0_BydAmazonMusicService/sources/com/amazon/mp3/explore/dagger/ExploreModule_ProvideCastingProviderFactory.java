package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.explore.providers.CastingProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideCastingProviderFactory implements Factory<CastingProvider> {
    private final Provider<Context> contextProvider;
    private final ExploreModule module;

    public ExploreModule_ProvideCastingProviderFactory(ExploreModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public CastingProvider get() {
        return provideCastingProvider(this.module, this.contextProvider.get());
    }

    public static ExploreModule_ProvideCastingProviderFactory create(ExploreModule module, Provider<Context> contextProvider) {
        return new ExploreModule_ProvideCastingProviderFactory(module, contextProvider);
    }

    public static CastingProvider provideCastingProvider(ExploreModule instance, Context context) {
        return (CastingProvider) Preconditions.checkNotNullFromProvides(instance.provideCastingProvider(context));
    }
}
