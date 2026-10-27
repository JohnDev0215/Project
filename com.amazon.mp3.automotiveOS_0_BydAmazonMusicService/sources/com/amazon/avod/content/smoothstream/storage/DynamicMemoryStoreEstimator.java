package com.amazon.avod.content.smoothstream.storage;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.DynamicMemoryStoreConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.quality.QualityLevelClamper;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicMemoryStoreEstimator {
    private final QualityLevelClamper mClamper;
    private final PlaybackConfig mConfig;
    private final ContentSessionContext mContentSessionContext;
    private final int mContinuousPlayPreinitBufferSizeSeconds;
    private final DynamicMemoryStoreConfig mDynamicMemoryStoreConfig;
    private final int mMaxFrontAndBackBufferSizeSeconds;
    private final MediaQualityConfig mMediaQualityConfig;
    private final SmoothStreamingPlaybackConfig mPlaybackConfig;
    private final String mVideoFourCC;

    public DynamicMemoryStoreEstimator(@Nonnull ContentSessionContext contentSessionContext, @Nonnull MediaQualityConfig mediaQualityConfig, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull DynamicMemoryStoreConfig dynamicMemoryStoreConfig, @Nonnull PlaybackConfig playbackConfig) {
        ContentSessionContext contentSessionContext2 = (ContentSessionContext) Preconditions.checkNotNull(contentSessionContext, "contentSessionContext");
        this.mContentSessionContext = contentSessionContext2;
        this.mVideoFourCC = contentSessionContext2.getStreamSelections().getVideoStream().getFourCC();
        this.mMediaQualityConfig = (MediaQualityConfig) Preconditions.checkNotNull(mediaQualityConfig, "mediaQualityConfig");
        this.mPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingPlaybackConfig");
        this.mDynamicMemoryStoreConfig = (DynamicMemoryStoreConfig) Preconditions.checkNotNull(dynamicMemoryStoreConfig, "dynamicMemoryStoreConfig");
        PlaybackConfig playbackConfig2 = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        this.mConfig = playbackConfig2;
        this.mMaxFrontAndBackBufferSizeSeconds = contentSessionContext2.getMaxFrontBufferSize().getTotalSeconds() + contentSessionContext2.getMaxBackBufferSize().getTotalSeconds();
        this.mContinuousPlayPreinitBufferSizeSeconds = playbackConfig2.isContinuousPlayPreinitEnabled() ? contentSessionContext2.getRequiredDurationForStart().getTotalSeconds() : 0;
        this.mClamper = contentSessionContext2.getClamper();
    }

    @Nonnegative
    public int getNativeMemoryAllocationMinInMegabytes() {
        int iMin;
        QualityLevel highestVideoQualityLevel = this.mContentSessionContext.getManifest().getHighestVideoQualityLevel();
        if (highestVideoQualityLevel == null) {
            return this.mPlaybackConfig.getNativeMemoryAllocationMinInMegabytes();
        }
        int bitrate = highestVideoQualityLevel.getBitrate();
        int videoStreamingBitrateCap = this.mMediaQualityConfig.getVideoStreamingBitrateCap(this.mContentSessionContext.getState().getMediaQuality(), this.mContentSessionContext.getMaxResolution(), this.mVideoFourCC);
        int bitrate2 = this.mClamper.getHighestPlayableQuality().getBitrate();
        if (bitrate <= videoStreamingBitrateCap) {
            iMin = Math.min(bitrate, bitrate2);
        } else {
            iMin = Math.min(videoStreamingBitrateCap, bitrate2);
        }
        return calculateBaseMemoryAllocationInMegabytes(iMin) + this.mDynamicMemoryStoreConfig.getNativeMemoryAllocationForAudioInMegabytes();
    }

    @Nonnegative
    private int calculateBaseMemoryAllocationInMegabytes(long j) {
        return (int) DataUnit.BITS.toMegaBytes(j * ((long) (this.mMaxFrontAndBackBufferSizeSeconds + this.mContinuousPlayPreinitBufferSizeSeconds)));
    }
}
