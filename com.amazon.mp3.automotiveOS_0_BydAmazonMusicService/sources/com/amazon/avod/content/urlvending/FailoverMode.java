package com.amazon.avod.content.urlvending;

import com.amazon.avod.util.DLog;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum FailoverMode {
    SEAMLESS("seamless"),
    MANIFEST("manifest"),
    DISCONTINUOUS("discontinuous");

    private final String mMode;

    FailoverMode(@Nonnull String str) {
        this.mMode = (String) Preconditions.checkNotNull(str, "mode");
    }

    @JsonCreator
    @Nonnull
    public static FailoverMode fromModeString(@Nullable String str) {
        if (str == null) {
            return DISCONTINUOUS;
        }
        String strTrim = str.trim();
        FailoverMode failoverMode = SEAMLESS;
        if (strTrim.equalsIgnoreCase(failoverMode.toString())) {
            return failoverMode;
        }
        FailoverMode failoverMode2 = MANIFEST;
        if (strTrim.equalsIgnoreCase(failoverMode2.toString())) {
            return failoverMode2;
        }
        FailoverMode failoverMode3 = DISCONTINUOUS;
        if (strTrim.equalsIgnoreCase(failoverMode3.toString())) {
            return failoverMode3;
        }
        DLog.warnf("No matching failover mode found for: %s, returning discontinuous!", str);
        return failoverMode3;
    }

    @Override // java.lang.Enum
    @Nonnull
    public String toString() {
        return this.mMode;
    }
}
