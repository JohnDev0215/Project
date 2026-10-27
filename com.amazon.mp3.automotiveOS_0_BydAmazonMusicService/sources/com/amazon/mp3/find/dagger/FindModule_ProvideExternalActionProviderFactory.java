package com.amazon.mp3.find.dagger;

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
public final class FindModule_ProvideExternalActionProviderFactory implements Factory<ExternalActionProvider> {
    private final Provider<DownloadProvider> downloadProvider;
    private final Provider<LibraryProvider> libraryProvider;
    private final FindModule module;
    private final Provider<NavigationProvider> navigationProvider;
    private final Provider<NotificationProvider> notificationProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<SharingProvider> sharingProvider;

    public FindModule_ProvideExternalActionProviderFactory(FindModule module, Provider<LibraryProvider> libraryProvider, Provider<NavigationProvider> navigationProvider, Provider<NotificationProvider> notificationProvider, Provider<PlaybackProvider> playbackProvider, Provider<SharingProvider> sharingProvider, Provider<DownloadProvider> downloadProvider) {
        this.module = module;
        this.libraryProvider = libraryProvider;
        this.navigationProvider = navigationProvider;
        this.notificationProvider = notificationProvider;
        this.playbackProvider = playbackProvider;
        this.sharingProvider = sharingProvider;
        this.downloadProvider = downloadProvider;
    }

    @Override // javax.inject.Provider
    public ExternalActionProvider get() {
        return provideExternalActionProvider(this.module, this.libraryProvider.get(), this.navigationProvider.get(), this.notificationProvider.get(), this.playbackProvider.get(), this.sharingProvider.get(), this.downloadProvider.get());
    }

    public static FindModule_ProvideExternalActionProviderFactory create(FindModule module, Provider<LibraryProvider> libraryProvider, Provider<NavigationProvider> navigationProvider, Provider<NotificationProvider> notificationProvider, Provider<PlaybackProvider> playbackProvider, Provider<SharingProvider> sharingProvider, Provider<DownloadProvider> downloadProvider) {
        return new FindModule_ProvideExternalActionProviderFactory(module, libraryProvider, navigationProvider, notificationProvider, playbackProvider, sharingProvider, downloadProvider);
    }

    public static ExternalActionProvider provideExternalActionProvider(FindModule instance, LibraryProvider libraryProvider, NavigationProvider navigationProvider, NotificationProvider notificationProvider, PlaybackProvider playbackProvider, SharingProvider sharingProvider, DownloadProvider downloadProvider) {
        return (ExternalActionProvider) Preconditions.checkNotNullFromProvides(instance.provideExternalActionProvider(libraryProvider, navigationProvider, notificationProvider, playbackProvider, sharingProvider, downloadProvider));
    }
}
