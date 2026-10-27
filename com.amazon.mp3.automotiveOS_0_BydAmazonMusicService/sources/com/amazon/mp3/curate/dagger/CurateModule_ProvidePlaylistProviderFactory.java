package com.amazon.mp3.curate.dagger;

import com.amazon.music.curate.provider.PlaylistProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class CurateModule_ProvidePlaylistProviderFactory implements Factory<PlaylistProvider> {
    public static PlaylistProvider providePlaylistProvider(CurateModule instance) {
        return (PlaylistProvider) Preconditions.checkNotNullFromProvides(instance.providePlaylistProvider());
    }
}
