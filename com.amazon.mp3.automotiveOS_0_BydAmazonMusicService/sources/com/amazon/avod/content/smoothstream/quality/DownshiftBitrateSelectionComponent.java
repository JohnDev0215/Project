package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;

/* JADX INFO: loaded from: classes2.dex */
public class DownshiftBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final float mDownshiftMultiplier;

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
    }

    public DownshiftBitrateSelectionComponent(SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig) {
        this.mDownshiftMultiplier = smoothStreamingHeuristicConfig.getDownshiftBitrateFactor();
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        if (streamingBitrateSelectionState.getPreviousQuality() == null || streamingBitrateSelectionState.getPreviousQuality().getBitrate() <= qualityLevel.getBitrate()) {
            return qualityLevel;
        }
        return streamingBitrateSelectionState.getStream().getQualityLevelLessThanEqual(streamingBitrateSelectionState.getChunkIndex(), (int) Math.floor(qualityLevel.getBitrate() / this.mDownshiftMultiplier));
    }
}
