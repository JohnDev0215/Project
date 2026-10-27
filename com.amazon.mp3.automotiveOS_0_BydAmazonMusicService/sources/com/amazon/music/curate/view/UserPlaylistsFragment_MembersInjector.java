package com.amazon.music.curate.view;

import com.amazon.hermes.RequestInterceptor;
import com.amazon.music.curate.provider.DownloadProvider;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.PlaybackProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class UserPlaylistsFragment_MembersInjector implements MembersInjector<UserPlaylistsFragment> {
    private final Provider<DownloadProvider> downloadProvider;
    private final Provider<FeatureFlagProvider> featureFlagProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<RequestInterceptor> requestInterceptorProvider;

    public UserPlaylistsFragment_MembersInjector(Provider<RequestInterceptor> requestInterceptorProvider, Provider<PlaybackProvider> playbackProvider, Provider<DownloadProvider> downloadProvider, Provider<FeatureFlagProvider> featureFlagProvider) {
        this.requestInterceptorProvider = requestInterceptorProvider;
        this.playbackProvider = playbackProvider;
        this.downloadProvider = downloadProvider;
        this.featureFlagProvider = featureFlagProvider;
    }

    public static MembersInjector<UserPlaylistsFragment> create(Provider<RequestInterceptor> requestInterceptorProvider, Provider<PlaybackProvider> playbackProvider, Provider<DownloadProvider> downloadProvider, Provider<FeatureFlagProvider> featureFlagProvider) {
        return new UserPlaylistsFragment_MembersInjector(requestInterceptorProvider, playbackProvider, downloadProvider, featureFlagProvider);
    }

    public void injectMembers(UserPlaylistsFragment instance) {
        injectRequestInterceptor(instance, this.requestInterceptorProvider.get());
        injectPlaybackProvider(instance, this.playbackProvider.get());
        injectDownloadProvider(instance, this.downloadProvider.get());
        injectFeatureFlagProvider(instance, this.featureFlagProvider.get());
    }

    public static void injectRequestInterceptor(UserPlaylistsFragment instance, RequestInterceptor requestInterceptor) {
        instance.requestInterceptor = requestInterceptor;
    }

    public static void injectPlaybackProvider(UserPlaylistsFragment instance, PlaybackProvider playbackProvider) {
        instance.playbackProvider = playbackProvider;
    }

    public static void injectDownloadProvider(UserPlaylistsFragment instance, DownloadProvider downloadProvider) {
        instance.downloadProvider = downloadProvider;
    }

    public static void injectFeatureFlagProvider(UserPlaylistsFragment instance, FeatureFlagProvider featureFlagProvider) {
        instance.featureFlagProvider = featureFlagProvider;
    }
}
