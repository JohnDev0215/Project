package com.amazon.av.clientdownloadservice;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ResponseStatusType implements NamedEnum {
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE");

    private final String strValue;

    ResponseStatusType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ResponseStatusType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ResponseStatusType responseStatusType : values()) {
            if (responseStatusType.strValue.equals(str)) {
                return responseStatusType;
            }
        }
        return null;
    }
}
