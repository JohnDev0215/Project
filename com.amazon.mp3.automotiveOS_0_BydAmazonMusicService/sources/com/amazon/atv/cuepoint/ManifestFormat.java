package com.amazon.atv.cuepoint;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ManifestFormat implements NamedEnum {
    VMAP_VAST("VMAP_VAST"),
    SMARTXML("SMARTXML");

    private final String strValue;

    ManifestFormat(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ManifestFormat forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ManifestFormat manifestFormat : values()) {
            if (manifestFormat.strValue.equals(str)) {
                return manifestFormat;
            }
        }
        return null;
    }
}
