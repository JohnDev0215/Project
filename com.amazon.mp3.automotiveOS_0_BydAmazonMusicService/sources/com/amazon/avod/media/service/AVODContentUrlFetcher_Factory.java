package com.amazon.avod.media.service;

import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AVODContentUrlFetcher_Factory implements Factory<AVODContentUrlFetcher> {
    private final Provider<AdvertisingIdCollector> advertisingIdCollectorProvider;
    private final Provider<MediaProfiler> mediaProfilerProvider;
    private final Provider<QOSCommunicationService> qosCommunicationServiceProvider;
    private final Provider<PlaybackSupportEvaluator> supportEvaluatorProvider;

    public AVODContentUrlFetcher_Factory(Provider<MediaProfiler> provider, Provider<PlaybackSupportEvaluator> provider2, Provider<AdvertisingIdCollector> provider3, Provider<QOSCommunicationService> provider4) {
        this.mediaProfilerProvider = provider;
        this.supportEvaluatorProvider = provider2;
        this.advertisingIdCollectorProvider = provider3;
        this.qosCommunicationServiceProvider = provider4;
    }

    @Override // javax.inject.Provider
    public AVODContentUrlFetcher get() {
        return new AVODContentUrlFetcher(this.mediaProfilerProvider.get(), this.supportEvaluatorProvider.get(), this.advertisingIdCollectorProvider.get(), this.qosCommunicationServiceProvider.get());
    }

    public static Factory<AVODContentUrlFetcher> create(Provider<MediaProfiler> provider, Provider<PlaybackSupportEvaluator> provider2, Provider<AdvertisingIdCollector> provider3, Provider<QOSCommunicationService> provider4) {
        return new AVODContentUrlFetcher_Factory(provider, provider2, provider3, provider4);
    }
}
