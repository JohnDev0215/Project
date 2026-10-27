package com.amazon.avtitleactionaggregationservice.model.detailpage.metadata;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ChangeType implements NamedEnum {
    COMING_SOON("COMING_SOON"),
    LEAVING_SOON("LEAVING_SOON"),
    UPCOMING_LINEAR("UPCOMING_LINEAR");

    private final String strValue;

    ChangeType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ChangeType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ChangeType changeType : values()) {
            if (changeType.strValue.equals(str)) {
                return changeType;
            }
        }
        return null;
    }
}
