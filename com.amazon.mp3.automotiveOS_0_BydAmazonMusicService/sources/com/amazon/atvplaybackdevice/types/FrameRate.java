package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum FrameRate implements NamedEnum {
    High("High"),
    Low("Low"),
    Standard("Standard");

    private final String strValue;

    FrameRate(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static FrameRate forValue(String str) {
        Preconditions.checkNotNull(str);
        for (FrameRate frameRate : values()) {
            if (frameRate.strValue.equals(str)) {
                return frameRate;
            }
        }
        return null;
    }
}
