package com.amazon.avod.media.contentcache.internal.executor;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.media.contentcache.internal.executor.DownloadExecutorTask;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.pmet.WhisperCacheMetrics$CacheOperation;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class DeletionTask implements DownloadExecutorTask {
    private final ContentStore mContentStore;
    private final Stopwatch mDeleteTimer;
    private final Downloadable mItem;
    private final PlaybackPmetMetricReporter mPlaybackPmetMetricReporter;

    @Override // com.amazon.avod.media.contentcache.internal.executor.DownloadExecutorTask
    public void cancel() {
    }

    public DeletionTask(@Nonnull ContentStore contentStore, @Nonnull Downloadable downloadable) {
        this(contentStore, downloadable, PlaybackPmetMetricReporter.getInstance());
    }

    @VisibleForTesting
    public DeletionTask(@Nonnull ContentStore contentStore, @Nonnull Downloadable downloadable, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        this.mContentStore = contentStore;
        this.mItem = downloadable;
        this.mPlaybackPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "playbackPmetMetricReporter");
        this.mDeleteTimer = Stopwatch.createStarted();
    }

    @Override // com.amazon.avod.media.contentcache.internal.executor.DownloadExecutorTask
    @Nonnull
    public DownloadExecutorTask.Result execute() {
        if (this.mItem.getSessionType() != ContentSessionType.LIVE_CACHE) {
            DLog.logf("DeletionTask: Releasing cached content for %s", this.mItem);
            this.mContentStore.releaseContent(this.mItem.getSessionType(), null, PlayableContent.newPlayableContent(this.mItem.getSpecification()), this.mItem.getAudioVideoUrls() != null ? this.mItem.getAudioVideoUrls().getEncodeId() : null);
        }
        this.mPlaybackPmetMetricReporter.reportCacheOperationTimerMetric(WhisperCacheMetrics$CacheOperation.DELETE, this.mDeleteTimer.stop().elapsed(TimeUnit.MILLISECONDS));
        return new DownloadExecutorTask.Result(Downloadable.DownloadableState.DELETED, null, Optional.fromNullable(this.mItem.getOfflineKeyId()), null, 0L);
    }
}
