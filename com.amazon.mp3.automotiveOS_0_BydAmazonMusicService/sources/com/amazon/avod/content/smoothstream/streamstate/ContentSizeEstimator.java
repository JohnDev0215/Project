package com.amazon.avod.content.smoothstream.streamstate;

import com.amazon.avod.content.LocalContentState;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.util.DataUnit;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class ContentSizeEstimator implements LocalContentState {
    private static final long DELTA_TIME_IN_NANOS = TimeUnit.MILLISECONDS.toNanos(1);
    private final long mCachedStreamSizeBytes;
    private final boolean mIsLive;
    private final long mStreamDurationInNanoseconds;
    private final StreamReaderState mStreamState;

    public ContentSizeEstimator(StreamReaderState streamReaderState, long j, int i, int i2, int i3, boolean z) {
        this.mStreamState = streamReaderState;
        this.mStreamDurationInNanoseconds = j;
        this.mCachedStreamSizeBytes = (DataUnit.BITS.toBytes(i) * TimeUnit.NANOSECONDS.toSeconds(j)) + (((long) i2) * (((long) i3) + 4096));
        this.mIsLive = z;
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getDownloadedTimeAfterPositionInNanos(long j) {
        return this.mStreamState.getDurationAfterPositionInNanos(j);
    }

    @Override // com.amazon.avod.content.LocalContentState
    public boolean isFullyDownloadedFromNanos(long j) {
        return DELTA_TIME_IN_NANOS > Math.abs(getDownloadedTimeAfterPositionInNanos(j) - Math.abs(this.mStreamDurationInNanoseconds - j));
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getRemainingNeededSizeInBytes() {
        double seconds;
        if (this.mIsLive) {
            seconds = 0.0d;
        } else {
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            seconds = timeUnit.toSeconds(Math.abs(getFirstAvailableTimeInNanos() - getLastAvailableTimeInNanos())) / timeUnit.toSeconds(this.mStreamDurationInNanoseconds);
        }
        return (long) (getTotalNeededSizeInBytes() * (1.0d - seconds));
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getTotalNeededSizeInBytes() {
        return this.mCachedStreamSizeBytes;
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getFirstAvailableTimeInNanos() {
        SmoothStreamingURI earliestExistingUri = this.mStreamState.getEarliestExistingUri();
        if (earliestExistingUri == null) {
            return 0L;
        }
        return earliestExistingUri.getPresentationTimeInNanos();
    }

    @Override // com.amazon.avod.content.LocalContentState
    public long getLastAvailableTimeInNanos() {
        SmoothStreamingURI latestExistingUri = this.mStreamState.getLatestExistingUri();
        if (latestExistingUri == null) {
            return 0L;
        }
        return latestExistingUri.getPresentationTimeInNanos() + latestExistingUri.getDurationInNanos();
    }
}
