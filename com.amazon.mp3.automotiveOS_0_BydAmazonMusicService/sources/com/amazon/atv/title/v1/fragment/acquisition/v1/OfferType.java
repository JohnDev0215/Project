package com.amazon.atv.title.v1.fragment.acquisition.v1;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum OfferType implements NamedEnum {
    SUBSCRIPTION("SUBSCRIPTION"),
    RENTAL("RENTAL"),
    PURCHASE("PURCHASE");

    private final String strValue;

    OfferType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static OfferType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (OfferType offerType : values()) {
            if (offerType.strValue.equals(str)) {
                return offerType;
            }
        }
        return null;
    }
}
