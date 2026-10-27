package com.amazon.atvplaybackresource.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum SsaiStitchType implements NamedEnum {
    Native("Native"),
    MultiPeriod("MultiPeriod");

    private final String strValue;

    SsaiStitchType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static SsaiStitchType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (SsaiStitchType ssaiStitchType : values()) {
            if (ssaiStitchType.strValue.equals(str)) {
                return ssaiStitchType;
            }
        }
        return null;
    }
}
