package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.media.VideoResolution;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: HighFrameRateQualityHolderInterface.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H&J(\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u000f\u001a\u00020\rH&J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0018\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0014H&J\u0010\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0018H&J\b\u0010\u001b\u001a\u00020\rH&J\b\u0010\u001c\u001a\u00020\rH&J\b\u0010\u001d\u001a\u00020\rH&J\b\u0010\u001e\u001a\u00020\u0016H&R\u0014\u0010!\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010#\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010 R\u0014\u0010%\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010 R\u0014\u0010&\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010 R\u0014\u0010(\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010 R\u0014\u0010+\u001a\u00020\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010.\u001a\u00020\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0014\u00100\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u0010 R\u0014\u00102\u001a\u00020\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b1\u0010*R\u0014\u00104\u001a\u00020\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b3\u0010-R\u0014\u00105\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b5\u0010 R\u0014\u00107\u001a\u00020\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b6\u0010-R\u0014\u00109\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\b8\u0010 R \u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020;0:8&X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R \u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020;0:8&X¦\u0004¢\u0006\u0006\u001a\u0004\b?\u0010=R\u0014\u0010A\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\bA\u0010 R\u0014\u0010C\u001a\u00020\u00168&X¦\u0004¢\u0006\u0006\u001a\u0004\bB\u0010 ¨\u0006D"}, d2 = {"Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateQualityHolderInterface;", "", "", "fourCC", "", "getBitrateCap", "(Ljava/lang/String;)Ljava/lang/Integer;", "Lcom/amazon/avod/media/VideoResolution$ResolutionBand;", "getStaticResolutionCap", "getDynamicResolutionCap", "bitrate", "width", "height", "", "addBitrateResolutionMapping", "clearBitrateResolutionMapping", "bitrateCap", "updateBitrateCap", "resolutionCap", "updateResolutionCap", "", "givenFrameRate", "", "isHighFrameRate", "Lcom/amazon/avod/content/smoothstream/quality/QualityCappingUpdateListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "registerBitrateCappingUpdateListener", "broadcastBitrateCappingUpdate", "clearBitrateCappingUpdateListenersList", "disableHFRPlaybackForPerfIssues", "isHFRPlaybackAllowedByPerfEvaluator", "getShouldApplyDynamicQualityCap", "()Z", "shouldApplyDynamicQualityCap", "getShouldApplyHFRCappingLive", "shouldApplyHFRCappingLive", "getShouldApplyHFRCappingNonLive", "shouldApplyHFRCappingNonLive", "isHFRPerformanceEvaluatorEnabled", "getShouldTrackFrameDropBurst", "shouldTrackFrameDropBurst", "getBurstFrameDropWindowThreshold", "()I", "burstFrameDropWindowThreshold", "getBurstFrameDropDetectionPercentage", "()F", "burstFrameDropDetectionPercentage", "getShouldTrackFrameDropContinual", "shouldTrackFrameDropContinual", "getContinualFrameDropWindowThreshold", "continualFrameDropWindowThreshold", "getContinualFrameDropDetectionPercentage", "continualFrameDropDetectionPercentage", "isSDHighFrameRateFallbackAllowed", "getMinimumFrameRateRendererPerformanceEvaluation", "minimumFrameRateRendererPerformanceEvaluation", "getShouldReportAllFrameDropAnomalies", "shouldReportAllFrameDropAnomalies", "", "Lcom/amazon/avod/media/VideoResolution;", "getAvcBitrateResolutionMap", "()Ljava/util/Map;", "avcBitrateResolutionMap", "getHevcBitrateResolutionMap", "hevcBitrateResolutionMap", "isPlayerRestartOnHFRPerformanceAnomalyEnabled", "getShouldRestartPlayerOnHFRDynamicCapping", "shouldRestartPlayerOnHFRDynamicCapping", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0})
public interface HighFrameRateQualityHolderInterface {
    void addBitrateResolutionMapping(int bitrate, int width, int height, String fourCC);

    void broadcastBitrateCappingUpdate();

    void clearBitrateCappingUpdateListenersList();

    void clearBitrateResolutionMapping();

    void disableHFRPlaybackForPerfIssues();

    Map<Integer, VideoResolution> getAvcBitrateResolutionMap();

    Integer getBitrateCap(String fourCC);

    float getBurstFrameDropDetectionPercentage();

    int getBurstFrameDropWindowThreshold();

    float getContinualFrameDropDetectionPercentage();

    int getContinualFrameDropWindowThreshold();

    VideoResolution.ResolutionBand getDynamicResolutionCap(String fourCC);

    Map<Integer, VideoResolution> getHevcBitrateResolutionMap();

    float getMinimumFrameRateRendererPerformanceEvaluation();

    boolean getShouldApplyDynamicQualityCap();

    boolean getShouldApplyHFRCappingLive();

    boolean getShouldApplyHFRCappingNonLive();

    boolean getShouldReportAllFrameDropAnomalies();

    boolean getShouldRestartPlayerOnHFRDynamicCapping();

    boolean getShouldTrackFrameDropBurst();

    boolean getShouldTrackFrameDropContinual();

    VideoResolution.ResolutionBand getStaticResolutionCap(String fourCC);

    boolean isHFRPerformanceEvaluatorEnabled();

    boolean isHFRPlaybackAllowedByPerfEvaluator();

    boolean isHighFrameRate(float givenFrameRate);

    boolean isPlayerRestartOnHFRPerformanceAnomalyEnabled();

    boolean isSDHighFrameRateFallbackAllowed();

    void registerBitrateCappingUpdateListener(QualityCappingUpdateListener listener);

    void updateBitrateCap(int bitrateCap, String fourCC);

    void updateResolutionCap(String resolutionCap, String fourCC);
}
