package com.amazon.avod.userdownload.internal;

import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.filter.PlaybackDownloadFilter;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import java.io.Serializable;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadQueueFactory extends BaseDownloadQueueFactory {
    private final PlaybackDownloadPersistence mDownloadPersistence;
    private static final PlaybackDownloadFilter DOWNLOAD_STATE_FILTER = PlaybackDownloadFilter.newMatchingStateFilter(BaseDownloadQueueFactory.DOWNLOAD_STATES);
    private static final PlaybackDownloadFilter DELETE_STATE_FILTER = PlaybackDownloadFilter.newMatchingStateFilter(BaseDownloadQueueFactory.DELETE_STATES);
    private static final Ordering<PlaybackDownload> QUEUE_POSITION_COMPARATOR = new QueuePositionComparator();

    public PlaybackDownloadQueueFactory(@Nonnull PlaybackDownloadPersistence playbackDownloadPersistence) {
        this.mDownloadPersistence = (PlaybackDownloadPersistence) Preconditions.checkNotNull(playbackDownloadPersistence, "downloadPersistence");
    }

    @Nonnull
    public PlaybackDownloadQueue newDownloadQueue() {
        return new PlaybackDownloadQueue() { // from class: com.amazon.avod.userdownload.internal.PlaybackDownloadQueueFactory.1
            @Override // com.amazon.avod.userdownload.internal.PlaybackDownloadQueueFactory.PlaybackDownloadQueue
            @Nonnull
            PlaybackDownloadFilter getQueueFilter() {
                return PlaybackDownloadQueueFactory.DOWNLOAD_STATE_FILTER;
            }

            @Override // com.amazon.avod.userdownload.internal.PlaybackDownloadQueueFactory.PlaybackDownloadQueue
            @Nonnull
            Ordering<PlaybackDownload> getQueueComparator() {
                return PlaybackDownloadQueueFactory.QUEUE_POSITION_COMPARATOR;
            }
        };
    }

    @Nonnull
    public PlaybackDownloadQueue newDeletionQueue() {
        return new PlaybackDownloadQueue() { // from class: com.amazon.avod.userdownload.internal.PlaybackDownloadQueueFactory.2
            @Override // com.amazon.avod.userdownload.internal.PlaybackDownloadQueueFactory.PlaybackDownloadQueue
            @Nonnull
            PlaybackDownloadFilter getQueueFilter() {
                return PlaybackDownloadQueueFactory.DELETE_STATE_FILTER;
            }

            @Override // com.amazon.avod.userdownload.internal.PlaybackDownloadQueueFactory.PlaybackDownloadQueue
            @Nonnull
            Ordering<PlaybackDownload> getQueueComparator() {
                return PlaybackDownloadQueueFactory.QUEUE_POSITION_COMPARATOR;
            }
        };
    }

    public abstract class PlaybackDownloadQueue {
        @Nonnull
        abstract Ordering<PlaybackDownload> getQueueComparator();

        @Nonnull
        abstract PlaybackDownloadFilter getQueueFilter();

        public PlaybackDownloadQueue() {
        }

        @Nonnull
        public ImmutableList<PlaybackDownload> getQueueSnapshot() {
            return getQueueComparator().immutableSortedCopy(Collections2.filter(PlaybackDownloadQueueFactory.this.mDownloadPersistence.getAllDownloads(), getQueueFilter()));
        }

        public boolean isInQueue(@Nonnull PlaybackDownload playbackDownload) {
            return getQueueFilter().apply(playbackDownload);
        }
    }

    private static class QueuePositionComparator extends Ordering<PlaybackDownload> implements Serializable {
        private QueuePositionComparator() {
        }

        @Override // com.google.common.collect.Ordering, java.util.Comparator
        public int compare(PlaybackDownload playbackDownload, PlaybackDownload playbackDownload2) {
            return Ints.compare(playbackDownload == null ? 0 : playbackDownload.getQueuePosition(), playbackDownload2 != null ? playbackDownload2.getQueuePosition() : 0);
        }
    }
}
