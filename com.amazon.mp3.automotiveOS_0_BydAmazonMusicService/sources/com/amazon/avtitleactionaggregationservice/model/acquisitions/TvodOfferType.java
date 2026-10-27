package com.amazon.avtitleactionaggregationservice.model.acquisitions;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum TvodOfferType implements NamedEnum {
    RENTAL("RENTAL"),
    PURCHASE("PURCHASE");

    private final String strValue;

    TvodOfferType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static TvodOfferType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (TvodOfferType tvodOfferType : values()) {
            if (tvodOfferType.strValue.equals(str)) {
                return tvodOfferType;
            }
        }
        return null;
    }
}
