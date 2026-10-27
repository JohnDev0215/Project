package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.qos.SimpleVideoPresentationEventReporter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AdaptiveStreamingModule_Dagger_ProvideVideoPresentationEventReporterFactory implements Factory<VideoPresentationEventReporter> {
    private final AdaptiveStreamingModule_Dagger module;
    private final Provider<SimpleVideoPresentationEventReporter> reporterProvider;

    public AdaptiveStreamingModule_Dagger_ProvideVideoPresentationEventReporterFactory(AdaptiveStreamingModule_Dagger adaptiveStreamingModule_Dagger, Provider<SimpleVideoPresentationEventReporter> provider) {
        this.module = adaptiveStreamingModule_Dagger;
        this.reporterProvider = provider;
    }

    @Override // javax.inject.Provider
    public VideoPresentationEventReporter get() {
        return (VideoPresentationEventReporter) Preconditions.checkNotNull(this.module.provideVideoPresentationEventReporter(this.reporterProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<VideoPresentationEventReporter> create(AdaptiveStreamingModule_Dagger adaptiveStreamingModule_Dagger, Provider<SimpleVideoPresentationEventReporter> provider) {
        return new AdaptiveStreamingModule_Dagger_ProvideVideoPresentationEventReporterFactory(adaptiveStreamingModule_Dagger, provider);
    }
}
