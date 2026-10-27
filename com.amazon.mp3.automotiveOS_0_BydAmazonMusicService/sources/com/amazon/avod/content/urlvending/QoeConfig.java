package com.amazon.avod.content.urlvending;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QoeConfig.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0007R\u0014\u0010\u0015\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0007R\u0014\u0010\u0017\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0007R\u0014\u0010\u0019\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0007R\u0014\u0010\u001b\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0007R\u0014\u0010!\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0014\u0010#\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001eR\u0014\u0010%\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0014\u0010'\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001eR\u0014\u0010)\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001eR\u0014\u0010+\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001eR\u0014\u0010-\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001eR\u0014\u0010/\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001eR\u0014\u00101\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001eR\u0014\u00103\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001eR\u0014\u00105\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001eR\u0014\u00107\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u001eR\u0014\u00109\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u001eR\u0014\u0010;\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u001eR\u0014\u0010=\u001a\u00020>X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0010R\u0014\u0010C\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0007R\u0014\u0010E\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0007R\u0014\u0010G\u001a\u00020HX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010JR\u0014\u0010K\u001a\u00020HX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010J¨\u0006M"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeConfig;", "Lcom/amazon/avod/media/framework/config/MediaConfigBase;", "Lcom/amazon/avod/content/urlvending/QoeConfigInterface;", "()V", "bandwidthChangeWeight", "", "getBandwidthChangeWeight", "()F", "bandwidthWeight", "getBandwidthWeight", "bitrateWeight", "getBitrateWeight", "frontBufferSizeWeight", "getFrontBufferSizeWeight", "isQoeCdnSwitchingEnabled", "", "()Z", "isQoeEvaluatorEnabledForLive", "isQoeEvaluatorEnabledForVOD", "latencyChangeWeight", "getLatencyChangeWeight", "latencyWeight", "getLatencyWeight", "manifestLatencyChangeWeight", "getManifestLatencyChangeWeight", "manifestLatencyWeight", "getManifestLatencyWeight", "maxNumOfCdnSwitchesCausedByQoeWithinWindow", "", "getMaxNumOfCdnSwitchesCausedByQoeWithinWindow", "()I", "numOfBufferingEventWeight", "getNumOfBufferingEventWeight", "numOfSamplesBeforeSwitching", "getNumOfSamplesBeforeSwitching", "numOfSamplesForAvgBandwidth", "getNumOfSamplesForAvgBandwidth", "numOfSamplesForAvgLatency", "getNumOfSamplesForAvgLatency", "numOfSamplesForAvgQoe", "getNumOfSamplesForAvgQoe", "numOfSamplesForBandwidthChangeSmoothing", "getNumOfSamplesForBandwidthChangeSmoothing", "numOfSamplesForBandwidthSmoothing", "getNumOfSamplesForBandwidthSmoothing", "numOfSamplesForBitrateSmoothing", "getNumOfSamplesForBitrateSmoothing", "numOfSamplesForFrontBufferSizeSmoothing", "getNumOfSamplesForFrontBufferSizeSmoothing", "numOfSamplesForLatencyChangeSmoothing", "getNumOfSamplesForLatencyChangeSmoothing", "numOfSamplesForLatencySmoothing", "getNumOfSamplesForLatencySmoothing", "numOfSamplesForManifestAvgLatency", "getNumOfSamplesForManifestAvgLatency", "numOfSamplesForManifestLatencyChangeSmoothing", "getNumOfSamplesForManifestLatencyChangeSmoothing", "numOfSamplesForManifestLatencySmoothing", "getNumOfSamplesForManifestLatencySmoothing", "numOfSamplesForQoeChangeSmoothing", "getNumOfSamplesForQoeChangeSmoothing", "qoeModelType", "Lcom/amazon/avod/content/urlvending/QoeModelType;", "getQoeModelType", "()Lcom/amazon/avod/content/urlvending/QoeModelType;", "shouldReportQoeDiagnosticEvent", "getShouldReportQoeDiagnosticEvent", "thresholdForCdnSwitching", "getThresholdForCdnSwitching", "timeSpentBufferingWeight", "getTimeSpentBufferingWeight", "windowLengthForTrackingBufferingEvent", "Lcom/amazon/avod/media/TimeSpan;", "getWindowLengthForTrackingBufferingEvent", "()Lcom/amazon/avod/media/TimeSpan;", "windowLengthForTrackingCdnSwitchesCausedByQoe", "getWindowLengthForTrackingCdnSwitchesCausedByQoe", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class QoeConfig extends MediaConfigBase implements QoeConfigInterface {
    public static final QoeConfig INSTANCE;
    private static final float bandwidthChangeWeight;
    private static final float bandwidthWeight;
    private static final float bitrateWeight;
    private static final float frontBufferSizeWeight;
    private static final boolean isQoeCdnSwitchingEnabled;
    private static final boolean isQoeEvaluatorEnabledForLive;
    private static final boolean isQoeEvaluatorEnabledForVOD;
    private static final float latencyChangeWeight;
    private static final float latencyWeight;
    private static final float manifestLatencyChangeWeight;
    private static final float manifestLatencyWeight;
    private static final int maxNumOfCdnSwitchesCausedByQoeWithinWindow;
    private static final float numOfBufferingEventWeight;
    private static final int numOfSamplesBeforeSwitching;
    private static final int numOfSamplesForAvgBandwidth;
    private static final int numOfSamplesForAvgLatency;
    private static final int numOfSamplesForAvgQoe;
    private static final int numOfSamplesForBandwidthChangeSmoothing;
    private static final int numOfSamplesForBandwidthSmoothing;
    private static final int numOfSamplesForBitrateSmoothing;
    private static final int numOfSamplesForFrontBufferSizeSmoothing;
    private static final int numOfSamplesForLatencyChangeSmoothing;
    private static final int numOfSamplesForLatencySmoothing;
    private static final int numOfSamplesForManifestAvgLatency;
    private static final int numOfSamplesForManifestLatencyChangeSmoothing;
    private static final int numOfSamplesForManifestLatencySmoothing;
    private static final int numOfSamplesForQoeChangeSmoothing;
    private static final QoeModelType qoeModelType;
    private static final boolean shouldReportQoeDiagnosticEvent;
    private static final float thresholdForCdnSwitching;
    private static final float timeSpentBufferingWeight;
    private static final TimeSpan windowLengthForTrackingBufferingEvent;
    private static final TimeSpan windowLengthForTrackingCdnSwitchesCausedByQoe;

    private QoeConfig() {
    }

    static {
        QoeConfig qoeConfig = new QoeConfig();
        INSTANCE = qoeConfig;
        Boolean value = qoeConfig.newBooleanConfigValue("playerQoe_isQoeEvaluatorEnabledForLive", true).getValue();
        Intrinsics.checkNotNullExpressionValue(value, "newBooleanConfigValue(\"p…bledForLive\", true).value");
        isQoeEvaluatorEnabledForLive = value.booleanValue();
        Boolean value2 = qoeConfig.newBooleanConfigValue("playerQoe_isQoeEvaluatorEnabledForVOD", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value2, "newBooleanConfigValue(\"p…bledForVOD\", false).value");
        isQoeEvaluatorEnabledForVOD = value2.booleanValue();
        Boolean value3 = qoeConfig.newBooleanConfigValue("playerQoe_isQoeCdnSwitchingEnabled", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value3, "newBooleanConfigValue(\"p…ingEnabled\", false).value");
        isQoeCdnSwitchingEnabled = value3.booleanValue();
        Integer value4 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForAvgBandwidth", 20).getValue();
        Intrinsics.checkNotNullExpressionValue(value4, "newIntConfigValue(\n     …rAvgBandwidth\", 20).value");
        numOfSamplesForAvgBandwidth = value4.intValue();
        Integer value5 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForAvgLatency", 20).getValue();
        Intrinsics.checkNotNullExpressionValue(value5, "newIntConfigValue(\n     …ForAvgLatency\", 20).value");
        numOfSamplesForAvgLatency = value5.intValue();
        Integer value6 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForManifestAvgLatency", 10).getValue();
        Intrinsics.checkNotNullExpressionValue(value6, "newIntConfigValue(\n     …estAvgLatency\", 10).value");
        numOfSamplesForManifestAvgLatency = value6.intValue();
        Integer value7 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForBandwidthChangeSmoothing", 5).getValue();
        Intrinsics.checkNotNullExpressionValue(value7, "newIntConfigValue(\n     …hangeSmoothing\", 5).value");
        numOfSamplesForBandwidthChangeSmoothing = value7.intValue();
        Integer value8 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForLatencyChangeSmoothing", 5).getValue();
        Intrinsics.checkNotNullExpressionValue(value8, "newIntConfigValue(\n     …hangeSmoothing\", 5).value");
        numOfSamplesForLatencyChangeSmoothing = value8.intValue();
        Integer value9 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForBitrateSmoothing", 1).getValue();
        Intrinsics.checkNotNullExpressionValue(value9, "newIntConfigValue(\n     …trateSmoothing\", 1).value");
        numOfSamplesForBitrateSmoothing = value9.intValue();
        Integer value10 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForFrontBufferSizeSmoothing", 5).getValue();
        Intrinsics.checkNotNullExpressionValue(value10, "newIntConfigValue(\n     …rSizeSmoothing\", 5).value");
        numOfSamplesForFrontBufferSizeSmoothing = value10.intValue();
        Integer value11 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForManifestLatencyChangeSmoothing", 3).getValue();
        Intrinsics.checkNotNullExpressionValue(value11, "newIntConfigValue(\n     …hangeSmoothing\", 3).value");
        numOfSamplesForManifestLatencyChangeSmoothing = value11.intValue();
        Integer value12 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForLatencySmoothing", 5).getValue();
        Intrinsics.checkNotNullExpressionValue(value12, "newIntConfigValue(\n     …tencySmoothing\", 5).value");
        numOfSamplesForLatencySmoothing = value12.intValue();
        Integer value13 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForBandwidthSmoothing", 5).getValue();
        Intrinsics.checkNotNullExpressionValue(value13, "newIntConfigValue(\n     …widthSmoothing\", 5).value");
        numOfSamplesForBandwidthSmoothing = value13.intValue();
        Integer value14 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForManifestLatencySmoothing", 3).getValue();
        Intrinsics.checkNotNullExpressionValue(value14, "newIntConfigValue(\n     …tencySmoothing\", 3).value");
        numOfSamplesForManifestLatencySmoothing = value14.intValue();
        TimeSpan timeSpanFromMinutes = TimeSpan.fromMinutes(5L);
        TimeUnit timeUnit = TimeUnit.MINUTES;
        TimeSpan value15 = qoeConfig.newTimeConfigurationValue("playerQoe_windowLengthForTrackingBufferingEvent", timeSpanFromMinutes, timeUnit).getValue();
        Intrinsics.checkNotNullExpressionValue(value15, "newTimeConfigurationValu…, TimeUnit.MINUTES).value");
        windowLengthForTrackingBufferingEvent = value15;
        Float value16 = qoeConfig.newFloatConfigValue("playerQoe_thresholdForCdnSwitching", -1.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value16, "newFloatConfigValue(\"pla…CdnSwitching\", -1f).value");
        thresholdForCdnSwitching = value16.floatValue();
        Float value17 = qoeConfig.newFloatConfigValue("playerQoe_bitrateWeight", 1.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value17, "newFloatConfigValue(\"pla…bitrateWeight\", 1f).value");
        bitrateWeight = value17.floatValue();
        Float value18 = qoeConfig.newFloatConfigValue("playerQoe_frontBufferSizeWeight", 1.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value18, "newFloatConfigValue(\"pla…ferSizeWeight\", 1f).value");
        frontBufferSizeWeight = value18.floatValue();
        Float value19 = qoeConfig.newFloatConfigValue("playerQoe_bandwidthChangeWeight", 0.2f).getValue();
        Intrinsics.checkNotNullExpressionValue(value19, "newFloatConfigValue(\"pla…hangeWeight\", 0.2f).value");
        bandwidthChangeWeight = value19.floatValue();
        Float value20 = qoeConfig.newFloatConfigValue("playerQoe_latencyChangeWeight", 1.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value20, "newFloatConfigValue(\"pla…yChangeWeight\", 1f).value");
        latencyChangeWeight = value20.floatValue();
        Float value21 = qoeConfig.newFloatConfigValue("playerQoe_timeSpentBufferingWeight", 1.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value21, "newFloatConfigValue(\"pla…fferingWeight\", 1f).value");
        timeSpentBufferingWeight = value21.floatValue();
        Float value22 = qoeConfig.newFloatConfigValue("playerQoe_numOfBufferingEventWeight", 1.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value22, "newFloatConfigValue(\"pla…ngEventWeight\", 1f).value");
        numOfBufferingEventWeight = value22.floatValue();
        Float value23 = qoeConfig.newFloatConfigValue("playerQoe_manifestLatencyChangeWeight", 1.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value23, "newFloatConfigValue(\"pla…yChangeWeight\", 1f).value");
        manifestLatencyChangeWeight = value23.floatValue();
        Float value24 = qoeConfig.newFloatConfigValue("playerQoe_bandwidthWeight", 0.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value24, "newFloatConfigValue(\"pla…ndwidthWeight\", 0f).value");
        bandwidthWeight = value24.floatValue();
        Float value25 = qoeConfig.newFloatConfigValue("playerQoe_latencyWeight", 0.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value25, "newFloatConfigValue(\"pla…latencyWeight\", 0f).value");
        latencyWeight = value25.floatValue();
        Float value26 = qoeConfig.newFloatConfigValue("playerQoe_manifestLatencyWeight", 0.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value26, "newFloatConfigValue(\"pla…LatencyWeight\", 0f).value");
        manifestLatencyWeight = value26.floatValue();
        Object value27 = qoeConfig.newEnumConfigValue("playerQoe_modelType", QoeModelType.MODEL_USING_PLAYBACK_AND_NETWORK_FEATURES, QoeModelType.class).getValue();
        Intrinsics.checkNotNullExpressionValue(value27, "newEnumConfigValue(\n    …elType::class.java).value");
        qoeModelType = (QoeModelType) value27;
        Integer value28 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesBeforeSwitching", 20).getValue();
        Intrinsics.checkNotNullExpressionValue(value28, "newIntConfigValue(\"playe…foreSwitching\", 20).value");
        numOfSamplesBeforeSwitching = value28.intValue();
        Integer value29 = qoeConfig.newIntConfigValue("playerQoe_maxNumOfCdnSwitchesCausedByQoeWithinWindow", 3).getValue();
        Intrinsics.checkNotNullExpressionValue(value29, "newIntConfigValue(\n     …oeWithinWindow\", 3).value");
        maxNumOfCdnSwitchesCausedByQoeWithinWindow = value29.intValue();
        TimeSpan value30 = qoeConfig.newTimeConfigurationValue("playerQoe_windowLengthForTrackingCdnSwitchesCausedByQoe", TimeSpan.fromMinutes(5L), timeUnit).getValue();
        Intrinsics.checkNotNullExpressionValue(value30, "newTimeConfigurationValu…, TimeUnit.MINUTES).value");
        windowLengthForTrackingCdnSwitchesCausedByQoe = value30;
        Integer value31 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForAvgQoe", 50).getValue();
        Intrinsics.checkNotNullExpressionValue(value31, "newIntConfigValue(\n     …plesForAvgQoe\", 50).value");
        numOfSamplesForAvgQoe = value31.intValue();
        Integer value32 = qoeConfig.newIntConfigValue("playerQoe_numOfSamplesForQoeChangeSmoothing", 1).getValue();
        Intrinsics.checkNotNullExpressionValue(value32, "newIntConfigValue(\n     …hangeSmoothing\", 1).value");
        numOfSamplesForQoeChangeSmoothing = value32.intValue();
        Boolean value33 = qoeConfig.newBooleanConfigValue("playerQoe_shouldReportQoeDiagnosticEvent", false).getValue();
        Intrinsics.checkNotNullExpressionValue(value33, "newBooleanConfigValue(\"p…osticEvent\", false).value");
        shouldReportQoeDiagnosticEvent = value33.booleanValue();
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    /* JADX INFO: renamed from: isQoeEvaluatorEnabledForLive */
    public boolean getIsQoeEvaluatorEnabledForLive() {
        return isQoeEvaluatorEnabledForLive;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    /* JADX INFO: renamed from: isQoeEvaluatorEnabledForVOD */
    public boolean getIsQoeEvaluatorEnabledForVOD() {
        return isQoeEvaluatorEnabledForVOD;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    /* JADX INFO: renamed from: isQoeCdnSwitchingEnabled */
    public boolean getIsQoeCdnSwitchingEnabled() {
        return isQoeCdnSwitchingEnabled;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForAvgBandwidth() {
        return numOfSamplesForAvgBandwidth;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForAvgLatency() {
        return numOfSamplesForAvgLatency;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForManifestAvgLatency() {
        return numOfSamplesForManifestAvgLatency;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForBandwidthChangeSmoothing() {
        return numOfSamplesForBandwidthChangeSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForLatencyChangeSmoothing() {
        return numOfSamplesForLatencyChangeSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForBitrateSmoothing() {
        return numOfSamplesForBitrateSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForFrontBufferSizeSmoothing() {
        return numOfSamplesForFrontBufferSizeSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForManifestLatencyChangeSmoothing() {
        return numOfSamplesForManifestLatencyChangeSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForLatencySmoothing() {
        return numOfSamplesForLatencySmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForBandwidthSmoothing() {
        return numOfSamplesForBandwidthSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForManifestLatencySmoothing() {
        return numOfSamplesForManifestLatencySmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public TimeSpan getWindowLengthForTrackingBufferingEvent() {
        return windowLengthForTrackingBufferingEvent;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getThresholdForCdnSwitching() {
        return thresholdForCdnSwitching;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getBitrateWeight() {
        return bitrateWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getFrontBufferSizeWeight() {
        return frontBufferSizeWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getBandwidthChangeWeight() {
        return bandwidthChangeWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getLatencyChangeWeight() {
        return latencyChangeWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getTimeSpentBufferingWeight() {
        return timeSpentBufferingWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getNumOfBufferingEventWeight() {
        return numOfBufferingEventWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getManifestLatencyChangeWeight() {
        return manifestLatencyChangeWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getBandwidthWeight() {
        return bandwidthWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getLatencyWeight() {
        return latencyWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getManifestLatencyWeight() {
        return manifestLatencyWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public QoeModelType getQoeModelType() {
        return qoeModelType;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesBeforeSwitching() {
        return numOfSamplesBeforeSwitching;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getMaxNumOfCdnSwitchesCausedByQoeWithinWindow() {
        return maxNumOfCdnSwitchesCausedByQoeWithinWindow;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public TimeSpan getWindowLengthForTrackingCdnSwitchesCausedByQoe() {
        return windowLengthForTrackingCdnSwitchesCausedByQoe;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForAvgQoe() {
        return numOfSamplesForAvgQoe;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForQoeChangeSmoothing() {
        return numOfSamplesForQoeChangeSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public boolean getShouldReportQoeDiagnosticEvent() {
        return shouldReportQoeDiagnosticEvent;
    }
}
