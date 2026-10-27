package com.amazon.avod.content.smoothstream.quality.timeout;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class RetryCountingFragmentTimeoutSelector implements FragmentTimeoutSelector {
    private final FragmentTimeoutSelector mDelegateSelector;
    private final int mTriesBeforeRemovingTimeout;

    public RetryCountingFragmentTimeoutSelector(FragmentTimeoutSelector fragmentTimeoutSelector, int i) {
        this.mDelegateSelector = fragmentTimeoutSelector;
        Preconditions.checkState(i >= 0, "triesBeforeRemovingTimeout must be positive!");
        this.mTriesBeforeRemovingTimeout = i;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.timeout.FragmentTimeoutSelector
    public long getFragmentTimeoutDurationInNanos(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        if (streamingBitrateSelectionState.getTryCount() > 0) {
            if (streamingBitrateSelectionState.getTryCount() > this.mTriesBeforeRemovingTimeout) {
                DLog.logf("Fragment timeout set to MAX [Try count: %d, Limit before removing timeout: %d]", Integer.valueOf(streamingBitrateSelectionState.getTryCount()), Integer.valueOf(this.mTriesBeforeRemovingTimeout));
                return Long.MAX_VALUE;
            }
        }
        return this.mDelegateSelector.getFragmentTimeoutDurationInNanos(streamingBitrateSelectionState, qualityLevel);
    }
}
