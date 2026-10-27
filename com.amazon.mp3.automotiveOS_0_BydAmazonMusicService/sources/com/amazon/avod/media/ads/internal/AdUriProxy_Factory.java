package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.playback.QOSCommunicationService;
import dagger.internal.Factory;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AdUriProxy_Factory implements Factory<AdUriProxy> {
    private final Provider<AndroidVideoPlayerV2Cache> androidVideoPlayerV2CacheProvider;
    private final Provider<DownloadService> downloadServiceProvider;
    private final Provider<ExecutorService> executorServiceProvider;
    private final Provider<FileSystem> fileSystemProvider;
    private final Provider<QOSCommunicationService> qosCommunicationServiceProvider;

    public AdUriProxy_Factory(Provider<DownloadService> provider, Provider<FileSystem> provider2, Provider<ExecutorService> provider3, Provider<QOSCommunicationService> provider4, Provider<AndroidVideoPlayerV2Cache> provider5) {
        this.downloadServiceProvider = provider;
        this.fileSystemProvider = provider2;
        this.executorServiceProvider = provider3;
        this.qosCommunicationServiceProvider = provider4;
        this.androidVideoPlayerV2CacheProvider = provider5;
    }

    @Override // javax.inject.Provider
    public AdUriProxy get() {
        return new AdUriProxy(this.downloadServiceProvider.get(), this.fileSystemProvider.get(), this.executorServiceProvider.get(), this.qosCommunicationServiceProvider.get(), this.androidVideoPlayerV2CacheProvider.get());
    }

    public static Factory<AdUriProxy> create(Provider<DownloadService> provider, Provider<FileSystem> provider2, Provider<ExecutorService> provider3, Provider<QOSCommunicationService> provider4, Provider<AndroidVideoPlayerV2Cache> provider5) {
        return new AdUriProxy_Factory(provider, provider2, provider3, provider4, provider5);
    }
}
