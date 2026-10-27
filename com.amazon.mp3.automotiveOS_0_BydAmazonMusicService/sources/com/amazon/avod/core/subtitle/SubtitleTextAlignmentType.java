package com.amazon.avod.core.subtitle;

import com.google.firebase.perf.plugin.util.AsmString;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleTextAlignmentType {
    LEFT("left"),
    RIGHT("right"),
    CENTER("center"),
    START(AsmString.METHOD_FIREBASE_PERF_TRACE_START),
    END("end"),
    JUSTIFY("justify"),
    UNRECOGNIZED(null);

    private final String mAttribute;

    SubtitleTextAlignmentType(@Nullable String str) {
        this.mAttribute = str;
    }

    public static SubtitleTextAlignmentType findMatch(String str) {
        if (str == null) {
            return UNRECOGNIZED;
        }
        for (SubtitleTextAlignmentType subtitleTextAlignmentType : values()) {
            if (str.equalsIgnoreCase(subtitleTextAlignmentType.mAttribute)) {
                return subtitleTextAlignmentType;
            }
        }
        return UNRECOGNIZED;
    }
}
