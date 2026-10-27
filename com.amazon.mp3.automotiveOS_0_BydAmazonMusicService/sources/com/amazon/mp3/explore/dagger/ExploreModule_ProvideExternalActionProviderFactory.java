package com.amazon.mp3.explore.dagger;

import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.LibraryProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.NotificationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideExternalActionProviderFactory implements Factory<ExternalActionProvider> {
    private final Provider<DownloadProvider> downloadProvider;
    private final Provider<LibraryProvider> libraryProvider;
    private final Provider<SharingProvider> linkSharingProvider;
    private final ExploreModule module;
    private final Provider<NavigationProvider> navigationProvider;
    private final Provider<NotificationProvider> notificationProvider;
    private final Provider<PlaybackProvider> playbackProvider;

    public ExploreModule_ProvideExternalActionProviderFactory(ExploreModule module, Provider<LibraryProvider> libraryProvider, Provider<NavigationProvider> navigationProvider, Provider<NotificationProvider> notificationProvider, Provider<PlaybackProvider> playbackProvider, Provider<SharingProvider> linkSharingProvider, Provider<DownloadProvider> downloadProvider) {
        this.module = module;
        this.libraryProvider = libraryProvider;
        this.navigationProvider = navigationProvider;
        this.notificationProvider = notificationProvider;
        this.playbackProvider = playbackProvider;
        this.linkSharingProvider = linkSharingProvider;
        this.downloadProvider = downloadProvider;
    }

    @Override // javax.inject.Provider
    public ExternalActionProvider get() {
        return provideExternalActionProvider(this.module, this.libraryProvider.get(), this.navigationProvider.get(), this.notificationProvider.get(), this.playbackProvider.get(), this.linkSharingProvider.get(), this.downloadProvider.get());
    }

    public static ExploreModule_ProvideExternalActionProviderFactory create(ExploreModule module, Provider<LibraryProvider> libraryProvider, Provider<NavigationProvider> navigationProvider, Provider<NotificationProvider> notificationProvider, Provider<PlaybackProvider> playbackProvider, Provider<SharingProvider> linkSharingProvider, Provider<DownloadProvider> downloadProvider) {
        return new ExploreModule_ProvideExternalActionProviderFactory(module, libraryProvider, navigationProvider, notificationProvider, playbackProvider, linkSharingProvider, downloadProvider);
    }

    public static ExternalActionProvider provideExternalActionProvider(ExploreModule instance, LibraryProvider libraryProvider, NavigationProvider navigationProvider, NotificationProvider notificationProvider, PlaybackProvider playbackProvider, SharingProvider linkSharingProvider, DownloadProvider downloadProvider) {
        return (ExternalActionProvider) Preconditions.checkNotNullFromProvides(instance.provideExternalActionProvider(libraryProvider, navigationProvider, notificationProvider, playbackProvider, linkSharingProvider, downloadProvider));
    }
}
