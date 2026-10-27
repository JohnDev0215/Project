package com.amazon.avod.content.smoothstream.quality.timeout;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public class RetryQualityTrackingFragmentTimeoutSelector implements FragmentTimeoutSelector {
    private final FragmentTimeoutSelector mDelegateSelector;

    public RetryQualityTrackingFragmentTimeoutSelector(FragmentTimeoutSelector fragmentTimeoutSelector) {
        this.mDelegateSelector = fragmentTimeoutSelector;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.timeout.FragmentTimeoutSelector
    public long getFragmentTimeoutDurationInNanos(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        if (streamingBitrateSelectionState.getTryCount() > 0) {
            if (streamingBitrateSelectionState.getPreviousQuality().getBitrate() == qualityLevel.getBitrate()) {
                DLog.logf("Fragment timeout set to MAX [Lowest bitrate tried: %s]", Integer.valueOf(qualityLevel.getBitrate()));
                return Long.MAX_VALUE;
            }
        }
        return this.mDelegateSelector.getFragmentTimeoutDurationInNanos(streamingBitrateSelectionState, qualityLevel);
    }
}
