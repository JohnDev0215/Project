package com.amazon.atv.schedule.distribution;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum AspectRatio implements NamedEnum {
    RATIO_3x4("3x4"),
    RATIO_4x3("4x3"),
    RATIO_2x3("2x3"),
    RATIO_UNKNOWN("Unknown"),
    RATIO_16x9("16x9"),
    RATIO_1x1("1x1");

    private final String strValue;

    AspectRatio(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AspectRatio forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AspectRatio aspectRatio : values()) {
            if (aspectRatio.strValue.equals(str)) {
                return aspectRatio;
            }
        }
        return null;
    }
}
