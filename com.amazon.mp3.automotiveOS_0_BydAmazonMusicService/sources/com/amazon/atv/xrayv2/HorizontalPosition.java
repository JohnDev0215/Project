package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum HorizontalPosition implements NamedEnum {
    LEFT("left"),
    RIGHT("right");

    private final String strValue;

    HorizontalPosition(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static HorizontalPosition forValue(String str) {
        Preconditions.checkNotNull(str);
        for (HorizontalPosition horizontalPosition : values()) {
            if (horizontalPosition.strValue.equals(str)) {
                return horizontalPosition;
            }
        }
        return null;
    }
}
