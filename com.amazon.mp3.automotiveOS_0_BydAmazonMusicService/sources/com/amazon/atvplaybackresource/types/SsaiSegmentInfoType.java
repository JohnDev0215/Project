package com.amazon.atvplaybackresource.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum SsaiSegmentInfoType implements NamedEnum {
    List("List"),
    Base("Base");

    private final String strValue;

    SsaiSegmentInfoType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static SsaiSegmentInfoType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (SsaiSegmentInfoType ssaiSegmentInfoType : values()) {
            if (ssaiSegmentInfoType.strValue.equals(str)) {
                return ssaiSegmentInfoType;
            }
        }
        return null;
    }
}
