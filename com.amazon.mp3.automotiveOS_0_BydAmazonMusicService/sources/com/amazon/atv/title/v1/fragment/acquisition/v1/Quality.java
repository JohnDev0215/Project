package com.amazon.atv.title.v1.fragment.acquisition.v1;

import com.amazon.avod.util.json.NamedEnum;
import com.amazon.music.find.api.RemoteSearchApi;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Quality implements NamedEnum {
    UHD(RemoteSearchApi.UHD_ASSET_QUALITY),
    SD("SD"),
    HD(RemoteSearchApi.HD_ASSET_QUALITY);

    private final String strValue;

    Quality(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Quality forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Quality quality : values()) {
            if (quality.strValue.equals(str)) {
                return quality;
            }
        }
        return null;
    }
}
