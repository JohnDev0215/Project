package com.amazon.atvplaybackresource.widevine;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum WidevineKeyType implements NamedEnum {
    CONTENT("CONTENT"),
    SERVICE_CERTIFICATE("SERVICE_CERTIFICATE"),
    HDCP_TEST("HDCP_TEST");

    private final String strValue;

    WidevineKeyType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static WidevineKeyType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (WidevineKeyType widevineKeyType : values()) {
            if (widevineKeyType.strValue.equals(str)) {
                return widevineKeyType;
            }
        }
        return null;
    }
}
