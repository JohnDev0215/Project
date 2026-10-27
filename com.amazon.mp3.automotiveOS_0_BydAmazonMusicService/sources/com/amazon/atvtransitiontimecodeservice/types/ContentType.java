package com.amazon.atvtransitiontimecodeservice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ContentType implements NamedEnum {
    MOVIE("MOVIE"),
    SERIES("SERIES"),
    EPISODE("EPISODE"),
    SEASON("SEASON");

    private final String strValue;

    ContentType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ContentType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ContentType contentType : values()) {
            if (contentType.strValue.equals(str)) {
                return contentType;
            }
        }
        return null;
    }
}
