package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum AssetIdType implements NamedEnum {
    VCID("VCID"),
    PID("PID");

    private final String strValue;

    AssetIdType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AssetIdType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AssetIdType assetIdType : values()) {
            if (assetIdType.strValue.equals(str)) {
                return assetIdType;
            }
        }
        return null;
    }
}
