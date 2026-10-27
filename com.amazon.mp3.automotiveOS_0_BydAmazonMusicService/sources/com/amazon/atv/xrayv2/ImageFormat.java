package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ImageFormat implements NamedEnum {
    imageV2("imageV2");

    private final String strValue;

    ImageFormat(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ImageFormat forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ImageFormat imageFormat : values()) {
            if (imageFormat.strValue.equals(str)) {
                return imageFormat;
            }
        }
        return null;
    }
}
