package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionState;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CappedQualityBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final MediaQualityConfig mConfig;
    private ContentSessionContext mContext;
    private MediaQuality mQualityCapPrePlayerBind;
    private Set<String> mSessionTypesToCapPrePlayerBind;

    public CappedQualityBitrateSelectionComponent(@Nonnull MediaQualityConfig mediaQualityConfig) {
        this.mConfig = (MediaQualityConfig) Preconditions.checkNotNull(mediaQualityConfig, "config");
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(@Nonnull ContentSessionContext contentSessionContext) {
        this.mContext = (ContentSessionContext) Preconditions.checkNotNull(contentSessionContext, "context");
        this.mQualityCapPrePlayerBind = this.mConfig.getQualityCapPrePlayerBind();
        this.mSessionTypesToCapPrePlayerBind = this.mConfig.getSessionTypesToCapQualityPrePlayerBind();
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        MediaQuality mediaQuality;
        if (!streamingBitrateSelectionState.getStream().isVideo()) {
            return qualityLevel;
        }
        ContentSessionState state = this.mContext.getState();
        if (this.mSessionTypesToCapPrePlayerBind.contains(this.mContext.getSessionType().name()) && state.shouldUseQualityCap()) {
            mediaQuality = this.mQualityCapPrePlayerBind;
        } else {
            mediaQuality = state.getMediaQuality();
        }
        VideoResolution.ResolutionBand maxResolution = state.getMaxResolution();
        int videoStreamingBitrateCap = this.mConfig.getVideoStreamingBitrateCap(mediaQuality, this.mContext.getMaxResolution(), qualityLevel.getFourCC());
        QualityLevel qualityLevel2 = streamingBitrateSelectionState.getStream().getSortedQualityLevels(state.getConsumptionHead(streamingBitrateSelectionState.getStream().getIndex()))[0];
        for (QualityLevel qualityLevel3 : streamingBitrateSelectionState.getStream().getSortedQualityLevels(state.getConsumptionHead(streamingBitrateSelectionState.getStream().getIndex()))) {
            VideoQualityLevel videoQualityLevel = (VideoQualityLevel) qualityLevel3;
            if (videoQualityLevel.getBitrate() <= videoStreamingBitrateCap && (maxResolution == VideoResolution.ResolutionBand.UNKNOWN || maxResolution.doesNotExceed(videoQualityLevel.getMaxWidth(), videoQualityLevel.getMaxHeight()))) {
                qualityLevel2 = videoQualityLevel;
            }
        }
        if (qualityLevel.getBitrate() <= qualityLevel2.getBitrate()) {
            return qualityLevel;
        }
        DLog.devf("CAPPED QUALITY! heuristic: %s, capped: %s, maxBitrate %s, resolution %s", qualityLevel, qualityLevel2, Integer.valueOf(videoStreamingBitrateCap), maxResolution);
        return qualityLevel2;
    }
}
