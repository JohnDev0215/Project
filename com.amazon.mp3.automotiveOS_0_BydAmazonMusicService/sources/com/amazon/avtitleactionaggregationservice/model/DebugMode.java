package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum DebugMode implements NamedEnum {
    LogDependencyTrace("LogDependencyTrace");

    private final String strValue;

    DebugMode(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static DebugMode forValue(String str) {
        Preconditions.checkNotNull(str);
        for (DebugMode debugMode : values()) {
            if (debugMode.strValue.equals(str)) {
                return debugMode;
            }
        }
        return null;
    }
}
