package com.amazon.avod.dash.config.miyagi;

import com.amazon.avod.content.dash.quality.heuristic.ReviewDownloadProgressStrategy;
import com.amazon.avod.content.dash.quality.heuristic.TimeoutStrategy;
import com.amazon.avod.dash.config.bola.BolaAlgorithm;
import com.amazon.avod.dash.config.bola.BolaConfigInterface;
import com.amazon.avod.util.DLog;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FixedBolaConfig.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b2\n\u0002\u0010\u000b\n\u0002\b-\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BI\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0002\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0014\u0010\u001b\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\rR\u0014\u0010\u001d\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\rR\u0014\u0010\u001f\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\rR\u0014\u0010!\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\rR\u0014\u0010#\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\rR\u0014\u0010%\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\rR\u0014\u0010'\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\rR\u0014\u0010)\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\rR\u0014\u0010+\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\rR\u0014\u0010-\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0011R\u0014\u0010/\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0011R\u0014\u00101\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0015R\u0014\u00103\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\rR\u0014\u00105\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0015R\u0014\u00107\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0011R\u0014\u00109\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\rR\u0014\u0010;\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\rR\u0014\u0010=\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0018R\u0014\u0010?\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\rR\u0014\u0010A\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\rR\u0014\u0010C\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\rR\u0014\u0010E\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0014\u0010I\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010HR\u0014\u0010K\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010HR\u0014\u0010M\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010HR\u0014\u0010O\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010HR\u0014\u0010Q\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010HR\u0014\u0010S\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010HR\u0014\u0010U\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010HR\u0014\u0010W\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010HR\u0014\u0010Y\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010HR\u0014\u0010[\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010HR\u0014\u0010]\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\rR\u0014\u0010_\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u0018R\u0014\u0010a\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\rR\u0014\u0010c\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\rR\u0014\u0010e\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\rR\u0014\u0010g\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\rR\u0014\u0010i\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0011R\u0014\u0010k\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\u0011R\u0014\u0010m\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0011R\u0014\u0010o\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bp\u0010\u0015R\u0014\u0010q\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010\u0015¨\u0006s"}, d2 = {"Lcom/amazon/avod/dash/config/miyagi/FixedBolaConfig;", "Lcom/amazon/avod/dash/config/miyagi/HeuristicsConfigBase;", "Lcom/amazon/avod/dash/config/bola/BolaConfigInterface;", "debugValues", "", "", "sessionValues", "appLoadValues", "bolaConfig", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/amazon/avod/dash/config/bola/BolaConfigInterface;)V", "abandonGoodnessValueThreshold", "", "getAbandonGoodnessValueThreshold", "()F", "audioSubtitleConcurrentDownloadRequests", "", "getAudioSubtitleConcurrentDownloadRequests", "()I", "audioSubtitleDownloadTimeoutMillis", "", "getAudioSubtitleDownloadTimeoutMillis", "()J", "bolaAlgorithm", "getBolaAlgorithm", "()Ljava/lang/String;", "bolaAlgorithmIndex", "getBolaAlgorithmIndex", "bufferHighWatermarkSeconds", "getBufferHighWatermarkSeconds", "bufferLowWatermarkSeconds", "getBufferLowWatermarkSeconds", "bufferLowWatermarkSecondsForLowTransient", "getBufferLowWatermarkSecondsForLowTransient", "bufferScaler", "getBufferScaler", "fragmentSizeOverhead", "getFragmentSizeOverhead", "lowTransientBufferDurationIncrementScaleFactor", "getLowTransientBufferDurationIncrementScaleFactor", "lowestBitrateScaleFactor", "getLowestBitrateScaleFactor", "maxAvailableBufferDurationScaleFactorHigh", "getMaxAvailableBufferDurationScaleFactorHigh", "maxAvailableBufferDurationScaleFactorLow", "getMaxAvailableBufferDurationScaleFactorLow", "maxNumCancellationsPerFragment", "getMaxNumCancellationsPerFragment", "maxRampUpQualityLevel", "getMaxRampUpQualityLevel", "maxVideoDownloadTimeoutMillis", "getMaxVideoDownloadTimeoutMillis", "minAbandonBitratePercent", "getMinAbandonBitratePercent", "minVideoDownloadTimeoutMillis", "getMinVideoDownloadTimeoutMillis", "numberOfFutureFragmentsToCheck", "getNumberOfFutureFragmentsToCheck", "rampUpGoodnessValueFactor", "getRampUpGoodnessValueFactor", "reservedBandwidthForAudioBitsPerSecond", "getReservedBandwidthForAudioBitsPerSecond", "reviewDownloadProgressStrategy", "getReviewDownloadProgressStrategy", "scalerThroughputEstimateInGetBolaBitRate", "getScalerThroughputEstimateInGetBolaBitRate", "scalerThroughputEstimateInGetBolaBitRateLowTransient", "getScalerThroughputEstimateInGetBolaBitRateLowTransient", "scalerThroughputEstimateInRDP", "getScalerThroughputEstimateInRDP", "shouldAdjustBufferLowWatermarkForCVBR", "", "getShouldAdjustBufferLowWatermarkForCVBR", "()Z", "shouldAdjustBufferLowWatermarkForLowTransient", "getShouldAdjustBufferLowWatermarkForLowTransient", "shouldAllowAggressiveRDPForLowBuffer", "getShouldAllowAggressiveRDPForLowBuffer", "shouldCompensateForDownloaderStall", "getShouldCompensateForDownloaderStall", "shouldCompensateMaxBufferForBufferingDelay", "getShouldCompensateMaxBufferForBufferingDelay", "shouldCompensateMaxBufferForStartupDelay", "getShouldCompensateMaxBufferForStartupDelay", "shouldResetStateVariablesOnResume", "getShouldResetStateVariablesOnResume", "shouldSwitchHybridBolaOnTransientStates", "getShouldSwitchHybridBolaOnTransientStates", "shouldUseRealBufferForTimeoutCalculation", "getShouldUseRealBufferForTimeoutCalculation", "shouldUseThroughputEstimateInGetBolaBitRate", "getShouldUseThroughputEstimateInGetBolaBitRate", "shouldUseThroughputEstimateInRDP", "getShouldUseThroughputEstimateInRDP", "startupBitratePercent", "getStartupBitratePercent", "timeoutCalculationStrategy", "getTimeoutCalculationStrategy", "tradeOffFactor", "getTradeOffFactor", "transientBufferDurationIncrementScaleFactor", "getTransientBufferDurationIncrementScaleFactor", "transientClipMaxDurationFactor", "getTransientClipMaxDurationFactor", "transientStateBufferThresholdSeconds", "getTransientStateBufferThresholdSeconds", "validatedReviewDownloadProgressStrategy", "getValidatedReviewDownloadProgressStrategy", "validatedTimeoutCalculationStrategy", "getValidatedTimeoutCalculationStrategy", "videoConcurrentDownloadRequests", "getVideoConcurrentDownloadRequests", "videoDownloadReservedTimeMillis", "getVideoDownloadReservedTimeMillis", "videoDownloadTimeoutWhenNotPlayingMillis", "getVideoDownloadTimeoutWhenNotPlayingMillis", "ATVPlaybackDash_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FixedBolaConfig extends HeuristicsConfigBase implements BolaConfigInterface {
    private final float abandonGoodnessValueThreshold;
    private final int audioSubtitleConcurrentDownloadRequests;
    private final long audioSubtitleDownloadTimeoutMillis;
    private final String bolaAlgorithm;
    private final int bolaAlgorithmIndex;
    private final float bufferHighWatermarkSeconds;
    private final float bufferLowWatermarkSeconds;
    private final float bufferLowWatermarkSecondsForLowTransient;
    private final float bufferScaler;
    private final float fragmentSizeOverhead;
    private final float lowTransientBufferDurationIncrementScaleFactor;
    private final float lowestBitrateScaleFactor;
    private final float maxAvailableBufferDurationScaleFactorHigh;
    private final float maxAvailableBufferDurationScaleFactorLow;
    private final int maxNumCancellationsPerFragment;
    private final int maxRampUpQualityLevel;
    private final long maxVideoDownloadTimeoutMillis;
    private final float minAbandonBitratePercent;
    private final long minVideoDownloadTimeoutMillis;
    private final int numberOfFutureFragmentsToCheck;
    private final float rampUpGoodnessValueFactor;
    private final float reservedBandwidthForAudioBitsPerSecond;
    private final String reviewDownloadProgressStrategy;
    private final float scalerThroughputEstimateInGetBolaBitRate;
    private final float scalerThroughputEstimateInGetBolaBitRateLowTransient;
    private final float scalerThroughputEstimateInRDP;
    private final boolean shouldAdjustBufferLowWatermarkForCVBR;
    private final boolean shouldAdjustBufferLowWatermarkForLowTransient;
    private final boolean shouldAllowAggressiveRDPForLowBuffer;
    private final boolean shouldCompensateForDownloaderStall;
    private final boolean shouldCompensateMaxBufferForBufferingDelay;
    private final boolean shouldCompensateMaxBufferForStartupDelay;
    private final boolean shouldResetStateVariablesOnResume;
    private final boolean shouldSwitchHybridBolaOnTransientStates;
    private final boolean shouldUseRealBufferForTimeoutCalculation;
    private final boolean shouldUseThroughputEstimateInGetBolaBitRate;
    private final boolean shouldUseThroughputEstimateInRDP;
    private final float startupBitratePercent;
    private final String timeoutCalculationStrategy;
    private final float tradeOffFactor;
    private final float transientBufferDurationIncrementScaleFactor;
    private final float transientClipMaxDurationFactor;
    private final float transientStateBufferThresholdSeconds;
    private final int validatedReviewDownloadProgressStrategy;
    private final int validatedTimeoutCalculationStrategy;
    private final int videoConcurrentDownloadRequests;
    private final long videoDownloadReservedTimeMillis;
    private final long videoDownloadTimeoutWhenNotPlayingMillis;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FixedBolaConfig(Map<String, String> debugValues, Map<String, String> sessionValues, Map<String, String> appLoadValues, BolaConfigInterface bolaConfig) {
        int iOrdinal;
        int iOrdinal2;
        super(debugValues, sessionValues, appLoadValues);
        Intrinsics.checkNotNullParameter(debugValues, "debugValues");
        Intrinsics.checkNotNullParameter(sessionValues, "sessionValues");
        Intrinsics.checkNotNullParameter(appLoadValues, "appLoadValues");
        Intrinsics.checkNotNullParameter(bolaConfig, "bolaConfig");
        String stringConfigValue = getStringConfigValue("bola_algorithm", bolaConfig.getBolaAlgorithm());
        Intrinsics.checkNotNullExpressionValue(stringConfigValue, "getStringConfigValue(\"bo…bolaConfig.bolaAlgorithm)");
        this.bolaAlgorithm = stringConfigValue;
        this.bolaAlgorithmIndex = BolaAlgorithm.INSTANCE.validateNameWithDefault(getBolaAlgorithm()).ordinal();
        this.bufferScaler = getFloatConfigValue("bola_bufferScaler", bolaConfig.getBufferScaler());
        this.tradeOffFactor = getFloatConfigValue("bola_tradeOffFactor", bolaConfig.getTradeOffFactor());
        this.bufferLowWatermarkSeconds = getFloatConfigValue("bola_bufferLowWatermarkSeconds", bolaConfig.getBufferLowWatermarkSeconds());
        this.bufferHighWatermarkSeconds = getFloatConfigValue("bola_bufferHighWatermarkSeconds", bolaConfig.getBufferHighWatermarkSeconds());
        this.transientStateBufferThresholdSeconds = getFloatConfigValue("bola_transientStateBufferThresholdSeconds", bolaConfig.getTransientStateBufferThresholdSeconds());
        this.minAbandonBitratePercent = getFloatConfigValue("bola_minAbandonBitratePercent", bolaConfig.getMinAbandonBitratePercent());
        this.abandonGoodnessValueThreshold = getFloatConfigValue("bola_abandonGoodnessValueThreshold", bolaConfig.getAbandonGoodnessValueThreshold());
        this.startupBitratePercent = getFloatConfigValue("bola_startupBitratePercent", bolaConfig.getStartupBitratePercent());
        this.rampUpGoodnessValueFactor = getFloatConfigValue("bola_rampUpGoodnessValueFactor", bolaConfig.getRampUpGoodnessValueFactor());
        this.maxRampUpQualityLevel = getIntegerConfigValue("bola_maxRampUpQualityLevel", bolaConfig.getMaxRampUpQualityLevel());
        this.transientClipMaxDurationFactor = getFloatConfigValue("bola_transientClipMaxDurationFactor", bolaConfig.getTransientClipMaxDurationFactor());
        this.transientBufferDurationIncrementScaleFactor = getFloatConfigValue("bola_transientBufferDurationIncrementScaleFactor", bolaConfig.getTransientBufferDurationIncrementScaleFactor());
        this.lowTransientBufferDurationIncrementScaleFactor = getFloatConfigValue("bola_lowTransientBufferDurationIncrementScaleFactor", bolaConfig.getLowTransientBufferDurationIncrementScaleFactor());
        this.lowestBitrateScaleFactor = getFloatConfigValue("bola_lowestBitrateScaleFactor", bolaConfig.getLowestBitrateScaleFactor());
        this.scalerThroughputEstimateInRDP = getFloatConfigValue("bola_scalerThroughputEstimateInRDP", bolaConfig.getScalerThroughputEstimateInRDP());
        this.maxAvailableBufferDurationScaleFactorLow = getFloatConfigValue("bola_maxAvailableBufferDurationScaleFactorLow", bolaConfig.getMaxAvailableBufferDurationScaleFactorLow());
        this.maxAvailableBufferDurationScaleFactorHigh = getFloatConfigValue("bola_maxAvailableBufferDurationScaleFactorHigh", bolaConfig.getMaxAvailableBufferDurationScaleFactorHigh());
        this.shouldCompensateMaxBufferForBufferingDelay = getBooleanConfigValue("bola_shouldCompensateMaxBufferForBufferingDelay", bolaConfig.getShouldCompensateMaxBufferForBufferingDelay());
        this.shouldCompensateMaxBufferForStartupDelay = getBooleanConfigValue("bola_shouldCompensateMaxBufferForStartupDelay", bolaConfig.getShouldCompensateMaxBufferForStartupDelay());
        this.shouldCompensateForDownloaderStall = getBooleanConfigValue("bola_shouldCompensateForDownloaderStall", bolaConfig.getShouldCompensateForDownloaderStall());
        this.maxNumCancellationsPerFragment = getIntegerConfigValue("bola_maxNumCancellationsPerFragment", bolaConfig.getMaxNumCancellationsPerFragment());
        this.shouldAllowAggressiveRDPForLowBuffer = getBooleanConfigValue("bola_shouldAllowAggressiveRDPForLowBuffer", bolaConfig.getShouldAllowAggressiveRDPForLowBuffer());
        this.shouldUseThroughputEstimateInRDP = getBooleanConfigValue("bola_shouldUseThroughputEstimateInRDP2", bolaConfig.getShouldUseThroughputEstimateInRDP());
        this.shouldAdjustBufferLowWatermarkForCVBR = getBooleanConfigValue("bola_shouldAdjustBufferLowWatermarkForCVBR", bolaConfig.getShouldAdjustBufferLowWatermarkForCVBR());
        this.scalerThroughputEstimateInGetBolaBitRate = getFloatConfigValue("bola_scalerThroughputEstimateInGetBolaBitRate", bolaConfig.getScalerThroughputEstimateInGetBolaBitRate());
        this.shouldUseThroughputEstimateInGetBolaBitRate = getBooleanConfigValue("bola_shouldUseThroughputEstimateInGetBolaBitRate", bolaConfig.getShouldUseThroughputEstimateInGetBolaBitRate());
        this.bufferLowWatermarkSecondsForLowTransient = getFloatConfigValue("bola_bufferLowWatermarkSecondsForLowTransient", bolaConfig.getBufferLowWatermarkSecondsForLowTransient());
        this.reservedBandwidthForAudioBitsPerSecond = getFloatConfigValue("bola_reservedBandwidthForAudioBitsPerSecond", bolaConfig.getReservedBandwidthForAudioBitsPerSecond());
        this.shouldAdjustBufferLowWatermarkForLowTransient = getBooleanConfigValue("bola_shouldAdjustBufferLowWatermarkForLowTransient", bolaConfig.getShouldAdjustBufferLowWatermarkForLowTransient());
        this.shouldUseRealBufferForTimeoutCalculation = getBooleanConfigValue("bola_shouldUseRealBufferForTimeoutCalculation", bolaConfig.getShouldUseRealBufferForTimeoutCalculation());
        this.shouldSwitchHybridBolaOnTransientStates = getBooleanConfigValue("bola_shouldSwitchHybridBolaOnTransientStates", bolaConfig.getShouldSwitchHybridBolaOnTransientStates());
        this.scalerThroughputEstimateInGetBolaBitRateLowTransient = getFloatConfigValue("bola_scalerThroughputEstimateInGetBolaBitRateLowTransient", bolaConfig.getScalerThroughputEstimateInGetBolaBitRateLowTransient());
        this.audioSubtitleConcurrentDownloadRequests = getIntegerConfigValue("bola_audioSubtitleConcurrentDownloadRequests", bolaConfig.getAudioSubtitleConcurrentDownloadRequests());
        this.videoConcurrentDownloadRequests = getIntegerConfigValue("bola_videoConcurrentDownloadRequests", bolaConfig.getVideoConcurrentDownloadRequests());
        this.numberOfFutureFragmentsToCheck = getIntegerConfigValue("bola_numberOfFutureFragmentsToCheck", bolaConfig.getNumberOfFutureFragmentsToCheck());
        this.audioSubtitleDownloadTimeoutMillis = getLongConfigValue("bola_audioSubtitleDownloadTimeoutMillis", bolaConfig.getAudioSubtitleDownloadTimeoutMillis());
        this.minVideoDownloadTimeoutMillis = getLongConfigValue("bola_minVideoDownloadTimeoutMillis", bolaConfig.getMinVideoDownloadTimeoutMillis());
        this.maxVideoDownloadTimeoutMillis = getLongConfigValue("bola_maxVideoDownloadTimeoutMillis", bolaConfig.getMaxVideoDownloadTimeoutMillis());
        this.videoDownloadReservedTimeMillis = getLongConfigValue("bola_videoDownloadReservedTimeMillis", bolaConfig.getVideoDownloadReservedTimeMillis());
        this.videoDownloadTimeoutWhenNotPlayingMillis = getLongConfigValue("bola_videoDownloadTimeoutWhenNotPlayingMillis", bolaConfig.getVideoDownloadTimeoutWhenNotPlayingMillis());
        this.fragmentSizeOverhead = getFloatConfigValue("bola_fragmentSizeOverhead", bolaConfig.getFragmentSizeOverhead());
        this.shouldResetStateVariablesOnResume = getBooleanConfigValue("bola_shouldResetStateVariablesOnResume", bolaConfig.getShouldResetStateVariablesOnResume());
        String stringConfigValue2 = getStringConfigValue("bola_timeoutCalculationStrategy", bolaConfig.getTimeoutCalculationStrategy());
        Intrinsics.checkNotNullExpressionValue(stringConfigValue2, "getStringConfigValue(\"bo…meoutCalculationStrategy)");
        this.timeoutCalculationStrategy = stringConfigValue2;
        try {
            iOrdinal = TimeoutStrategy.valueOf(getTimeoutCalculationStrategy()).ordinal();
        } catch (IllegalArgumentException e) {
            DLog.exceptionf(e, "Not valid TimeoutStrategy mapping; fallback to default", new Object[0]);
            iOrdinal = TimeoutStrategy.BUFFERED_DURATION.ordinal();
        }
        this.validatedTimeoutCalculationStrategy = iOrdinal;
        String stringConfigValue3 = getStringConfigValue("bola_reviewDownloadProgressStrategy", bolaConfig.getReviewDownloadProgressStrategy());
        Intrinsics.checkNotNullExpressionValue(stringConfigValue3, "getStringConfigValue(\"bo…DownloadProgressStrategy)");
        this.reviewDownloadProgressStrategy = stringConfigValue3;
        try {
            iOrdinal2 = ReviewDownloadProgressStrategy.valueOf(getReviewDownloadProgressStrategy()).ordinal();
        } catch (IllegalArgumentException e2) {
            DLog.exceptionf(e2, "Not valid ReviewDownloadProgressStrategy mapping; fallback to default", new Object[0]);
            iOrdinal2 = ReviewDownloadProgressStrategy.EARLY_REVIEW_DOWNLOAD_PROGRESS.ordinal();
        }
        this.validatedReviewDownloadProgressStrategy = iOrdinal2;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public String getBolaAlgorithm() {
        return this.bolaAlgorithm;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getBolaAlgorithmIndex() {
        return this.bolaAlgorithmIndex;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getBufferScaler() {
        return this.bufferScaler;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getTradeOffFactor() {
        return this.tradeOffFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getBufferLowWatermarkSeconds() {
        return this.bufferLowWatermarkSeconds;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getBufferHighWatermarkSeconds() {
        return this.bufferHighWatermarkSeconds;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getTransientStateBufferThresholdSeconds() {
        return this.transientStateBufferThresholdSeconds;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getMinAbandonBitratePercent() {
        return this.minAbandonBitratePercent;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getAbandonGoodnessValueThreshold() {
        return this.abandonGoodnessValueThreshold;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getStartupBitratePercent() {
        return this.startupBitratePercent;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getRampUpGoodnessValueFactor() {
        return this.rampUpGoodnessValueFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getMaxRampUpQualityLevel() {
        return this.maxRampUpQualityLevel;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getTransientClipMaxDurationFactor() {
        return this.transientClipMaxDurationFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getTransientBufferDurationIncrementScaleFactor() {
        return this.transientBufferDurationIncrementScaleFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getLowTransientBufferDurationIncrementScaleFactor() {
        return this.lowTransientBufferDurationIncrementScaleFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getLowestBitrateScaleFactor() {
        return this.lowestBitrateScaleFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getScalerThroughputEstimateInRDP() {
        return this.scalerThroughputEstimateInRDP;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getMaxAvailableBufferDurationScaleFactorLow() {
        return this.maxAvailableBufferDurationScaleFactorLow;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getMaxAvailableBufferDurationScaleFactorHigh() {
        return this.maxAvailableBufferDurationScaleFactorHigh;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldCompensateMaxBufferForBufferingDelay() {
        return this.shouldCompensateMaxBufferForBufferingDelay;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldCompensateMaxBufferForStartupDelay() {
        return this.shouldCompensateMaxBufferForStartupDelay;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldCompensateForDownloaderStall() {
        return this.shouldCompensateForDownloaderStall;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getMaxNumCancellationsPerFragment() {
        return this.maxNumCancellationsPerFragment;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldAllowAggressiveRDPForLowBuffer() {
        return this.shouldAllowAggressiveRDPForLowBuffer;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldUseThroughputEstimateInRDP() {
        return this.shouldUseThroughputEstimateInRDP;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldAdjustBufferLowWatermarkForCVBR() {
        return this.shouldAdjustBufferLowWatermarkForCVBR;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getScalerThroughputEstimateInGetBolaBitRate() {
        return this.scalerThroughputEstimateInGetBolaBitRate;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldUseThroughputEstimateInGetBolaBitRate() {
        return this.shouldUseThroughputEstimateInGetBolaBitRate;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getBufferLowWatermarkSecondsForLowTransient() {
        return this.bufferLowWatermarkSecondsForLowTransient;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getReservedBandwidthForAudioBitsPerSecond() {
        return this.reservedBandwidthForAudioBitsPerSecond;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldAdjustBufferLowWatermarkForLowTransient() {
        return this.shouldAdjustBufferLowWatermarkForLowTransient;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldUseRealBufferForTimeoutCalculation() {
        return this.shouldUseRealBufferForTimeoutCalculation;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldSwitchHybridBolaOnTransientStates() {
        return this.shouldSwitchHybridBolaOnTransientStates;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getScalerThroughputEstimateInGetBolaBitRateLowTransient() {
        return this.scalerThroughputEstimateInGetBolaBitRateLowTransient;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getAudioSubtitleConcurrentDownloadRequests() {
        return this.audioSubtitleConcurrentDownloadRequests;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getVideoConcurrentDownloadRequests() {
        return this.videoConcurrentDownloadRequests;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getNumberOfFutureFragmentsToCheck() {
        return this.numberOfFutureFragmentsToCheck;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public long getAudioSubtitleDownloadTimeoutMillis() {
        return this.audioSubtitleDownloadTimeoutMillis;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public long getMinVideoDownloadTimeoutMillis() {
        return this.minVideoDownloadTimeoutMillis;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public long getMaxVideoDownloadTimeoutMillis() {
        return this.maxVideoDownloadTimeoutMillis;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public long getVideoDownloadReservedTimeMillis() {
        return this.videoDownloadReservedTimeMillis;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public long getVideoDownloadTimeoutWhenNotPlayingMillis() {
        return this.videoDownloadTimeoutWhenNotPlayingMillis;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getFragmentSizeOverhead() {
        return this.fragmentSizeOverhead;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldResetStateVariablesOnResume() {
        return this.shouldResetStateVariablesOnResume;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public String getTimeoutCalculationStrategy() {
        return this.timeoutCalculationStrategy;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getValidatedTimeoutCalculationStrategy() {
        return this.validatedTimeoutCalculationStrategy;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public String getReviewDownloadProgressStrategy() {
        return this.reviewDownloadProgressStrategy;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getValidatedReviewDownloadProgressStrategy() {
        return this.validatedReviewDownloadProgressStrategy;
    }
}
