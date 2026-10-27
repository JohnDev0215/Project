package com.amazon.video.sdk.stream;

import com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderer;
import com.amazon.avod.util.Preconditions2;
import com.amazon.video.sdk.avod.playbackclient.subtitle.SubtitlePreset;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimedTextUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000\u001a\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0000\u001a\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0000\u001a\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0000\"0\u0010\u0013\u001a\u001e\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00040\u0004\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00120\u00120\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014\"0\u0010\u0017\u001a\u001e\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00150\u0015\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00160\u00160\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0014\"0\u0010\u0019\u001a\u001e\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\b0\b\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00180\u00180\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0014\"0\u0010\u001b\u001a\u001e\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\f0\f\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u001a0\u001a0\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u0014\"0\u0010\u001d\u001a\u001e\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00000\u0000\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u001c0\u001c0\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextFontStyle;", "fontStyle", "Lcom/amazon/video/sdk/avod/playbackclient/subtitle/SubtitlePreset$Font;", "getTimedTextFontStyle", "Lcom/amazon/video/sdk/stream/TimedTextColor;", "color", "", "getTimedTextColor", "Lcom/amazon/video/sdk/stream/TimedTextFontSize;", "fontSize", "", "getFontSize", "Lcom/amazon/video/sdk/stream/TimedTextCharacterEdgeType;", "edgeType", "Lcom/amazon/avod/playbackclient/subtitle/views/util/EdgeRenderer$EdgeType;", "getEdgeType", "Lcom/google/common/collect/ImmutableMap;", "kotlin.jvm.PlatformType", "Lcom/amazon/video/sdk/stream/TimedTextColorMapping;", "TIMED_TEXT_COLOR_TYPES", "Lcom/google/common/collect/ImmutableMap;", "Lcom/amazon/video/sdk/stream/TimedTextOpacity;", "Lcom/amazon/video/sdk/stream/TimedTextOpacityMapping;", "TIMED_TEXT_OPACITY_TYPES", "Lcom/amazon/video/sdk/stream/TimedTextFontSizeMapping;", "TIMED_TEXT_FONT_SIZE_TYPES", "Lcom/amazon/video/sdk/stream/TimedTextCharacterEdgeTypeMapping;", "TIMED_TEXT_CHARACTER_EDGE_TYPES", "Lcom/amazon/video/sdk/stream/TimedTextFontStyleMapping;", "TIMED_TEXT_FONT_STYLE_TYPES", "AmazonAndroidVideoPlayer_release"}, k = 2, mv = {1, 6, 0})
public final class TimedTextUtilsKt {
    private static final ImmutableMap<TimedTextCharacterEdgeType, TimedTextCharacterEdgeTypeMapping> TIMED_TEXT_CHARACTER_EDGE_TYPES;
    private static final ImmutableMap<TimedTextColor, TimedTextColorMapping> TIMED_TEXT_COLOR_TYPES;
    private static final ImmutableMap<TimedTextFontSize, TimedTextFontSizeMapping> TIMED_TEXT_FONT_SIZE_TYPES;
    private static final ImmutableMap<TimedTextFontStyle, TimedTextFontStyleMapping> TIMED_TEXT_FONT_STYLE_TYPES;
    private static final ImmutableMap<TimedTextOpacity, TimedTextOpacityMapping> TIMED_TEXT_OPACITY_TYPES;

    static {
        Map mapCheckFullKeyMapping = Preconditions2.checkFullKeyMapping(TimedTextColor.class, ImmutableMap.builder().put(TimedTextColor.BLACK, TimedTextColorMapping.BLACK).put(TimedTextColor.WHITE, TimedTextColorMapping.WHITE).put(TimedTextColor.RED, TimedTextColorMapping.RED).put(TimedTextColor.GREEN, TimedTextColorMapping.GREEN).put(TimedTextColor.YELLOW, TimedTextColorMapping.YELLOW).put(TimedTextColor.MAGENTA, TimedTextColorMapping.MAGENTA).put(TimedTextColor.CYAN, TimedTextColorMapping.CYAN).put(TimedTextColor.BLUE, TimedTextColorMapping.BLUE).build());
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping, "checkFullKeyMapping(\n   ….BLUE)\n        .build()\n)");
        TIMED_TEXT_COLOR_TYPES = (ImmutableMap) mapCheckFullKeyMapping;
        Map mapCheckFullKeyMapping2 = Preconditions2.checkFullKeyMapping(TimedTextOpacity.class, ImmutableMap.builder().put(TimedTextOpacity.SEMITRANSPARENT, TimedTextOpacityMapping.SEMITRANSPARENT).put(TimedTextOpacity.NORMAL, TimedTextOpacityMapping.NORMAL).build());
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping2, "checkFullKeyMapping(\n   …ORMAL)\n        .build()\n)");
        TIMED_TEXT_OPACITY_TYPES = (ImmutableMap) mapCheckFullKeyMapping2;
        Map mapCheckFullKeyMapping3 = Preconditions2.checkFullKeyMapping(TimedTextFontSize.class, ImmutableMap.builder().put(TimedTextFontSize.SMALL, TimedTextFontSizeMapping.SMALL).put(TimedTextFontSize.MEDIUM, TimedTextFontSizeMapping.MEDIUM).put(TimedTextFontSize.LARGE, TimedTextFontSizeMapping.LARGE).put(TimedTextFontSize.EXTRALARGE, TimedTextFontSizeMapping.EXTRALARGE).build());
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping3, "checkFullKeyMapping(\n   …LARGE)\n        .build()\n)");
        TIMED_TEXT_FONT_SIZE_TYPES = (ImmutableMap) mapCheckFullKeyMapping3;
        Map mapCheckFullKeyMapping4 = Preconditions2.checkFullKeyMapping(TimedTextCharacterEdgeType.class, ImmutableMap.builder().put(TimedTextCharacterEdgeType.NONE, TimedTextCharacterEdgeTypeMapping.NONE).put(TimedTextCharacterEdgeType.RAISED, TimedTextCharacterEdgeTypeMapping.RAISED).put(TimedTextCharacterEdgeType.DEPRESSED, TimedTextCharacterEdgeTypeMapping.DEPRESSED).put(TimedTextCharacterEdgeType.UNIFORM, TimedTextCharacterEdgeTypeMapping.UNIFORM).put(TimedTextCharacterEdgeType.LEFT_DROP_SHADOW, TimedTextCharacterEdgeTypeMapping.LEFT_DROP_SHADOW).put(TimedTextCharacterEdgeType.RIGHT_DROP_SHADOW, TimedTextCharacterEdgeTypeMapping.RIGHT_DROP_SHADOW).build());
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping4, "checkFullKeyMapping(\n   …HADOW)\n        .build()\n)");
        TIMED_TEXT_CHARACTER_EDGE_TYPES = (ImmutableMap) mapCheckFullKeyMapping4;
        Map mapCheckFullKeyMapping5 = Preconditions2.checkFullKeyMapping(TimedTextFontStyle.class, ImmutableMap.builder().put(TimedTextFontStyle.DEFAULT, TimedTextFontStyleMapping.DEFAULT).put(TimedTextFontStyle.MONOSPACE_SERIF, TimedTextFontStyleMapping.MONOSPACE_SERIF).put(TimedTextFontStyle.PROPORTIONAL_SERIF, TimedTextFontStyleMapping.PROPORTIONAL_SERIF).put(TimedTextFontStyle.MONOSPACE_SANS_SERIF, TimedTextFontStyleMapping.MONOSPACE_SANS_SERIF).put(TimedTextFontStyle.PROPORTIONAL_SANS_SERIF, TimedTextFontStyleMapping.PROPORTIONAL_SANS_SERIF).put(TimedTextFontStyle.CASUAL, TimedTextFontStyleMapping.CASUAL).put(TimedTextFontStyle.CURSIVE, TimedTextFontStyleMapping.CURSIVE).put(TimedTextFontStyle.SMALL_CAPITALS, TimedTextFontStyleMapping.SMALL_CAPITALS).build());
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping5, "checkFullKeyMapping(\n   …ITALS)\n        .build()\n)");
        TIMED_TEXT_FONT_STYLE_TYPES = (ImmutableMap) mapCheckFullKeyMapping5;
    }

    public static final SubtitlePreset.Font getTimedTextFontStyle(TimedTextFontStyle fontStyle) {
        Intrinsics.checkNotNullParameter(fontStyle, "fontStyle");
        TimedTextFontStyleMapping timedTextFontStyleMapping = TIMED_TEXT_FONT_STYLE_TYPES.get(fontStyle);
        Intrinsics.checkNotNull(timedTextFontStyleMapping);
        return timedTextFontStyleMapping.getFont();
    }

    public static final int getTimedTextColor(TimedTextColor color) {
        Intrinsics.checkNotNullParameter(color, "color");
        TimedTextColorMapping timedTextColorMapping = TIMED_TEXT_COLOR_TYPES.get(color);
        Intrinsics.checkNotNull(timedTextColorMapping);
        return timedTextColorMapping.getColor();
    }

    public static final float getFontSize(TimedTextFontSize fontSize) {
        Intrinsics.checkNotNullParameter(fontSize, "fontSize");
        TimedTextFontSizeMapping timedTextFontSizeMapping = TIMED_TEXT_FONT_SIZE_TYPES.get(fontSize);
        Intrinsics.checkNotNull(timedTextFontSizeMapping);
        return timedTextFontSizeMapping.getSize();
    }

    public static final EdgeRenderer.EdgeType getEdgeType(TimedTextCharacterEdgeType edgeType) {
        Intrinsics.checkNotNullParameter(edgeType, "edgeType");
        TimedTextCharacterEdgeTypeMapping timedTextCharacterEdgeTypeMapping = TIMED_TEXT_CHARACTER_EDGE_TYPES.get(edgeType);
        Intrinsics.checkNotNull(timedTextCharacterEdgeTypeMapping);
        return timedTextCharacterEdgeTypeMapping.getEdgeType();
    }
}
