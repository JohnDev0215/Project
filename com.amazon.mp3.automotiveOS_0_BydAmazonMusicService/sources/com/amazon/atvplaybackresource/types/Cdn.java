package com.amazon.atvplaybackresource.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Cdn implements NamedEnum {
    Limelight("Limelight"),
    Akamai("Akamai"),
    Level3("Level3"),
    Edgecast("Edgecast"),
    Cloudfront("Cloudfront");

    private final String strValue;

    Cdn(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Cdn forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Cdn cdn : values()) {
            if (cdn.strValue.equals(str)) {
                return cdn;
            }
        }
        return null;
    }
}
