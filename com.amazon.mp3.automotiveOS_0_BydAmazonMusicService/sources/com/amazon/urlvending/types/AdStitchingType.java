package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum AdStitchingType implements NamedEnum {
    Native("Native"),
    MultiPeriod("MultiPeriod");

    private final String strValue;

    AdStitchingType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AdStitchingType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AdStitchingType adStitchingType : values()) {
            if (adStitchingType.strValue.equals(str)) {
                return adStitchingType;
            }
        }
        return null;
    }
}
