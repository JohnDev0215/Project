package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ImageSizeUnit implements NamedEnum {
    PX("px"),
    RATIO("ratio");

    private final String strValue;

    ImageSizeUnit(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ImageSizeUnit forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ImageSizeUnit imageSizeUnit : values()) {
            if (imageSizeUnit.strValue.equals(str)) {
                return imageSizeUnit;
            }
        }
        return null;
    }
}
