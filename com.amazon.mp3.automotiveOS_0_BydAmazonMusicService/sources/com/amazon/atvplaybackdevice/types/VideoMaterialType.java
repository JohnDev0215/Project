package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum VideoMaterialType implements NamedEnum {
    ValueAdded("ValueAdded"),
    Promo("Promo"),
    External("External"),
    VideoRoll("VideoRoll"),
    Feature("Feature"),
    Trailer("Trailer"),
    LiveStreaming("LiveStreaming");

    private final String strValue;

    VideoMaterialType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static VideoMaterialType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (VideoMaterialType videoMaterialType : values()) {
            if (videoMaterialType.strValue.equals(str)) {
                return videoMaterialType;
            }
        }
        return null;
    }
}
