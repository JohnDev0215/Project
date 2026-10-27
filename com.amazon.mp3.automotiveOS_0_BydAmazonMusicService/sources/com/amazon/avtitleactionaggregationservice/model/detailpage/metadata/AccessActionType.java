package com.amazon.avtitleactionaggregationservice.model.detailpage.metadata;

import com.amazon.avod.util.json.NamedEnum;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum AccessActionType implements NamedEnum {
    FUTURE_PLAYBACK("FUTURE_PLAYBACK"),
    OFFER("OFFER"),
    PLAYBACK(Contexts.Upsell.playback);

    private final String strValue;

    AccessActionType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AccessActionType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AccessActionType accessActionType : values()) {
            if (accessActionType.strValue.equals(str)) {
                return accessActionType;
            }
        }
        return null;
    }
}
