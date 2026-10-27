package com.amazon.avod.media.framework.config.profiles;

import com.amazon.avod.util.DLog;
import com.amazon.avod.util.json.NamedEnum;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum ThirdPartyProfileName implements NamedEnum {
    SD,
    SD_MEDIACODEC_SOFTWARE_PLAYREADY,
    HD,
    HD_HEVC,
    HDR,
    UHD_HDR;

    @Nullable
    public static ThirdPartyProfileName fromProfileString(@Nullable String str) {
        if (str == null) {
            return null;
        }
        try {
            return valueOf(str.trim().toUpperCase(Locale.US));
        } catch (IllegalArgumentException unused) {
            DLog.warnf("No matching Profile found for: %s", str);
            return null;
        }
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    @Nonnull
    public String getValue() {
        return name();
    }
}
