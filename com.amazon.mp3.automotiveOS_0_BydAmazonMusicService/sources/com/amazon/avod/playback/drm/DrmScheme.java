package com.amazon.avod.playback.drm;

import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum DrmScheme {
    PLAYREADY,
    WIDEVINE,
    NONE;

    @Nullable
    public static DrmScheme fromSchemeString(@Nullable String str) {
        if (str == null) {
            DLog.warnf("Input scheme string was null, returning null!");
            return null;
        }
        String strTrim = str.trim();
        DrmScheme drmScheme = PLAYREADY;
        if (strTrim.equalsIgnoreCase(drmScheme.toString())) {
            return drmScheme;
        }
        String strTrim2 = str.trim();
        DrmScheme drmScheme2 = WIDEVINE;
        if (strTrim2.equalsIgnoreCase(drmScheme2.toString())) {
            return drmScheme2;
        }
        String strTrim3 = str.trim();
        DrmScheme drmScheme3 = NONE;
        if (strTrim3.equalsIgnoreCase(drmScheme3.toString())) {
            return drmScheme3;
        }
        DLog.warnf("No matching Drm scheme found for: %s", str);
        return null;
    }

    @Override // java.lang.Enum
    @Nonnull
    public String toString() {
        return name();
    }
}
