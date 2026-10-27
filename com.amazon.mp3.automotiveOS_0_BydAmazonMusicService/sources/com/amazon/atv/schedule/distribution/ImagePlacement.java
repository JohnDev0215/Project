package com.amazon.atv.schedule.distribution;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ImagePlacement implements NamedEnum {
    CAROUSEL("Carousel"),
    HERO("Hero"),
    BACKGROUND("Background");

    private final String strValue;

    ImagePlacement(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ImagePlacement forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ImagePlacement imagePlacement : values()) {
            if (imagePlacement.strValue.equals(str)) {
                return imagePlacement;
            }
        }
        return null;
    }
}
