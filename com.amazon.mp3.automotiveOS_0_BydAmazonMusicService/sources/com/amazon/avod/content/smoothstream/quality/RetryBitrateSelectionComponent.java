package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class RetryBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final SmoothStreamingHeuristicConfig mConfig;

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
    }

    public RetryBitrateSelectionComponent(SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig) {
        this.mConfig = smoothStreamingHeuristicConfig;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        if (streamingBitrateSelectionState.getTryCount() <= 0) {
            return qualityLevel;
        }
        Preconditions.checkNotNull(streamingBitrateSelectionState.getPreviousQuality(), "The previous quality should not be null on the second try.");
        int bitrate = (int) (streamingBitrateSelectionState.getPreviousQuality().getBitrate() * this.mConfig.getStreamingFragmentRetryDownswitchFactor());
        QualityLevel qualityLevelLessThanEqual = bitrate > qualityLevel.getBitrate() ? qualityLevel : streamingBitrateSelectionState.getStream().getQualityLevelLessThanEqual(streamingBitrateSelectionState.getChunkIndex(), bitrate);
        DLog.logf("Suggesting new bitrate %d => %d due to failed download attempt.", Integer.valueOf(qualityLevel.getBitrate()), Integer.valueOf(qualityLevelLessThanEqual.getBitrate()));
        return qualityLevelLessThanEqual;
    }
}
