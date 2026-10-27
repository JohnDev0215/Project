package com.amazon.avod.content.urlvending;

import com.amazon.avod.util.DLog;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum FailoverType {
    CDN("cdn"),
    MANIFEST_SERVICE("manifestService"),
    ORIGIN("origin"),
    CLOSE_PLAYER("closePlayer"),
    NO_ACTION("noAction"),
    DEFAULT("default");

    private final String mFailover;

    FailoverType(@Nonnull String str) {
        this.mFailover = (String) Preconditions.checkNotNull(str, "failover");
    }

    @JsonCreator
    @Nonnull
    public static FailoverType forValue(@Nullable String str) {
        if (str == null) {
            return DEFAULT;
        }
        String strTrim = str.trim();
        FailoverType failoverType = CDN;
        if (strTrim.equalsIgnoreCase(failoverType.toString())) {
            return failoverType;
        }
        FailoverType failoverType2 = MANIFEST_SERVICE;
        if (strTrim.equalsIgnoreCase(failoverType2.toString())) {
            return failoverType2;
        }
        FailoverType failoverType3 = ORIGIN;
        if (strTrim.equalsIgnoreCase(failoverType3.toString())) {
            return failoverType3;
        }
        FailoverType failoverType4 = NO_ACTION;
        if (strTrim.equalsIgnoreCase(failoverType4.toString())) {
            return failoverType4;
        }
        FailoverType failoverType5 = CLOSE_PLAYER;
        if (strTrim.equalsIgnoreCase(failoverType5.toString())) {
            return failoverType5;
        }
        FailoverType failoverType6 = DEFAULT;
        if (strTrim.equalsIgnoreCase(failoverType6.toString())) {
            return failoverType6;
        }
        DLog.warnf("No matching failover type found for: %s, returning default!", str);
        return failoverType6;
    }

    @Override // java.lang.Enum
    @Nonnull
    public String toString() {
        return this.mFailover;
    }
}
