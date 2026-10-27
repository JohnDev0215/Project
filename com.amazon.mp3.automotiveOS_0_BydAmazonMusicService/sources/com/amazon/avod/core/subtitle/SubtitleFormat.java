package com.amazon.avod.core.subtitle;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleFormat {
    DFXP,
    TTMLv2,
    SubtitleFormat;

    @Nonnull
    public static SubtitleFormat lookup(@Nonnull String str) {
        Preconditions.checkNotNull(str, "format");
        for (SubtitleFormat subtitleFormat : values()) {
            if (subtitleFormat.name().equalsIgnoreCase(str)) {
                return subtitleFormat;
            }
        }
        return DFXP;
    }
}
