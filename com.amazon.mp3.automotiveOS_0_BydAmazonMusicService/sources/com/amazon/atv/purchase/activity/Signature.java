package com.amazon.atv.purchase.activity;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Signature implements NamedEnum {
    NotAvailable("NotAvailable");

    private final String strValue;

    Signature(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Signature forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Signature signature : values()) {
            if (signature.strValue.equals(str)) {
                return signature;
            }
        }
        return null;
    }
}
