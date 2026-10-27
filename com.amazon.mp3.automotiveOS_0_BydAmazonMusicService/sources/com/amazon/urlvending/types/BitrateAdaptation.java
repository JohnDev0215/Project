package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum BitrateAdaptation implements NamedEnum {
    CBR("CBR"),
    CVBR("CVBR");

    private final String strValue;

    BitrateAdaptation(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static BitrateAdaptation forValue(String str) {
        Preconditions.checkNotNull(str);
        for (BitrateAdaptation bitrateAdaptation : values()) {
            if (bitrateAdaptation.strValue.equals(str)) {
                return bitrateAdaptation;
            }
        }
        return null;
    }
}
