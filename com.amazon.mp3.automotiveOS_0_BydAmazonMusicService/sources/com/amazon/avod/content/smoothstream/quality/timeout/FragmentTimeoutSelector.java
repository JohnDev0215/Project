package com.amazon.avod.content.smoothstream.quality.timeout;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface FragmentTimeoutSelector {
    @Nonnull
    long getFragmentTimeoutDurationInNanos(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel);
}
