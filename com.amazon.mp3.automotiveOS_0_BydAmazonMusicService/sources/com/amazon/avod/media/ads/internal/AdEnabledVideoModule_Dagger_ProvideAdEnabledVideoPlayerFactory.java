package com.amazon.avod.media.ads.internal;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class AdEnabledVideoModule_Dagger_ProvideAdEnabledVideoPlayerFactory implements Factory<AdEnabledVideoPlayer> {
    private final AdEnabledVideoModule_Dagger module;

    public AdEnabledVideoModule_Dagger_ProvideAdEnabledVideoPlayerFactory(AdEnabledVideoModule_Dagger adEnabledVideoModule_Dagger) {
        this.module = adEnabledVideoModule_Dagger;
    }

    @Override // javax.inject.Provider
    public AdEnabledVideoPlayer get() {
        return (AdEnabledVideoPlayer) Preconditions.checkNotNull(this.module.provideAdEnabledVideoPlayer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AdEnabledVideoPlayer> create(AdEnabledVideoModule_Dagger adEnabledVideoModule_Dagger) {
        return new AdEnabledVideoModule_Dagger_ProvideAdEnabledVideoPlayerFactory(adEnabledVideoModule_Dagger);
    }
}
