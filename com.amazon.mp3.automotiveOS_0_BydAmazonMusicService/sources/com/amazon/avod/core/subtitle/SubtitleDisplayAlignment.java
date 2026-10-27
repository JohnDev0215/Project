package com.amazon.avod.core.subtitle;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleDisplayAlignment {
    BEFORE("before"),
    AFTER("after"),
    CENTER("center"),
    JUSTIFY("justify"),
    UNRECOGNIZED(null);

    private final String mAttribute;

    SubtitleDisplayAlignment(@Nullable String str) {
        this.mAttribute = str;
    }

    public static SubtitleDisplayAlignment findMatch(String str) {
        if (str == null) {
            return UNRECOGNIZED;
        }
        for (SubtitleDisplayAlignment subtitleDisplayAlignment : values()) {
            if (str.equalsIgnoreCase(subtitleDisplayAlignment.mAttribute)) {
                return subtitleDisplayAlignment;
            }
        }
        return UNRECOGNIZED;
    }
}
