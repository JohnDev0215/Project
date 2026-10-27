package com.amazon.mp3.starlight.module;

import com.amazon.mp3.starlight.provider.VideoPlayerStateProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class StarlightModule_GetVideoPlayerStateProviderFactory implements Factory<VideoPlayerStateProvider> {
    private final StarlightModule module;

    public StarlightModule_GetVideoPlayerStateProviderFactory(StarlightModule module) {
        this.module = module;
    }

    @Override // javax.inject.Provider
    public VideoPlayerStateProvider get() {
        return getVideoPlayerStateProvider(this.module);
    }

    public static StarlightModule_GetVideoPlayerStateProviderFactory create(StarlightModule module) {
        return new StarlightModule_GetVideoPlayerStateProviderFactory(module);
    }

    public static VideoPlayerStateProvider getVideoPlayerStateProvider(StarlightModule instance) {
        return (VideoPlayerStateProvider) Preconditions.checkNotNullFromProvides(instance.getVideoPlayerStateProvider());
    }
}
