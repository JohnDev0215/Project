package com.amazon.avtitleactionaggregationservice.model.detailpage;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ClientCapability implements NamedEnum {
    SUPPORTS_DYNAMIC_PRICING("SUPPORTS_DYNAMIC_PRICING"),
    SUPPORTS_DYNAMIC_AD_INSERTION("SUPPORTS_DYNAMIC_AD_INSERTION"),
    SUPPORTS_ANONYMOUS_AVOD_PLAYBACK("SUPPORTS_ANONYMOUS_AVOD_PLAYBACK"),
    SUPPORTS_PLAYBACK_ENVELOPE("SUPPORTS_PLAYBACK_ENVELOPE"),
    SUPPORTS_LINEAR_DATA("SUPPORTS_LINEAR_DATA"),
    INCLUDE_CREDITS("INCLUDE_CREDITS"),
    SUPPORTS_DOWNLOAD_CONSUMPTION("SUPPORTS_DOWNLOAD_CONSUMPTION");

    private final String strValue;

    ClientCapability(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ClientCapability forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ClientCapability clientCapability : values()) {
            if (clientCapability.strValue.equals(str)) {
                return clientCapability;
            }
        }
        return null;
    }
}
