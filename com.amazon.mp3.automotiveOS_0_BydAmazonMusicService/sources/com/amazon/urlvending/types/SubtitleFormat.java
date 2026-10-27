package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum SubtitleFormat implements NamedEnum {
    TTMLv2("TTMLv2");

    private final String strValue;

    SubtitleFormat(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static SubtitleFormat forValue(String str) {
        Preconditions.checkNotNull(str);
        for (SubtitleFormat subtitleFormat : values()) {
            if (subtitleFormat.strValue.equals(str)) {
                return subtitleFormat;
            }
        }
        return null;
    }
}
