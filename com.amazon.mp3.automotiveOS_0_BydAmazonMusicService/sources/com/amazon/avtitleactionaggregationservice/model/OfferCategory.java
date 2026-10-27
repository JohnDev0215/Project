package com.amazon.avtitleactionaggregationservice.model;

import UpsellInterface.v1_0.UpsellType;
import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum OfferCategory implements NamedEnum {
    TVOD("TVOD"),
    PRIME(UpsellType.PRIME),
    SVOD("SVOD");

    private final String strValue;

    OfferCategory(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static OfferCategory forValue(String str) {
        Preconditions.checkNotNull(str);
        for (OfferCategory offerCategory : values()) {
            if (offerCategory.strValue.equals(str)) {
                return offerCategory;
            }
        }
        return null;
    }
}
