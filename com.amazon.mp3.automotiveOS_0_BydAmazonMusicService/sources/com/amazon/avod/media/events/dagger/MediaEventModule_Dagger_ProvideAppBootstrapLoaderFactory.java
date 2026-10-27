package com.amazon.avod.media.events.dagger;

import com.amazon.avod.media.events.AloysiusBootstrapLoader;
import com.amazon.avod.media.events.MediaEventDispatcher;
import com.amazon.avod.media.events.MediaReportDispatcherServices;
import com.amazon.avod.media.events.clientapi.MediaReportClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaEventModule_Dagger_ProvideAppBootstrapLoaderFactory implements Factory<AloysiusBootstrapLoader> {
    private final Provider<ScheduledExecutorService> executorServiceProvider;
    private final Provider<MediaEventDispatcher> mediaEventDispatcherProvider;
    private final Provider<MediaReportClient> mediaReportClientProvider;
    private final Provider<MediaReportDispatcherServices> mediaReportDispatcherServicesProvider;
    private final MediaEventModule_Dagger module;

    public MediaEventModule_Dagger_ProvideAppBootstrapLoaderFactory(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<MediaReportClient> provider, Provider<MediaEventDispatcher> provider2, Provider<MediaReportDispatcherServices> provider3, Provider<ScheduledExecutorService> provider4) {
        this.module = mediaEventModule_Dagger;
        this.mediaReportClientProvider = provider;
        this.mediaEventDispatcherProvider = provider2;
        this.mediaReportDispatcherServicesProvider = provider3;
        this.executorServiceProvider = provider4;
    }

    @Override // javax.inject.Provider
    public AloysiusBootstrapLoader get() {
        return (AloysiusBootstrapLoader) Preconditions.checkNotNull(this.module.provideAppBootstrapLoader(this.mediaReportClientProvider.get(), this.mediaEventDispatcherProvider.get(), this.mediaReportDispatcherServicesProvider.get(), this.executorServiceProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AloysiusBootstrapLoader> create(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<MediaReportClient> provider, Provider<MediaEventDispatcher> provider2, Provider<MediaReportDispatcherServices> provider3, Provider<ScheduledExecutorService> provider4) {
        return new MediaEventModule_Dagger_ProvideAppBootstrapLoaderFactory(mediaEventModule_Dagger, provider, provider2, provider3, provider4);
    }
}
