package com.amazon.avod.xray;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum XRayActionType implements NamedEnum {
    ADD("xrayAddActionType"),
    HIDE("xrayHideActionType"),
    REMOVE("xrayRemoveActionType"),
    UPDATE("xrayUpdateActionType");

    private final String strValue;

    XRayActionType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static XRayActionType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (XRayActionType xRayActionType : values()) {
            if (xRayActionType.strValue.equals(str)) {
                return xRayActionType;
            }
        }
        return null;
    }
}
