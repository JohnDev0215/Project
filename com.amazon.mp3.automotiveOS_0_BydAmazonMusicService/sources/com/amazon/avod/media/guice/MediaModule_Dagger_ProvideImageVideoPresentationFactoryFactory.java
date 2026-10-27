package com.amazon.avod.media.guice;

import com.amazon.avod.media.playback.VideoPresentationFactory;
import com.amazon.avod.media.playback.image.ImageVideoPresentationFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideImageVideoPresentationFactoryFactory implements Factory<VideoPresentationFactory> {
    private final Provider<ImageVideoPresentationFactory> factoryProvider;
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_ProvideImageVideoPresentationFactoryFactory(MediaModule_Dagger mediaModule_Dagger, Provider<ImageVideoPresentationFactory> provider) {
        this.module = mediaModule_Dagger;
        this.factoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public VideoPresentationFactory get() {
        return (VideoPresentationFactory) Preconditions.checkNotNull(this.module.provideImageVideoPresentationFactory(this.factoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<VideoPresentationFactory> create(MediaModule_Dagger mediaModule_Dagger, Provider<ImageVideoPresentationFactory> provider) {
        return new MediaModule_Dagger_ProvideImageVideoPresentationFactoryFactory(mediaModule_Dagger, provider);
    }
}
