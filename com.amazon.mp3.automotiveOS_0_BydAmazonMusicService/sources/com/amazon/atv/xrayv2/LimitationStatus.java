package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum LimitationStatus implements NamedEnum {
    ALMOSTBREACHED("ALMOSTBREACHED"),
    NOTBREACHED("NOTBREACHED"),
    BREACHED("BREACHED");

    private final String strValue;

    LimitationStatus(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static LimitationStatus forValue(String str) {
        Preconditions.checkNotNull(str);
        for (LimitationStatus limitationStatus : values()) {
            if (limitationStatus.strValue.equals(str)) {
                return limitationStatus;
            }
        }
        return null;
    }
}
