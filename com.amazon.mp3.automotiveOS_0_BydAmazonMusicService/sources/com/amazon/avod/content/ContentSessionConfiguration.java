package com.amazon.avod.content;

import android.widget.Spinner;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Optional;
import com.google.common.collect.Range;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentSessionConfiguration {
    private static final int BITS_IN_A_KILO_BIT = (int) DataUnit.KILOBITS.toBits(1.0f);
    private final Range<Integer> mBitrateRange;
    private final int mBufferLookAheadSeconds;
    private final int mBufferLookBehindSeconds;
    private final boolean mHeuristicsDownloadControlsEnabled;
    private final Range<Integer> mHorizontalResolutionRange;
    private final int mLiveBufferLookAheadSeconds;
    private final int mLiveCacheBufferLookAheadSeconds;
    private final int mLiveCacheBufferLookBehindSeconds;
    private final Optional<ABRMode> mOverrideAudioAbrMode;
    private final int mOverrideVideoAbrInterval;
    private final Optional<ABRMode> mOverrideVideoAbrMode;
    private final Optional<Spinner> mUserQualityLevelSelector;
    private final Range<Integer> mVerticalResolutionRange;

    public enum ABRMode {
        HEURISTIC("Heuristic"),
        LOWEST("Lowest"),
        HIGHEST("Highest"),
        ZIGZAG("ZigZag"),
        LOWESTHIGHEST("LowestHighest"),
        RANDOM("Random"),
        USER_SELECTED("UserSelected"),
        LEGACY_FIXED_AUDIO_BIRATE("LegacyFixedAudioBitrate");

        private final String mValue;

        ABRMode(String str) {
            this.mValue = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mValue;
        }
    }

    private ContentSessionConfiguration(Builder builder) {
        this.mOverrideVideoAbrMode = builder.mOverrideVideoAbrMode;
        this.mOverrideAudioAbrMode = builder.mOverrideAudioAbrMode;
        this.mUserQualityLevelSelector = builder.mUserQualityLevelSelector;
        this.mOverrideVideoAbrInterval = builder.mOverrideVideoAbrInterval;
        this.mBitrateRange = builder.mBitrateRange;
        this.mHorizontalResolutionRange = builder.mHorizontalResolutionRange;
        this.mVerticalResolutionRange = builder.mVerticalResolutionRange;
        this.mBufferLookAheadSeconds = builder.mBufferLookAheadSeconds;
        this.mBufferLookBehindSeconds = builder.mBufferLookBehindSeconds;
        this.mLiveBufferLookAheadSeconds = builder.mLiveBufferLookAheadSeconds;
        this.mHeuristicsDownloadControlsEnabled = builder.mHeuristicsDownloadControlsEnabled;
        this.mLiveCacheBufferLookAheadSeconds = builder.mLiveCacheBufferLookAheadSeconds;
        this.mLiveCacheBufferLookBehindSeconds = builder.mLiveCacheBufferLookBehindSeconds;
    }

    public Optional<ABRMode> getOverrideVideoAbrMode() {
        return this.mOverrideVideoAbrMode;
    }

    public Optional<ABRMode> getOverrideAudioAbrMode() {
        return this.mOverrideAudioAbrMode;
    }

    public Optional<Spinner> getUserQualityLevelSelector() {
        return this.mUserQualityLevelSelector;
    }

    public int getOverrideVideoAbrInterval() {
        return this.mOverrideVideoAbrInterval;
    }

    public Range<Integer> getBitrateRange() {
        return this.mBitrateRange;
    }

    public Range<Integer> getHorizontalResolutionRange() {
        return this.mHorizontalResolutionRange;
    }

    public Range<Integer> getVerticalResolutionRange() {
        return this.mVerticalResolutionRange;
    }

    public int getBufferLookAheadSeconds() {
        return this.mBufferLookAheadSeconds;
    }

    public int getLiveBufferLookAheadSeconds() {
        return this.mLiveBufferLookAheadSeconds;
    }

    public int getBufferLookBehindSeconds() {
        return this.mBufferLookBehindSeconds;
    }

    public boolean isHeuristicsDownloadControlsEnabled() {
        return this.mHeuristicsDownloadControlsEnabled;
    }

    public int getLiveCacheBufferLookAheadSeconds() {
        return this.mLiveCacheBufferLookAheadSeconds;
    }

    public int getLiveCacheBufferLookBehindSeconds() {
        return this.mLiveCacheBufferLookBehindSeconds;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Range<Integer> mBitrateRange;
        private int mBufferLookAheadSeconds;
        private int mBufferLookBehindSeconds;
        private boolean mHeuristicsDownloadControlsEnabled;
        private Range<Integer> mHorizontalResolutionRange;
        private int mLiveBufferLookAheadSeconds;
        private int mLiveCacheBufferLookAheadSeconds;
        private int mLiveCacheBufferLookBehindSeconds;
        private Optional<ABRMode> mOverrideAudioAbrMode;
        private int mOverrideVideoAbrInterval;
        private Optional<ABRMode> mOverrideVideoAbrMode;
        private Optional<Spinner> mUserQualityLevelSelector;
        private Range<Integer> mVerticalResolutionRange;

        private Builder() {
            this.mOverrideVideoAbrMode = Optional.absent();
            this.mOverrideAudioAbrMode = Optional.absent();
            this.mUserQualityLevelSelector = Optional.absent();
            this.mOverrideVideoAbrInterval = 1;
            this.mBitrateRange = Range.closed(0, Integer.MAX_VALUE);
            this.mHorizontalResolutionRange = Range.closed(0, Integer.MAX_VALUE);
            this.mVerticalResolutionRange = Range.closed(0, Integer.MAX_VALUE);
            this.mBufferLookAheadSeconds = 10;
            this.mBufferLookBehindSeconds = 10;
            this.mLiveBufferLookAheadSeconds = 10;
            this.mLiveCacheBufferLookAheadSeconds = 10;
            this.mLiveCacheBufferLookBehindSeconds = 10;
        }

        public Builder setFromConfig(@Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, @Nullable Heuristics heuristics, @Nonnull LiveStreamingPlaybackConfig liveStreamingPlaybackConfig) {
            ABRMode overrideVideoAbrMode = smoothStreamingHeuristicConfig.getOverrideVideoAbrMode();
            if (overrideVideoAbrMode == ABRMode.USER_SELECTED) {
                overrideVideoAbrMode = ABRMode.HEURISTIC;
            }
            this.mOverrideVideoAbrMode = Optional.of(overrideVideoAbrMode);
            this.mOverrideAudioAbrMode = Optional.of(smoothStreamingHeuristicConfig.getOverrideAudioAbrMode());
            this.mOverrideVideoAbrInterval = smoothStreamingHeuristicConfig.getOverrideVideoAbrInterval();
            if (heuristics != null && heuristics.getHeuristicsPlaybackConfig() != null) {
                int iMax = Math.max(heuristics.getHeuristicsPlaybackConfig().getMinBitrateKbps() * ContentSessionConfiguration.BITS_IN_A_KILO_BIT, smoothStreamingHeuristicConfig.getLowestPlayableBitrate());
                int iMin = Math.min(heuristics.getHeuristicsPlaybackConfig().getMaxBitrateKbps() * ContentSessionConfiguration.BITS_IN_A_KILO_BIT, smoothStreamingHeuristicConfig.getHighestPlayableBitrate());
                try {
                    this.mBitrateRange = Range.closed(Integer.valueOf(iMax), Integer.valueOf(iMin));
                } catch (IllegalArgumentException unused) {
                    DLog.warnf("Invalid bitrate range [%s - %s]! Fall back to default range.", Integer.valueOf(iMax), Integer.valueOf(iMin));
                    this.mBitrateRange = Range.closed(0, 25000000);
                }
            } else {
                this.mBitrateRange = Range.closed(Integer.valueOf(smoothStreamingHeuristicConfig.getLowestPlayableBitrate()), Integer.valueOf(smoothStreamingHeuristicConfig.getHighestPlayableBitrate()));
            }
            this.mHorizontalResolutionRange = Range.closed(Integer.valueOf(smoothStreamingHeuristicConfig.getMinimumPlayableHorizontalResolution()), Integer.valueOf(smoothStreamingHeuristicConfig.getMaximumPlayableHorizontalResolution()));
            this.mVerticalResolutionRange = Range.closed(Integer.valueOf(smoothStreamingHeuristicConfig.getMinimumPlayableVerticalResolution()), Integer.valueOf(smoothStreamingHeuristicConfig.getMaximumPlayableVerticalResolution()));
            this.mBufferLookAheadSeconds = smoothStreamingPlaybackConfig.getStreamingFutureBufferSizeSeconds(heuristics);
            this.mBufferLookBehindSeconds = smoothStreamingPlaybackConfig.getStreamingPastBufferSizeSeconds(heuristics);
            this.mLiveBufferLookAheadSeconds = liveStreamingPlaybackConfig.getLiveStreamingFutureBufferSizeSeconds(heuristics);
            this.mHeuristicsDownloadControlsEnabled = smoothStreamingPlaybackConfig.getHeuristicsDownloadControlsEnabled();
            this.mLiveCacheBufferLookAheadSeconds = smoothStreamingPlaybackConfig.getLiveCacheBufferLookAhead().getTotalSeconds();
            this.mLiveCacheBufferLookBehindSeconds = smoothStreamingPlaybackConfig.getLiveCacheBufferLookBehind().getTotalSeconds();
            return this;
        }

        public ContentSessionConfiguration build() {
            return new ContentSessionConfiguration(this);
        }
    }
}
