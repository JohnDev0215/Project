package com.amazon.avod.core.subtitle;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleFontStyleType {
    NORMAL("normal"),
    ITALIC("italic"),
    OBLIQUE("oblique");

    private final String mAttribute;

    SubtitleFontStyleType(@Nullable String str) {
        this.mAttribute = str;
    }

    public static SubtitleFontStyleType findMatch(String str) {
        if (str == null) {
            return NORMAL;
        }
        for (SubtitleFontStyleType subtitleFontStyleType : values()) {
            if (str.equalsIgnoreCase(subtitleFontStyleType.mAttribute)) {
                return subtitleFontStyleType;
            }
        }
        return NORMAL;
    }
}
