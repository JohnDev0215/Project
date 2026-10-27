package com.amazon.avod.download;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.media.download.plugin.ContentFetcherPlugin;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.qos.listeners.QoSEventManagerFactory;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.userdownload.internal.PlaybackDownloadQueueManager;
import com.google.common.collect.ImmutableList;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadExecutorFactory {
    private final BaseDrmSystem mBaseDrmSystem;
    private final ContentSessionFactory mContentSessionFactory;
    private final ContentStore mContentStore;
    private final ContentUrlFetcher mContentUrlFetcher;
    private final QoSEventManagerFactory mQoSEventManagerFactory;
    private final RendererSchemeController mRendererSchemeController;

    public PlaybackDownloadExecutorFactory(@Nonnull BaseDrmSystem baseDrmSystem, @Nonnull ContentStore contentStore, @Nonnull ContentSessionFactory contentSessionFactory, @Nonnull QoSEventManagerFactory qoSEventManagerFactory, @Nonnull ContentUrlFetcher contentUrlFetcher, @Nonnull RendererSchemeController rendererSchemeController) {
        this.mBaseDrmSystem = baseDrmSystem;
        this.mContentStore = contentStore;
        this.mContentSessionFactory = contentSessionFactory;
        this.mQoSEventManagerFactory = qoSEventManagerFactory;
        this.mContentUrlFetcher = contentUrlFetcher;
        this.mRendererSchemeController = rendererSchemeController;
    }

    public PlaybackQueueDownloadExecutor newDownloadExecutor(@Nonnull String str, @Nonnull PlaybackDownloadQueueManager playbackDownloadQueueManager, @Nonnull PlaybackDownloadListenerInternal playbackDownloadListenerInternal, @Nonnull ImmutableList<Provider<? extends ContentFetcherPlugin>> immutableList) {
        return newDownloadExecutor(newTaskFactory(immutableList), playbackDownloadQueueManager, playbackDownloadListenerInternal, newExecutorBuilder(str, "Download").build(), newExecutorBuilder(str, "Delete").build());
    }

    protected PlaybackQueueDownloadExecutor newDownloadExecutor(@Nonnull PlaybackQueueDownloadExecutorTaskFactory playbackQueueDownloadExecutorTaskFactory, @Nonnull PlaybackDownloadQueueManager playbackDownloadQueueManager, @Nonnull PlaybackDownloadListenerInternal playbackDownloadListenerInternal, @Nonnull ExecutorService executorService, @Nonnull ExecutorService executorService2) {
        return new PlaybackQueueDownloadExecutor(playbackQueueDownloadExecutorTaskFactory, playbackDownloadQueueManager, playbackDownloadListenerInternal, executorService, executorService2);
    }

    protected PlaybackQueueDownloadExecutorTaskFactory newTaskFactory(@Nonnull ImmutableList<Provider<? extends ContentFetcherPlugin>> immutableList) {
        return new PlaybackQueueDownloadExecutorTaskFactory(NetworkConnectionManager.getInstance(), this.mBaseDrmSystem, this.mContentSessionFactory, this.mContentStore, this.mQoSEventManagerFactory, this.mContentUrlFetcher, this.mRendererSchemeController, immutableList);
    }

    protected ExecutorBuilder newExecutorBuilder(@Nonnull String str, @Nonnull String str2) {
        return ExecutorBuilder.newBuilder(str, str2).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG);
    }
}
