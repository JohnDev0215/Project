package com.amazon.atvplaybackresource.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum daiLiveManifestType implements NamedEnum {
    Accumulating("Accumulating"),
    Live("Live"),
    PatternTemplate("PatternTemplate");

    private final String strValue;

    daiLiveManifestType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static daiLiveManifestType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (daiLiveManifestType dailivemanifesttype : values()) {
            if (dailivemanifesttype.strValue.equals(str)) {
                return dailivemanifesttype;
            }
        }
        return null;
    }
}
