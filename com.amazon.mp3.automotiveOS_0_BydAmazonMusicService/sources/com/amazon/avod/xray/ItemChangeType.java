package com.amazon.avod.xray;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ItemChangeType implements NamedEnum {
    ADD_ITEM("AddItem"),
    REMOVE_ITEM("RemoveItem");

    private final String strValue;

    ItemChangeType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ItemChangeType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ItemChangeType itemChangeType : values()) {
            if (itemChangeType.strValue.equals(str)) {
                return itemChangeType;
            }
        }
        return null;
    }
}
