package com.amazon.avod.download;

import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.download.PlaybackDownloadExecutorTask;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.userdownload.internal.PlaybackDownloadJob;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class PlaybackDeletionTask implements PlaybackDownloadExecutorTask {
    private final ContentStore mContentStore;
    private final PlaybackDownloadListenerInternal mDeletionProgressListener;
    private final PlaybackDownloadJob mItem;

    @Override // com.amazon.avod.download.PlaybackDownloadExecutorTask
    public void cancel() {
    }

    public PlaybackDeletionTask(@Nonnull ContentStore contentStore, @Nonnull PlaybackDownloadJob playbackDownloadJob, @Nonnull PlaybackDownloadListenerInternal playbackDownloadListenerInternal) {
        this.mContentStore = (ContentStore) Preconditions.checkNotNull(contentStore, "contentStore");
        this.mItem = (PlaybackDownloadJob) Preconditions.checkNotNull(playbackDownloadJob, "workItem");
        this.mDeletionProgressListener = (PlaybackDownloadListenerInternal) Preconditions.checkNotNull(playbackDownloadListenerInternal, "deletionProgressListener");
    }

    @Override // com.amazon.avod.download.PlaybackDownloadExecutorTask
    public PlaybackDownloadExecutorTask.Result execute() {
        this.mDeletionProgressListener.onJobStarted(this.mItem);
        DeletionTaskProgressListener deletionTaskProgressListener = new DeletionTaskProgressListener(getEstimatedFileCount(this.mItem));
        this.mContentStore.releaseContent(this.mItem.getSessionType(), this.mItem.getStoragePath(), PlayableContent.newPlayableContent(this.mItem.getSpecification()), null, Optional.of(deletionTaskProgressListener));
        DLog.logf("DWNLD_SDK Deleted content (%s). Estimated file count %s vs. actual file count %s. Final progress: %s", this.mItem.getSpecification().getTitleId(), Integer.valueOf(deletionTaskProgressListener.mEstimatedFileCount), Integer.valueOf(deletionTaskProgressListener.mTotalDeletedFileCount), Integer.valueOf(deletionTaskProgressListener.mProgress));
        return new PlaybackDownloadExecutorTask.Result(Downloadable.DownloadableState.DELETED, Optional.absent());
    }

    @Override // com.amazon.avod.download.PlaybackDownloadExecutorTask
    public PlaybackDownloadJob getItem() {
        return this.mItem;
    }

    private int getEstimatedFileCount(@Nonnull PlaybackDownloadJob playbackDownloadJob) {
        return ((int) ((playbackDownloadJob.getSpecification().getDuration() == null ? 0L : r3.getTotalSeconds()) * 2.1f)) + 300;
    }

    private class DeletionTaskProgressListener implements DiskUtils.DeletionProgressListener {
        private final int mEstimatedFileCount;
        private int mProgress = 0;
        private int mTotalDeletedFileCount = 0;

        DeletionTaskProgressListener(@Nonnegative int i) {
            this.mEstimatedFileCount = Preconditions2.checkNonNegative(i, "estimatedFileCount");
        }

        @Override // com.amazon.avod.fileio.DiskUtils.DeletionProgressListener
        public void onProgress(@Nonnegative int i) {
            this.mTotalDeletedFileCount = i;
            int iMin = Math.min((int) ((i * 100.0f) / this.mEstimatedFileCount), 99);
            if (iMin > this.mProgress) {
                this.mProgress = iMin;
                PlaybackDeletionTask.this.mDeletionProgressListener.updateDeletionProgress(PlaybackDeletionTask.this.mItem, this.mProgress);
            }
        }
    }
}
