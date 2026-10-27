package com.amazon.atvplaybackresource.types;

import com.amazon.avod.util.json.NamedEnum;
import com.amazon.music.find.api.RemoteSearchApi;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum SupportedVideoQuality implements NamedEnum {
    UHD(RemoteSearchApi.UHD_ASSET_QUALITY),
    SD("SD"),
    HD(RemoteSearchApi.HD_ASSET_QUALITY);

    private final String strValue;

    SupportedVideoQuality(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static SupportedVideoQuality forValue(String str) {
        Preconditions.checkNotNull(str);
        for (SupportedVideoQuality supportedVideoQuality : values()) {
            if (supportedVideoQuality.strValue.equals(str)) {
                return supportedVideoQuality;
            }
        }
        return null;
    }
}
