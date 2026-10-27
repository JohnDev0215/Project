package com.amazon.avod.userdownload;

import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.userdownload.internal.PlaybackDownloadManager;
import com.amazon.avod.userdownload.internal.PlaybackDownloadSharedComponents;
import com.amazon.avod.userdownload.internal.PlaybackDownloadsComponentFactory;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloads extends PVDownloadsPlayerShim {
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);
    private PlaybackDownloadManager mPlaybackDownloadManager;
    private PlaybackDownloadSharedComponents mSharedComponents;

    private static class Holder {
        private static volatile PlaybackDownloads sInstance;

        private Holder() {
        }
    }

    public static void initializeSingleton() {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "Downloads:CreateSingleton");
        setInstance(new PlaybackDownloads());
        Profiler.endTrace(traceKeyBeginTrace);
    }

    public static void initializePlaybackDownloadsSingleton() {
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "Downloads:CreatePlaybackDownloadSingleton");
        setPlaybackDownloadsInstance(new PlaybackDownloads());
        Profiler.endTrace(traceKeyBeginTrace);
    }

    PlaybackDownloads() {
    }

    public static PlaybackDownloads getInstance() {
        return Holder.sInstance;
    }

    public static void setInstance(@Nonnull PlaybackDownloads playbackDownloads) {
        Preconditions.checkNotNull(playbackDownloads, "instance");
        PlaybackDownloads unused = Holder.sInstance = playbackDownloads;
        PVDownloadsPlayerShim.setInstance(playbackDownloads);
    }

    public static void setPlaybackDownloadsInstance(@Nonnull PlaybackDownloads playbackDownloads) {
        Preconditions.checkNotNull(playbackDownloads, "instance");
        PlaybackDownloads unused = Holder.sInstance = playbackDownloads;
    }

    public void initialize(@Nonnull PlaybackDownloadsComponentFactory playbackDownloadsComponentFactory) {
        Preconditions.checkNotNull(playbackDownloadsComponentFactory, "downloadManagerFactory");
        this.mInitializationLatch.start(15L, TimeUnit.SECONDS);
        this.mSharedComponents = playbackDownloadsComponentFactory.getSharedComponents();
        this.mPlaybackDownloadManager = playbackDownloadsComponentFactory.generateDownloadManager();
        this.mInitializationLatch.complete();
    }

    @Nonnull
    public PlaybackDownloadLocationConfig getLocationConfig() {
        this.mInitializationLatch.checkInitialized();
        return this.mSharedComponents.getLocationConfig();
    }

    @Override // com.amazon.avod.userdownload.PVDownloadsPlayerShim
    @Nonnull
    public PlaybackDownloadManager getDownloadManager() {
        return this.mPlaybackDownloadManager;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadsPlayerShim
    @Nonnull
    public StorageHelper getStorageHelper() {
        this.mInitializationLatch.checkInitialized();
        return this.mSharedComponents.getStorageHelper();
    }
}
