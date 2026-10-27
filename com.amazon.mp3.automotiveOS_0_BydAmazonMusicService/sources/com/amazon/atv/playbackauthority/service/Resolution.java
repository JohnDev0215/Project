package com.amazon.atv.playbackauthority.service;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Resolution implements NamedEnum {
    RES_2160P("RES_2160P"),
    RES_1080P("RES_1080P"),
    RES_720P("RES_720P"),
    RES_576P("RES_576P");

    private final String strValue;

    Resolution(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Resolution forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Resolution resolution : values()) {
            if (resolution.strValue.equals(str)) {
                return resolution;
            }
        }
        return null;
    }
}
