package com.amazon.avod.core.subtitle;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleTextCombineType {
    NONE("none"),
    ALL("all"),
    UNRECOGNIZED(null);

    private final String mAttribute;

    SubtitleTextCombineType(@Nullable String str) {
        this.mAttribute = str;
    }

    public static SubtitleTextCombineType findMatch(String str) {
        if (str == null) {
            return UNRECOGNIZED;
        }
        for (SubtitleTextCombineType subtitleTextCombineType : values()) {
            if (str.equalsIgnoreCase(subtitleTextCombineType.mAttribute)) {
                return subtitleTextCombineType;
            }
        }
        return UNRECOGNIZED;
    }
}
