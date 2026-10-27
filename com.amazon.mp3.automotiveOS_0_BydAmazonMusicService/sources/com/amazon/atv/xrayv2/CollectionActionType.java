package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum CollectionActionType implements NamedEnum {
    MORE("more"),
    ZOOMOUT("zoomOut"),
    ZOOMIN("zoomIn");

    private final String strValue;

    CollectionActionType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static CollectionActionType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (CollectionActionType collectionActionType : values()) {
            if (collectionActionType.strValue.equals(str)) {
                return collectionActionType;
            }
        }
        return null;
    }
}
