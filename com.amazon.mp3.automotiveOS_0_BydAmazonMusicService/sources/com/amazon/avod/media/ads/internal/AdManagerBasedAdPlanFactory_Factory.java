package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.FirstMatchVideoPresentationFactory;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AdManagerBasedAdPlanFactory_Factory implements Factory<AdManagerBasedAdPlanFactory> {
    private final Provider<AdUriProxy> adUriProxyProvider;
    private final Provider<AdvertisingIdCollector> advertisingIdCollectorProvider;
    private final Provider<AndroidVideoPlayerV2Cache> androidVideoPlayerV2CacheProvider;
    private final Provider<DownloadService> downloadServiceProvider;
    private final Provider<PlaybackSupportEvaluator> playbackSupportEvaluatorProvider;
    private final Provider<QOSCommunicationService> qosCommunicationServiceProvider;
    private final Provider<MediaSystemSharedContext> sharedContextProvider;
    private final Provider<FirstMatchVideoPresentationFactory> videoPresentationFactoryProvider;

    public AdManagerBasedAdPlanFactory_Factory(Provider<MediaSystemSharedContext> provider, Provider<FirstMatchVideoPresentationFactory> provider2, Provider<AdUriProxy> provider3, Provider<DownloadService> provider4, Provider<PlaybackSupportEvaluator> provider5, Provider<QOSCommunicationService> provider6, Provider<AdvertisingIdCollector> provider7, Provider<AndroidVideoPlayerV2Cache> provider8) {
        this.sharedContextProvider = provider;
        this.videoPresentationFactoryProvider = provider2;
        this.adUriProxyProvider = provider3;
        this.downloadServiceProvider = provider4;
        this.playbackSupportEvaluatorProvider = provider5;
        this.qosCommunicationServiceProvider = provider6;
        this.advertisingIdCollectorProvider = provider7;
        this.androidVideoPlayerV2CacheProvider = provider8;
    }

    @Override // javax.inject.Provider
    public AdManagerBasedAdPlanFactory get() {
        return new AdManagerBasedAdPlanFactory(this.sharedContextProvider.get(), this.videoPresentationFactoryProvider.get(), this.adUriProxyProvider.get(), this.downloadServiceProvider.get(), this.playbackSupportEvaluatorProvider.get(), this.qosCommunicationServiceProvider.get(), this.advertisingIdCollectorProvider.get(), this.androidVideoPlayerV2CacheProvider.get());
    }

    public static Factory<AdManagerBasedAdPlanFactory> create(Provider<MediaSystemSharedContext> provider, Provider<FirstMatchVideoPresentationFactory> provider2, Provider<AdUriProxy> provider3, Provider<DownloadService> provider4, Provider<PlaybackSupportEvaluator> provider5, Provider<QOSCommunicationService> provider6, Provider<AdvertisingIdCollector> provider7, Provider<AndroidVideoPlayerV2Cache> provider8) {
        return new AdManagerBasedAdPlanFactory_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }
}
