package com.amazon.avod.qos;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class SmoothStreamingVideoPresentationEventReporter_Factory implements Factory<SmoothStreamingVideoPresentationEventReporter> {
    private final Provider<MediaSystemSharedContext> contextProvider;
    private final Provider<QosEventReporterFactory> reporterFactoryProvider;

    public SmoothStreamingVideoPresentationEventReporter_Factory(Provider<QosEventReporterFactory> provider, Provider<MediaSystemSharedContext> provider2) {
        this.reporterFactoryProvider = provider;
        this.contextProvider = provider2;
    }

    @Override // javax.inject.Provider
    public SmoothStreamingVideoPresentationEventReporter get() {
        return new SmoothStreamingVideoPresentationEventReporter(this.reporterFactoryProvider.get(), this.contextProvider.get());
    }

    public static Factory<SmoothStreamingVideoPresentationEventReporter> create(Provider<QosEventReporterFactory> provider, Provider<MediaSystemSharedContext> provider2) {
        return new SmoothStreamingVideoPresentationEventReporter_Factory(provider, provider2);
    }
}
