package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum ProtocolType implements NamedEnum {
    HTTPS("HTTPS"),
    HTTP("HTTP");

    private final String strValue;

    ProtocolType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ProtocolType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ProtocolType protocolType : values()) {
            if (protocolType.strValue.equals(str)) {
                return protocolType;
            }
        }
        return null;
    }
}
