package com.amazon.avod.content.backgrounddownload;

import com.amazon.avod.content.readytowatch.ReadyToWatch;
import com.amazon.avod.content.readytowatch.ReadyToWatchResult;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class SingleFileDownloaderReadyToWatch implements ReadyToWatch {
    private static final ReadyToWatchResult ALWAYS_READY_TO_WATCH = new ReadyToWatchResult(0, 0);
    private static final ReadyToWatchResult NOT_READY_TO_WATCH = new ReadyToWatchResult(1, 1);
    private final TimeSpan mContentDuration;
    private final int mDeltaPercentage;
    private final DownloadTask mDownloadTask;

    SingleFileDownloaderReadyToWatch(@Nonnull DownloadTask downloadTask, @Nonnull TimeSpan timeSpan, @Nonnegative int i) {
        this.mDownloadTask = (DownloadTask) Preconditions.checkNotNull(downloadTask, "downloadTask");
        this.mContentDuration = (TimeSpan) Preconditions.checkNotNull(timeSpan, "contentDuration");
        Preconditions.checkState(i >= 0 && i <= 100, "deltaPercentage must be between 0 and 100");
        this.mDeltaPercentage = i;
    }

    @Override // com.amazon.avod.content.readytowatch.ReadyToWatch
    @Nonnull
    public ReadyToWatchResult canResumeFromTimeInNanos(long j) {
        return isReadyToWatchFromTimeInNanos(j);
    }

    @Override // com.amazon.avod.content.readytowatch.ReadyToWatch
    @Nonnull
    public ReadyToWatchResult canStartWatchingFromTimeInNanos(long j) {
        return isReadyToWatchFromTimeInNanos(j);
    }

    private ReadyToWatchResult isReadyToWatchFromTimeInNanos(long j) {
        long totalNanoSeconds = this.mContentDuration.getTotalNanoSeconds();
        return ((totalNanoSeconds > 0L ? 1 : (totalNanoSeconds == 0L ? 0 : -1)) == 0 ? (float) totalNanoSeconds : ((float) j) / ((float) totalNanoSeconds)) < getDeltaAdjustedDownloadRatio() ? ALWAYS_READY_TO_WATCH : NOT_READY_TO_WATCH;
    }

    private float getDeltaAdjustedDownloadRatio() {
        float playableDownloadRatio = this.mDownloadTask.getPlayableDownloadRatio();
        return playableDownloadRatio == 1.0f ? playableDownloadRatio : playableDownloadRatio * ((100 - this.mDeltaPercentage) / 100.0f);
    }
}
