package com.amazon.atv.playbackauthority.service;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ConsumptionType implements NamedEnum {
    STREAM("STREAM"),
    DOWNLOAD("DOWNLOAD"),
    WHISPERCACHE("WHISPERCACHE");

    private final String strValue;

    ConsumptionType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ConsumptionType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ConsumptionType consumptionType : values()) {
            if (consumptionType.strValue.equals(str)) {
                return consumptionType;
            }
        }
        return null;
    }
}
