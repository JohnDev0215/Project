package com.amazon.avod.media.guice;

import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.service.playbackoptimizationservice.DefaultHeuristicsConfigurationSynchronizer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_GetHeuristicsConfigurationSynchronizerAsMediaComponentFactory implements Factory<MediaComponent> {
    private final Provider<DefaultHeuristicsConfigurationSynchronizer> defaultHeuristicsConfigurationSynchronizerProvider;
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_GetHeuristicsConfigurationSynchronizerAsMediaComponentFactory(MediaModule_Dagger mediaModule_Dagger, Provider<DefaultHeuristicsConfigurationSynchronizer> provider) {
        this.module = mediaModule_Dagger;
        this.defaultHeuristicsConfigurationSynchronizerProvider = provider;
    }

    @Override // javax.inject.Provider
    public MediaComponent get() {
        return (MediaComponent) Preconditions.checkNotNull(this.module.getHeuristicsConfigurationSynchronizerAsMediaComponent(this.defaultHeuristicsConfigurationSynchronizerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaComponent> create(MediaModule_Dagger mediaModule_Dagger, Provider<DefaultHeuristicsConfigurationSynchronizer> provider) {
        return new MediaModule_Dagger_GetHeuristicsConfigurationSynchronizerAsMediaComponentFactory(mediaModule_Dagger, provider);
    }
}
