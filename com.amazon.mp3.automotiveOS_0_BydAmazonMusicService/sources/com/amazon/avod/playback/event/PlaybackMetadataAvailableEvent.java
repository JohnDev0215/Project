package com.amazon.avod.playback.event;

import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.VideoResolution;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackMetadataAvailableEvent {
    private final VideoResolution[] mAvailableResolutions;
    private final double mContentFramerateFps;
    private final VideoQualityLevel[] mVideoQualityLevels;

    public PlaybackMetadataAvailableEvent(@Nonnull VideoResolution[] videoResolutionArr, @Nonnegative double d, @Nonnull VideoQualityLevel[] videoQualityLevelArr) {
        this.mAvailableResolutions = (VideoResolution[]) Preconditions.checkNotNull(videoResolutionArr, "availableResolutions");
        this.mContentFramerateFps = d;
        this.mVideoQualityLevels = (VideoQualityLevel[]) Preconditions.checkNotNull(videoQualityLevelArr, "sortedVideoQualityLevels");
    }

    @Nonnull
    public VideoResolution[] getAvailableResolutions() {
        return this.mAvailableResolutions;
    }

    @Nonnegative
    public double getVideoFramerateFps() {
        return this.mContentFramerateFps;
    }

    @Nonnull
    public VideoQualityLevel[] getSortedVideoQualityLevels() {
        return this.mVideoQualityLevels;
    }
}
