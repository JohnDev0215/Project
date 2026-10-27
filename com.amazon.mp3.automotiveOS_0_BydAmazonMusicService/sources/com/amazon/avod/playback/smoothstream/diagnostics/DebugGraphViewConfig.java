package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DebugGraphViewConfig.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006¨\u0006\u001b"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/DebugGraphViewConfig;", "Lcom/amazon/avod/media/framework/config/MediaConfigBase;", "()V", "compactBandwidthGraphHeightFactor", "", "getCompactBandwidthGraphHeightFactor", "()F", "compactBufferGraphHeightFactor", "getCompactBufferGraphHeightFactor", "compactViewGraphWidthFactor", "getCompactViewGraphWidthFactor", "compactViewStartXOffset", "getCompactViewStartXOffset", "compactViewStartYOffset", "getCompactViewStartYOffset", "maxBufferDuration", "Lcom/amazon/avod/media/TimeSpan;", "getMaxBufferDuration", "()Lcom/amazon/avod/media/TimeSpan;", "regularBandwidthGraphHeightFactor", "getRegularBandwidthGraphHeightFactor", "regularBufferGraphHeightFactor", "getRegularBufferGraphHeightFactor", "regularViewStartXOffset", "getRegularViewStartXOffset", "regularViewStartYOffset", "getRegularViewStartYOffset", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DebugGraphViewConfig extends MediaConfigBase {
    public static final DebugGraphViewConfig INSTANCE;
    private static final float compactBandwidthGraphHeightFactor;
    private static final float compactBufferGraphHeightFactor;
    private static final float compactViewGraphWidthFactor;
    private static final float compactViewStartXOffset;
    private static final float compactViewStartYOffset;
    private static final TimeSpan maxBufferDuration;
    private static final float regularBandwidthGraphHeightFactor;
    private static final float regularBufferGraphHeightFactor;
    private static final float regularViewStartXOffset;
    private static final float regularViewStartYOffset;

    private DebugGraphViewConfig() {
    }

    static {
        DebugGraphViewConfig debugGraphViewConfig = new DebugGraphViewConfig();
        INSTANCE = debugGraphViewConfig;
        Float value = debugGraphViewConfig.newFloatConfigValue("debugGraph_compactViewStartXOffset", 0.01f).getValue();
        Intrinsics.checkNotNullExpressionValue(value, "newFloatConfigValue(\"deb…artXOffset\", 0.01f).value");
        compactViewStartXOffset = value.floatValue();
        Float value2 = debugGraphViewConfig.newFloatConfigValue("debugGraph_compactViewStartYOffset", 0.99f).getValue();
        Intrinsics.checkNotNullExpressionValue(value2, "newFloatConfigValue(\"deb…artYOffset\", 0.99f).value");
        compactViewStartYOffset = value2.floatValue();
        Float value3 = debugGraphViewConfig.newFloatConfigValue("debugGraph_compactBandwidthGraphHeightFactor", 0.4f).getValue();
        Intrinsics.checkNotNullExpressionValue(value3, "newFloatConfigValue(\"deb…eightFactor\", 0.4f).value");
        compactBandwidthGraphHeightFactor = value3.floatValue();
        Float value4 = debugGraphViewConfig.newFloatConfigValue("debugGraph_compactBufferGraphHeightFactor", 0.1f).getValue();
        Intrinsics.checkNotNullExpressionValue(value4, "newFloatConfigValue(\"deb…eightFactor\", 0.1f).value");
        compactBufferGraphHeightFactor = value4.floatValue();
        Float value5 = debugGraphViewConfig.newFloatConfigValue("debugGraph_compactViewGraphWidthFactor", 0.5f).getValue();
        Intrinsics.checkNotNullExpressionValue(value5, "newFloatConfigValue(\"deb…WidthFactor\", 0.5f).value");
        compactViewGraphWidthFactor = value5.floatValue();
        Float value6 = debugGraphViewConfig.newFloatConfigValue("debugGraph_regularViewStartXOffset", 0.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value6, "newFloatConfigValue(\"deb…wStartXOffset\", 0f).value");
        regularViewStartXOffset = value6.floatValue();
        Float value7 = debugGraphViewConfig.newFloatConfigValue("debugGraph_regularViewStartYOffset", 1.0f).getValue();
        Intrinsics.checkNotNullExpressionValue(value7, "newFloatConfigValue(\"deb…tartYOffset\", 1.0f).value");
        regularViewStartYOffset = value7.floatValue();
        Float value8 = debugGraphViewConfig.newFloatConfigValue("debugGraph_regularBandwidthGraphHeightFactor", 0.8f).getValue();
        Intrinsics.checkNotNullExpressionValue(value8, "newFloatConfigValue(\"deb…eightFactor\", 0.8f).value");
        regularBandwidthGraphHeightFactor = value8.floatValue();
        Float value9 = debugGraphViewConfig.newFloatConfigValue("debugGraph_regularBufferGraphHeightFactor", 0.2f).getValue();
        Intrinsics.checkNotNullExpressionValue(value9, "newFloatConfigValue(\"deb…eightFactor\", 0.2f).value");
        regularBufferGraphHeightFactor = value9.floatValue();
        TimeSpan value10 = debugGraphViewConfig.newTimeConfigurationValue("debugGraph_maxBufferDurationSeconds", TimeSpan.fromSeconds(120.0d), TimeUnit.SECONDS).getValue();
        Intrinsics.checkNotNullExpressionValue(value10, "newTimeConfigurationValu…, TimeUnit.SECONDS).value");
        maxBufferDuration = value10;
    }

    public final float getCompactViewStartXOffset() {
        return compactViewStartXOffset;
    }

    public final float getCompactViewStartYOffset() {
        return compactViewStartYOffset;
    }

    public final float getCompactBandwidthGraphHeightFactor() {
        return compactBandwidthGraphHeightFactor;
    }

    public final float getCompactBufferGraphHeightFactor() {
        return compactBufferGraphHeightFactor;
    }

    public final float getCompactViewGraphWidthFactor() {
        return compactViewGraphWidthFactor;
    }

    public final float getRegularViewStartXOffset() {
        return regularViewStartXOffset;
    }

    public final float getRegularViewStartYOffset() {
        return regularViewStartYOffset;
    }

    public final float getRegularBandwidthGraphHeightFactor() {
        return regularBandwidthGraphHeightFactor;
    }

    public final float getRegularBufferGraphHeightFactor() {
        return regularBufferGraphHeightFactor;
    }

    public final TimeSpan getMaxBufferDuration() {
        return maxBufferDuration;
    }
}
