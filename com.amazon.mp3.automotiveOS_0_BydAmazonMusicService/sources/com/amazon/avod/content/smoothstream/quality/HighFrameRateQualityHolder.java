package com.amazon.avod.content.smoothstream.quality;

import amazon.android.config.ConfigurationValue;
import android.annotation.SuppressLint;
import com.amazon.avod.media.ExternalFourCCMapper;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.VideoStreamType;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HighFrameRateQualityHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\bW\u0010XJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J(\u0010\u000f\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0010\u001a\u00020\u0002H\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\u0010\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0019H\u0016J\b\u0010\u001c\u001a\u00020\u0002H\u0017J\b\u0010\u001d\u001a\u00020\u0002H\u0016J\b\u0010\u001e\u001a\u00020\u0002H\u0016J\b\u0010\u001f\u001a\u00020\u0017H\u0016R\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b'\u0010)R\u0014\u0010*\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010$R\u001a\u0010+\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\b+\u0010(\u001a\u0004\b,\u0010)R\u001a\u0010-\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\b-\u0010(\u001a\u0004\b.\u0010)R\u001a\u0010/\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\b/\u0010(\u001a\u0004\b0\u0010)R\u001a\u00101\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\b1\u0010(\u001a\u0004\b2\u0010)R\u001a\u00104\u001a\u0002038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b<\u0010$\u001a\u0004\b=\u0010&R\u001a\u0010>\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\b>\u0010(\u001a\u0004\b?\u0010)R\u001a\u0010@\u001a\u0002038\u0016X\u0096\u0004¢\u0006\f\n\u0004\b@\u00105\u001a\u0004\bA\u00107R\u001a\u0010B\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\bB\u00109\u001a\u0004\bC\u0010;R\u001a\u0010D\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\bD\u0010$\u001a\u0004\bE\u0010&R\u001a\u0010F\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\bF\u0010(\u001a\u0004\bF\u0010)R\u001a\u0010G\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\bG\u0010(\u001a\u0004\bH\u0010)R\u001a\u0010I\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\bI\u0010(\u001a\u0004\bI\u0010)R\u001a\u0010J\u001a\u00020\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\bJ\u0010(\u001a\u0004\bK\u0010)R\u001a\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00190L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010NR&\u0010Q\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020P0O8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010TR&\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020P0O8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bU\u0010R\u001a\u0004\bV\u0010T¨\u0006Y"}, d2 = {"Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateQualityHolder;", "Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateQualityHolderInterface;", "", "clearDynamicQualityCappingConfigs", "", "fourCC", "", "getBitrateCap", "(Ljava/lang/String;)Ljava/lang/Integer;", "Lcom/amazon/avod/media/VideoResolution$ResolutionBand;", "getStaticResolutionCap", "getDynamicResolutionCap", "bitrate", "width", "height", "addBitrateResolutionMapping", "clearBitrateResolutionMapping", "bitrateCap", "updateBitrateCap", "resolutionCap", "updateResolutionCap", "", "givenFrameRate", "", "isHighFrameRate", "Lcom/amazon/avod/content/smoothstream/quality/QualityCappingUpdateListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "registerBitrateCappingUpdateListener", "broadcastBitrateCappingUpdate", "clearBitrateCappingUpdateListenersList", "disableHFRPlaybackForPerfIssues", "isHFRPlaybackAllowedByPerfEvaluator", "Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateQualityConfig;", "config", "Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateQualityConfig;", "minimumFrameRateRendererPerformanceEvaluation", "F", "getMinimumFrameRateRendererPerformanceEvaluation", "()F", "isHFRPerformanceEvaluatorEnabled", "Z", "()Z", "highFrameRateThreshold", "shouldApplyDynamicQualityCap", "getShouldApplyDynamicQualityCap", "shouldApplyHFRCappingLive", "getShouldApplyHFRCappingLive", "shouldApplyHFRCappingNonLive", "getShouldApplyHFRCappingNonLive", "shouldTrackFrameDropBurst", "getShouldTrackFrameDropBurst", "Lcom/amazon/avod/media/TimeSpan;", "burstFrameDropWindowLength", "Lcom/amazon/avod/media/TimeSpan;", "getBurstFrameDropWindowLength", "()Lcom/amazon/avod/media/TimeSpan;", "burstFrameDropWindowThreshold", "I", "getBurstFrameDropWindowThreshold", "()I", "burstFrameDropDetectionPercentage", "getBurstFrameDropDetectionPercentage", "shouldTrackFrameDropContinual", "getShouldTrackFrameDropContinual", "continualFrameDropWindowLength", "getContinualFrameDropWindowLength", "continualFrameDropWindowThreshold", "getContinualFrameDropWindowThreshold", "continualFrameDropDetectionPercentage", "getContinualFrameDropDetectionPercentage", "isSDHighFrameRateFallbackAllowed", "shouldReportAllFrameDropAnomalies", "getShouldReportAllFrameDropAnomalies", "isPlayerRestartOnHFRPerformanceAnomalyEnabled", "shouldRestartPlayerOnHFRDynamicCapping", "getShouldRestartPlayerOnHFRDynamicCapping", "", "QUALITY_CAPPING_UPDATE_LISTENERS_LIST", "Ljava/util/List;", "", "Lcom/amazon/avod/media/VideoResolution;", "avcBitrateResolutionMap", "Ljava/util/Map;", "getAvcBitrateResolutionMap", "()Ljava/util/Map;", "hevcBitrateResolutionMap", "getHevcBitrateResolutionMap", "<init>", "()V", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0})
public final class HighFrameRateQualityHolder implements HighFrameRateQualityHolderInterface {
    public static final HighFrameRateQualityHolder INSTANCE;
    private static final List<QualityCappingUpdateListener> QUALITY_CAPPING_UPDATE_LISTENERS_LIST;
    private static final Map<Integer, VideoResolution> avcBitrateResolutionMap;
    private static final float burstFrameDropDetectionPercentage;
    private static final TimeSpan burstFrameDropWindowLength;
    private static final int burstFrameDropWindowThreshold;
    private static final HighFrameRateQualityConfig config;
    private static final float continualFrameDropDetectionPercentage;
    private static final TimeSpan continualFrameDropWindowLength;
    private static final int continualFrameDropWindowThreshold;
    private static final Map<Integer, VideoResolution> hevcBitrateResolutionMap;
    private static final float highFrameRateThreshold;
    private static final boolean isHFRPerformanceEvaluatorEnabled;
    private static final boolean isPlayerRestartOnHFRPerformanceAnomalyEnabled;
    private static final boolean isSDHighFrameRateFallbackAllowed;
    private static final float minimumFrameRateRendererPerformanceEvaluation;
    private static final boolean shouldApplyDynamicQualityCap;
    private static final boolean shouldApplyHFRCappingLive;
    private static final boolean shouldApplyHFRCappingNonLive;
    private static final boolean shouldReportAllFrameDropAnomalies;
    private static final boolean shouldRestartPlayerOnHFRDynamicCapping;
    private static final boolean shouldTrackFrameDropBurst;
    private static final boolean shouldTrackFrameDropContinual;

    /* JADX INFO: compiled from: HighFrameRateQualityHolder.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VideoStreamType.values().length];
            iArr[VideoStreamType.H264.ordinal()] = 1;
            iArr[VideoStreamType.H265.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private HighFrameRateQualityHolder() {
    }

    static {
        HighFrameRateQualityHolder highFrameRateQualityHolder = new HighFrameRateQualityHolder();
        INSTANCE = highFrameRateQualityHolder;
        HighFrameRateQualityConfig highFrameRateQualityConfig = HighFrameRateQualityConfig.INSTANCE;
        config = highFrameRateQualityConfig;
        minimumFrameRateRendererPerformanceEvaluation = highFrameRateQualityConfig.getMinimumFrameRateRendererPerformanceEvaluation();
        isHFRPerformanceEvaluatorEnabled = highFrameRateQualityConfig.isHFRPerformanceEvaluatorEnabled();
        highFrameRateThreshold = highFrameRateQualityConfig.getHighFrameRateThreshold();
        shouldApplyDynamicQualityCap = highFrameRateQualityConfig.getShouldApplyDynamicQualityCap();
        shouldApplyHFRCappingLive = highFrameRateQualityConfig.getShouldApplyHFRCappingLive();
        shouldApplyHFRCappingNonLive = highFrameRateQualityConfig.getShouldApplyHFRCappingNonLive();
        shouldTrackFrameDropBurst = highFrameRateQualityConfig.getShouldTrackFrameDropBurst();
        burstFrameDropWindowLength = highFrameRateQualityConfig.getBurstFrameDropWindowLength();
        burstFrameDropWindowThreshold = highFrameRateQualityConfig.getBurstFrameDropWindowThreshold();
        burstFrameDropDetectionPercentage = highFrameRateQualityConfig.getBurstFrameDropDetectionPercentage();
        shouldTrackFrameDropContinual = highFrameRateQualityConfig.getShouldTrackFrameDropContinual();
        continualFrameDropWindowLength = highFrameRateQualityConfig.getContinualFrameDropWindowLength();
        continualFrameDropWindowThreshold = highFrameRateQualityConfig.getContinualFrameDropWindowThreshold();
        continualFrameDropDetectionPercentage = highFrameRateQualityConfig.getContinualFrameDropDetectionPercentage();
        isSDHighFrameRateFallbackAllowed = highFrameRateQualityConfig.isSDHighFrameRateFallbackAllowed();
        shouldReportAllFrameDropAnomalies = highFrameRateQualityConfig.getShouldReportAllFrameDropAnomalies();
        isPlayerRestartOnHFRPerformanceAnomalyEnabled = highFrameRateQualityConfig.isPlayerRestartOnHFRPerformanceAnomalyEnabled();
        shouldRestartPlayerOnHFRDynamicCapping = highFrameRateQualityConfig.getShouldRestartPlayerOnHFRDynamicCapping();
        QUALITY_CAPPING_UPDATE_LISTENERS_LIST = new ArrayList();
        avcBitrateResolutionMap = new ConcurrentHashMap();
        hevcBitrateResolutionMap = new ConcurrentHashMap();
        if (highFrameRateQualityConfig.getClearStateInternalConfig().getValue().equals(highFrameRateQualityConfig.getClearStateConfig())) {
            return;
        }
        highFrameRateQualityConfig.getClearStateInternalConfig().updateValue(highFrameRateQualityConfig.getClearStateConfig());
        highFrameRateQualityHolder.clearDynamicQualityCappingConfigs();
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public float getMinimumFrameRateRendererPerformanceEvaluation() {
        return minimumFrameRateRendererPerformanceEvaluation;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean isHFRPerformanceEvaluatorEnabled() {
        return isHFRPerformanceEvaluatorEnabled;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean getShouldApplyDynamicQualityCap() {
        return shouldApplyDynamicQualityCap;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean getShouldApplyHFRCappingLive() {
        return shouldApplyHFRCappingLive;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean getShouldApplyHFRCappingNonLive() {
        return shouldApplyHFRCappingNonLive;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean getShouldTrackFrameDropBurst() {
        return shouldTrackFrameDropBurst;
    }

    public TimeSpan getBurstFrameDropWindowLength() {
        return burstFrameDropWindowLength;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public int getBurstFrameDropWindowThreshold() {
        return burstFrameDropWindowThreshold;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public float getBurstFrameDropDetectionPercentage() {
        return burstFrameDropDetectionPercentage;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean getShouldTrackFrameDropContinual() {
        return shouldTrackFrameDropContinual;
    }

    public TimeSpan getContinualFrameDropWindowLength() {
        return continualFrameDropWindowLength;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public int getContinualFrameDropWindowThreshold() {
        return continualFrameDropWindowThreshold;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public float getContinualFrameDropDetectionPercentage() {
        return continualFrameDropDetectionPercentage;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean isSDHighFrameRateFallbackAllowed() {
        return isSDHighFrameRateFallbackAllowed;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean getShouldReportAllFrameDropAnomalies() {
        return shouldReportAllFrameDropAnomalies;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean isPlayerRestartOnHFRPerformanceAnomalyEnabled() {
        return isPlayerRestartOnHFRPerformanceAnomalyEnabled;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean getShouldRestartPlayerOnHFRDynamicCapping() {
        return shouldRestartPlayerOnHFRDynamicCapping;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public Map<Integer, VideoResolution> getAvcBitrateResolutionMap() {
        return avcBitrateResolutionMap;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public Map<Integer, VideoResolution> getHevcBitrateResolutionMap() {
        return hevcBitrateResolutionMap;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public Integer getBitrateCap(String fourCC) {
        Integer numValueOf;
        Intrinsics.checkNotNullParameter(fourCC, "fourCC");
        VideoStreamType videoStreamTypeMapVideoCodecToStreamType = ExternalFourCCMapper.INSTANCE.mapVideoCodecToStreamType(fourCC);
        int i = videoStreamTypeMapVideoCodecToStreamType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[videoStreamTypeMapVideoCodecToStreamType.ordinal()];
        if (i == 1) {
            HighFrameRateQualityConfig highFrameRateQualityConfig = config;
            int avcHfrStaticStreamingBitrateCap = highFrameRateQualityConfig.getAvcHfrStaticStreamingBitrateCap();
            Integer value = getShouldApplyDynamicQualityCap() ? highFrameRateQualityConfig.getAvcHfrDynamicStreamingBitrateCap().getValue() : 50000000;
            Intrinsics.checkNotNullExpressionValue(value, "if (shouldApplyDynamicQu…alue else maxVideoBitrate");
            numValueOf = Integer.valueOf(Math.min(avcHfrStaticStreamingBitrateCap, value.intValue()));
        } else if (i != 2) {
            numValueOf = null;
        } else {
            HighFrameRateQualityConfig highFrameRateQualityConfig2 = config;
            int hevcHfrStaticStreamingBitrateCap = highFrameRateQualityConfig2.getHevcHfrStaticStreamingBitrateCap();
            Integer value2 = getShouldApplyDynamicQualityCap() ? highFrameRateQualityConfig2.getHevcHfrDynamicStreamingBitrateCap().getValue() : 50000000;
            Intrinsics.checkNotNullExpressionValue(value2, "if (shouldApplyDynamicQu…alue else maxVideoBitrate");
            numValueOf = Integer.valueOf(Math.min(hevcHfrStaticStreamingBitrateCap, value2.intValue()));
        }
        if (numValueOf == null) {
            return null;
        }
        return Integer.valueOf(numValueOf.intValue());
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public VideoResolution.ResolutionBand getStaticResolutionCap(String fourCC) {
        String avcHfrStaticStreamingResolutionCap;
        Intrinsics.checkNotNullParameter(fourCC, "fourCC");
        VideoStreamType videoStreamTypeMapVideoCodecToStreamType = ExternalFourCCMapper.INSTANCE.mapVideoCodecToStreamType(fourCC);
        int i = videoStreamTypeMapVideoCodecToStreamType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[videoStreamTypeMapVideoCodecToStreamType.ordinal()];
        if (i == 1) {
            avcHfrStaticStreamingResolutionCap = config.getAvcHfrStaticStreamingResolutionCap();
        } else {
            avcHfrStaticStreamingResolutionCap = i != 2 ? null : config.getHevcHfrStaticStreamingResolutionCap();
        }
        if (avcHfrStaticStreamingResolutionCap != null) {
            try {
                return VideoResolution.ResolutionBand.valueOf(avcHfrStaticStreamingResolutionCap);
            } catch (IllegalArgumentException e) {
                DLog.exceptionf(e, Intrinsics.stringPlus("No valid mapping for given resolution ", avcHfrStaticStreamingResolutionCap), new Object[0]);
                return VideoResolution.ResolutionBand.UNKNOWN;
            }
        }
        return VideoResolution.ResolutionBand.UNKNOWN;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public VideoResolution.ResolutionBand getDynamicResolutionCap(String fourCC) {
        ConfigurationValue<String> avcHfrDynamicStreamingResolutionCap;
        Intrinsics.checkNotNullParameter(fourCC, "fourCC");
        VideoStreamType videoStreamTypeMapVideoCodecToStreamType = ExternalFourCCMapper.INSTANCE.mapVideoCodecToStreamType(fourCC);
        int i = videoStreamTypeMapVideoCodecToStreamType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[videoStreamTypeMapVideoCodecToStreamType.ordinal()];
        if (i == 1) {
            avcHfrDynamicStreamingResolutionCap = config.getAvcHfrDynamicStreamingResolutionCap();
        } else {
            avcHfrDynamicStreamingResolutionCap = i != 2 ? null : config.getHevcHfrDynamicStreamingResolutionCap();
        }
        if (avcHfrDynamicStreamingResolutionCap != null) {
            try {
                String value = avcHfrDynamicStreamingResolutionCap.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "it.value");
                return VideoResolution.ResolutionBand.valueOf(value);
            } catch (IllegalArgumentException e) {
                DLog.exceptionf(e, Intrinsics.stringPlus("No valid mapping for given resolution ", avcHfrDynamicStreamingResolutionCap), new Object[0]);
                return VideoResolution.ResolutionBand.UNKNOWN;
            }
        }
        return VideoResolution.ResolutionBand.UNKNOWN;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public void addBitrateResolutionMapping(int bitrate, int width, int height, String fourCC) {
        Intrinsics.checkNotNullParameter(fourCC, "fourCC");
        if (Intrinsics.areEqual(fourCC, VideoStreamType.H264.getFourCC())) {
            getAvcBitrateResolutionMap().put(Integer.valueOf(bitrate), new VideoResolution(width, height));
        } else if (Intrinsics.areEqual(fourCC, VideoStreamType.H265.getFourCC())) {
            getHevcBitrateResolutionMap().put(Integer.valueOf(bitrate), new VideoResolution(width, height));
        }
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public void clearBitrateResolutionMapping() {
        getAvcBitrateResolutionMap().clear();
        getHevcBitrateResolutionMap().clear();
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public void updateBitrateCap(int bitrateCap, String fourCC) {
        Intrinsics.checkNotNullParameter(fourCC, "fourCC");
        DLog.logf("HFRQuality performanceCappedBitrate " + bitrateCap + " for " + fourCC);
        if (Intrinsics.areEqual(fourCC, VideoStreamType.H264.getFourCC())) {
            config.getAvcHfrDynamicStreamingBitrateCap().updateValue(Integer.valueOf(bitrateCap));
        } else if (Intrinsics.areEqual(fourCC, VideoStreamType.H265.getFourCC())) {
            config.getHevcHfrDynamicStreamingBitrateCap().updateValue(Integer.valueOf(bitrateCap));
        }
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public void updateResolutionCap(String resolutionCap, String fourCC) {
        Intrinsics.checkNotNullParameter(resolutionCap, "resolutionCap");
        Intrinsics.checkNotNullParameter(fourCC, "fourCC");
        DLog.logf("HFRQuality performanceCappedResolution " + resolutionCap + " for " + fourCC);
        if (Intrinsics.areEqual(fourCC, VideoStreamType.H264.getFourCC())) {
            config.getAvcHfrDynamicStreamingResolutionCap().updateValue(resolutionCap);
        } else if (Intrinsics.areEqual(fourCC, VideoStreamType.H265.getFourCC())) {
            config.getHevcHfrDynamicStreamingResolutionCap().updateValue(resolutionCap);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean isHighFrameRate(float givenFrameRate) {
        return givenFrameRate >= highFrameRateThreshold;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public void registerBitrateCappingUpdateListener(QualityCappingUpdateListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        QUALITY_CAPPING_UPDATE_LISTENERS_LIST.add(listener);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    @SuppressLint({"NewApi"})
    public void broadcastBitrateCappingUpdate() {
        Iterator<T> it = QUALITY_CAPPING_UPDATE_LISTENERS_LIST.iterator();
        while (it.hasNext()) {
            ((QualityCappingUpdateListener) it.next()).update();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public void clearBitrateCappingUpdateListenersList() {
        QUALITY_CAPPING_UPDATE_LISTENERS_LIST.clear();
    }

    private final void clearDynamicQualityCappingConfigs() {
        HighFrameRateQualityConfig highFrameRateQualityConfig = config;
        highFrameRateQualityConfig.getAvcHfrDynamicStreamingBitrateCap().updateValue(50000000);
        highFrameRateQualityConfig.getHevcHfrDynamicStreamingBitrateCap().updateValue(50000000);
        highFrameRateQualityConfig.getAvcHfrDynamicStreamingResolutionCap().updateValue("ULTRA_HD");
        highFrameRateQualityConfig.getHevcHfrDynamicStreamingResolutionCap().updateValue("ULTRA_HD");
        highFrameRateQualityConfig.isHFRPlaybackAllowedByPerfEvaluator().updateValue(Boolean.TRUE);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public void disableHFRPlaybackForPerfIssues() {
        config.isHFRPlaybackAllowedByPerfEvaluator().updateValue(Boolean.FALSE);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface
    public boolean isHFRPlaybackAllowedByPerfEvaluator() {
        Boolean value = config.isHFRPlaybackAllowedByPerfEvaluator().getValue();
        Intrinsics.checkNotNullExpressionValue(value, "config.isHFRPlaybackAllowedByPerfEvaluator.value");
        return value.booleanValue();
    }
}
