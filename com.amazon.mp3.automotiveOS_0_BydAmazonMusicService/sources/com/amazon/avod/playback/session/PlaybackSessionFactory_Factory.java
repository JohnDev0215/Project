package com.amazon.avod.playback.session;

import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class PlaybackSessionFactory_Factory implements Factory<PlaybackSessionFactory> {
    private final Provider<AdPlanFactory> adPlanFactoryProvider;
    private final Provider<AdUriProxy> adUriProxyProvider;
    private final Provider<VideoCacheManager> cacheManagerProvider;
    private final Provider<ContentSessionFactory> contentSessionFactoryProvider;
    private final Provider<DownloadService> downloadServiceProvider;
    private final Provider<BaseDrmSystem> drmSystemProvider;
    private final Provider<PlaybackSupportEvaluator> playbackEvaluatorProvider;
    private final Provider<PlaybackMediaEventReporters.Factory> playbackMediaEventReportersFactoryProvider;
    private final Provider<MediaProfiler> profilerProvider;
    private final Provider<QOSCommunicationService> qosCommunicationServiceProvider;
    private final Provider<RendererSchemeController> rendererSchemeControllerProvider;
    private final Provider<SmoothStreamingVideoPresentationEventReporter> reporterProvider;
    private final Provider<MediaSystemSharedContext> sharedContextProvider;
    private final Provider<ContentUrlFetcher> urlFetcherProvider;

    public PlaybackSessionFactory_Factory(Provider<MediaSystemSharedContext> provider, Provider<ContentSessionFactory> provider2, Provider<MediaProfiler> provider3, Provider<BaseDrmSystem> provider4, Provider<VideoCacheManager> provider5, Provider<DownloadService> provider6, Provider<SmoothStreamingVideoPresentationEventReporter> provider7, Provider<ContentUrlFetcher> provider8, Provider<PlaybackSupportEvaluator> provider9, Provider<RendererSchemeController> provider10, Provider<AdPlanFactory> provider11, Provider<AdUriProxy> provider12, Provider<PlaybackMediaEventReporters.Factory> provider13, Provider<QOSCommunicationService> provider14) {
        this.sharedContextProvider = provider;
        this.contentSessionFactoryProvider = provider2;
        this.profilerProvider = provider3;
        this.drmSystemProvider = provider4;
        this.cacheManagerProvider = provider5;
        this.downloadServiceProvider = provider6;
        this.reporterProvider = provider7;
        this.urlFetcherProvider = provider8;
        this.playbackEvaluatorProvider = provider9;
        this.rendererSchemeControllerProvider = provider10;
        this.adPlanFactoryProvider = provider11;
        this.adUriProxyProvider = provider12;
        this.playbackMediaEventReportersFactoryProvider = provider13;
        this.qosCommunicationServiceProvider = provider14;
    }

    @Override // javax.inject.Provider
    public PlaybackSessionFactory get() {
        return new PlaybackSessionFactory(this.sharedContextProvider.get(), this.contentSessionFactoryProvider.get(), this.profilerProvider.get(), this.drmSystemProvider.get(), this.cacheManagerProvider.get(), this.downloadServiceProvider.get(), this.reporterProvider, this.urlFetcherProvider, this.playbackEvaluatorProvider.get(), this.rendererSchemeControllerProvider.get(), this.adPlanFactoryProvider, this.adUriProxyProvider.get(), this.playbackMediaEventReportersFactoryProvider, this.qosCommunicationServiceProvider.get());
    }

    public static Factory<PlaybackSessionFactory> create(Provider<MediaSystemSharedContext> provider, Provider<ContentSessionFactory> provider2, Provider<MediaProfiler> provider3, Provider<BaseDrmSystem> provider4, Provider<VideoCacheManager> provider5, Provider<DownloadService> provider6, Provider<SmoothStreamingVideoPresentationEventReporter> provider7, Provider<ContentUrlFetcher> provider8, Provider<PlaybackSupportEvaluator> provider9, Provider<RendererSchemeController> provider10, Provider<AdPlanFactory> provider11, Provider<AdUriProxy> provider12, Provider<PlaybackMediaEventReporters.Factory> provider13, Provider<QOSCommunicationService> provider14) {
        return new PlaybackSessionFactory_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
    }
}
