package com.amazon.avod.media.playback.reporting.aloysius.dagger;

import android.content.Context;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AloysiusReporterModule_Dagger_ProvideAloysiusDisplayReporterFactory implements Factory<MediaEventProducer> {
    private final Provider<Context> contextProvider;
    private final Provider<MediaEventQueue> mediaEventQueueProvider;
    private final AloysiusReporterModule_Dagger module;

    public AloysiusReporterModule_Dagger_ProvideAloysiusDisplayReporterFactory(AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger, Provider<MediaEventQueue> provider, Provider<Context> provider2) {
        this.module = aloysiusReporterModule_Dagger;
        this.mediaEventQueueProvider = provider;
        this.contextProvider = provider2;
    }

    @Override // javax.inject.Provider
    public MediaEventProducer get() {
        return (MediaEventProducer) Preconditions.checkNotNull(this.module.provideAloysiusDisplayReporter(this.mediaEventQueueProvider.get(), this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaEventProducer> create(AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger, Provider<MediaEventQueue> provider, Provider<Context> provider2) {
        return new AloysiusReporterModule_Dagger_ProvideAloysiusDisplayReporterFactory(aloysiusReporterModule_Dagger, provider, provider2);
    }
}
