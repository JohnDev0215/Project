package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum AvailabilityChangeType implements NamedEnum {
    COMING_SOON("COMING_SOON"),
    UNKNOWN("UNKNOWN"),
    LEAVING_SOON("LEAVING_SOON");

    private final String strValue;

    AvailabilityChangeType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AvailabilityChangeType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AvailabilityChangeType availabilityChangeType : values()) {
            if (availabilityChangeType.strValue.equals(str)) {
                return availabilityChangeType;
            }
        }
        return null;
    }
}
