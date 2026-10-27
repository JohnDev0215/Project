package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum Resolution implements NamedEnum {
    RES_480p("480p"),
    RES_2160p("2160p"),
    RES_1080p("1080p"),
    RES_720p("720p");

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
