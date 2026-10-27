package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum OfferFeature implements NamedEnum {
    SUBSCRIPTION_OFFER_STAGES("SUBSCRIPTION_OFFER_STAGES"),
    PREFERRED_OFFERS("PREFERRED_OFFERS"),
    OFFER_TOKEN("OFFER_TOKEN");

    private final String strValue;

    OfferFeature(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static OfferFeature forValue(String str) {
        Preconditions.checkNotNull(str);
        for (OfferFeature offerFeature : values()) {
            if (offerFeature.strValue.equals(str)) {
                return offerFeature;
            }
        }
        return null;
    }
}
