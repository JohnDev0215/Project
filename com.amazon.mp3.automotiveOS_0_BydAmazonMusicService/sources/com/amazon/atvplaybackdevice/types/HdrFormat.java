package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum HdrFormat implements NamedEnum {
    Hdr10("Hdr10"),
    DolbyVision("DolbyVision"),
    None("None");

    private final String strValue;

    HdrFormat(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static HdrFormat forValue(String str) {
        Preconditions.checkNotNull(str);
        for (HdrFormat hdrFormat : values()) {
            if (hdrFormat.strValue.equals(str)) {
                return hdrFormat;
            }
        }
        return null;
    }
}
