package com.amazon.atv.playbackauthority.service;

import UpsellInterface.v1_0.ContentTrait;
import UpsellInterface.v1_0.UpsellType;
import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum EntitlementType implements NamedEnum {
    THIRD_PARTY_SUBSCRIPTION("THIRD_PARTY_SUBSCRIPTION"),
    AD_SUPPORTED(ContentTrait.AD_SUPPORTED),
    RENTAL("RENTAL"),
    A4K_SUBSCRIPTION("A4K_SUBSCRIPTION"),
    FREE(UpsellType.FREE),
    PRIME_SUBSCRIPTION("PRIME_SUBSCRIPTION"),
    PURCHASE("PURCHASE");

    private final String strValue;

    EntitlementType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static EntitlementType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (EntitlementType entitlementType : values()) {
            if (entitlementType.strValue.equals(str)) {
                return entitlementType;
            }
        }
        return null;
    }
}
