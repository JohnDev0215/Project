package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ActionType implements NamedEnum {
    NAVIGATIONAL("navigational"),
    COLLECTION_REFRESH("collectionItemsRefresh"),
    REMOVE("remove"),
    PAGINATION("pagination");

    private final String strValue;

    ActionType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ActionType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ActionType actionType : values()) {
            if (actionType.strValue.equals(str)) {
                return actionType;
            }
        }
        return null;
    }
}
