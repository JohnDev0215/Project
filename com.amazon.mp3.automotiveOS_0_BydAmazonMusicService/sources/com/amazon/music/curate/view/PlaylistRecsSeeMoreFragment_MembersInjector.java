package com.amazon.music.curate.view;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.SubscriptionProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class PlaylistRecsSeeMoreFragment_MembersInjector implements MembersInjector<PlaylistRecsSeeMoreFragment> {
    private final Provider<DeviceInfoProvider> deviceInfoProvider;
    private final Provider<DownloadProvider> downloadProvider;
    private final Provider<FeatureFlagProvider> featureFlagProvider;
    private final Provider<OverflowMenuProvider> overflowMenuProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<RequestInterceptor> requestInterceptorProvider;
    private final Provider<SubscriptionProvider> subscriptionProvider;

    public PlaylistRecsSeeMoreFragment_MembersInjector(Provider<RequestInterceptor> requestInterceptorProvider, Provider<DownloadProvider> downloadProvider, Provider<PlaybackProvider> playbackProvider, Provider<OverflowMenuProvider> overflowMenuProvider, Provider<FeatureFlagProvider> featureFlagProvider, Provider<SubscriptionProvider> subscriptionProvider, Provider<DeviceInfoProvider> deviceInfoProvider) {
        this.requestInterceptorProvider = requestInterceptorProvider;
        this.downloadProvider = downloadProvider;
        this.playbackProvider = playbackProvider;
        this.overflowMenuProvider = overflowMenuProvider;
        this.featureFlagProvider = featureFlagProvider;
        this.subscriptionProvider = subscriptionProvider;
        this.deviceInfoProvider = deviceInfoProvider;
    }

    public static MembersInjector<PlaylistRecsSeeMoreFragment> create(Provider<RequestInterceptor> requestInterceptorProvider, Provider<DownloadProvider> downloadProvider, Provider<PlaybackProvider> playbackProvider, Provider<OverflowMenuProvider> overflowMenuProvider, Provider<FeatureFlagProvider> featureFlagProvider, Provider<SubscriptionProvider> subscriptionProvider, Provider<DeviceInfoProvider> deviceInfoProvider) {
        return new PlaylistRecsSeeMoreFragment_MembersInjector(requestInterceptorProvider, downloadProvider, playbackProvider, overflowMenuProvider, featureFlagProvider, subscriptionProvider, deviceInfoProvider);
    }

    public void injectMembers(PlaylistRecsSeeMoreFragment instance) {
        injectRequestInterceptor(instance, this.requestInterceptorProvider.get());
        injectDownloadProvider(instance, this.downloadProvider.get());
        injectPlaybackProvider(instance, this.playbackProvider.get());
        injectOverflowMenuProvider(instance, this.overflowMenuProvider.get());
        injectFeatureFlagProvider(instance, this.featureFlagProvider.get());
        injectSubscriptionProvider(instance, this.subscriptionProvider.get());
        injectDeviceInfoProvider(instance, this.deviceInfoProvider.get());
    }

    public static void injectRequestInterceptor(PlaylistRecsSeeMoreFragment instance, RequestInterceptor requestInterceptor) {
        instance.requestInterceptor = requestInterceptor;
    }

    public static void injectDownloadProvider(PlaylistRecsSeeMoreFragment instance, DownloadProvider downloadProvider) {
        instance.downloadProvider = downloadProvider;
    }

    public static void injectPlaybackProvider(PlaylistRecsSeeMoreFragment instance, PlaybackProvider playbackProvider) {
        instance.playbackProvider = playbackProvider;
    }

    public static void injectOverflowMenuProvider(PlaylistRecsSeeMoreFragment instance, OverflowMenuProvider overflowMenuProvider) {
        instance.overflowMenuProvider = overflowMenuProvider;
    }

    public static void injectFeatureFlagProvider(PlaylistRecsSeeMoreFragment instance, FeatureFlagProvider featureFlagProvider) {
        instance.featureFlagProvider = featureFlagProvider;
    }

    public static void injectSubscriptionProvider(PlaylistRecsSeeMoreFragment instance, SubscriptionProvider subscriptionProvider) {
        instance.subscriptionProvider = subscriptionProvider;
    }

    public static void injectDeviceInfoProvider(PlaylistRecsSeeMoreFragment instance, DeviceInfoProvider deviceInfoProvider) {
        instance.deviceInfoProvider = deviceInfoProvider;
    }
}
