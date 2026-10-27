package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;

/* JADX INFO: loaded from: classes2.dex */
public class PanicModeBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final long mBufferDurationThresholdInNanoseconds;
    private ContentSessionContext mContext;

    public PanicModeBitrateSelectionComponent(SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig) {
        this.mBufferDurationThresholdInNanoseconds = smoothStreamingHeuristicConfig.getPanicModeBufferThresholdInNanos();
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        this.mContext = contentSessionContext;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        return (streamingBitrateSelectionState.getStreamingState() == StreamingState.NORMAL && streamingBitrateSelectionState.getBufferedContentInNanos() <= this.mBufferDurationThresholdInNanoseconds) ? streamingBitrateSelectionState.getStream().getSortedQualityLevels(this.mContext.getState().getConsumptionHead(streamingBitrateSelectionState.getStream().getIndex()))[0] : qualityLevel;
    }
}
