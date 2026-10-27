package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public class StabilizingBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final SmoothStreamingHeuristicConfig mHeuristicConfig;

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
    }

    public StabilizingBitrateSelectionComponent(SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig) {
        this.mHeuristicConfig = smoothStreamingHeuristicConfig;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        if (streamingBitrateSelectionState.getPreviousQuality() == null || !(isRestrictedQualityDecrease(streamingBitrateSelectionState.getBufferedContentInNanos(), streamingBitrateSelectionState.getPreviousQuality(), qualityLevel) || isRestrictedQualityIncrease(streamingBitrateSelectionState.getBufferedContentInNanos(), streamingBitrateSelectionState.getPreviousQuality(), qualityLevel))) {
            return qualityLevel;
        }
        DLog.devf("Ignoring quality change to %d recommended by heuristic since there is %s nanoseconds buffered. Staying at quality %d.", Integer.valueOf(qualityLevel.getBitrate()), Long.valueOf(streamingBitrateSelectionState.getBufferedContentInNanos()), Integer.valueOf(streamingBitrateSelectionState.getPreviousQuality().getBitrate()));
        return streamingBitrateSelectionState.getPreviousQuality();
    }

    private boolean isRestrictedQualityDecrease(long j, QualityLevel qualityLevel, QualityLevel qualityLevel2) {
        return j >= this.mHeuristicConfig.getNoDownshiftBufferDuration().getTotalNanoSeconds() && qualityLevel2.getBitrate() < qualityLevel.getBitrate();
    }

    private boolean isRestrictedQualityIncrease(long j, QualityLevel qualityLevel, QualityLevel qualityLevel2) {
        return j <= this.mHeuristicConfig.getNoUpshiftBufferDuration().getTotalNanoSeconds() && qualityLevel2.getBitrate() > qualityLevel.getBitrate();
    }
}
