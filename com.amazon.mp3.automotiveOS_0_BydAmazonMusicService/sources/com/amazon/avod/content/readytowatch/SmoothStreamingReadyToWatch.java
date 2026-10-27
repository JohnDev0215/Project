package com.amazon.avod.content.readytowatch;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.util.DataUnit;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class SmoothStreamingReadyToWatch implements ReadyToWatch {
    private final ContentAccessor mContentAccessor;
    private final ContentSessionContext mContext;
    private long mInitialBandwidthEstimationKbps;
    private long mInitialBitrateEstimationKbps;

    public SmoothStreamingReadyToWatch(@Nonnull ContentSessionContext contentSessionContext, @Nonnull ContentAccessor contentAccessor) {
        this.mContext = contentSessionContext;
        this.mContentAccessor = contentAccessor;
    }

    public void setInitialEstimates(long j, long j2) {
        this.mInitialBandwidthEstimationKbps = j;
        this.mInitialBitrateEstimationKbps = j2;
    }

    private long getEstimatedBandwidthBps() {
        int averageVideoBandwidthBps = this.mContentAccessor.getAverageVideoBandwidthBps();
        if (averageVideoBandwidthBps <= 0) {
            averageVideoBandwidthBps = (int) DataUnit.KILOBITS.toBits(this.mInitialBandwidthEstimationKbps);
        }
        return averageVideoBandwidthBps;
    }

    private long getCurrentBitrateBps() {
        return DataUnit.KILOBITS.toBits(this.mInitialBitrateEstimationKbps);
    }

    private ReadyToWatchResult getFromTime(long j, long j2) {
        long remainingNeededSizeInBytes = this.mContentAccessor.getRemainingNeededSizeInBytes();
        long estimatedBandwidthBps = getEstimatedBandwidthBps();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long nanos = timeUnit.toNanos((long) (DataUnit.BYTES.toBits(remainingNeededSizeInBytes) / Math.max(estimatedBandwidthBps, 1L)));
        long downloadedTimeAfterPositionInNanos = this.mContentAccessor.getDownloadedTimeAfterPositionInNanos(j);
        if (downloadedTimeAfterPositionInNanos > j2 || this.mContentAccessor.isFullyDownloadedFromNanos(j)) {
            return new ReadyToWatchResult(0L, nanos);
        }
        return new ReadyToWatchResult(timeUnit.toNanos((long) (((double) ((int) TimeUnit.NANOSECONDS.toSeconds(Math.abs(j2 - downloadedTimeAfterPositionInNanos)))) * (getCurrentBitrateBps() / Math.max(estimatedBandwidthBps, 1L)))), nanos);
    }

    @Override // com.amazon.avod.content.readytowatch.ReadyToWatch
    @Nonnull
    public ReadyToWatchResult canResumeFromTimeInNanos(long j) {
        return getFromTime(j, this.mContext.getRequiredDurationForResume().getTotalNanoSeconds());
    }

    @Override // com.amazon.avod.content.readytowatch.ReadyToWatch
    @Nonnull
    public ReadyToWatchResult canStartWatchingFromTimeInNanos(long j) {
        return getFromTime(j, this.mContext.getRequiredDurationForStart().getTotalNanoSeconds());
    }
}
