package com.amazon.avod.qos.listeners;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class QoSEventManagerFactory_Factory implements Factory<QoSEventManagerFactory> {
    private final Provider<QosEventReporterFactory> qosEventReporterFactoryProvider;
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public QoSEventManagerFactory_Factory(Provider<MediaSystemSharedContext> provider, Provider<QosEventReporterFactory> provider2) {
        this.sharedContextProvider = provider;
        this.qosEventReporterFactoryProvider = provider2;
    }

    @Override // javax.inject.Provider
    public QoSEventManagerFactory get() {
        return new QoSEventManagerFactory(this.sharedContextProvider.get(), this.qosEventReporterFactoryProvider.get());
    }

    public static Factory<QoSEventManagerFactory> create(Provider<MediaSystemSharedContext> provider, Provider<QosEventReporterFactory> provider2) {
        return new QoSEventManagerFactory_Factory(provider, provider2);
    }
}
