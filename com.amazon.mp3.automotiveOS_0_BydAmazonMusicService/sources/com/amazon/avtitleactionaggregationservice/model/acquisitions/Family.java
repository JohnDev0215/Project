package com.amazon.avtitleactionaggregationservice.model.acquisitions;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Family implements NamedEnum {
    MOVIE("MOVIE"),
    SEASON_PASS("SEASON_PASS"),
    EPISODE("EPISODE"),
    SEASON("SEASON");

    private final String strValue;

    Family(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Family forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Family family : values()) {
            if (family.strValue.equals(str)) {
                return family;
            }
        }
        return null;
    }
}
