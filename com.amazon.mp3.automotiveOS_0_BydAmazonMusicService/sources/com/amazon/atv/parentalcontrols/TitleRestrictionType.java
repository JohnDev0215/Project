package com.amazon.atv.parentalcontrols;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum TitleRestrictionType implements NamedEnum {
    CONTENT_NOT_SUPPORTED("contentNotSupported"),
    PIN_SETUP_REQUIRED("pinSetupRequired"),
    CONTENT_RESTRICTED("contentRestricted"),
    PIN_REQUIRED("pinRequired"),
    AGE_VERIFICATION_REQUIRED("ageVerificationRequired");

    private final String strValue;

    TitleRestrictionType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static TitleRestrictionType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (TitleRestrictionType titleRestrictionType : values()) {
            if (titleRestrictionType.strValue.equals(str)) {
                return titleRestrictionType;
            }
        }
        return null;
    }
}
