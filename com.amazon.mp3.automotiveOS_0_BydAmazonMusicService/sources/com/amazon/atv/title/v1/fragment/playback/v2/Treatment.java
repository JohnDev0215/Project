package com.amazon.atv.title.v1.fragment.playback.v2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Treatment implements NamedEnum {
    PROVENANCE("PROVENANCE");

    private final String strValue;

    Treatment(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Treatment forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Treatment treatment : values()) {
            if (treatment.strValue.equals(str)) {
                return treatment;
            }
        }
        return null;
    }
}
