package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Unit implements NamedEnum {
    percentage("percentage");

    private final String strValue;

    Unit(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Unit forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Unit unit : values()) {
            if (unit.strValue.equals(str)) {
                return unit;
            }
        }
        return null;
    }
}
