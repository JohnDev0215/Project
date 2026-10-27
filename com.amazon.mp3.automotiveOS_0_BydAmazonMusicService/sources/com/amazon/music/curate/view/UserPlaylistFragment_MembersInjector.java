package com.amazon.music.curate.view;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.NetworkProvider;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import com.amazon.music.curate.provider.PlaylistProvider;
import com.amazon.music.curate.provider.ShareProvider;
import com.amazon.music.curate.provider.StationProvider;
import com.amazon.music.curate.provider.SubscriptionProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class UserPlaylistFragment_MembersInjector implements MembersInjector<UserPlaylistFragment> {
    private final Provider<DeviceInfoProvider> deviceInfoProvider;
    private final Provider<DownloadProvider> downloadProvider;
    private final Provider<FeatureFlagProvider> featureFlagProvider;
    private final Provider<NetworkProvider> networkProvider;
    private final Provider<OverflowMenuProvider> overflowMenuProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<PlaylistProvider> playlistProvider;
    private final Provider<RequestInterceptor> requestInterceptorProvider;
    private final Provider<ShareProvider> shareProvider;
    private final Provider<StationProvider> stationProvider;
    private final Provider<SubscriptionProvider> subscriptionProvider;

    public UserPlaylistFragment_MembersInjector(Provider<RequestInterceptor> requestInterceptorProvider, Provider<DownloadProvider> downloadProvider, Provider<ShareProvider> shareProvider, Provider<StationProvider> stationProvider, Provider<PlaybackProvider> playbackProvider, Provider<OverflowMenuProvider> overflowMenuProvider, Provider<PlaylistProvider> playlistProvider, Provider<FeatureFlagProvider> featureFlagProvider, Provider<NetworkProvider> networkProvider, Provider<SubscriptionProvider> subscriptionProvider, Provider<DeviceInfoProvider> deviceInfoProvider) {
        this.requestInterceptorProvider = requestInterceptorProvider;
        this.downloadProvider = downloadProvider;
        this.shareProvider = shareProvider;
        this.stationProvider = stationProvider;
        this.playbackProvider = playbackProvider;
        this.overflowMenuProvider = overflowMenuProvider;
        this.playlistProvider = playlistProvider;
        this.featureFlagProvider = featureFlagProvider;
        this.networkProvider = networkProvider;
        this.subscriptionProvider = subscriptionProvider;
        this.deviceInfoProvider = deviceInfoProvider;
    }

    public static MembersInjector<UserPlaylistFragment> create(Provider<RequestInterceptor> requestInterceptorProvider, Provider<DownloadProvider> downloadProvider, Provider<ShareProvider> shareProvider, Provider<StationProvider> stationProvider, Provider<PlaybackProvider> playbackProvider, Provider<OverflowMenuProvider> overflowMenuProvider, Provider<PlaylistProvider> playlistProvider, Provider<FeatureFlagProvider> featureFlagProvider, Provider<NetworkProvider> networkProvider, Provider<SubscriptionProvider> subscriptionProvider, Provider<DeviceInfoProvider> deviceInfoProvider) {
        return new UserPlaylistFragment_MembersInjector(requestInterceptorProvider, downloadProvider, shareProvider, stationProvider, playbackProvider, overflowMenuProvider, playlistProvider, featureFlagProvider, networkProvider, subscriptionProvider, deviceInfoProvider);
    }

    public void injectMembers(UserPlaylistFragment instance) {
        injectRequestInterceptor(instance, this.requestInterceptorProvider.get());
        injectDownloadProvider(instance, this.downloadProvider.get());
        injectShareProvider(instance, this.shareProvider.get());
        injectStationProvider(instance, this.stationProvider.get());
        injectPlaybackProvider(instance, this.playbackProvider.get());
        injectOverflowMenuProvider(instance, this.overflowMenuProvider.get());
        injectPlaylistProvider(instance, this.playlistProvider.get());
        injectFeatureFlagProvider(instance, this.featureFlagProvider.get());
        injectNetworkProvider(instance, this.networkProvider.get());
        injectSubscriptionProvider(instance, this.subscriptionProvider.get());
        injectDeviceInfoProvider(instance, this.deviceInfoProvider.get());
    }

    public static void injectRequestInterceptor(UserPlaylistFragment instance, RequestInterceptor requestInterceptor) {
        instance.requestInterceptor = requestInterceptor;
    }

    public static void injectDownloadProvider(UserPlaylistFragment instance, DownloadProvider downloadProvider) {
        instance.downloadProvider = downloadProvider;
    }

    public static void injectShareProvider(UserPlaylistFragment instance, ShareProvider shareProvider) {
        instance.shareProvider = shareProvider;
    }

    public static void injectStationProvider(UserPlaylistFragment instance, StationProvider stationProvider) {
        instance.stationProvider = stationProvider;
    }

    public static void injectPlaybackProvider(UserPlaylistFragment instance, PlaybackProvider playbackProvider) {
        instance.playbackProvider = playbackProvider;
    }

    public static void injectOverflowMenuProvider(UserPlaylistFragment instance, OverflowMenuProvider overflowMenuProvider) {
        instance.overflowMenuProvider = overflowMenuProvider;
    }

    public static void injectPlaylistProvider(UserPlaylistFragment instance, PlaylistProvider playlistProvider) {
        instance.playlistProvider = playlistProvider;
    }

    public static void injectFeatureFlagProvider(UserPlaylistFragment instance, FeatureFlagProvider featureFlagProvider) {
        instance.featureFlagProvider = featureFlagProvider;
    }

    public static void injectNetworkProvider(UserPlaylistFragment instance, NetworkProvider networkProvider) {
        instance.networkProvider = networkProvider;
    }

    public static void injectSubscriptionProvider(UserPlaylistFragment instance, SubscriptionProvider subscriptionProvider) {
        instance.subscriptionProvider = subscriptionProvider;
    }

    public static void injectDeviceInfoProvider(UserPlaylistFragment instance, DeviceInfoProvider deviceInfoProvider) {
        instance.deviceInfoProvider = deviceInfoProvider;
    }
}
