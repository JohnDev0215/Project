package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum ContiguityType {
    Contiguous("Contiguous"),
    Noncontiguous("Noncontiguous"),
    Unknown("Unknown");

    private final String strValue;

    ContiguityType(String str) {
        this.strValue = str;
    }

    @JsonValue
    public String getValue() {
        return this.strValue;
    }

    @JsonCreator
    @Nullable
    public static ContiguityType forValue(String str) {
        if (str == null) {
            return null;
        }
        for (ContiguityType contiguityType : values()) {
            if (str.equalsIgnoreCase(contiguityType.getValue())) {
                return contiguityType;
            }
        }
        return null;
    }
}
