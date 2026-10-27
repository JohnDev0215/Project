package com.amazon.music.explore.providers.impl;

import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.LibraryProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.NotificationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class ExploreExternalActionProvider_Factory implements Factory<ExploreExternalActionProvider> {
    private final Provider<DownloadProvider> downloadProvider;
    private final Provider<LibraryProvider> libraryProvider;
    private final Provider<NavigationProvider> navigationProvider;
    private final Provider<NotificationProvider> notificationProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<SharingProvider> sharingProvider;

    public ExploreExternalActionProvider_Factory(Provider<LibraryProvider> provider, Provider<NavigationProvider> provider2, Provider<NotificationProvider> provider3, Provider<PlaybackProvider> provider4, Provider<SharingProvider> provider5, Provider<DownloadProvider> provider6) {
        this.libraryProvider = provider;
        this.navigationProvider = provider2;
        this.notificationProvider = provider3;
        this.playbackProvider = provider4;
        this.sharingProvider = provider5;
        this.downloadProvider = provider6;
    }

    @Override // javax.inject.Provider
    public ExploreExternalActionProvider get() {
        return newInstance(this.libraryProvider.get(), this.navigationProvider.get(), this.notificationProvider.get(), this.playbackProvider.get(), this.sharingProvider.get(), this.downloadProvider.get());
    }

    public static ExploreExternalActionProvider_Factory create(Provider<LibraryProvider> provider, Provider<NavigationProvider> provider2, Provider<NotificationProvider> provider3, Provider<PlaybackProvider> provider4, Provider<SharingProvider> provider5, Provider<DownloadProvider> provider6) {
        return new ExploreExternalActionProvider_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static ExploreExternalActionProvider newInstance(LibraryProvider libraryProvider, NavigationProvider navigationProvider, NotificationProvider notificationProvider, PlaybackProvider playbackProvider, SharingProvider sharingProvider, DownloadProvider downloadProvider) {
        return new ExploreExternalActionProvider(libraryProvider, navigationProvider, notificationProvider, playbackProvider, sharingProvider, downloadProvider);
    }
}
