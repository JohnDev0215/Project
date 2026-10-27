package com.amazon.avod.media.guice;

import com.amazon.avod.media.playback.support.ConfigurablePlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideConfigurablePlaybackSupportEvaluatorFactory implements Factory<ConfigurablePlaybackSupportEvaluator> {
    private final MediaModule_Dagger module;
    private final Provider<RendererSchemeController> rendererSchemeControllerProvider;

    public MediaModule_Dagger_ProvideConfigurablePlaybackSupportEvaluatorFactory(MediaModule_Dagger mediaModule_Dagger, Provider<RendererSchemeController> provider) {
        this.module = mediaModule_Dagger;
        this.rendererSchemeControllerProvider = provider;
    }

    @Override // javax.inject.Provider
    public ConfigurablePlaybackSupportEvaluator get() {
        return (ConfigurablePlaybackSupportEvaluator) Preconditions.checkNotNull(this.module.provideConfigurablePlaybackSupportEvaluator(this.rendererSchemeControllerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ConfigurablePlaybackSupportEvaluator> create(MediaModule_Dagger mediaModule_Dagger, Provider<RendererSchemeController> provider) {
        return new MediaModule_Dagger_ProvideConfigurablePlaybackSupportEvaluatorFactory(mediaModule_Dagger, provider);
    }
}
