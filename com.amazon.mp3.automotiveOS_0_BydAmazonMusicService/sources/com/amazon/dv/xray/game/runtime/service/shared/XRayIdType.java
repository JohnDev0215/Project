package com.amazon.dv.xray.game.runtime.service.shared;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum XRayIdType implements NamedEnum {
    LIVE_EVENT_ID("LIVE_EVENT_ID");

    private final String strValue;

    XRayIdType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static XRayIdType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (XRayIdType xRayIdType : values()) {
            if (xRayIdType.strValue.equals(str)) {
                return xRayIdType;
            }
        }
        return null;
    }
}
