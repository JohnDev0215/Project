package com.amazon.avod.playback.drm;

import com.amazon.avod.util.DLog;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum DrmSecurityLevel {
    LEVEL_1("L1"),
    LEVEL_2("L2"),
    LEVEL_3("L3"),
    NOT_PROVISIONED("NOT_PROVISIONED"),
    SL_150("SL_150"),
    SL_2000("SL_2000"),
    SL_3000("SL_3000"),
    UNKNOWN("UNKNOWN");

    private final String mSecurityLevelString;

    DrmSecurityLevel(String str) {
        this.mSecurityLevelString = str;
    }

    @Nonnull
    public static DrmSecurityLevel fromSecurityLevelString(@Nullable String str) {
        if (str == null) {
            return UNKNOWN;
        }
        String strTrim = str.trim();
        DrmSecurityLevel drmSecurityLevel = LEVEL_1;
        if (strTrim.equalsIgnoreCase(drmSecurityLevel.toString())) {
            return drmSecurityLevel;
        }
        DrmSecurityLevel drmSecurityLevel2 = LEVEL_2;
        if (strTrim.equalsIgnoreCase(drmSecurityLevel2.toString())) {
            return drmSecurityLevel2;
        }
        DrmSecurityLevel drmSecurityLevel3 = LEVEL_3;
        if (strTrim.equalsIgnoreCase(drmSecurityLevel3.toString())) {
            return drmSecurityLevel3;
        }
        DrmSecurityLevel drmSecurityLevel4 = NOT_PROVISIONED;
        if (strTrim.equalsIgnoreCase(drmSecurityLevel4.toString())) {
            return drmSecurityLevel4;
        }
        DrmSecurityLevel drmSecurityLevel5 = SL_150;
        if (strTrim.equalsIgnoreCase(drmSecurityLevel5.toString())) {
            return drmSecurityLevel5;
        }
        DrmSecurityLevel drmSecurityLevel6 = SL_2000;
        if (strTrim.equalsIgnoreCase(drmSecurityLevel6.toString())) {
            return drmSecurityLevel6;
        }
        DrmSecurityLevel drmSecurityLevel7 = SL_3000;
        if (strTrim.equalsIgnoreCase(drmSecurityLevel7.toString())) {
            return drmSecurityLevel7;
        }
        DLog.warnf("No matching Drm security level found for: %s", str);
        return UNKNOWN;
    }

    @Override // java.lang.Enum
    @Nonnull
    public String toString() {
        return this.mSecurityLevelString;
    }
}
