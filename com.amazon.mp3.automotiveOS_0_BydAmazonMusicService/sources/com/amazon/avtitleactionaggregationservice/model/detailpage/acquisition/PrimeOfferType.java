package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum PrimeOfferType implements NamedEnum {
    MAGELLAN_PRIME("MAGELLAN_PRIME"),
    CLASSIC_PRIME("CLASSIC_PRIME");

    private final String strValue;

    PrimeOfferType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static PrimeOfferType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (PrimeOfferType primeOfferType : values()) {
            if (primeOfferType.strValue.equals(str)) {
                return primeOfferType;
            }
        }
        return null;
    }
}
