package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum SelectionType implements NamedEnum {
    EXCLUSIVE("exclusive"),
    INCLUSIVE("inclusive");

    private final String strValue;

    SelectionType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static SelectionType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (SelectionType selectionType : values()) {
            if (selectionType.strValue.equals(str)) {
                return selectionType;
            }
        }
        return null;
    }
}
