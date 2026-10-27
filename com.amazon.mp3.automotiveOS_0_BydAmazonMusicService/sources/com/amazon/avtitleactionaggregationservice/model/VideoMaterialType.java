package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum VideoMaterialType implements NamedEnum {
    FEATURE("Feature"),
    LIVE("LiveStreaming");

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
