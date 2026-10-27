package com.amazon.avod.media.contentcache.internal;

import android.content.Context;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.qos.listeners.QoSEventManagerFactory;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class PrioritizingVideoCacheManager_Factory implements Factory<PrioritizingVideoCacheManager> {
    private final Provider<Context> appContextProvider;
    private final Provider<ClientCacheConfig> clientCacheConfigProvider;
    private final Provider<ContentSessionFactory> contentManagerProvider;
    private final Provider<ContentStore> contentStoreProvider;
    private final Provider<BaseDrmSystem> drmSystemProvider;
    private final Provider<FileSystem> fileSystemProvider;
    private final Provider<PlaybackNativeLibrariesLoader> playbackNativeLibrariesLoaderProvider;
    private final Provider<QoSEventManagerFactory> qosEventManagerFactoryProvider;
    private final Provider<QosEventReporterFactory> qosEventReporterFactoryProvider;
    private final Provider<RendererSchemeController> rendererSchemeControllerProvider;
    private final Provider<ContentUrlFetcher> urlFetcherProvider;

    public PrioritizingVideoCacheManager_Factory(Provider<BaseDrmSystem> provider, Provider<Context> provider2, Provider<ContentSessionFactory> provider3, Provider<FileSystem> provider4, Provider<ContentStore> provider5, Provider<QoSEventManagerFactory> provider6, Provider<ContentUrlFetcher> provider7, Provider<QosEventReporterFactory> provider8, Provider<PlaybackNativeLibrariesLoader> provider9, Provider<RendererSchemeController> provider10, Provider<ClientCacheConfig> provider11) {
        this.drmSystemProvider = provider;
        this.appContextProvider = provider2;
        this.contentManagerProvider = provider3;
        this.fileSystemProvider = provider4;
        this.contentStoreProvider = provider5;
        this.qosEventManagerFactoryProvider = provider6;
        this.urlFetcherProvider = provider7;
        this.qosEventReporterFactoryProvider = provider8;
        this.playbackNativeLibrariesLoaderProvider = provider9;
        this.rendererSchemeControllerProvider = provider10;
        this.clientCacheConfigProvider = provider11;
    }

    @Override // javax.inject.Provider
    public PrioritizingVideoCacheManager get() {
        return new PrioritizingVideoCacheManager(this.drmSystemProvider.get(), this.appContextProvider.get(), this.contentManagerProvider.get(), this.fileSystemProvider.get(), this.contentStoreProvider.get(), this.qosEventManagerFactoryProvider.get(), this.urlFetcherProvider.get(), this.qosEventReporterFactoryProvider.get(), this.playbackNativeLibrariesLoaderProvider.get(), this.rendererSchemeControllerProvider.get(), this.clientCacheConfigProvider.get());
    }

    public static Factory<PrioritizingVideoCacheManager> create(Provider<BaseDrmSystem> provider, Provider<Context> provider2, Provider<ContentSessionFactory> provider3, Provider<FileSystem> provider4, Provider<ContentStore> provider5, Provider<QoSEventManagerFactory> provider6, Provider<ContentUrlFetcher> provider7, Provider<QosEventReporterFactory> provider8, Provider<PlaybackNativeLibrariesLoader> provider9, Provider<RendererSchemeController> provider10, Provider<ClientCacheConfig> provider11) {
        return new PrioritizingVideoCacheManager_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }
}
