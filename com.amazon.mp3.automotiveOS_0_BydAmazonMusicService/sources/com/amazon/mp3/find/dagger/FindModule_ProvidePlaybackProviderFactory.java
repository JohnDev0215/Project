package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvidePlaybackProviderFactory implements Factory<PlaybackProvider> {
    private final Provider<Context> contextProvider;
    private final FindModule module;

    public FindModule_ProvidePlaybackProviderFactory(FindModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public PlaybackProvider get() {
        return providePlaybackProvider(this.module, this.contextProvider.get());
    }

    public static FindModule_ProvidePlaybackProviderFactory create(FindModule module, Provider<Context> contextProvider) {
        return new FindModule_ProvidePlaybackProviderFactory(module, contextProvider);
    }

    public static PlaybackProvider providePlaybackProvider(FindModule instance, Context context) {
        return (PlaybackProvider) Preconditions.checkNotNullFromProvides(instance.providePlaybackProvider(context));
    }
}
