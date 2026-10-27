package com.amazon.avod.media.events.dagger;

import com.amazon.avod.media.events.BatchDispatcherListener;
import com.amazon.avod.media.events.MediaEventDispatcher;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaReportDispatcherServices;
import com.amazon.avod.media.events.dao.MediaReportDAO;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaEventModule_Dagger_ProvideMediaEventDispatcherFactory implements Factory<MediaEventDispatcher> {
    private final Provider<BatchDispatcherListener> batchDispatcherListenerProvider;
    private final Provider<MediaReportDAO> mediaReportDAOProvider;
    private final Provider<MediaReportDispatcherServices> mediaReportDispatcherServicesProvider;
    private final MediaEventModule_Dagger module;
    private final Provider<MediaEventQueue> queueProvider;
    private final Provider<QosEventReporterFactory> reporterFactoryProvider;
    private final Provider<ScheduledExecutorService> scheduledExecutorServiceProvider;

    public MediaEventModule_Dagger_ProvideMediaEventDispatcherFactory(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<MediaEventQueue> provider, Provider<MediaReportDAO> provider2, Provider<MediaReportDispatcherServices> provider3, Provider<ScheduledExecutorService> provider4, Provider<QosEventReporterFactory> provider5, Provider<BatchDispatcherListener> provider6) {
        this.module = mediaEventModule_Dagger;
        this.queueProvider = provider;
        this.mediaReportDAOProvider = provider2;
        this.mediaReportDispatcherServicesProvider = provider3;
        this.scheduledExecutorServiceProvider = provider4;
        this.reporterFactoryProvider = provider5;
        this.batchDispatcherListenerProvider = provider6;
    }

    @Override // javax.inject.Provider
    public MediaEventDispatcher get() {
        return (MediaEventDispatcher) Preconditions.checkNotNull(this.module.provideMediaEventDispatcher(this.queueProvider.get(), this.mediaReportDAOProvider.get(), this.mediaReportDispatcherServicesProvider.get(), this.scheduledExecutorServiceProvider.get(), this.reporterFactoryProvider.get(), this.batchDispatcherListenerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaEventDispatcher> create(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<MediaEventQueue> provider, Provider<MediaReportDAO> provider2, Provider<MediaReportDispatcherServices> provider3, Provider<ScheduledExecutorService> provider4, Provider<QosEventReporterFactory> provider5, Provider<BatchDispatcherListener> provider6) {
        return new MediaEventModule_Dagger_ProvideMediaEventDispatcherFactory(mediaEventModule_Dagger, provider, provider2, provider3, provider4, provider5, provider6);
    }
}
