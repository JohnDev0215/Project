package com.amazon.avod.dash.config.bola;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.content.dash.quality.heuristic.ReviewDownloadProgressStrategy;
import com.amazon.avod.content.dash.quality.heuristic.TimeoutStrategy;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.util.DLog;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BolaConfig.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b/\n\u0002\u0010\u000b\n\u0002\b-\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010R\u0014\u0010\u001b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0007R\u0014\u0010\u001d\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0007R\u0014\u0010\u001f\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0007R\u0014\u0010!\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0007R\u0014\u0010#\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0007R\u0014\u0010%\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0007R\u0014\u0010'\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0007R\u0014\u0010)\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0007R\u0014\u0010+\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0007R\u0014\u0010-\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0010R\u0014\u0010/\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0010R\u0014\u00101\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0014R\u0014\u00103\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0007R\u0014\u00105\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0014R\u0014\u00107\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0010R\u0014\u00109\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0007R\u0014\u0010;\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0007R\u0014\u0010=\u001a\u00020\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0018R\u0014\u0010?\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0007R\u0014\u0010A\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0007R\u0014\u0010C\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0007R\u0014\u0010E\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0014\u0010I\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010HR\u0014\u0010K\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010HR\u0014\u0010M\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010HR\u0014\u0010O\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010HR\u0014\u0010Q\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010HR\u0014\u0010S\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010HR\u0014\u0010U\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010HR\u0014\u0010W\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010HR\u0014\u0010Y\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010HR\u0014\u0010[\u001a\u00020FX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010HR\u0014\u0010]\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0007R\u0014\u0010_\u001a\u00020\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u0018R\u0014\u0010a\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0007R\u0014\u0010c\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0007R\u0014\u0010e\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0007R\u0014\u0010g\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\u0007R\u0014\u0010i\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0010R\u0014\u0010k\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\u0010R\u0014\u0010m\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0010R\u0014\u0010o\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bp\u0010\u0014R\u0014\u0010q\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010\u0014¨\u0006s"}, d2 = {"Lcom/amazon/avod/dash/config/bola/BolaConfig;", "Lcom/amazon/avod/media/framework/config/MediaConfigBase;", "Lcom/amazon/avod/dash/config/bola/BolaConfigInterface;", "()V", "abandonGoodnessValueThreshold", "", "getAbandonGoodnessValueThreshold", "()F", "algorithm", "Lamazon/android/config/ConfigurationValue;", "Lcom/amazon/avod/dash/config/bola/BolaAlgorithm;", "getAlgorithm", "()Lamazon/android/config/ConfigurationValue;", "audioSubtitleConcurrentDownloadRequests", "", "getAudioSubtitleConcurrentDownloadRequests", "()I", "audioSubtitleDownloadTimeoutMillis", "", "getAudioSubtitleDownloadTimeoutMillis", "()J", "bolaAlgorithm", "", "getBolaAlgorithm", "()Ljava/lang/String;", "bolaAlgorithmIndex", "getBolaAlgorithmIndex", "bufferHighWatermarkSeconds", "getBufferHighWatermarkSeconds", "bufferLowWatermarkSeconds", "getBufferLowWatermarkSeconds", "bufferLowWatermarkSecondsForLowTransient", "getBufferLowWatermarkSecondsForLowTransient", "bufferScaler", "getBufferScaler", "fragmentSizeOverhead", "getFragmentSizeOverhead", "lowTransientBufferDurationIncrementScaleFactor", "getLowTransientBufferDurationIncrementScaleFactor", "lowestBitrateScaleFactor", "getLowestBitrateScaleFactor", "maxAvailableBufferDurationScaleFactorHigh", "getMaxAvailableBufferDurationScaleFactorHigh", "maxAvailableBufferDurationScaleFactorLow", "getMaxAvailableBufferDurationScaleFactorLow", "maxNumCancellationsPerFragment", "getMaxNumCancellationsPerFragment", "maxRampUpQualityLevel", "getMaxRampUpQualityLevel", "maxVideoDownloadTimeoutMillis", "getMaxVideoDownloadTimeoutMillis", "minAbandonBitratePercent", "getMinAbandonBitratePercent", "minVideoDownloadTimeoutMillis", "getMinVideoDownloadTimeoutMillis", "numberOfFutureFragmentsToCheck", "getNumberOfFutureFragmentsToCheck", "rampUpGoodnessValueFactor", "getRampUpGoodnessValueFactor", "reservedBandwidthForAudioBitsPerSecond", "getReservedBandwidthForAudioBitsPerSecond", "reviewDownloadProgressStrategy", "getReviewDownloadProgressStrategy", "scalerThroughputEstimateInGetBolaBitRate", "getScalerThroughputEstimateInGetBolaBitRate", "scalerThroughputEstimateInGetBolaBitRateLowTransient", "getScalerThroughputEstimateInGetBolaBitRateLowTransient", "scalerThroughputEstimateInRDP", "getScalerThroughputEstimateInRDP", "shouldAdjustBufferLowWatermarkForCVBR", "", "getShouldAdjustBufferLowWatermarkForCVBR", "()Z", "shouldAdjustBufferLowWatermarkForLowTransient", "getShouldAdjustBufferLowWatermarkForLowTransient", "shouldAllowAggressiveRDPForLowBuffer", "getShouldAllowAggressiveRDPForLowBuffer", "shouldCompensateForDownloaderStall", "getShouldCompensateForDownloaderStall", "shouldCompensateMaxBufferForBufferingDelay", "getShouldCompensateMaxBufferForBufferingDelay", "shouldCompensateMaxBufferForStartupDelay", "getShouldCompensateMaxBufferForStartupDelay", "shouldResetStateVariablesOnResume", "getShouldResetStateVariablesOnResume", "shouldSwitchHybridBolaOnTransientStates", "getShouldSwitchHybridBolaOnTransientStates", "shouldUseRealBufferForTimeoutCalculation", "getShouldUseRealBufferForTimeoutCalculation", "shouldUseThroughputEstimateInGetBolaBitRate", "getShouldUseThroughputEstimateInGetBolaBitRate", "shouldUseThroughputEstimateInRDP", "getShouldUseThroughputEstimateInRDP", "startupBitratePercent", "getStartupBitratePercent", "timeoutCalculationStrategy", "getTimeoutCalculationStrategy", "tradeOffFactor", "getTradeOffFactor", "transientBufferDurationIncrementScaleFactor", "getTransientBufferDurationIncrementScaleFactor", "transientClipMaxDurationFactor", "getTransientClipMaxDurationFactor", "transientStateBufferThresholdSeconds", "getTransientStateBufferThresholdSeconds", "validatedReviewDownloadProgressStrategy", "getValidatedReviewDownloadProgressStrategy", "validatedTimeoutCalculationStrategy", "getValidatedTimeoutCalculationStrategy", "videoConcurrentDownloadRequests", "getVideoConcurrentDownloadRequests", "videoDownloadReservedTimeMillis", "getVideoDownloadReservedTimeMillis", "videoDownloadTimeoutWhenNotPlayingMillis", "getVideoDownloadTimeoutWhenNotPlayingMillis", "ATVPlaybackDash_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BolaConfig extends MediaConfigBase implements BolaConfigInterface {
    public static final BolaConfig INSTANCE;
    private static final float abandonGoodnessValueThreshold;
    private static final ConfigurationValue<BolaAlgorithm> algorithm;
    private static final int audioSubtitleConcurrentDownloadRequests;
    private static final long audioSubtitleDownloadTimeoutMillis;
    private static final String bolaAlgorithm;
    private static final int bolaAlgorithmIndex;
    private static final float bufferHighWatermarkSeconds;
    private static final float bufferLowWatermarkSeconds;
    private static final float bufferLowWatermarkSecondsForLowTransient;
    private static final float bufferScaler;
    private static final float fragmentSizeOverhead;
    private static final float lowTransientBufferDurationIncrementScaleFactor;
    private static final float lowestBitrateScaleFactor;
    private static final float maxAvailableBufferDurationScaleFactorHigh;
    private static final float maxAvailableBufferDurationScaleFactorLow;
    private static final int maxNumCancellationsPerFragment;
    private static final int maxRampUpQualityLevel;
    private static final long maxVideoDownloadTimeoutMillis;
    private static final float minAbandonBitratePercent;
    private static final long minVideoDownloadTimeoutMillis;
    private static final int numberOfFutureFragmentsToCheck;
    private static final float rampUpGoodnessValueFactor;
    private static final float reservedBandwidthForAudioBitsPerSecond;
    private static final String reviewDownloadProgressStrategy;
    private static final float scalerThroughputEstimateInGetBolaBitRate;
    private static final float scalerThroughputEstimateInGetBolaBitRateLowTransient;
    private static final float scalerThroughputEstimateInRDP;
    private static final boolean shouldAdjustBufferLowWatermarkForCVBR;
    private static final boolean shouldAdjustBufferLowWatermarkForLowTransient;
    private static final boolean shouldAllowAggressiveRDPForLowBuffer;
    private static final boolean shouldCompensateForDownloaderStall;
    private static final boolean shouldCompensateMaxBufferForBufferingDelay;
    private static final boolean shouldCompensateMaxBufferForStartupDelay;
    private static final boolean shouldResetStateVariablesOnResume;
    private static final boolean shouldSwitchHybridBolaOnTransientStates;
    private static final boolean shouldUseRealBufferForTimeoutCalculation;
    private static final boolean shouldUseThroughputEstimateInGetBolaBitRate;
    private static final boolean shouldUseThroughputEstimateInRDP;
    private static final float startupBitratePercent;
    private static final String timeoutCalculationStrategy;
    private static final float tradeOffFactor;
    private static final float transientBufferDurationIncrementScaleFactor;
    private static final float transientClipMaxDurationFactor;
    private static final float transientStateBufferThresholdSeconds;
    private static final int validatedReviewDownloadProgressStrategy;
    private static final int validatedTimeoutCalculationStrategy;
    private static final int videoConcurrentDownloadRequests;
    private static final long videoDownloadReservedTimeMillis;
    private static final long videoDownloadTimeoutWhenNotPlayingMillis;

    private BolaConfig() {
    }

    public final ConfigurationValue<BolaAlgorithm> getAlgorithm() {
        return algorithm;
    }

    static {
        int iOrdinal;
        int iOrdinal2;
        BolaConfig bolaConfig = new BolaConfig();
        INSTANCE = bolaConfig;
        ConfigurationValue<BolaAlgorithm> configurationValueNewEnumConfigValue = bolaConfig.newEnumConfigValue("playback_bolaAlgorithm", BolaAlgorithm.DYNAMIC_TRADE_OFF_FACTOR, BolaAlgorithm.class);
        Intrinsics.checkNotNullExpressionValue(configurationValueNewEnumConfigValue, "newEnumConfigValue(\"play…olaAlgorithm::class.java)");
        algorithm = configurationValueNewEnumConfigValue;
        bolaAlgorithm = configurationValueNewEnumConfigValue.getValue().name();
        bolaAlgorithmIndex = configurationValueNewEnumConfigValue.getValue().ordinal();
        Float value = bolaConfig.newFloatConfigValue("playback_bolaBufferScaler", 0.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value, "newFloatConfigValue(\n   …ufferScaler\", 0.0f).value");
        bufferScaler = value.floatValue();
        Float value2 = bolaConfig.newFloatConfigValue("playback_bolaTradeOffFactor", 10.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value2, "newFloatConfigValue(\n   …eOffFactor\", 10.0f).value");
        tradeOffFactor = value2.floatValue();
        Float value3 = bolaConfig.newFloatConfigValue("playback_bolaBufferLowWatermarkSeconds", 6.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value3, "newFloatConfigValue(\n   …markSeconds\", 6.0f).value");
        bufferLowWatermarkSeconds = value3.floatValue();
        Float value4 = bolaConfig.newFloatConfigValue("playback_bolaBufferHighWatermarkSeconds", 20.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value4, "newFloatConfigValue(\n   …arkSeconds\", 20.0f).value");
        bufferHighWatermarkSeconds = value4.floatValue();
        Float value5 = bolaConfig.newFloatConfigValue("playback_bolaTransientStateBufferThresholdSeconds", 4.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value5, "newFloatConfigValue(\n   …holdSeconds\", 4.0f).value");
        transientStateBufferThresholdSeconds = value5.floatValue();
        Float value6 = bolaConfig.newFloatConfigValue("playback_minAbandonBitratePercent", 0.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value6, "newFloatConfigValue(\n   …itratePercent\", 0f).value");
        minAbandonBitratePercent = value6.floatValue();
        Float value7 = bolaConfig.newFloatConfigValue("playback_bolaAbandonGoodnessValueThreshold", 0.5f).getValue();
        Intrinsics.checkNotNullExpressionValue(value7, "newFloatConfigValue(\n   …ueThreshold\", 0.5f).value");
        abandonGoodnessValueThreshold = value7.floatValue();
        Float value8 = bolaConfig.newFloatConfigValue("playback_bolaStartupBitratePercent", 0.5f).getValue();
        Intrinsics.checkNotNullExpressionValue(value8, "newFloatConfigValue(\n   …ratePercent\", 0.5f).value");
        startupBitratePercent = value8.floatValue();
        Float value9 = bolaConfig.newFloatConfigValue("playback_bolaRampUpGoodnessValueFactor", 0.95f).getValue();
        Intrinsics.checkNotNullExpressionValue(value9, "newFloatConfigValue(\n   …alueFactor\", 0.95f).value");
        rampUpGoodnessValueFactor = value9.floatValue();
        Integer value10 = bolaConfig.newIntConfigValue("playback_bolaMaxRampUpQualityLevel", 2).getValue();
        Intrinsics.checkNotNullExpressionValue(value10, "newIntConfigValue(\"playb…UpQualityLevel\", 2).value");
        maxRampUpQualityLevel = value10.intValue();
        Float value11 = bolaConfig.newFloatConfigValue("playback_bolaTransientClipMaxDurationFactor", 0.99f).getValue();
        Intrinsics.checkNotNullExpressionValue(value11, "newFloatConfigValue(\n   …tionFactor\", 0.99f).value");
        transientClipMaxDurationFactor = value11.floatValue();
        Float value12 = bolaConfig.newFloatConfigValue("playback_bolaTransientBufferDurationIncrementScaleFactor", 0.9f).getValue();
        Intrinsics.checkNotNullExpressionValue(value12, "newFloatConfigValue(\n   …ScaleFactor\", 0.9f).value");
        transientBufferDurationIncrementScaleFactor = value12.floatValue();
        Float value13 = bolaConfig.newFloatConfigValue("playback_bolaLowTransientBufferDurationIncrementScaleFactor", 0.7f).getValue();
        Intrinsics.checkNotNullExpressionValue(value13, "newFloatConfigValue(\n   …ScaleFactor\", 0.7f).value");
        lowTransientBufferDurationIncrementScaleFactor = value13.floatValue();
        Float value14 = bolaConfig.newFloatConfigValue("playback_bolaLowestBitrateScaleFactor", 3.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value14, "newFloatConfigValue(\n   …ScaleFactor\", 3.0f).value");
        lowestBitrateScaleFactor = value14.floatValue();
        Float value15 = bolaConfig.newFloatConfigValue("playback_bolaScalerThroughputEstimateInRDP", 0.5f).getValue();
        Intrinsics.checkNotNullExpressionValue(value15, "newFloatConfigValue(\n   …timateInRDP\", 0.5f).value");
        scalerThroughputEstimateInRDP = value15.floatValue();
        Float value16 = bolaConfig.newFloatConfigValue("playback_bolaMaxAvailableBufferDurationScaleFactorLow", 1.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value16, "newFloatConfigValue(\n   …leFactorLow\", 1.0f).value");
        maxAvailableBufferDurationScaleFactorLow = value16.floatValue();
        Float value17 = bolaConfig.newFloatConfigValue("playback_bolaMaxAvailableBufferDurationScaleFactorHigh", 1.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value17, "newFloatConfigValue(\n   …eFactorHigh\", 1.0f).value");
        maxAvailableBufferDurationScaleFactorHigh = value17.floatValue();
        Boolean value18 = bolaConfig.newBooleanConfigValue("playback_bolaShouldCompensateMaxBufferForBufferingDelay", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value18, "newBooleanConfigValue(\n …eringDelay\", false).value");
        shouldCompensateMaxBufferForBufferingDelay = value18.booleanValue();
        Boolean value19 = bolaConfig.newBooleanConfigValue("playback_bolaShouldCompensateMaxBufferForStartupDelay", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value19, "newBooleanConfigValue(\n …artupDelay\", false).value");
        shouldCompensateMaxBufferForStartupDelay = value19.booleanValue();
        Boolean value20 = bolaConfig.newBooleanConfigValue("playback_bolaShouldCompensateForDownloaderStall", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value20, "newBooleanConfigValue(\n …oaderStall\", false).value");
        shouldCompensateForDownloaderStall = value20.booleanValue();
        Integer value21 = bolaConfig.newIntConfigValue("playback_bolaMaxNumCancellationsPerFragment", 1).getValue();
        Intrinsics.checkNotNullExpressionValue(value21, "newIntConfigValue(\n     …onsPerFragment\", 1).value");
        maxNumCancellationsPerFragment = value21.intValue();
        Boolean value22 = bolaConfig.newBooleanConfigValue("playback_bolaShouldAllowAggressiveRDPForLowBuffer", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value22, "newBooleanConfigValue(\n …rLowBuffer\", false).value");
        shouldAllowAggressiveRDPForLowBuffer = value22.booleanValue();
        Boolean value23 = bolaConfig.newBooleanConfigValue("playback_bolaShouldUseThroughputEstimateInRDP", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value23, "newBooleanConfigValue(\n …imateInRDP\", false).value");
        shouldUseThroughputEstimateInRDP = value23.booleanValue();
        Boolean value24 = bolaConfig.newBooleanConfigValue("playback_bolaShouldAdjustBufferLowWatermarkForCVBR", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value24, "newBooleanConfigValue(\n …arkForCVBR\", false).value");
        shouldAdjustBufferLowWatermarkForCVBR = value24.booleanValue();
        Float value25 = bolaConfig.newFloatConfigValue("playback_bolaScalerThroughputEstimateInGetBolaBitRate", 0.9f).getValue();
        Intrinsics.checkNotNullExpressionValue(value25, "newFloatConfigValue(\n   …BolaBitRate\", 0.9f).value");
        scalerThroughputEstimateInGetBolaBitRate = value25.floatValue();
        Boolean value26 = bolaConfig.newBooleanConfigValue("playback_bolaShouldUseThroughputEstimateInGetBolaBitRate", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value26, "newBooleanConfigValue(\n …olaBitRate\", false).value");
        shouldUseThroughputEstimateInGetBolaBitRate = value26.booleanValue();
        Float value27 = bolaConfig.newFloatConfigValue("playback_bolaBufferLowWatermarkSecondsForLowTransient", 6.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value27, "newFloatConfigValue(\n   …owTransient\", 6.0f).value");
        bufferLowWatermarkSecondsForLowTransient = value27.floatValue();
        Boolean value28 = bolaConfig.newBooleanConfigValue("playback_bolaShouldAdjustBufferLowWatermarkForLowTransient", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value28, "newBooleanConfigValue(\n …wTransient\", false).value");
        shouldAdjustBufferLowWatermarkForLowTransient = value28.booleanValue();
        Boolean value29 = bolaConfig.newBooleanConfigValue("playback_bolaShouldUseRealBufferForTimeoutCalculation", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value29, "newBooleanConfigValue(\n …alculation\", false).value");
        shouldUseRealBufferForTimeoutCalculation = value29.booleanValue();
        Float value30 = bolaConfig.newFloatConfigValue("playback_bolaReservedBandwidthForAudioBitsPerSecond", 0.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value30, "newFloatConfigValue(\n   …tsPerSecond\", 0.0f).value");
        reservedBandwidthForAudioBitsPerSecond = value30.floatValue();
        Boolean value31 = bolaConfig.newBooleanConfigValue("playback_bolaShouldSwitchHybridBolaOnTransientStates", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value31, "newBooleanConfigValue(\n …ientStates\", false).value");
        shouldSwitchHybridBolaOnTransientStates = value31.booleanValue();
        Float value32 = bolaConfig.newFloatConfigValue("playback_bolaScalerThroughputEstimateInGetBolaBitRateLowTransient", 0.9f).getValue();
        Intrinsics.checkNotNullExpressionValue(value32, "newFloatConfigValue(\n   …owTransient\", 0.9f).value");
        scalerThroughputEstimateInGetBolaBitRateLowTransient = value32.floatValue();
        Integer value33 = bolaConfig.newIntConfigValue("playback_bolaAudioSubtitleConcurrentDownloadRequests", 1).getValue();
        Intrinsics.checkNotNullExpressionValue(value33, "newIntConfigValue(\n     …wnloadRequests\", 1).value");
        audioSubtitleConcurrentDownloadRequests = value33.intValue();
        Integer value34 = bolaConfig.newIntConfigValue("playback_bolaVideoConcurrentDownloadRequests", 1).getValue();
        Intrinsics.checkNotNullExpressionValue(value34, "newIntConfigValue(\n     …wnloadRequests\", 1).value");
        videoConcurrentDownloadRequests = value34.intValue();
        Integer value35 = bolaConfig.newIntConfigValue("playback_bolaNumberOfFutureFragmentsToCheck", 1).getValue();
        Intrinsics.checkNotNullExpressionValue(value35, "newIntConfigValue(\n     …agmentsToCheck\", 1).value");
        numberOfFutureFragmentsToCheck = value35.intValue();
        TimeSpan timeSpanFromMilliseconds = TimeSpan.fromMilliseconds(3000L);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        audioSubtitleDownloadTimeoutMillis = bolaConfig.newTimeConfigurationValue("playback_bolaAudioSubtitleDownloadTimeoutMillis", timeSpanFromMilliseconds, timeUnit).getValue().getTotalMilliseconds();
        minVideoDownloadTimeoutMillis = bolaConfig.newTimeConfigurationValue("playback_bolaMinVideoDownloadTimeoutMillis", TimeSpan.fromMilliseconds(2500L), timeUnit).getValue().getTotalMilliseconds();
        maxVideoDownloadTimeoutMillis = bolaConfig.newTimeConfigurationValue("playback_bolaMaxVideoDownloadTimeoutMillis", TimeSpan.fromMilliseconds(7000L), timeUnit).getValue().getTotalMilliseconds();
        videoDownloadReservedTimeMillis = bolaConfig.newTimeConfigurationValue("playback_bolaVideoDownloadReservedTimeMillis", TimeSpan.fromMilliseconds(2000L), timeUnit).getValue().getTotalMilliseconds();
        videoDownloadTimeoutWhenNotPlayingMillis = bolaConfig.newTimeConfigurationValue("playback_bolaVideoDownloadTimeoutWhenNotPlayingMillis", TimeSpan.fromMilliseconds(6000L), timeUnit).getValue().getTotalMilliseconds();
        Float value36 = bolaConfig.newFloatConfigValue("playback_bolaFragmentSizeOverhead", 1.3f).getValue();
        Intrinsics.checkNotNullExpressionValue(value36, "newFloatConfigValue(\n   …izeOverhead\", 1.3f).value");
        fragmentSizeOverhead = value36.floatValue();
        Boolean value37 = bolaConfig.newBooleanConfigValue("playback_shouldResetStateVariablesOnResume", true).getValue();
        Intrinsics.checkNotNullExpressionValue(value37, "newBooleanConfigValue(\n …lesOnResume\", true).value");
        shouldResetStateVariablesOnResume = value37.booleanValue();
        timeoutCalculationStrategy = ((TimeoutStrategy) bolaConfig.newEnumConfigValue("playback_bolaTimeoutCalculationStrategy", TimeoutStrategy.BUFFERED_DURATION, TimeoutStrategy.class).getValue()).name();
        try {
            iOrdinal = TimeoutStrategy.valueOf(bolaConfig.getTimeoutCalculationStrategy()).ordinal();
        } catch (IllegalArgumentException e) {
            DLog.exceptionf(e, "Not valid TimeoutStrategy mapping; fallback to default", new Object[0]);
            iOrdinal = TimeoutStrategy.BUFFERED_DURATION.ordinal();
        }
        validatedTimeoutCalculationStrategy = iOrdinal;
        BolaConfig bolaConfig2 = INSTANCE;
        reviewDownloadProgressStrategy = ((ReviewDownloadProgressStrategy) bolaConfig2.newEnumConfigValue("playback_BolaReviewDownloadProgressStrategy", ReviewDownloadProgressStrategy.EARLY_REVIEW_DOWNLOAD_PROGRESS, ReviewDownloadProgressStrategy.class).getValue()).name();
        try {
            iOrdinal2 = ReviewDownloadProgressStrategy.valueOf(bolaConfig2.getReviewDownloadProgressStrategy()).ordinal();
        } catch (IllegalArgumentException e2) {
            DLog.exceptionf(e2, "Not valid ReviewDownloadProgressStrategy mapping; fallback to default", new Object[0]);
            iOrdinal2 = ReviewDownloadProgressStrategy.EARLY_REVIEW_DOWNLOAD_PROGRESS.ordinal();
        }
        validatedReviewDownloadProgressStrategy = iOrdinal2;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public String getBolaAlgorithm() {
        return bolaAlgorithm;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getBolaAlgorithmIndex() {
        return bolaAlgorithmIndex;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getBufferScaler() {
        return bufferScaler;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getTradeOffFactor() {
        return tradeOffFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getBufferLowWatermarkSeconds() {
        return bufferLowWatermarkSeconds;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getBufferHighWatermarkSeconds() {
        return bufferHighWatermarkSeconds;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getTransientStateBufferThresholdSeconds() {
        return transientStateBufferThresholdSeconds;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getMinAbandonBitratePercent() {
        return minAbandonBitratePercent;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getAbandonGoodnessValueThreshold() {
        return abandonGoodnessValueThreshold;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getStartupBitratePercent() {
        return startupBitratePercent;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getRampUpGoodnessValueFactor() {
        return rampUpGoodnessValueFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getMaxRampUpQualityLevel() {
        return maxRampUpQualityLevel;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getTransientClipMaxDurationFactor() {
        return transientClipMaxDurationFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getTransientBufferDurationIncrementScaleFactor() {
        return transientBufferDurationIncrementScaleFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getLowTransientBufferDurationIncrementScaleFactor() {
        return lowTransientBufferDurationIncrementScaleFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getLowestBitrateScaleFactor() {
        return lowestBitrateScaleFactor;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getScalerThroughputEstimateInRDP() {
        return scalerThroughputEstimateInRDP;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getMaxAvailableBufferDurationScaleFactorLow() {
        return maxAvailableBufferDurationScaleFactorLow;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getMaxAvailableBufferDurationScaleFactorHigh() {
        return maxAvailableBufferDurationScaleFactorHigh;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldCompensateMaxBufferForBufferingDelay() {
        return shouldCompensateMaxBufferForBufferingDelay;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldCompensateMaxBufferForStartupDelay() {
        return shouldCompensateMaxBufferForStartupDelay;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldCompensateForDownloaderStall() {
        return shouldCompensateForDownloaderStall;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getMaxNumCancellationsPerFragment() {
        return maxNumCancellationsPerFragment;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldAllowAggressiveRDPForLowBuffer() {
        return shouldAllowAggressiveRDPForLowBuffer;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldUseThroughputEstimateInRDP() {
        return shouldUseThroughputEstimateInRDP;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldAdjustBufferLowWatermarkForCVBR() {
        return shouldAdjustBufferLowWatermarkForCVBR;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getScalerThroughputEstimateInGetBolaBitRate() {
        return scalerThroughputEstimateInGetBolaBitRate;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldUseThroughputEstimateInGetBolaBitRate() {
        return shouldUseThroughputEstimateInGetBolaBitRate;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getBufferLowWatermarkSecondsForLowTransient() {
        return bufferLowWatermarkSecondsForLowTransient;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldAdjustBufferLowWatermarkForLowTransient() {
        return shouldAdjustBufferLowWatermarkForLowTransient;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldUseRealBufferForTimeoutCalculation() {
        return shouldUseRealBufferForTimeoutCalculation;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getReservedBandwidthForAudioBitsPerSecond() {
        return reservedBandwidthForAudioBitsPerSecond;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldSwitchHybridBolaOnTransientStates() {
        return shouldSwitchHybridBolaOnTransientStates;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getScalerThroughputEstimateInGetBolaBitRateLowTransient() {
        return scalerThroughputEstimateInGetBolaBitRateLowTransient;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getAudioSubtitleConcurrentDownloadRequests() {
        return audioSubtitleConcurrentDownloadRequests;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getVideoConcurrentDownloadRequests() {
        return videoConcurrentDownloadRequests;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getNumberOfFutureFragmentsToCheck() {
        return numberOfFutureFragmentsToCheck;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public long getAudioSubtitleDownloadTimeoutMillis() {
        return audioSubtitleDownloadTimeoutMillis;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public long getMinVideoDownloadTimeoutMillis() {
        return minVideoDownloadTimeoutMillis;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public long getMaxVideoDownloadTimeoutMillis() {
        return maxVideoDownloadTimeoutMillis;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public long getVideoDownloadReservedTimeMillis() {
        return videoDownloadReservedTimeMillis;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public long getVideoDownloadTimeoutWhenNotPlayingMillis() {
        return videoDownloadTimeoutWhenNotPlayingMillis;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public float getFragmentSizeOverhead() {
        return fragmentSizeOverhead;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public boolean getShouldResetStateVariablesOnResume() {
        return shouldResetStateVariablesOnResume;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public String getTimeoutCalculationStrategy() {
        return timeoutCalculationStrategy;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getValidatedTimeoutCalculationStrategy() {
        return validatedTimeoutCalculationStrategy;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public String getReviewDownloadProgressStrategy() {
        return reviewDownloadProgressStrategy;
    }

    @Override // com.amazon.avod.dash.config.bola.BolaConfigInterface
    public int getValidatedReviewDownloadProgressStrategy() {
        return validatedReviewDownloadProgressStrategy;
    }
}
