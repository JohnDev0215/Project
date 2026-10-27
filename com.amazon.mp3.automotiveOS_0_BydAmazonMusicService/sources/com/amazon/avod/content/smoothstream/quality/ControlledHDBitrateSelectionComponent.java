package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;

/* JADX INFO: loaded from: classes2.dex */
public class ControlledHDBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final long mBufferedTimeRequiredToGoAboveLowestHDInNanoseconds;
    private VideoQualityLevel mLowestQualityLevelHD;

    public ControlledHDBitrateSelectionComponent(SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig) {
        this.mBufferedTimeRequiredToGoAboveLowestHDInNanoseconds = smoothStreamingHeuristicConfig.getBufferedTimeRequiredToGoAboveLowestHDInNanos();
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        VideoQualityLevel videoQualityLevel;
        StreamIndex videoStream = contentSessionContext.getManifest().getVideoStream();
        for (QualityLevel qualityLevel : videoStream.getSortedQualityLevels(contentSessionContext.getState().getConsumptionHead(videoStream.getIndex()))) {
            VideoQualityLevel videoQualityLevel2 = (VideoQualityLevel) qualityLevel;
            if (videoQualityLevel2.isHD() && ((videoQualityLevel = this.mLowestQualityLevelHD) == null || videoQualityLevel.getBitrate() > videoQualityLevel2.getBitrate())) {
                this.mLowestQualityLevelHD = videoQualityLevel2;
            }
        }
    }

    public VideoQualityLevel getLowestHDQualityLevel() {
        return this.mLowestQualityLevelHD;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        return (getLowestHDQualityLevel() == null || streamingBitrateSelectionState.getBufferedContentInNanos() >= this.mBufferedTimeRequiredToGoAboveLowestHDInNanoseconds || qualityLevel.getBitrate() <= getLowestHDQualityLevel().getBitrate()) ? qualityLevel : getLowestHDQualityLevel();
    }
}
