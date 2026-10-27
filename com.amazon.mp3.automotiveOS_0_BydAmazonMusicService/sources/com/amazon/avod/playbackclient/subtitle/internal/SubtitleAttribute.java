package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.playbackclient.subtitle.internal.attributes.Dimension;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleAttribute {
    ID("id"),
    LANGUAGE("lang"),
    SPACE("space"),
    BEGIN("begin"),
    DUR("dur"),
    END("end"),
    REGION("region"),
    STYLE("style"),
    BACKGROUND_COLOR("backgroundColor"),
    COLOR("color"),
    DIRECTION("direction"),
    DISPLAY("display"),
    FONT_FAMILY("fontFamily"),
    FONT_SIZE("fontSize"),
    FONT_STYLE("fontStyle"),
    FONT_WEIGHT("fontWeight"),
    FONT_SHEAR("fontShear"),
    LINE_HEIGHT("lineHeight"),
    RUBY("ruby"),
    RUBY_POSITION("rubyPosition"),
    RB_FONT_SIZE("rbFontSize"),
    TEXT_ALIGN("textAlign"),
    TEXT_DECORATION("textDecoration"),
    TEXT_OUTLINE("textOutline"),
    TEXT_COMBINE("textCombine"),
    TEXT_EMPHASIS("textEmphasis"),
    UNICODE_BIDI("unicodeBidi"),
    VISIBILITY("visibility"),
    WRAP_OPTION("wrapOption"),
    DISPLAY_ALIGN("displayAlign"),
    EXTENT("extent"),
    OPACITY("opacity"),
    ORIGIN("origin"),
    OVERFLOW("overflow"),
    PADDING("padding"),
    POSITION("position"),
    SHOW_BACKGROUND("showBackground"),
    WRITING_MODE("writingMode"),
    Z_INDEX("zIndex"),
    UNRECOGNIZED(null);

    String mAttribute;

    public static class Defaults {
        public static final Dimension ORIGIN = Dimension.from("10% 80%");
        public static final Dimension EXTENT = Dimension.from("80% 15%");
    }

    SubtitleAttribute(String str) {
        this.mAttribute = str;
    }

    static SubtitleAttribute findMatch(String str) {
        if (str == null) {
            return UNRECOGNIZED;
        }
        for (SubtitleAttribute subtitleAttribute : values()) {
            if (str.equalsIgnoreCase(subtitleAttribute.mAttribute)) {
                return subtitleAttribute;
            }
        }
        return UNRECOGNIZED;
    }
}
