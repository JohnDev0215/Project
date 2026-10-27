package com.amazon.avod.media.playback.reporting.aloysius.dagger;

import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AloysiusReporterModule_Dagger_ProvideAloysiusWeblabReporterFactory implements Factory<MediaEventProducer> {
    private final Provider<MediaEventQueue> mediaEventQueueProvider;
    private final AloysiusReporterModule_Dagger module;

    public AloysiusReporterModule_Dagger_ProvideAloysiusWeblabReporterFactory(AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger, Provider<MediaEventQueue> provider) {
        this.module = aloysiusReporterModule_Dagger;
        this.mediaEventQueueProvider = provider;
    }

    @Override // javax.inject.Provider
    public MediaEventProducer get() {
        return (MediaEventProducer) Preconditions.checkNotNull(this.module.provideAloysiusWeblabReporter(this.mediaEventQueueProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaEventProducer> create(AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger, Provider<MediaEventQueue> provider) {
        return new AloysiusReporterModule_Dagger_ProvideAloysiusWeblabReporterFactory(aloysiusReporterModule_Dagger, provider);
    }
}
