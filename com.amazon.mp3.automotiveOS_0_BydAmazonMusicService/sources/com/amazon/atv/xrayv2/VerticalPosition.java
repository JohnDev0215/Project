package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum VerticalPosition implements NamedEnum {
    CENTER("center"),
    TOP("top"),
    BOTTOM("bottom");

    private final String strValue;

    VerticalPosition(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static VerticalPosition forValue(String str) {
        Preconditions.checkNotNull(str);
        for (VerticalPosition verticalPosition : values()) {
            if (verticalPosition.strValue.equals(str)) {
                return verticalPosition;
            }
        }
        return null;
    }
}
