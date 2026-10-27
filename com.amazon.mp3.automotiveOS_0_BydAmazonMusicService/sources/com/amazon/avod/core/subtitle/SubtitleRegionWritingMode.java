package com.amazon.avod.core.subtitle;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleRegionWritingMode {
    LRTB("lrtb"),
    RLTB("rltb"),
    TBRL("tbrl"),
    TBLR("tblr"),
    LR("lr"),
    RL("rl"),
    TB("tb");

    private final String mAttribute;

    SubtitleRegionWritingMode(@Nullable String str) {
        this.mAttribute = str;
    }

    public static SubtitleRegionWritingMode findMatch(String str) {
        if (str == null) {
            return LRTB;
        }
        for (SubtitleRegionWritingMode subtitleRegionWritingMode : values()) {
            if (str.equalsIgnoreCase(subtitleRegionWritingMode.mAttribute)) {
                return subtitleRegionWritingMode;
            }
        }
        return LRTB;
    }
}
