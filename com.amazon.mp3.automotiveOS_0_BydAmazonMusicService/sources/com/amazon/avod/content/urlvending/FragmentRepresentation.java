package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum FragmentRepresentation {
    ByteOffsetRange("ByteOffsetRange"),
    SeparateFile("SeparateFile"),
    Mixed("Mixed"),
    Unknown("Unknown");

    private final String strValue;

    FragmentRepresentation(String str) {
        this.strValue = str;
    }

    @JsonValue
    public String getValue() {
        return this.strValue;
    }

    @JsonCreator
    @Nullable
    public static FragmentRepresentation forValue(String str) {
        if (str == null) {
            return null;
        }
        for (FragmentRepresentation fragmentRepresentation : values()) {
            if (str.equalsIgnoreCase(fragmentRepresentation.getValue())) {
                return fragmentRepresentation;
            }
        }
        return null;
    }
}
