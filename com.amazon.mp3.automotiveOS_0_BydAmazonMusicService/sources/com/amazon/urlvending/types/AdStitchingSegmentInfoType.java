package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum AdStitchingSegmentInfoType implements NamedEnum {
    List("List"),
    Base("Base");

    private final String strValue;

    AdStitchingSegmentInfoType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AdStitchingSegmentInfoType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AdStitchingSegmentInfoType adStitchingSegmentInfoType : values()) {
            if (adStitchingSegmentInfoType.strValue.equals(str)) {
                return adStitchingSegmentInfoType;
            }
        }
        return null;
    }
}
