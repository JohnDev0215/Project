package com.amazon.avod.core.subtitle;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleShowBackgroundStatus {
    ALWAYS("always"),
    WHEN_ACTIVE("whenActive"),
    UNRECOGNIZED(null);

    private final String mAttribute;

    SubtitleShowBackgroundStatus(@Nullable String str) {
        this.mAttribute = str;
    }

    public static SubtitleShowBackgroundStatus findMatch(String str) {
        if (str == null) {
            return UNRECOGNIZED;
        }
        for (SubtitleShowBackgroundStatus subtitleShowBackgroundStatus : values()) {
            if (str.equalsIgnoreCase(subtitleShowBackgroundStatus.mAttribute)) {
                return subtitleShowBackgroundStatus;
            }
        }
        return UNRECOGNIZED;
    }
}
