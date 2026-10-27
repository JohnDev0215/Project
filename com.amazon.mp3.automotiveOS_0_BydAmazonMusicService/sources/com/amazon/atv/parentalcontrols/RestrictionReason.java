package com.amazon.atv.parentalcontrols;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum RestrictionReason implements NamedEnum {
    TIME_RESTRICTED_RATING("timeRestrictedRating"),
    RATING("rating"),
    INTERNAL_FAILURE("internalFailure"),
    CHILD_PROFILE("childProfile"),
    AGE_RESTRICTED_RATING("ageRestrictedRating"),
    PURCHASE_PIN_ENABLED("purchasePinEnabled"),
    ADULT("adult");

    private final String strValue;

    RestrictionReason(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static RestrictionReason forValue(String str) {
        Preconditions.checkNotNull(str);
        for (RestrictionReason restrictionReason : values()) {
            if (restrictionReason.strValue.equals(str)) {
                return restrictionReason;
            }
        }
        return null;
    }
}
