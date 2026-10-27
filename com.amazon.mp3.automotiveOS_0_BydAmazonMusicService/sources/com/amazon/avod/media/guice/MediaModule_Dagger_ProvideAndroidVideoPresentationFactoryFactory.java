package com.amazon.avod.media.guice;

import com.amazon.avod.media.playback.VideoPresentationFactory;
import com.amazon.avod.media.playback.android.AndroidVideoPresentationFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideAndroidVideoPresentationFactoryFactory implements Factory<VideoPresentationFactory> {
    private final Provider<AndroidVideoPresentationFactory> factoryProvider;
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_ProvideAndroidVideoPresentationFactoryFactory(MediaModule_Dagger mediaModule_Dagger, Provider<AndroidVideoPresentationFactory> provider) {
        this.module = mediaModule_Dagger;
        this.factoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public VideoPresentationFactory get() {
        return (VideoPresentationFactory) Preconditions.checkNotNull(this.module.provideAndroidVideoPresentationFactory(this.factoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<VideoPresentationFactory> create(MediaModule_Dagger mediaModule_Dagger, Provider<AndroidVideoPresentationFactory> provider) {
        return new MediaModule_Dagger_ProvideAndroidVideoPresentationFactoryFactory(mediaModule_Dagger, provider);
    }
}
