package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum TimeType implements NamedEnum {
    ABSOLUTE_UTC("AbsoluteUtc"),
    ABSOLUTE_PLAYBACK("AbsolutePlayback");

    private final String strValue;

    TimeType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static TimeType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (TimeType timeType : values()) {
            if (timeType.strValue.equals(str)) {
                return timeType;
            }
        }
        return null;
    }
}
