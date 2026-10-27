package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum DebugType implements NamedEnum {
    COLLECTION("COLLECTION"),
    AWARENESS_TRANSACTION("AWARENESS_TRANSACTION"),
    AUTOBOT_TRANSACTION("AUTOBOT_TRANSACTION"),
    REQUEST_INFO("REQUEST_INFO"),
    LOG("LOG"),
    CHURCHILL_TRANSACTION("CHURCHILL_TRANSACTION"),
    CUSTOMER_INFO("CUSTOMER_INFO"),
    SCHEDULING_LOGS("SCHEDULING_LOGS"),
    WPOS_TRANSACTION("WPOS_TRANSACTION");

    private final String strValue;

    DebugType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static DebugType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (DebugType debugType : values()) {
            if (debugType.strValue.equals(str)) {
                return debugType;
            }
        }
        return null;
    }
}
