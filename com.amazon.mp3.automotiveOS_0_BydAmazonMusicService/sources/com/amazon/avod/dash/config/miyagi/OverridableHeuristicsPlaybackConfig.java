package com.amazon.avod.dash.config.miyagi;

import com.amazon.avod.content.classification.ClassifierType;
import com.amazon.avod.content.config.ClassifierConfig;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.QualityScoreCappingStrategy;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.dash.config.miyagi.HeuristicsConfigBase;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class OverridableHeuristicsPlaybackConfig extends HeuristicsConfigBase implements HeuristicsPlaybackConfig {
    private static final String SERVER_SELECTION_MODE_NETWORK_HISTORY_BASED = "RETENTION_LOW";
    private final double desiredMediaQualityScore;
    private final Float mAlpha;
    private final String mAudioAdaptationSetSelectionMode;
    private final double[][] mAudioTransferSpeedVariationFactorToTransferSpeedSharePair;
    private final int mAudioVideoDownloadSynchronizationThresholdSeconds;
    private final int mBackwardBufferLengthSeconds;
    private final int mBufferFullnessForPlaybackStartSecondsForLiveWithDynamicAds;
    private final int mBufferFullnessForPlaybackStartSecondsLive;
    private final ClassifierType mClassifierType;
    private final int mDelayBehindLiveHeadSeconds;
    private final int mForwardBufferLengthSeconds;
    private final String mHeuristicAlgorithmLive;
    private final String mHeuristicAlgorithmVOD;
    private final boolean mIsBidirectionalAudioVideoSynchronizationEnabled;
    private final boolean mIsMidstreamSwitchingEnabled;
    private final int mMaxAuxiliaryConcurrentRequests;
    private final int mMaxAuxiliaryRequestsCancellation;
    private final int mMinBandwidthToMaintainAuxiliaryDownloadKbps;
    private final int mMinBandwidthToStartAuxiliaryDownloadKbps;
    private final int mMinBufferFullnessToMaintainAuxiliaryDownloadSeconds;
    private final int mMinBufferFullnessToStartAuxiliaryDownloadSeconds;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig;
    private final Integer mWindowSize;
    private final int maxBitrateKbps;
    private final int minBitrateKbps;
    private final String qualityScoreCappingStrategy;

    OverridableHeuristicsPlaybackConfig(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, LiveStreamingPlaybackConfig liveStreamingPlaybackConfig, SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, ClassifierConfig classifierConfig) {
        super(map, map2, map3);
        this.mSmoothStreamingPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingHeuristicConfig");
        DataUnit dataUnit = DataUnit.BITS;
        this.minBitrateKbps = getIntegerConfigValue("filters_minBitrateKbps", Math.round(dataUnit.toKiloBits(smoothStreamingHeuristicConfig.getLowestPlayableBitrate())));
        this.maxBitrateKbps = getIntegerConfigValue("filters_maxBitrateKbps", Math.round(dataUnit.toKiloBits(smoothStreamingHeuristicConfig.getHighestPlayableBitrate())));
        this.desiredMediaQualityScore = getDoubleConfigValue("filters_desiredMediaQualityScore", smoothStreamingHeuristicConfig.getDesiredMediaQualityScore());
        this.qualityScoreCappingStrategy = getStringConfigValue("filters_qualityScoreCappingStrategy", smoothStreamingHeuristicConfig.getQualityScoreCappingStrategy().name());
        this.mBufferFullnessForPlaybackStartSecondsLive = getIntegerConfigValue("buffer_bufferFullnessForPlaybackStartSecondsLive", smoothStreamingPlaybackConfig.getRequiredCacheSizeForStreamingPlaybackForLive().getTotalSeconds());
        this.mBufferFullnessForPlaybackStartSecondsForLiveWithDynamicAds = getIntegerConfigValue("buffer_bufferFullnessForPlaybackStartSecondsWithDynamicAd", smoothStreamingPlaybackConfig.getRequiredCacheSizeForStreamingPlaybackForLiveWithDynamicAd().getTotalSeconds());
        this.mForwardBufferLengthSeconds = getIntegerConfigValue("buffer_forwardBufferLengthSeconds", smoothStreamingPlaybackConfig.getStreamingFutureBufferSizeSeconds(null));
        this.mBackwardBufferLengthSeconds = getIntegerConfigValue("buffer_backwardBufferLengthSeconds", smoothStreamingPlaybackConfig.getStreamingPastBufferSizeSeconds(null));
        this.mDelayBehindLiveHeadSeconds = getIntegerConfigValue("buffer_delayBehindLiveHeadSeconds", liveStreamingPlaybackConfig.getLiveStreamingFutureBufferSizeSeconds(null));
        this.mMaxAuxiliaryConcurrentRequests = getIntegerConfigValue("auxiliaryDownload_maxAuxiliaryConcurrentRequests", smoothStreamingPlaybackConfig.getMaxAuxiliaryConcurrentRequests());
        this.mMaxAuxiliaryRequestsCancellation = getIntegerConfigValue("auxiliaryDownload_maxAuxiliaryRequestsCancellation", smoothStreamingPlaybackConfig.getMaxAuxiliaryRequestsCancellation());
        this.mMinBandwidthToStartAuxiliaryDownloadKbps = getIntegerConfigValue("auxiliaryDownload_minBandwidthToStartAuxiliaryDownloadKbps", smoothStreamingPlaybackConfig.getMinBandwidthToStartAuxiliaryDownloadKbps());
        this.mMinBandwidthToMaintainAuxiliaryDownloadKbps = getIntegerConfigValue("auxiliaryDownload_minBandwidthToMaintainAuxiliaryDownloadKbps", smoothStreamingPlaybackConfig.getMinBandwidthToMaintainAuxiliaryDownloadKbps());
        this.mMinBufferFullnessToStartAuxiliaryDownloadSeconds = getIntegerConfigValue("auxiliaryDownload_minBufferFullnessToStartAuxiliaryDownloadSeconds", smoothStreamingPlaybackConfig.getMinBufferFullnessToStartAuxiliaryDownloadSeconds());
        this.mMinBufferFullnessToMaintainAuxiliaryDownloadSeconds = getIntegerConfigValue("auxiliaryDownload_minBufferFullnessToMaintainAuxiliaryDownloadSeconds", smoothStreamingPlaybackConfig.getMinBufferFullnessToMaintainAuxiliaryDownloadSeconds());
        this.mAudioTransferSpeedVariationFactorToTransferSpeedSharePair = getHeuristicsPairConfigValue("audioSelection_audioTransferSpeedBpsVariationFactorToSpeedSharePair", new double[][]{new double[]{0.0d, 0.1d}, new double[]{1.5d, 0.2d}});
        this.mAudioAdaptationSetSelectionMode = getStringConfigValue("audioSelection_audioAdaptationSetSelectionMode", smoothStreamingPlaybackConfig.getAudioAdaptationSetSelectionMode());
        this.mHeuristicAlgorithmVOD = getStringConfigValue("playback_heuristicAlgorithmVOD", smoothStreamingPlaybackConfig.getHeuristicAlgorithmVOD());
        this.mIsMidstreamSwitchingEnabled = getBooleanConfigValue("playback_midstreamSwitchingEnabled", smoothStreamingPlaybackConfig.isMidstreamSwitchingEnabled());
        this.mHeuristicAlgorithmLive = getStringConfigValue("playback_heuristicAlgorithmLive", smoothStreamingPlaybackConfig.getHeuristicAlgorithmLive());
        this.mAudioVideoDownloadSynchronizationThresholdSeconds = getIntegerConfigValue("buffer_audioVideoDownloadSynchronizationThresholdSeconds", smoothStreamingPlaybackConfig.getDashAudioVideoDownloadSynchronizationThreshold().getTotalSeconds());
        this.mIsBidirectionalAudioVideoSynchronizationEnabled = getBooleanConfigValue("buffer_isBidirectionalAudioVideoSynchronizationEnabled", smoothStreamingPlaybackConfig.isBidirectionalAudioVideoSynchronizationEnabled());
        this.mClassifierType = (ClassifierType) getEnumConfigValue("classifierParameters_type", classifierConfig.getClassifierType(), ClassifierType.class);
        this.mWindowSize = Integer.valueOf(getIntegerConfigValue("classifierParameters_windowSize", classifierConfig.getWindowSize()));
        this.mAlpha = Float.valueOf(getFloatConfigValue("classifierParameters_alpha", classifierConfig.getClassifierAlpha()));
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getMinBitrateKbps() {
        return this.minBitrateKbps;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getMaxBitrateKbps() {
        return this.maxBitrateKbps;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getBufferFullnessForPlaybackStartSeconds() {
        return getIntegerConfigValue("buffer_bufferFullnessForPlaybackStartSeconds", this.mSmoothStreamingPlaybackConfig.getRequiredCacheSizeForStreamingPlayback(null).getTotalSeconds());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getBufferLengthForWhisperCacheSeconds() {
        return getIntegerConfigValue("buffer_reactiveCacheBufferLengthSeconds", this.mSmoothStreamingPlaybackConfig.getRequiredCacheSizeForWhisperCache(null).getTotalSeconds());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getBufferFullnessForPlaybackStartSeconds(boolean z, boolean z2) {
        if (z) {
            return this.mBufferFullnessForPlaybackStartSecondsForLiveWithDynamicAds;
        }
        if (z2) {
            return this.mBufferFullnessForPlaybackStartSecondsLive;
        }
        return getBufferFullnessForPlaybackStartSeconds();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getBufferFullnessForPlaybackResumeSeconds() {
        return getIntegerConfigValue("buffer_bufferFullnessForPlaybackResumeSeconds", this.mSmoothStreamingPlaybackConfig.getRequiredCacheSizeForResume(null).getTotalSeconds());
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getMaxAuxiliaryConcurrentRequests() {
        return this.mMaxAuxiliaryConcurrentRequests;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getMaxAuxiliaryRequestsCancellation() {
        return this.mMaxAuxiliaryRequestsCancellation;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getMinBandwidthToStartAuxiliaryDownloadKbps() {
        return this.mMinBandwidthToStartAuxiliaryDownloadKbps;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getMinBufferFullnessToStartAuxiliaryDownloadSeconds() {
        return this.mMinBufferFullnessToStartAuxiliaryDownloadSeconds;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getMinBandwidthToMaintainAuxiliaryDownloadKbps() {
        return this.mMinBandwidthToMaintainAuxiliaryDownloadKbps;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getMinBufferFullnessToMaintainAuxiliaryDownloadSeconds() {
        return this.mMinBufferFullnessToMaintainAuxiliaryDownloadSeconds;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public double[][] getAudioTransferSpeedVariationFactorToTransferSpeedSharePair() {
        return this.mAudioTransferSpeedVariationFactorToTransferSpeedSharePair;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public boolean isNetworkHistoryBasedAudioAdaptationSetSelectionEnabled() {
        return SERVER_SELECTION_MODE_NETWORK_HISTORY_BASED.equalsIgnoreCase(this.mAudioAdaptationSetSelectionMode);
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getForwardBufferLengthSeconds() {
        return this.mForwardBufferLengthSeconds;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getBackwardBufferLengthSeconds() {
        return this.mBackwardBufferLengthSeconds;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getDelayBehindLiveHeadSeconds() {
        return this.mDelayBehindLiveHeadSeconds;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    @Nonnull
    public String getHeuristicAlgorithm(boolean z) {
        return z ? this.mHeuristicAlgorithmLive : this.mHeuristicAlgorithmVOD;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getAudioVideoDownloadSynchronizationThresholdSeconds() {
        return this.mAudioVideoDownloadSynchronizationThresholdSeconds;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public boolean isBidirectionalAudioVideoSynchronizationEnabled() {
        return this.mIsBidirectionalAudioVideoSynchronizationEnabled;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public double getDesiredMediaQualityScore() {
        return this.desiredMediaQualityScore;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    @Nonnull
    public QualityScoreCappingStrategy getQualityScoreCappingStrategy() {
        return QualityScoreCappingStrategy.INSTANCE.lookupQualityScoreCappingStrategy(this.qualityScoreCappingStrategy);
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public boolean isMidstreamSwitchingEnabled() {
        return this.mIsMidstreamSwitchingEnabled;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    @Nonnull
    public ClassifierType getClassifierType() {
        return this.mClassifierType;
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public int getWindowSize() {
        return this.mWindowSize.intValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public float getAlpha() {
        return this.mAlpha.floatValue();
    }

    @Override // com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig
    public void applyHeuristicsEngineOverrides(@Nonnull Map<String, String> map) {
        applyOverrides(map, HeuristicsConfigBase.OverrideType.HEURISTICS_ENGINE);
    }
}
