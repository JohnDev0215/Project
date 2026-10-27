package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.google.common.collect.Range;

/* JADX INFO: loaded from: classes2.dex */
public class QualityLevelClamper {
    private final Range<Integer> mBitrateRange;
    private final Range<Integer> mHorizontalResolutionRange;
    private final VideoQualityLevel mLowerVideoQuality;
    private final VideoQualityLevel mUpperVideoQuality;
    private final Range<Integer> mVerticalResolutionRange;

    public QualityLevelClamper(Manifest manifest, Range<Integer> range, Range<Integer> range2, Range<Integer> range3) {
        this.mBitrateRange = range;
        this.mHorizontalResolutionRange = range2;
        this.mVerticalResolutionRange = range3;
        QualityLevel[] sortedQualityLevels = manifest.getVideoStream().getSortedQualityLevels(0);
        VideoQualityLevel videoQualityLevel = null;
        VideoQualityLevel videoQualityLevel2 = null;
        for (int length = sortedQualityLevels.length - 1; length >= 0; length--) {
            videoQualityLevel2 = (VideoQualityLevel) sortedQualityLevels[length];
            if (this.mBitrateRange.contains(Integer.valueOf(videoQualityLevel2.getBitrate())) && this.mVerticalResolutionRange.contains(Integer.valueOf(videoQualityLevel2.getMaxHeight())) && this.mHorizontalResolutionRange.contains(Integer.valueOf(videoQualityLevel2.getMaxWidth()))) {
                break;
            }
        }
        for (QualityLevel qualityLevel : sortedQualityLevels) {
            videoQualityLevel = (VideoQualityLevel) qualityLevel;
            if (this.mBitrateRange.contains(Integer.valueOf(videoQualityLevel.getBitrate())) && this.mVerticalResolutionRange.contains(Integer.valueOf(videoQualityLevel.getMaxHeight())) && this.mHorizontalResolutionRange.contains(Integer.valueOf(videoQualityLevel.getMaxWidth()))) {
                break;
            }
        }
        this.mUpperVideoQuality = videoQualityLevel2;
        this.mLowerVideoQuality = videoQualityLevel;
    }

    public VideoQualityLevel getLowestPlayableQuality() {
        return this.mLowerVideoQuality;
    }

    public VideoQualityLevel getHighestPlayableQuality() {
        return this.mUpperVideoQuality;
    }

    public VideoQualityLevel clampQualityLevel(VideoQualityLevel videoQualityLevel) {
        if (videoQualityLevel.getMaxHeight() > ((Integer) this.mVerticalResolutionRange.upperEndpoint()).intValue() || videoQualityLevel.getMaxWidth() > ((Integer) this.mHorizontalResolutionRange.upperEndpoint()).intValue() || videoQualityLevel.getBitrate() > ((Integer) this.mBitrateRange.upperEndpoint()).intValue()) {
            return getHighestPlayableQuality();
        }
        return (videoQualityLevel.getMaxHeight() < ((Integer) this.mVerticalResolutionRange.lowerEndpoint()).intValue() || videoQualityLevel.getMaxWidth() < ((Integer) this.mHorizontalResolutionRange.lowerEndpoint()).intValue() || videoQualityLevel.getBitrate() < ((Integer) this.mBitrateRange.lowerEndpoint()).intValue()) ? getLowestPlayableQuality() : videoQualityLevel;
    }

    public boolean isValidQualityLevel(VideoQualityLevel videoQualityLevel) {
        return this.mVerticalResolutionRange.contains(Integer.valueOf(videoQualityLevel.getMaxHeight())) && this.mHorizontalResolutionRange.contains(Integer.valueOf(videoQualityLevel.getMaxWidth())) && this.mBitrateRange.contains(Integer.valueOf(videoQualityLevel.getBitrate())) && this.mVerticalResolutionRange.contains(Integer.valueOf(videoQualityLevel.getMaxHeight())) && this.mHorizontalResolutionRange.contains(Integer.valueOf(videoQualityLevel.getMaxWidth())) && this.mBitrateRange.contains(Integer.valueOf(videoQualityLevel.getBitrate()));
    }
}
