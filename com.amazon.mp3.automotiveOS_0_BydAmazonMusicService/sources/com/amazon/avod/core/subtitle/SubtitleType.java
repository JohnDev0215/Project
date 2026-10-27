package com.amazon.avod.core.subtitle;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleType {
    AUDIO,
    CAPTION,
    SDH,
    SUBTITLE;

    @Nonnull
    public static SubtitleType lookup(@Nonnull String str) {
        Preconditions.checkNotNull(str, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        try {
            return valueOf(str.toUpperCase(Locale.US));
        } catch (IllegalArgumentException unused) {
            return SUBTITLE;
        }
    }
}
