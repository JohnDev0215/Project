package com.amazon.avod.core.subtitle;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleTextStylingType {
    NONE("none"),
    CONTAINER("container"),
    BASE("base"),
    BASE_CONTAINER("baseContainer"),
    TEXT("text"),
    TEXT_CONTAINER("textContainer"),
    DELIMITER("delimiter"),
    UNRECOGNIZED(null);

    private final String mAttribute;

    SubtitleTextStylingType(@Nullable String str) {
        this.mAttribute = str;
    }

    public static SubtitleTextStylingType findMatch(String str) {
        if (str == null) {
            return UNRECOGNIZED;
        }
        for (SubtitleTextStylingType subtitleTextStylingType : values()) {
            if (str.equalsIgnoreCase(subtitleTextStylingType.mAttribute)) {
                return subtitleTextStylingType;
            }
        }
        return UNRECOGNIZED;
    }
}
