package com.amazon.avod.dash.config.miyagi;

import com.amazon.avod.content.urlvending.QoeConfigInterface;
import com.amazon.avod.content.urlvending.QoeModelType;
import com.amazon.avod.media.TimeSpan;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FixedQoeConfig.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BI\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0002\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0014\u0010\u0012\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\rR\u0014\u0010\u001b\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\rR\u0014\u0010\u001d\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\rR\u0014\u0010\u001f\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\rR\u0014\u0010!\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\rR\u0014\u0010'\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010$R\u0014\u0010)\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010$R\u0014\u0010+\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010$R\u0014\u0010-\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010$R\u0014\u0010/\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010$R\u0014\u00101\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010$R\u0014\u00103\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010$R\u0014\u00105\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010$R\u0014\u00107\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010$R\u0014\u00109\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010$R\u0014\u0010;\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010$R\u0014\u0010=\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010$R\u0014\u0010?\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010$R\u0014\u0010A\u001a\u00020\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010$R\u0014\u0010C\u001a\u00020D8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u000e\u0010G\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010H\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u0016R\u0014\u0010J\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010\rR\u0014\u0010L\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010\rR\u0014\u0010N\u001a\u00020O8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bP\u0010QR\u000e\u0010R\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010S\u001a\u00020O8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bT\u0010QR\u000e\u0010U\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006V"}, d2 = {"Lcom/amazon/avod/dash/config/miyagi/FixedQoeConfig;", "Lcom/amazon/avod/dash/config/miyagi/HeuristicsConfigBase;", "Lcom/amazon/avod/content/urlvending/QoeConfigInterface;", "debugValues", "", "", "sessionValues", "appLoadValues", "qoeConfig", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/amazon/avod/content/urlvending/QoeConfigInterface;)V", "bandwidthChangeWeight", "", "getBandwidthChangeWeight", "()F", "bandwidthWeight", "getBandwidthWeight", "bitrateWeight", "getBitrateWeight", "frontBufferSizeWeight", "getFrontBufferSizeWeight", "isQoeCdnSwitchingEnabled", "", "()Z", "isQoeEvaluatorEnabledForLive", "isQoeEvaluatorEnabledForVOD", "latencyChangeWeight", "getLatencyChangeWeight", "latencyWeight", "getLatencyWeight", "manifestLatencyChangeWeight", "getManifestLatencyChangeWeight", "manifestLatencyWeight", "getManifestLatencyWeight", "maxNumOfCdnSwitchesCausedByQoeWithinWindow", "", "getMaxNumOfCdnSwitchesCausedByQoeWithinWindow", "()I", "numOfBufferingEventWeight", "getNumOfBufferingEventWeight", "numOfSamplesBeforeSwitching", "getNumOfSamplesBeforeSwitching", "numOfSamplesForAvgBandwidth", "getNumOfSamplesForAvgBandwidth", "numOfSamplesForAvgLatency", "getNumOfSamplesForAvgLatency", "numOfSamplesForAvgQoe", "getNumOfSamplesForAvgQoe", "numOfSamplesForBandwidthChangeSmoothing", "getNumOfSamplesForBandwidthChangeSmoothing", "numOfSamplesForBandwidthSmoothing", "getNumOfSamplesForBandwidthSmoothing", "numOfSamplesForBitrateSmoothing", "getNumOfSamplesForBitrateSmoothing", "numOfSamplesForFrontBufferSizeSmoothing", "getNumOfSamplesForFrontBufferSizeSmoothing", "numOfSamplesForLatencyChangeSmoothing", "getNumOfSamplesForLatencyChangeSmoothing", "numOfSamplesForLatencySmoothing", "getNumOfSamplesForLatencySmoothing", "numOfSamplesForManifestAvgLatency", "getNumOfSamplesForManifestAvgLatency", "numOfSamplesForManifestLatencyChangeSmoothing", "getNumOfSamplesForManifestLatencyChangeSmoothing", "numOfSamplesForManifestLatencySmoothing", "getNumOfSamplesForManifestLatencySmoothing", "numOfSamplesForQoeChangeSmoothing", "getNumOfSamplesForQoeChangeSmoothing", "qoeModelType", "Lcom/amazon/avod/content/urlvending/QoeModelType;", "getQoeModelType", "()Lcom/amazon/avod/content/urlvending/QoeModelType;", "qoeModelTypeStr", "shouldReportQoeDiagnosticEvent", "getShouldReportQoeDiagnosticEvent", "thresholdForCdnSwitching", "getThresholdForCdnSwitching", "timeSpentBufferingWeight", "getTimeSpentBufferingWeight", "windowLengthForTrackingBufferingEvent", "Lcom/amazon/avod/media/TimeSpan;", "getWindowLengthForTrackingBufferingEvent", "()Lcom/amazon/avod/media/TimeSpan;", "windowLengthForTrackingBufferingEventMinutes", "windowLengthForTrackingCdnSwitchesCausedByQoe", "getWindowLengthForTrackingCdnSwitchesCausedByQoe", "windowLengthForTrackingCdnSwitchesCausedByQoeMinutes", "ATVPlaybackDash_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FixedQoeConfig extends HeuristicsConfigBase implements QoeConfigInterface {
    private final float bandwidthChangeWeight;
    private final float bandwidthWeight;
    private final float bitrateWeight;
    private final float frontBufferSizeWeight;
    private final boolean isQoeCdnSwitchingEnabled;
    private final boolean isQoeEvaluatorEnabledForLive;
    private final boolean isQoeEvaluatorEnabledForVOD;
    private final float latencyChangeWeight;
    private final float latencyWeight;
    private final float manifestLatencyChangeWeight;
    private final float manifestLatencyWeight;
    private final int maxNumOfCdnSwitchesCausedByQoeWithinWindow;
    private final float numOfBufferingEventWeight;
    private final int numOfSamplesBeforeSwitching;
    private final int numOfSamplesForAvgBandwidth;
    private final int numOfSamplesForAvgLatency;
    private final int numOfSamplesForAvgQoe;
    private final int numOfSamplesForBandwidthChangeSmoothing;
    private final int numOfSamplesForBandwidthSmoothing;
    private final int numOfSamplesForBitrateSmoothing;
    private final int numOfSamplesForFrontBufferSizeSmoothing;
    private final int numOfSamplesForLatencyChangeSmoothing;
    private final int numOfSamplesForLatencySmoothing;
    private final int numOfSamplesForManifestAvgLatency;
    private final int numOfSamplesForManifestLatencyChangeSmoothing;
    private final int numOfSamplesForManifestLatencySmoothing;
    private final int numOfSamplesForQoeChangeSmoothing;
    private final String qoeModelTypeStr;
    private final boolean shouldReportQoeDiagnosticEvent;
    private final float thresholdForCdnSwitching;
    private final float timeSpentBufferingWeight;
    private final int windowLengthForTrackingBufferingEventMinutes;
    private final int windowLengthForTrackingCdnSwitchesCausedByQoeMinutes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FixedQoeConfig(Map<String, String> debugValues, Map<String, String> sessionValues, Map<String, String> appLoadValues, QoeConfigInterface qoeConfig) {
        super(debugValues, sessionValues, appLoadValues);
        Intrinsics.checkNotNullParameter(debugValues, "debugValues");
        Intrinsics.checkNotNullParameter(sessionValues, "sessionValues");
        Intrinsics.checkNotNullParameter(appLoadValues, "appLoadValues");
        Intrinsics.checkNotNullParameter(qoeConfig, "qoeConfig");
        this.isQoeEvaluatorEnabledForLive = getBooleanConfigValue("qoe_isQoeEvaluatorEnabledForLive", qoeConfig.getIsQoeEvaluatorEnabledForLive());
        this.isQoeEvaluatorEnabledForVOD = getBooleanConfigValue("qoe_isQoeEvaluatorEnabledForVOD", qoeConfig.getIsQoeEvaluatorEnabledForVOD());
        this.isQoeCdnSwitchingEnabled = getBooleanConfigValue("qoe_isQoeCdnSwitchingEnabled", qoeConfig.getIsQoeCdnSwitchingEnabled());
        this.numOfSamplesForAvgBandwidth = getIntegerConfigValue("qoe_numOfSamplesForAvgBandwidth", qoeConfig.getNumOfSamplesForAvgBandwidth());
        this.numOfSamplesForAvgLatency = getIntegerConfigValue("qoe_numOfSamplesForAvgLatency", qoeConfig.getNumOfSamplesForAvgLatency());
        this.numOfSamplesForManifestAvgLatency = getIntegerConfigValue("qoe_numOfSamplesForManifestAvgLatency", qoeConfig.getNumOfSamplesForManifestAvgLatency());
        this.numOfSamplesForBandwidthChangeSmoothing = getIntegerConfigValue("qoe_numOfSamplesForBandwidthChangeSmoothing", qoeConfig.getNumOfSamplesForBandwidthChangeSmoothing());
        this.numOfSamplesForLatencyChangeSmoothing = getIntegerConfigValue("qoe_numOfSamplesForLatencyChangeSmoothing", qoeConfig.getNumOfSamplesForLatencyChangeSmoothing());
        this.numOfSamplesForManifestLatencyChangeSmoothing = getIntegerConfigValue("qoe_numOfSamplesForManifestLatencyChangeSmoothing", qoeConfig.getNumOfSamplesForManifestLatencyChangeSmoothing());
        this.numOfSamplesForBitrateSmoothing = getIntegerConfigValue("qoe_numOfSamplesForBitrateSmoothing", qoeConfig.getNumOfSamplesForBitrateSmoothing());
        this.numOfSamplesForFrontBufferSizeSmoothing = getIntegerConfigValue("qoe_numOfSamplesForFrontBufferSizeSmoothing", qoeConfig.getNumOfSamplesForFrontBufferSizeSmoothing());
        this.numOfSamplesForLatencySmoothing = getIntegerConfigValue("qoe_numOfSamplesForLatencySmoothing", qoeConfig.getNumOfSamplesForLatencySmoothing());
        this.numOfSamplesForBandwidthSmoothing = getIntegerConfigValue("qoe_numOfSamplesForBandwidthSmoothing", qoeConfig.getNumOfSamplesForBandwidthSmoothing());
        this.numOfSamplesForManifestLatencySmoothing = getIntegerConfigValue("qoe_numOfSamplesForManifestLatencySmoothing", qoeConfig.getNumOfSamplesForManifestLatencySmoothing());
        this.windowLengthForTrackingBufferingEventMinutes = getIntegerConfigValue("qoe_windowLengthForTrackingBufferingEventMinutes", qoeConfig.getWindowLengthForTrackingBufferingEvent().getTotalMinutes());
        this.thresholdForCdnSwitching = getFloatConfigValue("qoe_thresholdForCdnSwitching", qoeConfig.getThresholdForCdnSwitching());
        this.bitrateWeight = getFloatConfigValue("qoe_bitrateWeight", qoeConfig.getBitrateWeight());
        this.frontBufferSizeWeight = getFloatConfigValue("qoe_frontBufferSizeWeight", qoeConfig.getFrontBufferSizeWeight());
        this.bandwidthChangeWeight = getFloatConfigValue("qoe_bandwidthChangeWeight", qoeConfig.getBandwidthChangeWeight());
        this.latencyChangeWeight = getFloatConfigValue("qoe_latencyChangeWeight", qoeConfig.getLatencyChangeWeight());
        this.timeSpentBufferingWeight = getFloatConfigValue("qoe_timeSpentBufferingWeight", qoeConfig.getTimeSpentBufferingWeight());
        this.numOfBufferingEventWeight = getFloatConfigValue("qoe_numOfBufferingEventWeight", qoeConfig.getNumOfBufferingEventWeight());
        this.manifestLatencyChangeWeight = getFloatConfigValue("qoe_manifestLatencyChangeWeight", qoeConfig.getManifestLatencyChangeWeight());
        this.bandwidthWeight = getFloatConfigValue("qoe_bandwidthWeight", qoeConfig.getBandwidthWeight());
        this.latencyWeight = getFloatConfigValue("qoe_latencyWeight", qoeConfig.getLatencyWeight());
        this.manifestLatencyWeight = getFloatConfigValue("qoe_manifestLatencyWeight", qoeConfig.getManifestLatencyWeight());
        String stringConfigValue = getStringConfigValue("qoe_modelTypeStr", qoeConfig.getQoeModelType().name());
        Intrinsics.checkNotNullExpressionValue(stringConfigValue, "getStringConfigValue(\n  …Config.qoeModelType.name)");
        this.qoeModelTypeStr = stringConfigValue;
        this.numOfSamplesBeforeSwitching = getIntegerConfigValue("qoe_numOfSamplesBeforeSwitching", qoeConfig.getNumOfSamplesBeforeSwitching());
        this.maxNumOfCdnSwitchesCausedByQoeWithinWindow = getIntegerConfigValue("qoe_maxNumOfCdnSwitchesCausedByQoeWithinWindow", qoeConfig.getMaxNumOfCdnSwitchesCausedByQoeWithinWindow());
        this.windowLengthForTrackingCdnSwitchesCausedByQoeMinutes = getIntegerConfigValue("qoe_windowLengthForTrackingCdnSwitchesCausedByQoeMinutes", qoeConfig.getWindowLengthForTrackingCdnSwitchesCausedByQoe().getTotalMinutes());
        this.numOfSamplesForAvgQoe = getIntegerConfigValue("qoe_numOfSamplesForAvgQoe", qoeConfig.getNumOfSamplesForAvgQoe());
        this.numOfSamplesForQoeChangeSmoothing = getIntegerConfigValue("qoe_numOfSamplesForQoeChangeSmoothing", qoeConfig.getNumOfSamplesForQoeChangeSmoothing());
        this.shouldReportQoeDiagnosticEvent = getBooleanConfigValue("qoe_shouldReportQoeDiagnosticEvent", qoeConfig.getShouldReportQoeDiagnosticEvent());
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    /* JADX INFO: renamed from: isQoeEvaluatorEnabledForLive, reason: from getter */
    public boolean getIsQoeEvaluatorEnabledForLive() {
        return this.isQoeEvaluatorEnabledForLive;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    /* JADX INFO: renamed from: isQoeEvaluatorEnabledForVOD, reason: from getter */
    public boolean getIsQoeEvaluatorEnabledForVOD() {
        return this.isQoeEvaluatorEnabledForVOD;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    /* JADX INFO: renamed from: isQoeCdnSwitchingEnabled, reason: from getter */
    public boolean getIsQoeCdnSwitchingEnabled() {
        return this.isQoeCdnSwitchingEnabled;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForAvgBandwidth() {
        return this.numOfSamplesForAvgBandwidth;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForAvgLatency() {
        return this.numOfSamplesForAvgLatency;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForManifestAvgLatency() {
        return this.numOfSamplesForManifestAvgLatency;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForBandwidthChangeSmoothing() {
        return this.numOfSamplesForBandwidthChangeSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForLatencyChangeSmoothing() {
        return this.numOfSamplesForLatencyChangeSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForManifestLatencyChangeSmoothing() {
        return this.numOfSamplesForManifestLatencyChangeSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForBitrateSmoothing() {
        return this.numOfSamplesForBitrateSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForFrontBufferSizeSmoothing() {
        return this.numOfSamplesForFrontBufferSizeSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForLatencySmoothing() {
        return this.numOfSamplesForLatencySmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForBandwidthSmoothing() {
        return this.numOfSamplesForBandwidthSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForManifestLatencySmoothing() {
        return this.numOfSamplesForManifestLatencySmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public TimeSpan getWindowLengthForTrackingBufferingEvent() {
        TimeSpan timeSpanFromMinutes = TimeSpan.fromMinutes(this.windowLengthForTrackingBufferingEventMinutes);
        Intrinsics.checkNotNullExpressionValue(timeSpanFromMinutes, "fromMinutes(windowLength…ingEventMinutes.toLong())");
        return timeSpanFromMinutes;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getThresholdForCdnSwitching() {
        return this.thresholdForCdnSwitching;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getBitrateWeight() {
        return this.bitrateWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getFrontBufferSizeWeight() {
        return this.frontBufferSizeWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getBandwidthChangeWeight() {
        return this.bandwidthChangeWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getLatencyChangeWeight() {
        return this.latencyChangeWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getTimeSpentBufferingWeight() {
        return this.timeSpentBufferingWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getNumOfBufferingEventWeight() {
        return this.numOfBufferingEventWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getManifestLatencyChangeWeight() {
        return this.manifestLatencyChangeWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getBandwidthWeight() {
        return this.bandwidthWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getLatencyWeight() {
        return this.latencyWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public float getManifestLatencyWeight() {
        return this.manifestLatencyWeight;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public QoeModelType getQoeModelType() {
        return QoeModelType.valueOf(this.qoeModelTypeStr);
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesBeforeSwitching() {
        return this.numOfSamplesBeforeSwitching;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getMaxNumOfCdnSwitchesCausedByQoeWithinWindow() {
        return this.maxNumOfCdnSwitchesCausedByQoeWithinWindow;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public TimeSpan getWindowLengthForTrackingCdnSwitchesCausedByQoe() {
        TimeSpan timeSpanFromMinutes = TimeSpan.fromMinutes(this.windowLengthForTrackingCdnSwitchesCausedByQoeMinutes);
        Intrinsics.checkNotNullExpressionValue(timeSpanFromMinutes, "fromMinutes(windowLength…sedByQoeMinutes.toLong())");
        return timeSpanFromMinutes;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForAvgQoe() {
        return this.numOfSamplesForAvgQoe;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public int getNumOfSamplesForQoeChangeSmoothing() {
        return this.numOfSamplesForQoeChangeSmoothing;
    }

    @Override // com.amazon.avod.content.urlvending.QoeConfigInterface
    public boolean getShouldReportQoeDiagnosticEvent() {
        return this.shouldReportQoeDiagnosticEvent;
    }
}
