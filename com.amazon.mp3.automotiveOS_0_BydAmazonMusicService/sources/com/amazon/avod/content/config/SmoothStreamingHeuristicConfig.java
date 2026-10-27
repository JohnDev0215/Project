package com.amazon.avod.content.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.ContentSessionConfiguration;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import com.amazon.avod.media.framework.config.TimeConfigurationValue;
import com.amazon.avod.media.framework.config.VideoQualityConfig;
import com.amazon.avod.media.framework.platform.DeviceConfiguration;
import com.amazon.avod.util.DLog;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingHeuristicConfig extends VideoQualityConfig {
    private static SmoothStreamingHeuristicConfig INSTANCE;
    private final ConfigurationValue<Set<String>> mBlacklistedComponentClassNames;
    private final TimeConfigurationValue mBufferedTimeRequiredToGoAboveLowestHD;
    private final ConfigurationValue<Float> mDesiredMediaQualityScore;
    private final ConfigurationValue<Float> mDownshiftBitrateFactor;
    private final ConfigurationValue<Boolean> mEnableMultiBitrateSwitch;
    private final ConfigurationValue<Integer> mHeuristicOutputQualityPessimisticAverageCount;
    private final ConfigurationValue<Integer> mHighThreshhold;
    private final ConfigurationValue<Integer> mLookForwardDuration;
    private final ConfigurationValue<Float> mLookupFactor;
    private final TimeConfigurationValue mLowBufferControlDuration;
    private final ConfigurationValue<Integer> mLowThreshhold;
    private final TimeConfigurationValue mNoDownshiftBufferDurationSeconds;
    private final TimeConfigurationValue mNoUpshiftBufferDurationSeconds;
    private final ConfigurationValue<ContentSessionConfiguration.ABRMode> mOverrideAudioAbrMode;
    private final ConfigurationValue<Integer> mOverrideVideoAbrInterval;
    private final ConfigurationValue<ContentSessionConfiguration.ABRMode> mOverrideVideoAbrMode;
    private final TimeConfigurationValue mPanicModeBufferThreshold;
    private final ConfigurationValue<QualityScoreCappingStrategy> mQualityScoreCappingStrategy;
    private final ConfigurationValue<Float> mSSPKBandwdithLookupFactor;
    private final ConfigurationValue<Float> mStreamingFragmentRetryDownswitchFactor;
    private final ConfigurationValue<Float> mTrickmodeBitrateFactor;

    public static synchronized SmoothStreamingHeuristicConfig getInstance(DeviceConfiguration deviceConfiguration) {
        if (INSTANCE == null) {
            INSTANCE = new SmoothStreamingHeuristicConfig(deviceConfiguration);
        }
        return INSTANCE;
    }

    private SmoothStreamingHeuristicConfig(DeviceConfiguration deviceConfiguration) {
        super(deviceConfiguration, "heuristic");
        this.mEnableMultiBitrateSwitch = newBooleanConfigValue("heuristic_enableMultiBitrateSwitch", true);
        this.mHighThreshhold = newIntConfigValue("heuristic_highThreshhold", 15);
        this.mLookForwardDuration = newIntConfigValue("heuristic_lookForwardDuration", 120);
        this.mSSPKBandwdithLookupFactor = newFloatConfigValue("heuristic_sspk_lookupFactor", 0.8f);
        this.mLookupFactor = newFloatConfigValue("heuristic_lookupFactor", 0.8f);
        this.mLowThreshhold = newIntConfigValue("heuristic_lowThreshhold", 5);
        this.mTrickmodeBitrateFactor = newFloatConfigValue("heuristic_trickModeBitrateFactor", 0.7692308f);
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(15.0d);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        this.mNoDownshiftBufferDurationSeconds = newTimeConfigurationValue("heuristic_noDownshiftBufferDurationSeconds", timeSpanFromSeconds, timeUnit);
        this.mNoUpshiftBufferDurationSeconds = newTimeConfigurationValue("heuristic_noUpshiftBufferDurationSeconds", TimeSpan.fromSeconds(10.0d), timeUnit);
        this.mHeuristicOutputQualityPessimisticAverageCount = newIntConfigValue("heuristic_outputQualityPessimisticAverageCount", 3);
        this.mStreamingFragmentRetryDownswitchFactor = newFloatConfigValue("heuristic_streamingFragmentRetryDownswitchFactor", 0.5f);
        this.mBufferedTimeRequiredToGoAboveLowestHD = newTimeConfigurationValue("heuristic_bufferedTimeRequiredToGoAboveLowestHDSeconds", TimeSpan.fromSeconds(25.0d), timeUnit);
        this.mLowBufferControlDuration = newTimeConfigurationValue("heuristic_lowBufferControlDuration", TimeSpan.fromSeconds(3.0d), timeUnit);
        this.mBlacklistedComponentClassNames = newStringSetConfigValue("heuristic_blackListedComponentClassNames", "MinimalizingBitrateSelectionComponent", ";");
        this.mDownshiftBitrateFactor = newFloatConfigValue("heuristic_downshiftBitrateFactor", 1.0f);
        this.mPanicModeBufferThreshold = newTimeConfigurationValue("heuristic_panicModeBufferThreshold", TimeSpan.fromSeconds(3.0d), timeUnit);
        this.mOverrideVideoAbrMode = newEnumConfigValue(PlaybackConfigKeys.HEURISTIC_OVERRIDE_ABR_MODE, ContentSessionConfiguration.ABRMode.HEURISTIC, ContentSessionConfiguration.ABRMode.class);
        this.mOverrideAudioAbrMode = newEnumConfigValue(PlaybackConfigKeys.HEURISTIC_OVERRIDE_AUDIO_ABR_MODE, ContentSessionConfiguration.ABRMode.LEGACY_FIXED_AUDIO_BIRATE, ContentSessionConfiguration.ABRMode.class);
        this.mOverrideVideoAbrInterval = newIntConfigValue("heuristic_overrideVideoAbrInterval", 1);
        this.mDesiredMediaQualityScore = newFloatConfigValue("playback_desiredMediaQualityScore", 100.0f);
        this.mQualityScoreCappingStrategy = newEnumConfigValue("playback_qualityScoreCappingStrategy", QualityScoreCappingStrategy.NO_QUALITY_SCORE, QualityScoreCappingStrategy.class);
    }

    @Deprecated
    public int getHighThreshhold() {
        return this.mHighThreshhold.getValue().intValue();
    }

    @Deprecated
    public int getLookForwardDuration() {
        return this.mLookForwardDuration.getValue().intValue();
    }

    @Deprecated
    public float getLookupFactor() {
        return this.mLookupFactor.getValue().floatValue();
    }

    @Deprecated
    public float getSSPKBandwidthLookupFactor() {
        return this.mSSPKBandwdithLookupFactor.getValue().floatValue();
    }

    @Deprecated
    public int getLowThreshhold() {
        return this.mLowThreshhold.getValue().intValue();
    }

    @Deprecated
    public float getTrickmodeBitrateFactor() {
        return this.mTrickmodeBitrateFactor.getValue().floatValue();
    }

    @Deprecated
    public TimeSpan getNoDownshiftBufferDuration() {
        return this.mNoDownshiftBufferDurationSeconds.getValue();
    }

    @Deprecated
    public TimeSpan getNoUpshiftBufferDuration() {
        return this.mNoUpshiftBufferDurationSeconds.getValue();
    }

    @Deprecated
    public int getHeuristicOutputQualityPessimisticAverageCount() {
        return this.mHeuristicOutputQualityPessimisticAverageCount.getValue().intValue();
    }

    @Deprecated
    public float getStreamingFragmentRetryDownswitchFactor() {
        return this.mStreamingFragmentRetryDownswitchFactor.getValue().floatValue();
    }

    @Deprecated
    public long getBufferedTimeRequiredToGoAboveLowestHDInNanos() {
        return this.mBufferedTimeRequiredToGoAboveLowestHD.getValue().getTotalNanoSeconds();
    }

    @Deprecated
    public long getLowBufferControlDurationInNanos() {
        return this.mLowBufferControlDuration.getValue().getTotalNanoSeconds();
    }

    @Deprecated
    public Set<String> getBlacklistedComponentClassNames() {
        return this.mBlacklistedComponentClassNames.getValue();
    }

    @Deprecated
    public float getDownshiftBitrateFactor() {
        if (this.mDownshiftBitrateFactor.getValue().floatValue() <= 0.0f) {
            DLog.logf("heuristic_downshiftBitrateFactor cannot be negative or 0, using 1 as default");
            return 1.0f;
        }
        return this.mDownshiftBitrateFactor.getValue().floatValue();
    }

    @Deprecated
    public long getPanicModeBufferThresholdInNanos() {
        return this.mPanicModeBufferThreshold.getValue().getTotalNanoSeconds();
    }

    public ContentSessionConfiguration.ABRMode getOverrideVideoAbrMode() {
        return this.mOverrideVideoAbrMode.getValue();
    }

    public ContentSessionConfiguration.ABRMode getOverrideAudioAbrMode() {
        return this.mOverrideAudioAbrMode.getValue();
    }

    public int getOverrideVideoAbrInterval() {
        return this.mOverrideVideoAbrInterval.getValue().intValue();
    }

    public double getDesiredMediaQualityScore() {
        return this.mDesiredMediaQualityScore.getValue().floatValue();
    }

    @Nonnull
    public QualityScoreCappingStrategy getQualityScoreCappingStrategy() {
        return this.mQualityScoreCappingStrategy.getValue();
    }
}
