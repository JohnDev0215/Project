package com.amazon.atv.featureconfig;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Scope implements NamedEnum {
    DEVICE("device"),
    CUSTOMER("customer");

    private final String strValue;

    Scope(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Scope forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Scope scope : values()) {
            if (scope.strValue.equals(str)) {
                return scope;
            }
        }
        return null;
    }
}
