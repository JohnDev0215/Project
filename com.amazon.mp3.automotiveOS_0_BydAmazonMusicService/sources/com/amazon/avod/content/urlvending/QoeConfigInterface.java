package com.amazon.avod.content.urlvending;

import com.amazon.avod.media.TimeSpan;
import kotlin.Metadata;

/* JADX INFO: compiled from: QoeConfigInterface.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000eR\u0012\u0010\u0010\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0012\u0010\u0011\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0005R\u0012\u0010\u0013\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0005R\u0012\u0010\u0015\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0005R\u0012\u0010\u0017\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0005R\u0012\u0010\u0019\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0005R\u0012\u0010\u001f\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001cR\u0012\u0010!\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001cR\u0012\u0010#\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u001cR\u0012\u0010%\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u001cR\u0012\u0010'\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u001cR\u0012\u0010)\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u001cR\u0012\u0010+\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u001cR\u0012\u0010-\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u001cR\u0012\u0010/\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u001cR\u0012\u00101\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u001cR\u0012\u00103\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b4\u0010\u001cR\u0012\u00105\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\u001cR\u0012\u00107\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b8\u0010\u001cR\u0012\u00109\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b:\u0010\u001cR\u0012\u0010;\u001a\u00020<X¦\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0012\u0010?\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b@\u0010\u000eR\u0012\u0010A\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bB\u0010\u0005R\u0012\u0010C\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\bD\u0010\u0005R\u0012\u0010E\u001a\u00020FX¦\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0012\u0010I\u001a\u00020FX¦\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010H¨\u0006K"}, d2 = {"Lcom/amazon/avod/content/urlvending/QoeConfigInterface;", "", "bandwidthChangeWeight", "", "getBandwidthChangeWeight", "()F", "bandwidthWeight", "getBandwidthWeight", "bitrateWeight", "getBitrateWeight", "frontBufferSizeWeight", "getFrontBufferSizeWeight", "isQoeCdnSwitchingEnabled", "", "()Z", "isQoeEvaluatorEnabledForLive", "isQoeEvaluatorEnabledForVOD", "latencyChangeWeight", "getLatencyChangeWeight", "latencyWeight", "getLatencyWeight", "manifestLatencyChangeWeight", "getManifestLatencyChangeWeight", "manifestLatencyWeight", "getManifestLatencyWeight", "maxNumOfCdnSwitchesCausedByQoeWithinWindow", "", "getMaxNumOfCdnSwitchesCausedByQoeWithinWindow", "()I", "numOfBufferingEventWeight", "getNumOfBufferingEventWeight", "numOfSamplesBeforeSwitching", "getNumOfSamplesBeforeSwitching", "numOfSamplesForAvgBandwidth", "getNumOfSamplesForAvgBandwidth", "numOfSamplesForAvgLatency", "getNumOfSamplesForAvgLatency", "numOfSamplesForAvgQoe", "getNumOfSamplesForAvgQoe", "numOfSamplesForBandwidthChangeSmoothing", "getNumOfSamplesForBandwidthChangeSmoothing", "numOfSamplesForBandwidthSmoothing", "getNumOfSamplesForBandwidthSmoothing", "numOfSamplesForBitrateSmoothing", "getNumOfSamplesForBitrateSmoothing", "numOfSamplesForFrontBufferSizeSmoothing", "getNumOfSamplesForFrontBufferSizeSmoothing", "numOfSamplesForLatencyChangeSmoothing", "getNumOfSamplesForLatencyChangeSmoothing", "numOfSamplesForLatencySmoothing", "getNumOfSamplesForLatencySmoothing", "numOfSamplesForManifestAvgLatency", "getNumOfSamplesForManifestAvgLatency", "numOfSamplesForManifestLatencyChangeSmoothing", "getNumOfSamplesForManifestLatencyChangeSmoothing", "numOfSamplesForManifestLatencySmoothing", "getNumOfSamplesForManifestLatencySmoothing", "numOfSamplesForQoeChangeSmoothing", "getNumOfSamplesForQoeChangeSmoothing", "qoeModelType", "Lcom/amazon/avod/content/urlvending/QoeModelType;", "getQoeModelType", "()Lcom/amazon/avod/content/urlvending/QoeModelType;", "shouldReportQoeDiagnosticEvent", "getShouldReportQoeDiagnosticEvent", "thresholdForCdnSwitching", "getThresholdForCdnSwitching", "timeSpentBufferingWeight", "getTimeSpentBufferingWeight", "windowLengthForTrackingBufferingEvent", "Lcom/amazon/avod/media/TimeSpan;", "getWindowLengthForTrackingBufferingEvent", "()Lcom/amazon/avod/media/TimeSpan;", "windowLengthForTrackingCdnSwitchesCausedByQoe", "getWindowLengthForTrackingCdnSwitchesCausedByQoe", "ATVPlayerHeuristics_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface QoeConfigInterface {
    float getBandwidthChangeWeight();

    float getBandwidthWeight();

    float getBitrateWeight();

    float getFrontBufferSizeWeight();

    float getLatencyChangeWeight();

    float getLatencyWeight();

    float getManifestLatencyChangeWeight();

    float getManifestLatencyWeight();

    int getMaxNumOfCdnSwitchesCausedByQoeWithinWindow();

    float getNumOfBufferingEventWeight();

    int getNumOfSamplesBeforeSwitching();

    int getNumOfSamplesForAvgBandwidth();

    int getNumOfSamplesForAvgLatency();

    int getNumOfSamplesForAvgQoe();

    int getNumOfSamplesForBandwidthChangeSmoothing();

    int getNumOfSamplesForBandwidthSmoothing();

    int getNumOfSamplesForBitrateSmoothing();

    int getNumOfSamplesForFrontBufferSizeSmoothing();

    int getNumOfSamplesForLatencyChangeSmoothing();

    int getNumOfSamplesForLatencySmoothing();

    int getNumOfSamplesForManifestAvgLatency();

    int getNumOfSamplesForManifestLatencyChangeSmoothing();

    int getNumOfSamplesForManifestLatencySmoothing();

    int getNumOfSamplesForQoeChangeSmoothing();

    QoeModelType getQoeModelType();

    boolean getShouldReportQoeDiagnosticEvent();

    float getThresholdForCdnSwitching();

    float getTimeSpentBufferingWeight();

    TimeSpan getWindowLengthForTrackingBufferingEvent();

    TimeSpan getWindowLengthForTrackingCdnSwitchesCausedByQoe();

    boolean isQoeCdnSwitchingEnabled();

    boolean isQoeEvaluatorEnabledForLive();

    boolean isQoeEvaluatorEnabledForVOD();
}
