package com.amazon.avod.media.playback.support;

import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ConfigurablePlaybackSupportEvaluator_Factory implements Factory<ConfigurablePlaybackSupportEvaluator> {
    private final Provider<RendererSchemeController> rendererSchemeControllerProvider;

    public ConfigurablePlaybackSupportEvaluator_Factory(Provider<RendererSchemeController> provider) {
        this.rendererSchemeControllerProvider = provider;
    }

    @Override // javax.inject.Provider
    public ConfigurablePlaybackSupportEvaluator get() {
        return new ConfigurablePlaybackSupportEvaluator(this.rendererSchemeControllerProvider.get());
    }

    public static Factory<ConfigurablePlaybackSupportEvaluator> create(Provider<RendererSchemeController> provider) {
        return new ConfigurablePlaybackSupportEvaluator_Factory(provider);
    }
}
