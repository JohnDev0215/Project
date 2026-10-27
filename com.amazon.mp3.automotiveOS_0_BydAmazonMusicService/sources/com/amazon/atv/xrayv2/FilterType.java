package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum FilterType implements NamedEnum {
    SINGLE_FILTER("singleFilter"),
    MULTI_FILTER("multiFilter");

    private final String strValue;

    FilterType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static FilterType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (FilterType filterType : values()) {
            if (filterType.strValue.equals(str)) {
                return filterType;
            }
        }
        return null;
    }
}
