package com.amazon.atv.schedule;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum TimeZone implements NamedEnum {
    PST("PST"),
    CST("CST"),
    UTC("UTC"),
    EST("EST"),
    JST("JST"),
    CEST("CEST"),
    GMT("GMT"),
    MST("MST");

    private final String strValue;

    TimeZone(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static TimeZone forValue(String str) {
        Preconditions.checkNotNull(str);
        for (TimeZone timeZone : values()) {
            if (timeZone.strValue.equals(str)) {
                return timeZone;
            }
        }
        return null;
    }
}
