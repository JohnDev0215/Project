package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum SortOrder implements NamedEnum {
    ASCENDING("ascending"),
    DESCENDING("descending"),
    NONE("none");

    private final String strValue;

    SortOrder(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static SortOrder forValue(String str) {
        Preconditions.checkNotNull(str);
        for (SortOrder sortOrder : values()) {
            if (sortOrder.strValue.equals(str)) {
                return sortOrder;
            }
        }
        return null;
    }
}
