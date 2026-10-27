package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvidePlaybackProviderFactory implements Factory<PlaybackProvider> {
    private final Provider<Context> contextProvider;
    private final ExploreModule module;

    public ExploreModule_ProvidePlaybackProviderFactory(ExploreModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public PlaybackProvider get() {
        return providePlaybackProvider(this.module, this.contextProvider.get());
    }

    public static ExploreModule_ProvidePlaybackProviderFactory create(ExploreModule module, Provider<Context> contextProvider) {
        return new ExploreModule_ProvidePlaybackProviderFactory(module, contextProvider);
    }

    public static PlaybackProvider providePlaybackProvider(ExploreModule instance, Context context) {
        return (PlaybackProvider) Preconditions.checkNotNullFromProvides(instance.providePlaybackProvider(context));
    }
}
