package com.amazon.atv.playbackauthority.service;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum AssetMaterialType implements NamedEnum {
    FEATURE("FEATURE"),
    TRAILER("TRAILER"),
    VALUE_ADDED("VALUE_ADDED");

    private final String strValue;

    AssetMaterialType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AssetMaterialType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AssetMaterialType assetMaterialType : values()) {
            if (assetMaterialType.strValue.equals(str)) {
                return assetMaterialType;
            }
        }
        return null;
    }
}
