package com.amazon.avod.core.subtitle;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleSubtype {
    COMMENTARY,
    DESCRIPTIVE,
    DIALOG;

    @Nonnull
    public static SubtitleSubtype lookup(@Nonnull String str) {
        Preconditions.checkNotNull(str, "subtype");
        try {
            return valueOf(str.toUpperCase(Locale.US));
        } catch (IllegalArgumentException unused) {
            return DIALOG;
        }
    }
}
