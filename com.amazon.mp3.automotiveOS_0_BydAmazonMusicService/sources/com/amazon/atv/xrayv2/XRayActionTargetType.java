package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum XRayActionTargetType implements NamedEnum {
    ITEM("xrayItemTargetType"),
    WIDGET("xrayWidgetTargetType");

    private final String strValue;

    XRayActionTargetType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static XRayActionTargetType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (XRayActionTargetType xRayActionTargetType : values()) {
            if (xRayActionTargetType.strValue.equals(str)) {
                return xRayActionTargetType;
            }
        }
        return null;
    }
}
