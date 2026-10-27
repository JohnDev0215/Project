package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.amazon.music.find.api.RemoteSearchApi;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum AssetQuality implements NamedEnum {
    UHD(RemoteSearchApi.UHD_ASSET_QUALITY),
    SD("SD"),
    HD(RemoteSearchApi.HD_ASSET_QUALITY);

    private final String strValue;

    AssetQuality(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AssetQuality forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AssetQuality assetQuality : values()) {
            if (assetQuality.strValue.equals(str)) {
                return assetQuality;
            }
        }
        return null;
    }
}
