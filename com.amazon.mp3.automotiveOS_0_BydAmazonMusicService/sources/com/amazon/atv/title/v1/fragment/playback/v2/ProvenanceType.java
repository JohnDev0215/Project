package com.amazon.atv.title.v1.fragment.playback.v2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ProvenanceType implements NamedEnum {
    SUBSCRIPTION("SUBSCRIPTION");

    private final String strValue;

    ProvenanceType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ProvenanceType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ProvenanceType provenanceType : values()) {
            if (provenanceType.strValue.equals(str)) {
                return provenanceType;
            }
        }
        return null;
    }
}
