package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum FragmentRepresentation implements NamedEnum {
    ByteOffsetRange("ByteOffsetRange"),
    SeparateFile("SeparateFile");

    private final String strValue;

    FragmentRepresentation(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static FragmentRepresentation forValue(String str) {
        Preconditions.checkNotNull(str);
        for (FragmentRepresentation fragmentRepresentation : values()) {
            if (fragmentRepresentation.strValue.equals(str)) {
                return fragmentRepresentation;
            }
        }
        return null;
    }
}
