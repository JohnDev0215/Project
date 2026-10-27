package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.amazon.music.find.api.RemoteSearchApi;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum VideoQuality implements NamedEnum {
    UHD(RemoteSearchApi.UHD_ASSET_QUALITY),
    SD("SD"),
    HD(RemoteSearchApi.HD_ASSET_QUALITY);

    private final String strValue;

    VideoQuality(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static VideoQuality forValue(String str) {
        Preconditions.checkNotNull(str);
        for (VideoQuality videoQuality : values()) {
            if (videoQuality.strValue.equals(str)) {
                return videoQuality;
            }
        }
        return null;
    }
}
