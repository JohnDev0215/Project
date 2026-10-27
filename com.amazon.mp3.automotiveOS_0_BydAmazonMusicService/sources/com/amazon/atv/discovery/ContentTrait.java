package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ContentTrait implements NamedEnum {
    CUSTOMER_LOCATION_DEPENDENT("customerLocationDependent");

    private final String strValue;

    ContentTrait(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ContentTrait forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ContentTrait contentTrait : values()) {
            if (contentTrait.strValue.equals(str)) {
                return contentTrait;
            }
        }
        return null;
    }
}
