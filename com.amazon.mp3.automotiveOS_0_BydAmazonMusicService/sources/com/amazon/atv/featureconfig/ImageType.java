package com.amazon.atv.featureconfig;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ImageType implements NamedEnum {
    SERIES("series"),
    EPISODE("episode"),
    SEASON("season");

    private final String strValue;

    ImageType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ImageType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ImageType imageType : values()) {
            if (imageType.strValue.equals(str)) {
                return imageType;
            }
        }
        return null;
    }
}
