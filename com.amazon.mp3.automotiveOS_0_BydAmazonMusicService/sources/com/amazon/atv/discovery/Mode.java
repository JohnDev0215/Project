package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Mode implements NamedEnum {
    GLOBAL_TRAINING("GlobalTraining"),
    CUSTOMIZED_USER("CustomizedUser"),
    LIVE("Live");

    private final String strValue;

    Mode(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Mode forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Mode mode : values()) {
            if (mode.strValue.equals(str)) {
                return mode;
            }
        }
        return null;
    }
}
