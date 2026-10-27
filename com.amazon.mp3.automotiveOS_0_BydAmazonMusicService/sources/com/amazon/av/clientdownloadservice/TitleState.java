package com.amazon.av.clientdownloadservice;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum TitleState implements NamedEnum {
    DISABLED("DISABLED"),
    PLAYED_SINCE_LAST_REFRESH("PLAYED_SINCE_LAST_REFRESH"),
    NO_LICENSE("NO_LICENSE"),
    INVALID_LICENSE("INVALID_LICENSE"),
    READY_TO_PLAY("READY_TO_PLAY");

    private final String strValue;

    TitleState(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static TitleState forValue(String str) {
        Preconditions.checkNotNull(str);
        for (TitleState titleState : values()) {
            if (titleState.strValue.equals(str)) {
                return titleState;
            }
        }
        return null;
    }
}
