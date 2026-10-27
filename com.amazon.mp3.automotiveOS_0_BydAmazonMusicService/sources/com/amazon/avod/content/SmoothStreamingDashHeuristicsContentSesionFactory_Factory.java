package com.amazon.avod.content;

import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class SmoothStreamingDashHeuristicsContentSesionFactory_Factory implements Factory<SmoothStreamingDashHeuristicsContentSesionFactory> {
    private final Provider<SmoothStreamingContentStore> contentStoreProvider;
    private final Provider<DownloadService> downloadServiceProvider;
    private final Provider<FileBackedContentStore> fileBackedContentStoreProvider;
    private final Provider<FileSystem> fileSystemProvider;
    private final Provider<PlaybackNativeLibrariesLoader> libraryLoaderProvider;
    private final Provider<MediaProfiler> mediaProfilerProvider;
    private final Provider<NetworkHistoryManager> networkHistoryManagerProvider;
    private final Provider<RendererSchemeController> rendererSchemeControllerProvider;
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public SmoothStreamingDashHeuristicsContentSesionFactory_Factory(Provider<NetworkHistoryManager> provider, Provider<MediaSystemSharedContext> provider2, Provider<DownloadService> provider3, Provider<FileSystem> provider4, Provider<MediaProfiler> provider5, Provider<SmoothStreamingContentStore> provider6, Provider<PlaybackNativeLibrariesLoader> provider7, Provider<RendererSchemeController> provider8, Provider<FileBackedContentStore> provider9) {
        this.networkHistoryManagerProvider = provider;
        this.sharedContextProvider = provider2;
        this.downloadServiceProvider = provider3;
        this.fileSystemProvider = provider4;
        this.mediaProfilerProvider = provider5;
        this.contentStoreProvider = provider6;
        this.libraryLoaderProvider = provider7;
        this.rendererSchemeControllerProvider = provider8;
        this.fileBackedContentStoreProvider = provider9;
    }

    @Override // javax.inject.Provider
    public SmoothStreamingDashHeuristicsContentSesionFactory get() {
        return new SmoothStreamingDashHeuristicsContentSesionFactory(this.networkHistoryManagerProvider.get(), this.sharedContextProvider.get(), this.downloadServiceProvider.get(), this.fileSystemProvider.get(), this.mediaProfilerProvider.get(), this.contentStoreProvider.get(), this.libraryLoaderProvider.get(), this.rendererSchemeControllerProvider.get(), this.fileBackedContentStoreProvider.get());
    }

    public static Factory<SmoothStreamingDashHeuristicsContentSesionFactory> create(Provider<NetworkHistoryManager> provider, Provider<MediaSystemSharedContext> provider2, Provider<DownloadService> provider3, Provider<FileSystem> provider4, Provider<MediaProfiler> provider5, Provider<SmoothStreamingContentStore> provider6, Provider<PlaybackNativeLibrariesLoader> provider7, Provider<RendererSchemeController> provider8, Provider<FileBackedContentStore> provider9) {
        return new SmoothStreamingDashHeuristicsContentSesionFactory_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }
}
