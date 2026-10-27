package com.amazon.atv.discovery;

import UpsellInterface.v1_0.PlaybackMode;
import com.amazon.avod.util.json.NamedEnum;
import com.google.android.gms.cast.MediaError;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum DebugLevel implements NamedEnum {
    ERROR(MediaError.ERROR_TYPE_ERROR),
    NONE(PlaybackMode.NONE),
    INFO("INFO"),
    DEBUG("DEBUG"),
    WARN("WARN");

    private final String strValue;

    DebugLevel(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static DebugLevel forValue(String str) {
        Preconditions.checkNotNull(str);
        for (DebugLevel debugLevel : values()) {
            if (debugLevel.strValue.equals(str)) {
                return debugLevel;
            }
        }
        return null;
    }
}
