package com.amazon.avod.content.smoothstream.quality;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HighFrameRateQualityConfig.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0018\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\bJ\u0010KR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR\u0017\u0010\u0012\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0012\u0010\rR\u0017\u0010\u0013\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0014\u0010\rR\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001b\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u0004\u001a\u0004\b \u0010\u0006R\u0017\u0010!\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b!\u0010\u000b\u001a\u0004\b\"\u0010\rR\u0017\u0010#\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b#\u0010\u0017\u001a\u0004\b$\u0010\u0019R\u0017\u0010%\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b%\u0010\u001c\u001a\u0004\b&\u0010\u001eR\u0017\u0010'\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b'\u0010\u0004\u001a\u0004\b(\u0010\u0006R\u0017\u0010)\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b)\u0010\u001c\u001a\u0004\b*\u0010\u001eR\u0017\u0010+\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b+\u0010\u001c\u001a\u0004\b,\u0010\u001eR\u001d\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001a0-8\u0006¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R\u001d\u00102\u001a\b\u0012\u0004\u0012\u00020\u001a0-8\u0006¢\u0006\f\n\u0004\b2\u0010/\u001a\u0004\b3\u00101R\u0017\u00105\u001a\u0002048\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0017\u00109\u001a\u0002048\u0006¢\u0006\f\n\u0004\b9\u00106\u001a\u0004\b:\u00108R\u001d\u0010;\u001a\b\u0012\u0004\u0012\u0002040-8\u0006¢\u0006\f\n\u0004\b;\u0010/\u001a\u0004\b<\u00101R\u001d\u0010=\u001a\b\u0012\u0004\u0012\u0002040-8\u0006¢\u0006\f\n\u0004\b=\u0010/\u001a\u0004\b>\u00101R\u0017\u0010?\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b?\u0010\u000b\u001a\u0004\b?\u0010\rR\u0017\u0010@\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b@\u0010\u000b\u001a\u0004\bA\u0010\rR\u0017\u0010B\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\bB\u0010\u000b\u001a\u0004\bB\u0010\rR\u0017\u0010C\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\bC\u0010\u000b\u001a\u0004\bD\u0010\rR\u001d\u0010E\u001a\b\u0012\u0004\u0012\u00020\t0-8\u0006¢\u0006\f\n\u0004\bE\u0010/\u001a\u0004\bE\u00101R\u0017\u0010F\u001a\u0002048\u0006¢\u0006\f\n\u0004\bF\u00106\u001a\u0004\bG\u00108R\u001d\u0010H\u001a\b\u0012\u0004\u0012\u0002040-8\u0006¢\u0006\f\n\u0004\bH\u0010/\u001a\u0004\bI\u00101¨\u0006L"}, d2 = {"Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateQualityConfig;", "Lcom/amazon/avod/media/framework/config/MediaConfigBase;", "", "minimumFrameRateRendererPerformanceEvaluation", "F", "getMinimumFrameRateRendererPerformanceEvaluation", "()F", "highFrameRateThreshold", "getHighFrameRateThreshold", "", "shouldApplyHFRCappingLive", "Z", "getShouldApplyHFRCappingLive", "()Z", "shouldApplyHFRCappingNonLive", "getShouldApplyHFRCappingNonLive", "shouldApplyDynamicQualityCap", "getShouldApplyDynamicQualityCap", "isHFRPerformanceEvaluatorEnabled", "shouldTrackFrameDropBurst", "getShouldTrackFrameDropBurst", "Lcom/amazon/avod/media/TimeSpan;", "burstFrameDropWindowLength", "Lcom/amazon/avod/media/TimeSpan;", "getBurstFrameDropWindowLength", "()Lcom/amazon/avod/media/TimeSpan;", "", "burstFrameDropWindowThreshold", "I", "getBurstFrameDropWindowThreshold", "()I", "burstFrameDropDetectionPercentage", "getBurstFrameDropDetectionPercentage", "shouldTrackFrameDropContinual", "getShouldTrackFrameDropContinual", "continualFrameDropWindowLength", "getContinualFrameDropWindowLength", "continualFrameDropWindowThreshold", "getContinualFrameDropWindowThreshold", "continualFrameDropDetectionPercentage", "getContinualFrameDropDetectionPercentage", "avcHfrStaticStreamingBitrateCap", "getAvcHfrStaticStreamingBitrateCap", "hevcHfrStaticStreamingBitrateCap", "getHevcHfrStaticStreamingBitrateCap", "Lamazon/android/config/ConfigurationValue;", "avcHfrDynamicStreamingBitrateCap", "Lamazon/android/config/ConfigurationValue;", "getAvcHfrDynamicStreamingBitrateCap", "()Lamazon/android/config/ConfigurationValue;", "hevcHfrDynamicStreamingBitrateCap", "getHevcHfrDynamicStreamingBitrateCap", "", "avcHfrStaticStreamingResolutionCap", "Ljava/lang/String;", "getAvcHfrStaticStreamingResolutionCap", "()Ljava/lang/String;", "hevcHfrStaticStreamingResolutionCap", "getHevcHfrStaticStreamingResolutionCap", "avcHfrDynamicStreamingResolutionCap", "getAvcHfrDynamicStreamingResolutionCap", "hevcHfrDynamicStreamingResolutionCap", "getHevcHfrDynamicStreamingResolutionCap", "isSDHighFrameRateFallbackAllowed", "shouldReportAllFrameDropAnomalies", "getShouldReportAllFrameDropAnomalies", "isPlayerRestartOnHFRPerformanceAnomalyEnabled", "shouldRestartPlayerOnHFRDynamicCapping", "getShouldRestartPlayerOnHFRDynamicCapping", "isHFRPlaybackAllowedByPerfEvaluator", "clearStateConfig", "getClearStateConfig", "clearStateInternalConfig", "getClearStateInternalConfig", "<init>", "()V", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0})
public final class HighFrameRateQualityConfig extends MediaConfigBase {
    public static final HighFrameRateQualityConfig INSTANCE;
    private static final ConfigurationValue<Integer> avcHfrDynamicStreamingBitrateCap;
    private static final ConfigurationValue<String> avcHfrDynamicStreamingResolutionCap;
    private static final int avcHfrStaticStreamingBitrateCap;
    private static final String avcHfrStaticStreamingResolutionCap;
    private static final float burstFrameDropDetectionPercentage;
    private static final TimeSpan burstFrameDropWindowLength;
    private static final int burstFrameDropWindowThreshold;
    private static final String clearStateConfig;
    private static final ConfigurationValue<String> clearStateInternalConfig;
    private static final float continualFrameDropDetectionPercentage;
    private static final TimeSpan continualFrameDropWindowLength;
    private static final int continualFrameDropWindowThreshold;
    private static final ConfigurationValue<Integer> hevcHfrDynamicStreamingBitrateCap;
    private static final ConfigurationValue<String> hevcHfrDynamicStreamingResolutionCap;
    private static final int hevcHfrStaticStreamingBitrateCap;
    private static final String hevcHfrStaticStreamingResolutionCap;
    private static final float highFrameRateThreshold;
    private static final boolean isHFRPerformanceEvaluatorEnabled;
    private static final ConfigurationValue<Boolean> isHFRPlaybackAllowedByPerfEvaluator;
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

    private HighFrameRateQualityConfig() {
    }

    public final float getMinimumFrameRateRendererPerformanceEvaluation() {
        return minimumFrameRateRendererPerformanceEvaluation;
    }

    static {
        HighFrameRateQualityConfig highFrameRateQualityConfig = new HighFrameRateQualityConfig();
        INSTANCE = highFrameRateQualityConfig;
        ConfigType configType = ConfigType.SERVER;
        Float value = highFrameRateQualityConfig.newFloatConfigValue("playback_minimumFrameRateRendererPerformanceEvaluation", 10.0f, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value, "newFloatConfigValue(\n   … ConfigType.SERVER).value");
        minimumFrameRateRendererPerformanceEvaluation = value.floatValue();
        Float value2 = highFrameRateQualityConfig.newFloatConfigValue("playback_highFrameRateThreshold", 45.0f, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value2, "newFloatConfigValue(\n   … ConfigType.SERVER).value");
        highFrameRateThreshold = value2.floatValue();
        Boolean value3 = highFrameRateQualityConfig.newBooleanConfigValue("playback_shouldApplyHFRCappingLive", true, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value3, "newBooleanConfigValue(\n … ConfigType.SERVER).value");
        shouldApplyHFRCappingLive = value3.booleanValue();
        Boolean value4 = highFrameRateQualityConfig.newBooleanConfigValue("playback_shouldApplyHFRCappingNonLive", false, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value4, "newBooleanConfigValue(\n … ConfigType.SERVER).value");
        shouldApplyHFRCappingNonLive = value4.booleanValue();
        Boolean value5 = highFrameRateQualityConfig.newBooleanConfigValue("playback_shouldApplyDynamicQualityCap", false, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value5, "newBooleanConfigValue(\n … ConfigType.SERVER).value");
        shouldApplyDynamicQualityCap = value5.booleanValue();
        Boolean value6 = highFrameRateQualityConfig.newBooleanConfigValue("playback_isHFRPerformanceEvaluatorEnabled", false, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value6, "newBooleanConfigValue(\n … ConfigType.SERVER).value");
        isHFRPerformanceEvaluatorEnabled = value6.booleanValue();
        Boolean value7 = highFrameRateQualityConfig.newBooleanConfigValue("playback_shouldTrackFrameDropBurst", true, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value7, "newBooleanConfigValue(\n … ConfigType.SERVER).value");
        shouldTrackFrameDropBurst = value7.booleanValue();
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(30.0d);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        TimeSpan value8 = highFrameRateQualityConfig.newTimeConfigurationValue("playback_burstFrameDropWindowLengthMillis", timeSpanFromSeconds, timeUnit, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value8, "newTimeConfigurationValu… ConfigType.SERVER).value");
        burstFrameDropWindowLength = value8;
        Integer value9 = highFrameRateQualityConfig.newIntConfigValue("playback_burstFrameDropWindowThreshold", 3, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value9, "newIntConfigValue(\n     … ConfigType.SERVER).value");
        burstFrameDropWindowThreshold = value9.intValue();
        Float value10 = highFrameRateQualityConfig.newFloatConfigValue("playback_burstFrameDropDetectionPercentage", 30.0f, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value10, "newFloatConfigValue(\n   … ConfigType.SERVER).value");
        burstFrameDropDetectionPercentage = value10.floatValue();
        Boolean value11 = highFrameRateQualityConfig.newBooleanConfigValue("playback_shouldTrackFrameDropContinual", true, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value11, "newBooleanConfigValue(\n … ConfigType.SERVER).value");
        shouldTrackFrameDropContinual = value11.booleanValue();
        TimeSpan value12 = highFrameRateQualityConfig.newTimeConfigurationValue("playback_continualFrameDropWindowLengthMillis", TimeSpan.fromSeconds(30.0d), timeUnit, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value12, "newTimeConfigurationValu… ConfigType.SERVER).value");
        continualFrameDropWindowLength = value12;
        Integer value13 = highFrameRateQualityConfig.newIntConfigValue("playback_continualFrameDropWindowThreshold", 10, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value13, "newIntConfigValue(\n     … ConfigType.SERVER).value");
        continualFrameDropWindowThreshold = value13.intValue();
        Float value14 = highFrameRateQualityConfig.newFloatConfigValue("playback_continualFrameDropDetectionPercentage", 5.0f, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value14, "newFloatConfigValue(\n   … ConfigType.SERVER).value");
        continualFrameDropDetectionPercentage = value14.floatValue();
        Integer value15 = highFrameRateQualityConfig.newIntConfigValue("playback_avcHfrStreamingBitrateCap_static", 50000000, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value15, "newIntConfigValue(\n     … ConfigType.SERVER).value");
        avcHfrStaticStreamingBitrateCap = value15.intValue();
        Integer value16 = highFrameRateQualityConfig.newIntConfigValue("playback_hevcHfrStreamingBitrateCap_static", 50000000, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value16, "newIntConfigValue(\n     … ConfigType.SERVER).value");
        hevcHfrStaticStreamingBitrateCap = value16.intValue();
        ConfigType configType2 = ConfigType.INTERNAL;
        ConfigurationValue<Integer> configurationValueNewIntConfigValue = highFrameRateQualityConfig.newIntConfigValue("playback_avcHfrStreamingBitrateCap_dynamic", 50000000, configType2);
        Intrinsics.checkNotNullExpressionValue(configurationValueNewIntConfigValue, "newIntConfigValue(\n     …     ConfigType.INTERNAL)");
        avcHfrDynamicStreamingBitrateCap = configurationValueNewIntConfigValue;
        ConfigurationValue<Integer> configurationValueNewIntConfigValue2 = highFrameRateQualityConfig.newIntConfigValue("playback_hevcHfrStreamingBitrateCap_dynamic", 50000000, configType2);
        Intrinsics.checkNotNullExpressionValue(configurationValueNewIntConfigValue2, "newIntConfigValue(\n     …     ConfigType.INTERNAL)");
        hevcHfrDynamicStreamingBitrateCap = configurationValueNewIntConfigValue2;
        String value17 = highFrameRateQualityConfig.newStringConfigValue("playback_avcHfrStreamingResolutionCap_static", "ULTRA_HD", configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value17, "newStringConfigValue(\n  … ConfigType.SERVER).value");
        avcHfrStaticStreamingResolutionCap = value17;
        String value18 = highFrameRateQualityConfig.newStringConfigValue("playback_hevcHfrStreamingResolutionCap_static", "ULTRA_HD", configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value18, "newStringConfigValue(\n  … ConfigType.SERVER).value");
        hevcHfrStaticStreamingResolutionCap = value18;
        ConfigurationValue<String> configurationValueNewStringConfigValue = highFrameRateQualityConfig.newStringConfigValue("playback_avcHfrStreamingResolutionCap_dynamic", "ULTRA_HD", configType2);
        Intrinsics.checkNotNullExpressionValue(configurationValueNewStringConfigValue, "newStringConfigValue(\n  …     ConfigType.INTERNAL)");
        avcHfrDynamicStreamingResolutionCap = configurationValueNewStringConfigValue;
        ConfigurationValue<String> configurationValueNewStringConfigValue2 = highFrameRateQualityConfig.newStringConfigValue("playback_hevcHfrStreamingResolutionCap_dynamic", "ULTRA_HD", configType2);
        Intrinsics.checkNotNullExpressionValue(configurationValueNewStringConfigValue2, "newStringConfigValue(\n  …     ConfigType.INTERNAL)");
        hevcHfrDynamicStreamingResolutionCap = configurationValueNewStringConfigValue2;
        Boolean value19 = highFrameRateQualityConfig.newBooleanConfigValue("playback_isSDHighFrameRateFallbackAllowed", false, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value19, "newBooleanConfigValue(\n … ConfigType.SERVER).value");
        isSDHighFrameRateFallbackAllowed = value19.booleanValue();
        Boolean value20 = highFrameRateQualityConfig.newBooleanConfigValue("playback_shouldReportAllFrameDropAnomalies", true, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value20, "newBooleanConfigValue(\n … ConfigType.SERVER).value");
        shouldReportAllFrameDropAnomalies = value20.booleanValue();
        Boolean value21 = highFrameRateQualityConfig.newBooleanConfigValue("playback_isPlayerRestartOnHFRPerformanceAnomalyEnabled", true, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value21, "newBooleanConfigValue(\n … ConfigType.SERVER).value");
        isPlayerRestartOnHFRPerformanceAnomalyEnabled = value21.booleanValue();
        Boolean value22 = highFrameRateQualityConfig.newBooleanConfigValue("playback_shouldRestartPlayerOnHFRDynamicCapping", true, configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value22, "newBooleanConfigValue(\n … ConfigType.SERVER).value");
        shouldRestartPlayerOnHFRDynamicCapping = value22.booleanValue();
        ConfigurationValue<Boolean> configurationValueNewBooleanConfigValue = highFrameRateQualityConfig.newBooleanConfigValue("playback_isHFRPlaybackAllowedByPerfEvaluator", true, configType2);
        Intrinsics.checkNotNullExpressionValue(configurationValueNewBooleanConfigValue, "newBooleanConfigValue(\n …rue, ConfigType.INTERNAL)");
        isHFRPlaybackAllowedByPerfEvaluator = configurationValueNewBooleanConfigValue;
        String value23 = highFrameRateQualityConfig.newStringConfigValue("highFrameRateQualityConfig_clearStateConfig", "TRY_0", configType).getValue();
        Intrinsics.checkNotNullExpressionValue(value23, "newStringConfigValue(\n  … ConfigType.SERVER).value");
        clearStateConfig = value23;
        ConfigurationValue<String> configurationValueNewStringConfigValue3 = highFrameRateQualityConfig.newStringConfigValue("highFrameRateQualityConfig_clearStateInternalConfig", "NA", configType2);
        Intrinsics.checkNotNullExpressionValue(configurationValueNewStringConfigValue3, "newStringConfigValue(\n  …NA\", ConfigType.INTERNAL)");
        clearStateInternalConfig = configurationValueNewStringConfigValue3;
    }

    public final float getHighFrameRateThreshold() {
        return highFrameRateThreshold;
    }

    public final boolean getShouldApplyHFRCappingLive() {
        return shouldApplyHFRCappingLive;
    }

    public final boolean getShouldApplyHFRCappingNonLive() {
        return shouldApplyHFRCappingNonLive;
    }

    public final boolean getShouldApplyDynamicQualityCap() {
        return shouldApplyDynamicQualityCap;
    }

    public final boolean isHFRPerformanceEvaluatorEnabled() {
        return isHFRPerformanceEvaluatorEnabled;
    }

    public final boolean getShouldTrackFrameDropBurst() {
        return shouldTrackFrameDropBurst;
    }

    public final TimeSpan getBurstFrameDropWindowLength() {
        return burstFrameDropWindowLength;
    }

    public final int getBurstFrameDropWindowThreshold() {
        return burstFrameDropWindowThreshold;
    }

    public final float getBurstFrameDropDetectionPercentage() {
        return burstFrameDropDetectionPercentage;
    }

    public final boolean getShouldTrackFrameDropContinual() {
        return shouldTrackFrameDropContinual;
    }

    public final TimeSpan getContinualFrameDropWindowLength() {
        return continualFrameDropWindowLength;
    }

    public final int getContinualFrameDropWindowThreshold() {
        return continualFrameDropWindowThreshold;
    }

    public final float getContinualFrameDropDetectionPercentage() {
        return continualFrameDropDetectionPercentage;
    }

    public final int getAvcHfrStaticStreamingBitrateCap() {
        return avcHfrStaticStreamingBitrateCap;
    }

    public final int getHevcHfrStaticStreamingBitrateCap() {
        return hevcHfrStaticStreamingBitrateCap;
    }

    public final ConfigurationValue<Integer> getAvcHfrDynamicStreamingBitrateCap() {
        return avcHfrDynamicStreamingBitrateCap;
    }

    public final ConfigurationValue<Integer> getHevcHfrDynamicStreamingBitrateCap() {
        return hevcHfrDynamicStreamingBitrateCap;
    }

    public final String getAvcHfrStaticStreamingResolutionCap() {
        return avcHfrStaticStreamingResolutionCap;
    }

    public final String getHevcHfrStaticStreamingResolutionCap() {
        return hevcHfrStaticStreamingResolutionCap;
    }

    public final ConfigurationValue<String> getAvcHfrDynamicStreamingResolutionCap() {
        return avcHfrDynamicStreamingResolutionCap;
    }

    public final ConfigurationValue<String> getHevcHfrDynamicStreamingResolutionCap() {
        return hevcHfrDynamicStreamingResolutionCap;
    }

    public final boolean isSDHighFrameRateFallbackAllowed() {
        return isSDHighFrameRateFallbackAllowed;
    }

    public final boolean getShouldReportAllFrameDropAnomalies() {
        return shouldReportAllFrameDropAnomalies;
    }

    public final boolean isPlayerRestartOnHFRPerformanceAnomalyEnabled() {
        return isPlayerRestartOnHFRPerformanceAnomalyEnabled;
    }

    public final boolean getShouldRestartPlayerOnHFRDynamicCapping() {
        return shouldRestartPlayerOnHFRDynamicCapping;
    }

    public final ConfigurationValue<Boolean> isHFRPlaybackAllowedByPerfEvaluator() {
        return isHFRPlaybackAllowedByPerfEvaluator;
    }

    public final String getClearStateConfig() {
        return clearStateConfig;
    }

    public final ConfigurationValue<String> getClearStateInternalConfig() {
        return clearStateInternalConfig;
    }
}
