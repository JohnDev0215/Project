package com.amazon.avod.media.contentcache.internal.executor;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.media.contentcache.internal.LiveCache;
import com.amazon.avod.media.contentcache.internal.executor.DownloadExecutorTask;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.qos.listeners.QoSEventManagerFactory;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadExecutor {
    private final AtomicBoolean mDownloadIsEnabled;
    private DownloadQueue mDownloadQueue;
    private volatile DownloadExecutorTask mExecutingTask;
    private final ExecutorService mExecutor;
    private final AtomicBoolean mIsRunning;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final DownloadExecutorTaskFactory mTaskFactory;
    private final Runnable mTaskRunner;

    public DownloadExecutor(@Nonnull BaseDrmSystem baseDrmSystem, @Nonnull ContentStore contentStore, @Nonnull ContentSessionFactory contentSessionFactory, @Nonnull String str, @Nonnull QoSEventManagerFactory qoSEventManagerFactory, @Nonnull ContentUrlFetcher contentUrlFetcher, @Nonnull RendererSchemeController rendererSchemeController, @Nonnull LiveCache liveCache) {
        this(NetworkConnectionManager.getInstance(), new DownloadExecutorTaskFactory(baseDrmSystem, contentSessionFactory, contentStore, qoSEventManagerFactory, contentUrlFetcher, rendererSchemeController, liveCache), ExecutorBuilder.newBuilder(str, new String[0]).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build());
    }

    DownloadExecutor(@Nonnull NetworkConnectionManager networkConnectionManager, @Nonnull DownloadExecutorTaskFactory downloadExecutorTaskFactory, @Nonnull ExecutorService executorService) {
        this.mIsRunning = new AtomicBoolean(false);
        this.mDownloadIsEnabled = new AtomicBoolean(true);
        this.mTaskRunner = new Runnable() { // from class: com.amazon.avod.media.contentcache.internal.executor.DownloadExecutor.1
            @Override // java.lang.Runnable
            public void run() {
                Downloadable nextItem;
                while (!DownloadExecutor.this.mExecutor.isShutdown() && DownloadExecutor.this.mDownloadIsEnabled.get() && DownloadExecutor.this.mNetworkConnectionManager.hasDataConnection() && (nextItem = DownloadExecutor.this.mDownloadQueue.getNextItem()) != null) {
                    DownloadExecutor downloadExecutor = DownloadExecutor.this;
                    downloadExecutor.mExecutingTask = downloadExecutor.mTaskFactory.newTaskForItem(nextItem);
                    DownloadExecutorTask.Result resultExecute = DownloadExecutor.this.mExecutingTask.execute();
                    DownloadExecutor.this.mDownloadQueue.updateItem(nextItem, resultExecute.getNewState(), resultExecute.getSessionData(), resultExecute.getOfflineKeyId(), resultExecute.getDurationCached(), resultExecute.getSizeGainedInKiloBytes());
                }
                DownloadExecutor.this.mExecutingTask = null;
                DownloadExecutor.this.mIsRunning.set(false);
            }
        };
        this.mNetworkConnectionManager = (NetworkConnectionManager) Preconditions.checkNotNull(networkConnectionManager, "networkConnectionManager");
        this.mTaskFactory = (DownloadExecutorTaskFactory) Preconditions.checkNotNull(downloadExecutorTaskFactory, "taskFactory");
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "executor");
    }

    public void start(DownloadQueue downloadQueue) {
        this.mDownloadQueue = downloadQueue;
        startIfNotRunning();
    }

    public void setDownloadIsEnabled(boolean z) {
        if (this.mDownloadIsEnabled.compareAndSet(!z, z)) {
            DLog.logf("%s %s", z ? "Resuming" : "Pausing", getClass().getSimpleName());
            refreshCurrentTask();
        }
    }

    public void refreshCurrentTask() {
        cancelCurrentTask();
        startIfNotRunning();
    }

    private void startIfNotRunning() {
        if (this.mIsRunning.compareAndSet(false, true)) {
            this.mExecutor.execute(this.mTaskRunner);
        }
    }

    private void cancelCurrentTask() {
        DownloadExecutorTask downloadExecutorTask = this.mExecutingTask;
        if (downloadExecutorTask != null) {
            downloadExecutorTask.cancel();
        }
    }
}
