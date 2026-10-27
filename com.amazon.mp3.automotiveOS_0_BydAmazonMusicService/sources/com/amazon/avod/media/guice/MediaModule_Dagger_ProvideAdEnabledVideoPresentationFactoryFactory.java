package com.amazon.avod.media.guice;

import com.amazon.avod.media.playback.VideoPresentationFactory;
import com.amazon.avod.playback.session.AdEnabledVideoPresentationFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideAdEnabledVideoPresentationFactoryFactory implements Factory<VideoPresentationFactory> {
    private final Provider<AdEnabledVideoPresentationFactory> factoryProvider;
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_ProvideAdEnabledVideoPresentationFactoryFactory(MediaModule_Dagger mediaModule_Dagger, Provider<AdEnabledVideoPresentationFactory> provider) {
        this.module = mediaModule_Dagger;
        this.factoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public VideoPresentationFactory get() {
        return (VideoPresentationFactory) Preconditions.checkNotNull(this.module.provideAdEnabledVideoPresentationFactory(this.factoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<VideoPresentationFactory> create(MediaModule_Dagger mediaModule_Dagger, Provider<AdEnabledVideoPresentationFactory> provider) {
        return new MediaModule_Dagger_ProvideAdEnabledVideoPresentationFactoryFactory(mediaModule_Dagger, provider);
    }
}
