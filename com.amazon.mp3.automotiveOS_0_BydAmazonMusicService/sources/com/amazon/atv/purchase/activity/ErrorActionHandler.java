package com.amazon.atv.purchase.activity;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ErrorActionHandler implements NamedEnum {
    MSG("MSG"),
    ADD_PAYMENT_INSTRUMENT("ADD_PAYMENT_INSTRUMENT"),
    SELECT_PAYMENT_INSTRUMENT("SELECT_PAYMENT_INSTRUMENT"),
    CHANGE_PAYMENT_INSTRUMENT("CHANGE_PAYMENT_INSTRUMENT");

    private final String strValue;

    ErrorActionHandler(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ErrorActionHandler forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ErrorActionHandler errorActionHandler : values()) {
            if (errorActionHandler.strValue.equals(str)) {
                return errorActionHandler;
            }
        }
        return null;
    }
}
