package com.amazon.atv.playbackauthority.service;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum DrmType implements NamedEnum {
    OTHER("OTHER"),
    SOFTWARE_PLAYREADY("SOFTWARE_PLAYREADY"),
    FAIRPLAY("FAIRPLAY");

    private final String strValue;

    DrmType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static DrmType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (DrmType drmType : values()) {
            if (drmType.strValue.equals(str)) {
                return drmType;
            }
        }
        return null;
    }
}
