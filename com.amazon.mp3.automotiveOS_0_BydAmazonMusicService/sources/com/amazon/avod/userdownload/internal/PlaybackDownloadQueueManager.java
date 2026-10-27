package com.amazon.avod.userdownload.internal;

import android.content.Context;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.userdownload.filter.PlaybackDownloadFilter;
import com.amazon.avod.userdownload.internal.PlaybackDownloadQueueFactory;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.UnmodifiableIterator;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadQueueManager {
    private final PlaybackDownloadQueueFactory.PlaybackDownloadQueue mDeletionQueue;
    private final PlaybackDownloadQueueFactory.PlaybackDownloadQueue mDownloadQueue;
    private final InitializationLatch mInitializationLatch;
    private final PauseTokenVendor mPauseTokenVendor;
    private final PlaybackDownloadPersistence mPlaybackDownloadPersistence;

    private boolean canRetryErroredDownload(@Nonnull PlaybackDownload playbackDownload) {
        return false;
    }

    private boolean shouldWait(@Nonnull PlaybackDownload playbackDownload) {
        return false;
    }

    public PlaybackDownloadQueueManager(@Nonnull PlaybackDownloadPersistence playbackDownloadPersistence, @Nonnull PauseTokenVendor pauseTokenVendor) {
        this(playbackDownloadPersistence, pauseTokenVendor, new PlaybackDownloadQueueFactory(playbackDownloadPersistence));
    }

    PlaybackDownloadQueueManager(@Nonnull PlaybackDownloadPersistence playbackDownloadPersistence, @Nonnull PauseTokenVendor pauseTokenVendor, @Nonnull PlaybackDownloadQueueFactory playbackDownloadQueueFactory) {
        this.mInitializationLatch = new InitializationLatch(this);
        this.mPlaybackDownloadPersistence = (PlaybackDownloadPersistence) Preconditions.checkNotNull(playbackDownloadPersistence, "downloadPersistence");
        this.mPauseTokenVendor = (PauseTokenVendor) Preconditions.checkNotNull(pauseTokenVendor, "pauseTokenVendor");
        this.mDeletionQueue = playbackDownloadQueueFactory.newDeletionQueue();
        this.mDownloadQueue = playbackDownloadQueueFactory.newDownloadQueue();
    }

    public void initialize(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        this.mInitializationLatch.start(30L, TimeUnit.SECONDS);
        this.mInitializationLatch.complete();
    }

    @Nonnull
    public Optional<PlaybackDownloadJob> getNextDownload() {
        this.mInitializationLatch.checkInitialized();
        ImmutableList<PlaybackDownload> queueSnapshot = this.mDownloadQueue.getQueueSnapshot();
        Optional<PlaybackDownloadJob> runningJob = getRunningJob(queueSnapshot);
        UnmodifiableIterator<PlaybackDownload> it = queueSnapshot.iterator();
        while (it.hasNext()) {
            Optional<PlaybackDownloadJob> optionalApplyDownloadRules = applyDownloadRules(it.next(), queueSnapshot);
            if (optionalApplyDownloadRules.isPresent()) {
                if (!runningJob.isPresent() || optionalApplyDownloadRules.get().getDownload().getState() != UserDownloadState.ERROR) {
                    runningJob = optionalApplyDownloadRules;
                }
                DLog.logf("DWNLD_SDK DownloadQueue Processed download queue with %d pending jobs; selected %s as next job", Integer.valueOf(queueSnapshot.size()), runningJob.orNull());
                return runningJob;
            }
        }
        DLog.logf("DWNLD_SDK DownloadQueue Processed download queue with %d pending jobs; no actionable job found.", Integer.valueOf(queueSnapshot.size()));
        return Optional.absent();
    }

    private Optional<PlaybackDownloadJob> getRunningJob(@Nonnull ImmutableList<PlaybackDownload> immutableList) {
        UnmodifiableIterator<PlaybackDownload> it = immutableList.iterator();
        while (it.hasNext()) {
            PlaybackDownload next = it.next();
            if (next.getState() == UserDownloadState.DOWNLOADING) {
                return applyDownloadRules(next, immutableList);
            }
        }
        return Optional.absent();
    }

    @Nonnull
    public PlaybackDownload promoteDownloadToFrontOfQueue(@Nonnull PlaybackDownload playbackDownload, @Nonnull ImmutableList<PlaybackDownload> immutableList) {
        Preconditions.checkNotNull(playbackDownload, "downloadToPromote");
        Preconditions.checkNotNull(immutableList, "downloadQueue");
        UnmodifiableIterator<PlaybackDownload> it = immutableList.iterator();
        int iMin = 0;
        while (it.hasNext()) {
            iMin = Math.min(iMin, it.next().getQueuePosition() - 1);
        }
        PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(playbackDownload).setQueuePosition(iMin).build();
        this.mPlaybackDownloadPersistence.update(playbackDownloadBuild);
        return playbackDownloadBuild;
    }

    @Nonnull
    public Optional<PlaybackDownloadJob> getNextDeletion() {
        this.mInitializationLatch.checkInitialized();
        ImmutableList<PlaybackDownload> queueSnapshot = this.mDeletionQueue.getQueueSnapshot();
        if (queueSnapshot.isEmpty()) {
            return Optional.absent();
        }
        return toJob(queueSnapshot.iterator().next());
    }

    @Nonnull
    public ImmutableList<PlaybackDownload> getDownloadQueueSnapshot() {
        this.mInitializationLatch.checkInitialized();
        return this.mDownloadQueue.getQueueSnapshot();
    }

    public boolean isJobInQueue(@Nonnull PlaybackDownloadJob playbackDownloadJob) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownloadJob, "job");
        if (playbackDownloadJob.getDownloadableState() == Downloadable.DownloadableState.DELETE_IMMEDIATELY) {
            return isJobInQueue(playbackDownloadJob, this.mDeletionQueue);
        }
        if (playbackDownloadJob.getDownloadableState() == Downloadable.DownloadableState.QUEUED) {
            return isJobInQueue(playbackDownloadJob, this.mDownloadQueue);
        }
        return false;
    }

    private boolean isJobInQueue(@Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull PlaybackDownloadQueueFactory.PlaybackDownloadQueue playbackDownloadQueue) {
        this.mInitializationLatch.checkInitialized();
        PlaybackDownload download = playbackDownloadJob.getDownload();
        Optional optionalTryFind = Iterables.tryFind(this.mPlaybackDownloadPersistence.getAllDownloads(download.getAsin()), PlaybackDownloadFilter.newOwnerIdFilter(download.getSessionContext()));
        if (!optionalTryFind.isPresent() || !playbackDownloadQueue.isInQueue((PlaybackDownload) optionalTryFind.get())) {
            return false;
        }
        Optional<PlaybackDownloadJob> job = toJob((PlaybackDownload) optionalTryFind.get());
        return job.isPresent() && job.get().isEquivalent(playbackDownloadJob);
    }

    @Nonnull
    private Optional<PlaybackDownloadJob> toJob(@Nonnull PlaybackDownload playbackDownload) {
        Optional<Downloadable.DownloadableState> jobState = toJobState(playbackDownload.getState());
        if (jobState.isPresent()) {
            return Optional.of(new PlaybackDownloadJob(playbackDownload, jobState.get(), playbackDownload.getPlaybackSessionContext()));
        }
        return Optional.absent();
    }

    @Nonnull
    private Optional<Downloadable.DownloadableState> toJobState(@Nonnull UserDownloadState userDownloadState) {
        if (BaseDownloadQueueFactory.DOWNLOAD_STATES.contains(userDownloadState)) {
            return Optional.of(Downloadable.DownloadableState.QUEUED);
        }
        if (BaseDownloadQueueFactory.DELETE_STATES.contains(userDownloadState)) {
            return Optional.of(Downloadable.DownloadableState.DELETE_IMMEDIATELY);
        }
        return Optional.absent();
    }

    private Optional<PlaybackDownloadJob> applyDownloadRules(@Nonnull PlaybackDownload playbackDownload, @Nonnull ImmutableList<PlaybackDownload> immutableList) {
        if (playbackDownload.getState() == UserDownloadState.PAUSED || shouldWait(playbackDownload) || hasPauseToken(playbackDownload)) {
            return Optional.absent();
        }
        if (playbackDownload.getState() == UserDownloadState.ERROR && !canRetryErroredDownload(playbackDownload)) {
            return Optional.absent();
        }
        return toJob(playbackDownload);
    }

    private boolean hasPauseToken(@Nonnull PlaybackDownload playbackDownload) {
        if (shouldIgnorePauseToken(playbackDownload)) {
            return false;
        }
        return this.mPauseTokenVendor.hasAnyVendedTokens();
    }

    private boolean shouldIgnorePauseToken(@Nonnull PlaybackDownload playbackDownload) {
        boolean zIsPresent = allowRetryForDownloadRegardlessNetworkType(playbackDownload) ? playbackDownload.getErrorCode().isPresent() : false;
        DLog.logf("DWNLD DownloadQueue should ignore pause token: %b", Boolean.valueOf(zIsPresent));
        return zIsPresent;
    }

    private boolean allowRetryForDownloadRegardlessNetworkType(@Nonnull PlaybackDownload playbackDownload) {
        return playbackDownload.isCompleted();
    }
}
