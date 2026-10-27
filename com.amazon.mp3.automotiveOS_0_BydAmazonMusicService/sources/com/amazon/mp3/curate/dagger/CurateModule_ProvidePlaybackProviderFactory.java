package com.amazon.mp3.curate.dagger;

import com.amazon.music.curate.provider.PlaybackProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvidePlaybackProviderFactory implements Factory<PlaybackProvider> {
    public static PlaybackProvider providePlaybackProvider(CurateModule instance) {
        return (PlaybackProvider) Preconditions.checkNotNullFromProvides(instance.providePlaybackProvider());
    }
}
