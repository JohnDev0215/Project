package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum GetLivePlaybackUrlsConsumptionType implements NamedEnum {
    Streaming("Streaming"),
    Download("Download");

    private final String strValue;

    GetLivePlaybackUrlsConsumptionType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static GetLivePlaybackUrlsConsumptionType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (GetLivePlaybackUrlsConsumptionType getLivePlaybackUrlsConsumptionType : values()) {
            if (getLivePlaybackUrlsConsumptionType.strValue.equals(str)) {
                return getLivePlaybackUrlsConsumptionType;
            }
        }
        return null;
    }
}
