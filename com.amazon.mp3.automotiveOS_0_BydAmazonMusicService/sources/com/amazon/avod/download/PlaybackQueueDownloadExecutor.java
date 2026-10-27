package com.amazon.avod.download;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.download.PlaybackDownloadExecutorTask;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.userdownload.internal.PlaybackDownloadJob;
import com.amazon.avod.userdownload.internal.PlaybackDownloadQueueManager;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackQueueDownloadExecutor {
    private final ExecutorService mDeleteExecutor;
    private final DownloadTaskRunner mDeletionTaskRunner;
    private final ExecutorService mDownloadExecutor;
    private final PlaybackDownloadListenerInternal mDownloadListener;
    private final PlaybackDownloadQueueManager mDownloadQueue;
    private final DownloadTaskRunner mDownloadTaskRunner;
    private final PlaybackQueueDownloadExecutorTaskFactory mTaskFactory;
    private final Object mTaskLock = new Object();

    @VisibleForTesting
    PlaybackQueueDownloadExecutor(@Nonnull PlaybackQueueDownloadExecutorTaskFactory playbackQueueDownloadExecutorTaskFactory, @Nonnull PlaybackDownloadQueueManager playbackDownloadQueueManager, @Nonnull PlaybackDownloadListenerInternal playbackDownloadListenerInternal, @Nonnull ExecutorService executorService, @Nonnull ExecutorService executorService2) {
        this.mTaskFactory = (PlaybackQueueDownloadExecutorTaskFactory) Preconditions.checkNotNull(playbackQueueDownloadExecutorTaskFactory, "taskFactory");
        this.mDownloadQueue = (PlaybackDownloadQueueManager) Preconditions.checkNotNull(playbackDownloadQueueManager, "downloadQueue");
        this.mDownloadListener = (PlaybackDownloadListenerInternal) Preconditions.checkNotNull(playbackDownloadListenerInternal, "downloadListener");
        this.mDownloadExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "downloadExecutor");
        this.mDeleteExecutor = (ExecutorService) Preconditions.checkNotNull(executorService2, "deleteExecutor");
        this.mDownloadTaskRunner = new DownloadTaskRunner(new DownloadTaskProvider());
        this.mDeletionTaskRunner = new DownloadTaskRunner(new DeletionTaskProvider());
    }

    public void refreshCurrentTask() {
        Optional<PlaybackDownloadJob> nextDeletion = this.mDownloadQueue.getNextDeletion();
        Optional<PlaybackDownloadJob> nextDownload = this.mDownloadQueue.getNextDownload();
        synchronized (this.mTaskLock) {
            refreshCurrentTask(nextDeletion, this.mDeletionTaskRunner, this.mDeleteExecutor);
            refreshCurrentTask(nextDownload, this.mDownloadTaskRunner, this.mDownloadExecutor);
        }
    }

    private void refreshCurrentTask(@Nonnull Optional<PlaybackDownloadJob> optional, @Nonnull DownloadTaskRunner downloadTaskRunner, @Nonnull ExecutorService executorService) {
        if (downloadTaskRunner.mExecutingTask == null || !downloadTaskRunner.mExecutingTask.getItem().isEquivalent(optional.orNull())) {
            if (downloadTaskRunner.mExecutingTask != null) {
                downloadTaskRunner.mExecutingTask.cancel();
            }
            downloadTaskRunner.mLatestRequestId++;
            executorService.execute(downloadTaskRunner);
        }
    }

    private class DownloadTaskRunner implements Runnable {

        @GuardedBy("mTaskLock")
        private PlaybackDownloadExecutorTask mExecutingTask;

        @GuardedBy("mTaskLock")
        private long mLastProcessedRequestId;

        @GuardedBy("mTaskLock")
        private long mLatestRequestId;
        private final Provider<Optional<PlaybackDownloadExecutorTask>> mTaskProvider;

        DownloadTaskRunner(@Nonnull Provider<Optional<PlaybackDownloadExecutorTask>> provider) {
            this.mTaskProvider = (Provider) Preconditions.checkNotNull(provider, "taskProvider");
        }

        @Override // java.lang.Runnable
        public void run() {
            long j;
            synchronized (PlaybackQueueDownloadExecutor.this.mTaskLock) {
                j = this.mLatestRequestId;
            }
            while (!PlaybackQueueDownloadExecutor.this.mDownloadExecutor.isShutdown()) {
                PlaybackDownloadExecutorTask playbackDownloadExecutorTaskOrNull = this.mTaskProvider.get().orNull();
                synchronized (PlaybackQueueDownloadExecutor.this.mTaskLock) {
                    this.mExecutingTask = playbackDownloadExecutorTaskOrNull;
                    if (playbackDownloadExecutorTaskOrNull != null && j == this.mLatestRequestId) {
                    }
                    this.mLastProcessedRequestId = j;
                    return;
                }
                TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, playbackDownloadExecutorTaskOrNull.getClass().getSimpleName());
                PlaybackDownloadExecutorTask.Result resultExecute = playbackDownloadExecutorTaskOrNull.execute();
                PlaybackQueueDownloadExecutor.this.mDownloadListener.updateState(playbackDownloadExecutorTaskOrNull.getItem(), resultExecute.getNewState(), resultExecute.getErrorCode());
                Profiler.endTrace(traceKeyBeginTrace);
            }
        }
    }

    private class DeletionTaskProvider implements Provider<Optional<PlaybackDownloadExecutorTask>> {
        private DeletionTaskProvider() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public Optional<PlaybackDownloadExecutorTask> get() {
            Optional<PlaybackDownloadJob> nextDeletion = PlaybackQueueDownloadExecutor.this.mDownloadQueue.getNextDeletion();
            if (nextDeletion.isPresent()) {
                return PlaybackQueueDownloadExecutor.this.mTaskFactory.newDeleteTaskForItem(nextDeletion.get(), PlaybackQueueDownloadExecutor.this.mDownloadListener);
            }
            return Optional.absent();
        }
    }

    private class DownloadTaskProvider implements Provider<Optional<PlaybackDownloadExecutorTask>> {
        private DownloadTaskProvider() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public Optional<PlaybackDownloadExecutorTask> get() {
            Optional<PlaybackDownloadJob> nextDownload = PlaybackQueueDownloadExecutor.this.mDownloadQueue.getNextDownload();
            if (nextDownload.isPresent()) {
                return PlaybackQueueDownloadExecutor.this.mTaskFactory.newDownloadTaskForItem(nextDownload.get(), PlaybackQueueDownloadExecutor.this.mDownloadListener);
            }
            return Optional.absent();
        }
    }
}
