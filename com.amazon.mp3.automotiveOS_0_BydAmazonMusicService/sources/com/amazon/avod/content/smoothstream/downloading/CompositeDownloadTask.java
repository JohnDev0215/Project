package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.downloading.DownloadTask;
import com.amazon.avod.content.smoothstream.FragmentStreamRequestResult;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CompositeDownloadTask {
    private ImmutableList<? extends DownloadTask> mTasks;

    public void setDownloadTasks(ImmutableList<? extends DownloadTask> immutableList) {
        this.mTasks = immutableList;
    }

    public long getDownloadedTimeAfterPositionInNanos(long j) {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        long j2 = Long.MAX_VALUE;
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (isEssentialStream(next)) {
                long downloadedTimeAfterPositionInNanos = next.getDownloadedTimeAfterPositionInNanos(j);
                if (j2 > downloadedTimeAfterPositionInNanos) {
                    j2 = downloadedTimeAfterPositionInNanos;
                }
            }
        }
        return j2;
    }

    public boolean hasUpdatesForContentView() {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (true) {
            boolean z = true;
            while (it.hasNext()) {
                DownloadTask next = it.next();
                if (!z || !next.hasUpdatesForContentView()) {
                    z = false;
                }
            }
            return z;
        }
    }

    public boolean hasDownloadErrors() {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (true) {
            boolean z = false;
            while (it.hasNext()) {
                DownloadTask next = it.next();
                if (z || next.hasDownloadErrors()) {
                    z = true;
                }
            }
            return z;
        }
    }

    public boolean isFullyDownloadedFromNanos(long j) {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (isEssentialStream(next) && !next.isFullyDownloadedFromNanos(j)) {
                return false;
            }
        }
        return true;
    }

    public long getRemainingNeededSizeInBytes() {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        long remainingNeededSizeInBytes = 0;
        while (it.hasNext()) {
            remainingNeededSizeInBytes += it.next().getRemainingNeededSizeInBytes();
        }
        return remainingNeededSizeInBytes;
    }

    public long getTotalNeededSizeInBytes() {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        long totalNeededSizeInBytes = 0;
        while (it.hasNext()) {
            totalNeededSizeInBytes += it.next().getTotalNeededSizeInBytes();
        }
        return totalNeededSizeInBytes;
    }

    @Nullable
    public FragmentStreamRequestResult requestFragment(SmoothStreamingURI smoothStreamingURI) throws ContentException {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (next.canServiceRequest(smoothStreamingURI)) {
                return next.requestFragment(smoothStreamingURI);
            }
        }
        throw new IllegalArgumentException("Unable to find task that can download: " + smoothStreamingURI);
    }

    public void notifyFragmentCorrupt(SmoothStreamingURI smoothStreamingURI) {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (next.canServiceRequest(smoothStreamingURI)) {
                next.notifyFragmentCorrupt(smoothStreamingURI);
                return;
            }
        }
        throw new IllegalArgumentException("Unable to find task that can download: " + smoothStreamingURI);
    }

    public void start() {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (isEssentialStream(next)) {
                next.start();
            }
        }
    }

    public void cancel() {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    public void seek(long j) {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            it.next().seek(j);
        }
    }

    public void enableTask(StreamType streamType) {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (next.getStreamType() == streamType) {
                next.start();
            }
        }
    }

    public void disableTask(StreamType streamType) {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (next.getStreamType() == streamType) {
                next.cancel();
            }
        }
    }

    public void onContentContextChanged() {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            it.next().onContentContextChanged();
        }
    }

    public long getFirstAvailableTimeInNanos(@Nullable StreamType streamType) {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        long j = Long.MIN_VALUE;
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (isEssentialStream(next) && (streamType == null || next.getStreamType().equals(streamType))) {
                long firstAvailableTimeInNanos = next.getFirstAvailableTimeInNanos();
                if (j < firstAvailableTimeInNanos) {
                    j = firstAvailableTimeInNanos;
                }
            }
        }
        return j;
    }

    public long getLastAvailableTimeInNanos(@Nullable StreamType streamType) {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        long j = Long.MAX_VALUE;
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (isEssentialStream(next) && (streamType == null || next.getStreamType().equals(streamType))) {
                long lastAvailableTimeInNanos = next.getLastAvailableTimeInNanos();
                if (j > lastAvailableTimeInNanos) {
                    j = lastAvailableTimeInNanos;
                }
            }
        }
        return j;
    }

    public int getAverageVideoBandwidthBps() {
        float averageVideoBandwidthBps = 0.0f;
        while (this.mTasks.iterator().hasNext()) {
            averageVideoBandwidthBps += r0.next().getAverageVideoBandwidthBps();
        }
        return (int) (averageVideoBandwidthBps / Math.max(this.mTasks.size(), 1));
    }

    public void restrictToQuality(@Nonnull QualityLevel qualityLevel, @Nonnull long j) {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            it.next().restrictToQuality(qualityLevel, j);
        }
    }

    public void releaseFragment(FragmentStreamRequestResult fragmentStreamRequestResult) {
        UnmodifiableIterator<? extends DownloadTask> it = this.mTasks.iterator();
        while (it.hasNext()) {
            DownloadTask next = it.next();
            if (next.canServiceRequest(fragmentStreamRequestResult.getRequestedURI())) {
                next.releaseFragment(fragmentStreamRequestResult);
                return;
            }
        }
    }

    public void dispose() {
        ImmutableList<? extends DownloadTask> immutableList = this.mTasks;
        if (immutableList != null) {
            UnmodifiableIterator<? extends DownloadTask> it = immutableList.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
        }
    }

    public void notifyLivePointUpdated(long j) {
        ImmutableList<? extends DownloadTask> immutableList = this.mTasks;
        if (immutableList != null) {
            UnmodifiableIterator<? extends DownloadTask> it = immutableList.iterator();
            while (it.hasNext()) {
                DownloadTask next = it.next();
                if (isEssentialStream(next)) {
                    next.notifyLivePointUpdated(j);
                }
            }
        }
    }

    private boolean isEssentialStream(DownloadTask downloadTask) {
        return downloadTask.getStreamType() == StreamType.AUDIO || downloadTask.getStreamType() == StreamType.VIDEO;
    }
}
