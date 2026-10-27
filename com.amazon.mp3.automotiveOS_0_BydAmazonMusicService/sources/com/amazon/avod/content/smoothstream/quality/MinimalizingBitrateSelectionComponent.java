package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.media.framework.util.IntRollingStatisticsAggregator;

/* JADX INFO: loaded from: classes2.dex */
public class MinimalizingBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final IntRollingStatisticsAggregator mQualityLevelAggregator;

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
    }

    public MinimalizingBitrateSelectionComponent(SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig) {
        this.mQualityLevelAggregator = new IntRollingStatisticsAggregator(smoothStreamingHeuristicConfig.getHeuristicOutputQualityPessimisticAverageCount());
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        this.mQualityLevelAggregator.addSample(qualityLevel.getBitrate());
        return streamingBitrateSelectionState.getStream().getQualityLevelLessThanEqual(streamingBitrateSelectionState.getChunkIndex(), this.mQualityLevelAggregator.getMinimum());
    }
}
