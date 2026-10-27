package com.amazon.avod.core.subtitle;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleRubyPosition {
    AFTER("after"),
    BEFORE("before"),
    OUTSIDE("outside"),
    UNRECOGNIZED(null);

    private final String mAttribute;

    SubtitleRubyPosition(@Nullable String str) {
        this.mAttribute = str;
    }

    public static SubtitleRubyPosition findMatch(String str) {
        if (str == null) {
            return UNRECOGNIZED;
        }
        for (SubtitleRubyPosition subtitleRubyPosition : values()) {
            if (str.equalsIgnoreCase(subtitleRubyPosition.mAttribute)) {
                return subtitleRubyPosition;
            }
        }
        return UNRECOGNIZED;
    }
}
