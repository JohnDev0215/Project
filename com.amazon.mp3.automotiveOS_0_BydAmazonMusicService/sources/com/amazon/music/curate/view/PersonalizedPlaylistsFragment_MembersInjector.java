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
public final class PersonalizedPlaylistsFragment_MembersInjector implements MembersInjector<PersonalizedPlaylistsFragment> {
    private final Provider<DeviceInfoProvider> deviceInfoProvider;
    private final Provider<DownloadProvider> downloadProvider;
    private final Provider<FeatureFlagProvider> featureFlagProvider;
    private final Provider<NavigationProvider> navigationProvider;
    private final Provider<OverflowMenuProvider> overflowMenuProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<RequestInterceptor> requestInterceptorProvider;
    private final Provider<SubscriptionProvider> subscriptionProvider;

    public PersonalizedPlaylistsFragment_MembersInjector(Provider<RequestInterceptor> requestInterceptorProvider, Provider<PlaybackProvider> playbackProvider, Provider<OverflowMenuProvider> overflowMenuProvider, Provider<DownloadProvider> downloadProvider, Provider<FeatureFlagProvider> featureFlagProvider, Provider<SubscriptionProvider> subscriptionProvider, Provider<NavigationProvider> navigationProvider, Provider<DeviceInfoProvider> deviceInfoProvider) {
        this.requestInterceptorProvider = requestInterceptorProvider;
        this.playbackProvider = playbackProvider;
        this.overflowMenuProvider = overflowMenuProvider;
        this.downloadProvider = downloadProvider;
        this.featureFlagProvider = featureFlagProvider;
        this.subscriptionProvider = subscriptionProvider;
        this.navigationProvider = navigationProvider;
        this.deviceInfoProvider = deviceInfoProvider;
    }

    public static MembersInjector<PersonalizedPlaylistsFragment> create(Provider<RequestInterceptor> requestInterceptorProvider, Provider<PlaybackProvider> playbackProvider, Provider<OverflowMenuProvider> overflowMenuProvider, Provider<DownloadProvider> downloadProvider, Provider<FeatureFlagProvider> featureFlagProvider, Provider<SubscriptionProvider> subscriptionProvider, Provider<NavigationProvider> navigationProvider, Provider<DeviceInfoProvider> deviceInfoProvider) {
        return new PersonalizedPlaylistsFragment_MembersInjector(requestInterceptorProvider, playbackProvider, overflowMenuProvider, downloadProvider, featureFlagProvider, subscriptionProvider, navigationProvider, deviceInfoProvider);
    }

    public void injectMembers(PersonalizedPlaylistsFragment instance) {
        injectRequestInterceptor(instance, this.requestInterceptorProvider.get());
        injectPlaybackProvider(instance, this.playbackProvider.get());
        injectOverflowMenuProvider(instance, this.overflowMenuProvider.get());
        injectDownloadProvider(instance, this.downloadProvider.get());
        injectFeatureFlagProvider(instance, this.featureFlagProvider.get());
        injectSubscriptionProvider(instance, this.subscriptionProvider.get());
        injectNavigationProvider(instance, this.navigationProvider.get());
        injectDeviceInfoProvider(instance, this.deviceInfoProvider.get());
    }

    public static void injectRequestInterceptor(PersonalizedPlaylistsFragment instance, RequestInterceptor requestInterceptor) {
        instance.requestInterceptor = requestInterceptor;
    }

    public static void injectPlaybackProvider(PersonalizedPlaylistsFragment instance, PlaybackProvider playbackProvider) {
        instance.playbackProvider = playbackProvider;
    }

    public static void injectOverflowMenuProvider(PersonalizedPlaylistsFragment instance, OverflowMenuProvider overflowMenuProvider) {
        instance.overflowMenuProvider = overflowMenuProvider;
    }

    public static void injectDownloadProvider(PersonalizedPlaylistsFragment instance, DownloadProvider downloadProvider) {
        instance.downloadProvider = downloadProvider;
    }

    public static void injectFeatureFlagProvider(PersonalizedPlaylistsFragment instance, FeatureFlagProvider featureFlagProvider) {
        instance.featureFlagProvider = featureFlagProvider;
    }

    public static void injectSubscriptionProvider(PersonalizedPlaylistsFragment instance, SubscriptionProvider subscriptionProvider) {
        instance.subscriptionProvider = subscriptionProvider;
    }

    public static void injectNavigationProvider(PersonalizedPlaylistsFragment instance, NavigationProvider navigationProvider) {
        instance.navigationProvider = navigationProvider;
    }

    public static void injectDeviceInfoProvider(PersonalizedPlaylistsFragment instance, DeviceInfoProvider deviceInfoProvider) {
        instance.deviceInfoProvider = deviceInfoProvider;
    }
}
