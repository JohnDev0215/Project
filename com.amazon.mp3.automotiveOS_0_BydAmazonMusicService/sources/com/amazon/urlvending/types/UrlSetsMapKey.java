package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum UrlSetsMapKey implements NamedEnum {
    ContentType("ContentType"),
    Cdn("Cdn");

    private final String strValue;

    UrlSetsMapKey(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static UrlSetsMapKey forValue(String str) {
        Preconditions.checkNotNull(str);
        for (UrlSetsMapKey urlSetsMapKey : values()) {
            if (urlSetsMapKey.strValue.equals(str)) {
                return urlSetsMapKey;
            }
        }
        return null;
    }
}
