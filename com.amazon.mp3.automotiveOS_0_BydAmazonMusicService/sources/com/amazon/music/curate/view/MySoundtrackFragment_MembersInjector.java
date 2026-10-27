package com.amazon.music.curate.view;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.NavigationProvider;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.SubscriptionProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class MySoundtrackFragment_MembersInjector implements MembersInjector<MySoundtrackFragment> {
    private final Provider<DeviceInfoProvider> deviceInfoProvider;
    private final Provider<DownloadProvider> downloadProvider;
    private final Provider<FeatureFlagProvider> featureFlagProvider;
    private final Provider<NavigationProvider> navigationProvider;
    private final Provider<OverflowMenuProvider> overflowMenuProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<RequestInterceptor> requestInterceptorProvider;
    private final Provider<SubscriptionProvider> subscriptionProvider;

    public MySoundtrackFragment_MembersInjector(Provider<RequestInterceptor> requestInterceptorProvider, Provider<PlaybackProvider> playbackProvider, Provider<OverflowMenuProvider> overflowMenuProvider, Provider<FeatureFlagProvider> featureFlagProvider, Provider<SubscriptionProvider> subscriptionProvider, Provider<DeviceInfoProvider> deviceInfoProvider, Provider<NavigationProvider> navigationProvider, Provider<DownloadProvider> downloadProvider) {
        this.requestInterceptorProvider = requestInterceptorProvider;
        this.playbackProvider = playbackProvider;
        this.overflowMenuProvider = overflowMenuProvider;
        this.featureFlagProvider = featureFlagProvider;
        this.subscriptionProvider = subscriptionProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.navigationProvider = navigationProvider;
        this.downloadProvider = downloadProvider;
    }

    public static MembersInjector<MySoundtrackFragment> create(Provider<RequestInterceptor> requestInterceptorProvider, Provider<PlaybackProvider> playbackProvider, Provider<OverflowMenuProvider> overflowMenuProvider, Provider<FeatureFlagProvider> featureFlagProvider, Provider<SubscriptionProvider> subscriptionProvider, Provider<DeviceInfoProvider> deviceInfoProvider, Provider<NavigationProvider> navigationProvider, Provider<DownloadProvider> downloadProvider) {
        return new MySoundtrackFragment_MembersInjector(requestInterceptorProvider, playbackProvider, overflowMenuProvider, featureFlagProvider, subscriptionProvider, deviceInfoProvider, navigationProvider, downloadProvider);
    }

    public void injectMembers(MySoundtrackFragment instance) {
        injectRequestInterceptor(instance, this.requestInterceptorProvider.get());
        injectPlaybackProvider(instance, this.playbackProvider.get());
        injectOverflowMenuProvider(instance, this.overflowMenuProvider.get());
        injectFeatureFlagProvider(instance, this.featureFlagProvider.get());
        injectSubscriptionProvider(instance, this.subscriptionProvider.get());
        injectDeviceInfoProvider(instance, this.deviceInfoProvider.get());
        injectNavigationProvider(instance, this.navigationProvider.get());
        injectDownloadProvider(instance, this.downloadProvider.get());
    }

    public static void injectRequestInterceptor(MySoundtrackFragment instance, RequestInterceptor requestInterceptor) {
        instance.requestInterceptor = requestInterceptor;
    }

    public static void injectPlaybackProvider(MySoundtrackFragment instance, PlaybackProvider playbackProvider) {
        instance.playbackProvider = playbackProvider;
    }

    public static void injectOverflowMenuProvider(MySoundtrackFragment instance, OverflowMenuProvider overflowMenuProvider) {
        instance.overflowMenuProvider = overflowMenuProvider;
    }

    public static void injectFeatureFlagProvider(MySoundtrackFragment instance, FeatureFlagProvider featureFlagProvider) {
        instance.featureFlagProvider = featureFlagProvider;
    }

    public static void injectSubscriptionProvider(MySoundtrackFragment instance, SubscriptionProvider subscriptionProvider) {
        instance.subscriptionProvider = subscriptionProvider;
    }

    public static void injectDeviceInfoProvider(MySoundtrackFragment instance, DeviceInfoProvider deviceInfoProvider) {
        instance.deviceInfoProvider = deviceInfoProvider;
    }

    public static void injectNavigationProvider(MySoundtrackFragment instance, NavigationProvider navigationProvider) {
        instance.navigationProvider = navigationProvider;
    }

    public static void injectDownloadProvider(MySoundtrackFragment instance, DownloadProvider downloadProvider) {
        instance.downloadProvider = downloadProvider;
    }
}
