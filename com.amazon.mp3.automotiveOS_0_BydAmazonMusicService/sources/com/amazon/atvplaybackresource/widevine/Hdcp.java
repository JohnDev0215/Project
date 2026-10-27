package com.amazon.atvplaybackresource.widevine;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Hdcp implements NamedEnum {
    HDCP_V1("HDCP_V1"),
    HDCP_V2("HDCP_V2"),
    HDCP_V2_1("HDCP_V2_1"),
    HDCP_V2_2("HDCP_V2_2"),
    HDCP_NONE("HDCP_NONE");

    private final String strValue;

    Hdcp(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Hdcp forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Hdcp hdcp : values()) {
            if (hdcp.strValue.equals(str)) {
                return hdcp;
            }
        }
        return null;
    }
}
