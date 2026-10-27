package com.amazon.avod.qos;

import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class QoSModule_Dagger_ProvideEventReporterFactoryFactory implements Factory<EventReporterFactory> {
    private final QoSModule_Dagger module;
    private final Provider<QosEventReporterFactory> reporterFactoryProvider;

    public QoSModule_Dagger_ProvideEventReporterFactoryFactory(QoSModule_Dagger qoSModule_Dagger, Provider<QosEventReporterFactory> provider) {
        this.module = qoSModule_Dagger;
        this.reporterFactoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public EventReporterFactory get() {
        return (EventReporterFactory) Preconditions.checkNotNull(this.module.provideEventReporterFactory(this.reporterFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<EventReporterFactory> create(QoSModule_Dagger qoSModule_Dagger, Provider<QosEventReporterFactory> provider) {
        return new QoSModule_Dagger_ProvideEventReporterFactoryFactory(qoSModule_Dagger, provider);
    }

    public static EventReporterFactory proxyProvideEventReporterFactory(QoSModule_Dagger qoSModule_Dagger, QosEventReporterFactory qosEventReporterFactory) {
        return qoSModule_Dagger.provideEventReporterFactory(qosEventReporterFactory);
    }
}
