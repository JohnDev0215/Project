package com.amazon.avtitleactionaggregationservice.model.visualcues.acquisition;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum OfferStageType implements NamedEnum {
    PERIODIC("PERIODIC"),
    FIXED_DATE("FIXED_DATE");

    private final String strValue;

    OfferStageType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static OfferStageType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (OfferStageType offerStageType : values()) {
            if (offerStageType.strValue.equals(str)) {
                return offerStageType;
            }
        }
        return null;
    }
}
