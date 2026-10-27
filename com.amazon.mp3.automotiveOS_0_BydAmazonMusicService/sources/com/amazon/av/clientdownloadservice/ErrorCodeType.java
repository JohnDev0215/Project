package com.amazon.av.clientdownloadservice;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ErrorCodeType implements NamedEnum {
    DEFAULT("DEFAULT");

    private final String strValue;

    ErrorCodeType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ErrorCodeType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ErrorCodeType errorCodeType : values()) {
            if (errorCodeType.strValue.equals(str)) {
                return errorCodeType;
            }
        }
        return null;
    }
}
