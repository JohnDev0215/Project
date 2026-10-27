package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.media.ads.AdvertisingIdCollector;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class AdaptiveStreamingModule_Dagger_ProvideAdIdProviderFactory implements Factory<AdvertisingIdCollector> {
    private final AdaptiveStreamingModule_Dagger module;

    public AdaptiveStreamingModule_Dagger_ProvideAdIdProviderFactory(AdaptiveStreamingModule_Dagger adaptiveStreamingModule_Dagger) {
        this.module = adaptiveStreamingModule_Dagger;
    }

    @Override // javax.inject.Provider
    public AdvertisingIdCollector get() {
        return (AdvertisingIdCollector) Preconditions.checkNotNull(this.module.provideAdIdProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AdvertisingIdCollector> create(AdaptiveStreamingModule_Dagger adaptiveStreamingModule_Dagger) {
        return new AdaptiveStreamingModule_Dagger_ProvideAdIdProviderFactory(adaptiveStreamingModule_Dagger);
    }
}
