package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.media.contentcache.internal.ClientCacheConfig;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class AdaptiveStreamingModule_Dagger_ProvidePlaybackClientsConfigFactory implements Factory<ClientCacheConfig> {
    private final AdaptiveStreamingModule_Dagger module;

    public AdaptiveStreamingModule_Dagger_ProvidePlaybackClientsConfigFactory(AdaptiveStreamingModule_Dagger adaptiveStreamingModule_Dagger) {
        this.module = adaptiveStreamingModule_Dagger;
    }

    @Override // javax.inject.Provider
    public ClientCacheConfig get() {
        return (ClientCacheConfig) Preconditions.checkNotNull(this.module.providePlaybackClientsConfig(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ClientCacheConfig> create(AdaptiveStreamingModule_Dagger adaptiveStreamingModule_Dagger) {
        return new AdaptiveStreamingModule_Dagger_ProvidePlaybackClientsConfigFactory(adaptiveStreamingModule_Dagger);
    }
}
