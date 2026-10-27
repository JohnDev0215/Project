package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
class LiveStreamingCappedQualityBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private final MediaQualityConfig mConfig;
    private boolean mIsInitialized = false;
    private LiveCappedVideoBitrates mLiveCappedVideoBitrates;
    private final int mServerSideBaselineBitrateCap;

    public LiveStreamingCappedQualityBitrateSelectionComponent(MediaQualityConfig mediaQualityConfig, LiveStreamingPlaybackConfig liveStreamingPlaybackConfig) {
        this.mConfig = mediaQualityConfig;
        this.mServerSideBaselineBitrateCap = liveStreamingPlaybackConfig.getLiveStreamingBaselineBitrateCap();
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext contentSessionContext) {
        if (contentSessionContext.getSpecification().isLiveStream()) {
            QualityLevel[] sortedQualityLevels = contentSessionContext.getStreamSelections().getVideoStream().getSortedQualityLevels(contentSessionContext.getState().getConsumptionHead(contentSessionContext.getStreamSelections().getVideoStream().getIndex()));
            Preconditions.checkArgument(sortedQualityLevels.length > 0, "No quality found in video stream.");
            int videoStreamingBitrateCap = this.mConfig.getVideoStreamingBitrateCap(contentSessionContext.getState().getMediaQuality(), contentSessionContext.getMaxResolution(), sortedQualityLevels[0].getFourCC());
            this.mLiveCappedVideoBitrates = LiveCappedVideoBitrates.newLiveCappedVideoBitrates(videoStreamingBitrateCap, sortedQualityLevels, ((Heuristics) Preconditions.checkNotNull(contentSessionContext.getHeuristics(), "Heuristics must be nonnull in live streaming.")).getDownloadConfig().getBaselineShare(), this.mServerSideBaselineBitrateCap);
            DLog.logf("Live streaming client side capping information: total cap -> %sbps | baseline -> %sbps | upgrade -> %sbps", Integer.valueOf(videoStreamingBitrateCap), Integer.valueOf(this.mLiveCappedVideoBitrates.getBaselineBitrateCap().getBitrate()), Integer.valueOf(this.mLiveCappedVideoBitrates.getUpgradeBitrateCap().getBitrate()));
            this.mIsInitialized = true;
        }
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState streamingBitrateSelectionState, QualityLevel qualityLevel) {
        QualityLevel baselineBitrateCap;
        if (!this.mIsInitialized) {
            return qualityLevel;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$dash$quality$heuristic$FragmentType[streamingBitrateSelectionState.getFragmentType().ordinal()];
        if (i == 1) {
            baselineBitrateCap = this.mLiveCappedVideoBitrates.getBaselineBitrateCap();
        } else {
            if (i != 2) {
                return qualityLevel;
            }
            baselineBitrateCap = this.mLiveCappedVideoBitrates.getUpgradeBitrateCap();
        }
        return qualityLevel.getBitrate() > baselineBitrateCap.getBitrate() ? baselineBitrateCap : qualityLevel;
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.smoothstream.quality.LiveStreamingCappedQualityBitrateSelectionComponent$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$FragmentType;

        static {
            int[] iArr = new int[FragmentType.values().length];
            $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$FragmentType = iArr;
            try {
                iArr[FragmentType.BASELINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$FragmentType[FragmentType.UPGRADE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$FragmentType[FragmentType.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static class LiveCappedVideoBitrates {
        private final QualityLevel mBaselineBitrateCap;
        private final QualityLevel mUpgradeBitrateCap;

        static LiveCappedVideoBitrates newLiveCappedVideoBitrates(int i, QualityLevel[] qualityLevelArr, double d, int i2) {
            Preconditions.checkArgument(i > 0, "videoBitrateToCap");
            Preconditions.checkArgument(d >= 0.0d, "baselineShare");
            Preconditions.checkNotNull(qualityLevelArr, "sortedVideoQualityLevels");
            Preconditions.checkArgument(qualityLevelArr.length > 0, "sortedVideoQualityLevels should contain at least one element");
            Preconditions.checkArgument(d < 0.5d, "baselineShare should be less than 0.5");
            Preconditions.checkArgument(i2 > 0, "serverSideBaselineBitrateCap");
            int iMin = Math.min(i2, Double.valueOf(((double) i) * d).intValue());
            QualityLevel qualityLevel = qualityLevelArr[0];
            for (QualityLevel qualityLevel2 : qualityLevelArr) {
                if (qualityLevel2.getBitrate() <= iMin) {
                    qualityLevel = qualityLevel2;
                }
            }
            int iMax = Math.max(qualityLevelArr[0].getBitrate(), i - qualityLevel.getBitrate());
            QualityLevel qualityLevel3 = qualityLevelArr[0];
            for (QualityLevel qualityLevel4 : qualityLevelArr) {
                if (qualityLevel4.getBitrate() <= iMax) {
                    qualityLevel3 = qualityLevel4;
                }
            }
            return new LiveCappedVideoBitrates(qualityLevel, qualityLevel3);
        }

        private LiveCappedVideoBitrates(QualityLevel qualityLevel, QualityLevel qualityLevel2) {
            this.mBaselineBitrateCap = qualityLevel;
            this.mUpgradeBitrateCap = qualityLevel2;
        }

        QualityLevel getBaselineBitrateCap() {
            return this.mBaselineBitrateCap;
        }

        QualityLevel getUpgradeBitrateCap() {
            return this.mUpgradeBitrateCap;
        }
    }
}
