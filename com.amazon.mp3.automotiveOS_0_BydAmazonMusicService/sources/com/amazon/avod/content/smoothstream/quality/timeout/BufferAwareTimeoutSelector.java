package com.amazon.avod.content.smoothstream.quality.timeout;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState;
import com.google.common.collect.Range;

/* JADX INFO: loaded from: classes2.dex */
public class BufferAwareTimeoutSelector implements FragmentTimeoutSelector {
    private final double mScalingFactor;
    private final Range<Long> mTimeoutRange;

    public BufferAwareTimeoutSelector(Range<Long> range, double d) {
        this.mTimeoutRange = range;
        this.mScalingFactor = d;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.timeout.FragmentTimeoutSelector
    public long getFragmentTimeoutDurationInNanos(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        long bufferedContentInNanos = (long) (this.mScalingFactor * streamingBitrateSelectionState.getBufferedContentInNanos());
        if (this.mTimeoutRange.contains(Long.valueOf(bufferedContentInNanos))) {
            return bufferedContentInNanos;
        }
        if (bufferedContentInNanos < ((Long) this.mTimeoutRange.lowerEndpoint()).longValue()) {
            return ((Long) this.mTimeoutRange.lowerEndpoint()).longValue();
        }
        return ((Long) this.mTimeoutRange.upperEndpoint()).longValue();
    }
}
