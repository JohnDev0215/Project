package com.amazon.avod.qos;

import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class SimpleVideoPresentationEventReporter_Factory implements Factory<SimpleVideoPresentationEventReporter> {
    private final Provider<QosEventReporterFactory> reporterFactoryProvider;

    public SimpleVideoPresentationEventReporter_Factory(Provider<QosEventReporterFactory> provider) {
        this.reporterFactoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public SimpleVideoPresentationEventReporter get() {
        return new SimpleVideoPresentationEventReporter(this.reporterFactoryProvider.get());
    }

    public static Factory<SimpleVideoPresentationEventReporter> create(Provider<QosEventReporterFactory> provider) {
        return new SimpleVideoPresentationEventReporter_Factory(provider);
    }
}
