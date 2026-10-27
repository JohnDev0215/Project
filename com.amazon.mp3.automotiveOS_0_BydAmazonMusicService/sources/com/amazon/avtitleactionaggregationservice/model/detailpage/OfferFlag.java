package com.amazon.avtitleactionaggregationservice.model.detailpage;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum OfferFlag implements NamedEnum {
    POPS_AMAZON_WALLET_PAYMENT("POPS_AMAZON_WALLET_PAYMENT"),
    POPS_GOOGLE_IN_APP_PAYMENT("POPS_GOOGLE_IN_APP_PAYMENT"),
    POPS_APPLE_IN_APP_PAYMENT_V2("POPS_APPLE_IN_APP_PAYMENT_V2"),
    POPS_APPLE_IN_APP_PAYMENT("POPS_APPLE_IN_APP_PAYMENT");

    private final String strValue;

    OfferFlag(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static OfferFlag forValue(String str) {
        Preconditions.checkNotNull(str);
        for (OfferFlag offerFlag : values()) {
            if (offerFlag.strValue.equals(str)) {
                return offerFlag;
            }
        }
        return null;
    }
}
