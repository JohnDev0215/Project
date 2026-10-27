package com.amazon.avod.content.dash.quality.heuristic;

import com.amazon.avod.content.classification.ClassifierType;
import com.amazon.avod.content.config.QualityScoreCappingStrategy;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface HeuristicsPlaybackConfig {
    void applyHeuristicsEngineOverrides(@Nonnull Map<String, String> map);

    float getAlpha();

    double[][] getAudioTransferSpeedVariationFactorToTransferSpeedSharePair();

    int getAudioVideoDownloadSynchronizationThresholdSeconds();

    int getBackwardBufferLengthSeconds();

    int getBufferFullnessForPlaybackResumeSeconds();

    int getBufferFullnessForPlaybackStartSeconds();

    int getBufferFullnessForPlaybackStartSeconds(boolean z, boolean z2);

    int getBufferLengthForWhisperCacheSeconds();

    @Nonnull
    ClassifierType getClassifierType();

    int getDelayBehindLiveHeadSeconds();

    double getDesiredMediaQualityScore();

    int getForwardBufferLengthSeconds();

    @Nonnull
    String getHeuristicAlgorithm(boolean z);

    int getMaxAuxiliaryConcurrentRequests();

    int getMaxAuxiliaryRequestsCancellation();

    int getMaxBitrateKbps();

    int getMinBandwidthToMaintainAuxiliaryDownloadKbps();

    int getMinBandwidthToStartAuxiliaryDownloadKbps();

    int getMinBitrateKbps();

    int getMinBufferFullnessToMaintainAuxiliaryDownloadSeconds();

    int getMinBufferFullnessToStartAuxiliaryDownloadSeconds();

    @Nonnull
    QualityScoreCappingStrategy getQualityScoreCappingStrategy();

    int getWindowSize();

    boolean isBidirectionalAudioVideoSynchronizationEnabled();

    boolean isMidstreamSwitchingEnabled();

    boolean isNetworkHistoryBasedAudioAdaptationSetSelectionEnabled();
}
