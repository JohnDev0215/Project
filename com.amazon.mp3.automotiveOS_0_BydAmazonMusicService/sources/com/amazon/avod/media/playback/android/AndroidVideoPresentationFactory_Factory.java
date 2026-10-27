package com.amazon.avod.media.playback.android;

import com.amazon.avod.media.ads.internal.AndroidVideoPlayerV2Cache;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidVideoPresentationFactory_Factory implements Factory<AndroidVideoPresentationFactory> {
    private final Provider<AndroidVideoPlayerV2Cache> androidVideoPlayerV2CacheProvider;
    private final Provider<MediaSystemSharedContext> contextProvider;
    private final Provider<AndroidVideoPlayerDiagnosticsController> diagControllerProvider;
    private final Provider<VideoPresentationEventReporter> presentationReporterProvider;
    private final Provider<QOSCommunicationService> qosCommunicationServiceProvider;
    private final Provider<AndroidVideoPlayer> viewProvider;

    public AndroidVideoPresentationFactory_Factory(Provider<AndroidVideoPlayer> provider, Provider<VideoPresentationEventReporter> provider2, Provider<AndroidVideoPlayerDiagnosticsController> provider3, Provider<MediaSystemSharedContext> provider4, Provider<QOSCommunicationService> provider5, Provider<AndroidVideoPlayerV2Cache> provider6) {
        this.viewProvider = provider;
        this.presentationReporterProvider = provider2;
        this.diagControllerProvider = provider3;
        this.contextProvider = provider4;
        this.qosCommunicationServiceProvider = provider5;
        this.androidVideoPlayerV2CacheProvider = provider6;
    }

    @Override // javax.inject.Provider
    public AndroidVideoPresentationFactory get() {
        return new AndroidVideoPresentationFactory(this.viewProvider, this.presentationReporterProvider, this.diagControllerProvider, this.contextProvider.get(), this.qosCommunicationServiceProvider, this.androidVideoPlayerV2CacheProvider.get());
    }

    public static Factory<AndroidVideoPresentationFactory> create(Provider<AndroidVideoPlayer> provider, Provider<VideoPresentationEventReporter> provider2, Provider<AndroidVideoPlayerDiagnosticsController> provider3, Provider<MediaSystemSharedContext> provider4, Provider<QOSCommunicationService> provider5, Provider<AndroidVideoPlayerV2Cache> provider6) {
        return new AndroidVideoPresentationFactory_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
