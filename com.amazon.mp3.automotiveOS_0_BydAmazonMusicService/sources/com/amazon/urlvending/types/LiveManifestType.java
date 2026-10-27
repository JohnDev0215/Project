package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum LiveManifestType implements NamedEnum {
    Accumulating("Accumulating"),
    Live("Live"),
    PatternTemplate("PatternTemplate");

    private final String strValue;

    LiveManifestType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static LiveManifestType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (LiveManifestType liveManifestType : values()) {
            if (liveManifestType.strValue.equals(str)) {
                return liveManifestType;
            }
        }
        return null;
    }
}
