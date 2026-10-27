package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum DRMKeyScheme implements NamedEnum {
    SINGLE_KEY("SINGLE_KEY"),
    DUAL_KEY("DUAL_KEY");

    private final String strValue;

    DRMKeyScheme(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static DRMKeyScheme forValue(String str) {
        Preconditions.checkNotNull(str);
        for (DRMKeyScheme dRMKeyScheme : values()) {
            if (dRMKeyScheme.strValue.equals(str)) {
                return dRMKeyScheme;
            }
        }
        return null;
    }
}
