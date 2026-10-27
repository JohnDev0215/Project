package com.amazon.atv.title.v1.fragment.catalog.v1;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum TitleType implements NamedEnum {
    MOVIE("MOVIE"),
    EPISODE("EPISODE"),
    SHOW("SHOW"),
    SEASON("SEASON");

    private final String strValue;

    TitleType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static TitleType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (TitleType titleType : values()) {
            if (titleType.strValue.equals(str)) {
                return titleType;
            }
        }
        return null;
    }
}
