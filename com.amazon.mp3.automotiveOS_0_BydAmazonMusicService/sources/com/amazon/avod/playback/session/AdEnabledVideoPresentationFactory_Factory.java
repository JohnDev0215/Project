package com.amazon.avod.playback.session;

import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.AdEnabledVideoPlayer;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.framework.volume.VolumeManager;
import dagger.internal.Factory;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AdEnabledVideoPresentationFactory_Factory implements Factory<AdEnabledVideoPresentationFactory> {
    private final Provider<AdEnabledVideoPlayer> adEnabledVideoPlayerProvider;
    private final Provider<AdPlanFactory> adPlanFactoryProvider;
    private final Provider<AdUriProxy> adUriProxyProvider;
    private final Provider<AdvertisingIdCollector> advertisingIdCollectorProvider;
    private final Provider<AmazonVideoPlayerFactory> amazonVideoPlayerFactoryProvider;
    private final Provider<ExecutorService> executorProvider;
    private final Provider<PlaybackSessionFactory> playbackSessionFactoryProvider;
    private final Provider<VolumeManager> volumeManagerProvider;

    public AdEnabledVideoPresentationFactory_Factory(Provider<PlaybackSessionFactory> provider, Provider<AmazonVideoPlayerFactory> provider2, Provider<AdEnabledVideoPlayer> provider3, Provider<AdPlanFactory> provider4, Provider<VolumeManager> provider5, Provider<AdvertisingIdCollector> provider6, Provider<ExecutorService> provider7, Provider<AdUriProxy> provider8) {
        this.playbackSessionFactoryProvider = provider;
        this.amazonVideoPlayerFactoryProvider = provider2;
        this.adEnabledVideoPlayerProvider = provider3;
        this.adPlanFactoryProvider = provider4;
        this.volumeManagerProvider = provider5;
        this.advertisingIdCollectorProvider = provider6;
        this.executorProvider = provider7;
        this.adUriProxyProvider = provider8;
    }

    @Override // javax.inject.Provider
    public AdEnabledVideoPresentationFactory get() {
        return new AdEnabledVideoPresentationFactory(this.playbackSessionFactoryProvider.get(), this.amazonVideoPlayerFactoryProvider.get(), this.adEnabledVideoPlayerProvider, this.adPlanFactoryProvider, this.volumeManagerProvider, this.advertisingIdCollectorProvider.get(), this.executorProvider.get(), this.adUriProxyProvider.get());
    }

    public static Factory<AdEnabledVideoPresentationFactory> create(Provider<PlaybackSessionFactory> provider, Provider<AmazonVideoPlayerFactory> provider2, Provider<AdEnabledVideoPlayer> provider3, Provider<AdPlanFactory> provider4, Provider<VolumeManager> provider5, Provider<AdvertisingIdCollector> provider6, Provider<ExecutorService> provider7, Provider<AdUriProxy> provider8) {
        return new AdEnabledVideoPresentationFactory_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }
}
