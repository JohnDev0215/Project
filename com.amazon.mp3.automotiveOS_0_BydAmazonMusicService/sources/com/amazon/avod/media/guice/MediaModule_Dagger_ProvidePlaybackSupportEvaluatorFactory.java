package com.amazon.avod.media.guice;

import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvidePlaybackSupportEvaluatorFactory implements Factory<PlaybackSupportEvaluator> {
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_ProvidePlaybackSupportEvaluatorFactory(MediaModule_Dagger mediaModule_Dagger) {
        this.module = mediaModule_Dagger;
    }

    @Override // javax.inject.Provider
    public PlaybackSupportEvaluator get() {
        return (PlaybackSupportEvaluator) Preconditions.checkNotNull(this.module.providePlaybackSupportEvaluator(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PlaybackSupportEvaluator> create(MediaModule_Dagger mediaModule_Dagger) {
        return new MediaModule_Dagger_ProvidePlaybackSupportEvaluatorFactory(mediaModule_Dagger);
    }
}
