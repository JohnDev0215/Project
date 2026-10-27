package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CappedResolutionBandBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final MediaQualityConfig mConfig;
    private ContentSessionContext mContext;
    private final boolean mIsResolutionBandBasedCappingEnabled;
    private VideoResolution.ResolutionBand mResolutionBandCap = VideoResolution.ResolutionBand.UNKNOWN;

    public CappedResolutionBandBitrateSelectionComponent(@Nonnull MediaQualityConfig mediaQualityConfig) {
        MediaQualityConfig mediaQualityConfig2 = (MediaQualityConfig) Preconditions.checkNotNull(mediaQualityConfig, "config");
        this.mConfig = mediaQualityConfig2;
        this.mIsResolutionBandBasedCappingEnabled = mediaQualityConfig2.isResolutionBandBasedCappingEnabled();
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(@Nonnull ContentSessionContext contentSessionContext) {
        ContentSessionContext contentSessionContext2 = (ContentSessionContext) Preconditions.checkNotNull(contentSessionContext, "context");
        this.mContext = contentSessionContext2;
        if (contentSessionContext2.getAvailableResolutions().length > 0) {
            this.mResolutionBandCap = this.mConfig.getResolutionBandCapForManifestType(this.mContext.getMaxResolution().getResolutionBand());
        }
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(@Nonnull StreamingBitrateSelectionState streamingBitrateSelectionState, @Nonnull QualityLevel qualityLevel) {
        if (streamingBitrateSelectionState.getStream().isVideo() && this.mIsResolutionBandBasedCappingEnabled && this.mResolutionBandCap != VideoResolution.ResolutionBand.UNKNOWN && !this.mContext.getSessionType().isDownload() && this.mContext.getState().shouldUseQualityCap()) {
            QualityLevel[] sortedQualityLevels = streamingBitrateSelectionState.getStream().getSortedQualityLevels(this.mContext.getState().getConsumptionHead(streamingBitrateSelectionState.getStream().getIndex()));
            QualityLevel qualityLevel2 = sortedQualityLevels[sortedQualityLevels.length - 1];
            int length = sortedQualityLevels.length - 1;
            while (length >= 0) {
                VideoQualityLevel videoQualityLevel = (VideoQualityLevel) sortedQualityLevels[length];
                if (!this.mResolutionBandCap.satisfiesOrExceeds(videoQualityLevel.getMaxWidth(), videoQualityLevel.getMaxHeight())) {
                    break;
                }
                length--;
                qualityLevel2 = videoQualityLevel;
            }
            if (qualityLevel.getBitrate() > qualityLevel2.getBitrate()) {
                DLog.devf("CAPPED QUALITY! heuristic: %s, capped: %s, resolutionBandCap(%s x %s), cappedResolution(%s x %s)", qualityLevel, qualityLevel2, Integer.valueOf(this.mResolutionBandCap.getMinWidth()), Integer.valueOf(this.mResolutionBandCap.getMinHeight()), Integer.valueOf(qualityLevel2.getMaxWidth()), Integer.valueOf(qualityLevel2.getMaxHeight()));
                return qualityLevel2;
            }
        }
        return qualityLevel;
    }
}
