package com.amazon.atv.parentalcontrols;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ClientType implements NamedEnum {
    DEVICE("device"),
    WEBSITE("website");

    private final String strValue;

    ClientType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ClientType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ClientType clientType : values()) {
            if (clientType.strValue.equals(str)) {
                return clientType;
            }
        }
        return null;
    }
}
