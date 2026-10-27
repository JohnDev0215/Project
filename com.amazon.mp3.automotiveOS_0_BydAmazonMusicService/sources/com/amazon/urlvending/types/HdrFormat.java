package com.amazon.urlvending.types;

import UpsellInterface.v1_0.PlaybackMode;
import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum HdrFormat implements NamedEnum {
    HDR10("HDR10"),
    DOLBY_VISION("DOLBY_VISION"),
    NONE(PlaybackMode.NONE);

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
