package com.amazon.atvtransitiontimecodeservice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ElementType implements NamedEnum {
    INTRO("INTRO"),
    RECAP("RECAP");

    private final String strValue;

    ElementType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ElementType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ElementType elementType : values()) {
            if (elementType.strValue.equals(str)) {
                return elementType;
            }
        }
        return null;
    }
}
