package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Protocol implements NamedEnum {
    Https("Https"),
    Http("Http");

    private final String strValue;

    Protocol(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Protocol forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Protocol protocol : values()) {
            if (protocol.strValue.equals(str)) {
                return protocol;
            }
        }
        return null;
    }
}
