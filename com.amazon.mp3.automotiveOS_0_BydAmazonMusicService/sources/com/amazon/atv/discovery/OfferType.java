package com.amazon.atv.discovery;

import UpsellInterface.v1_0.PlaybackMode;
import UpsellInterface.v1_0.UpsellType;
import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum OfferType implements NamedEnum {
    TVOD("TVOD"),
    PRIME(UpsellType.PRIME),
    FVOD("FVOD"),
    SUBSCRIPTION("SUBSCRIPTION"),
    FREE_WITH_ADS("FREE_WITH_ADS"),
    NONE(PlaybackMode.NONE);

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
