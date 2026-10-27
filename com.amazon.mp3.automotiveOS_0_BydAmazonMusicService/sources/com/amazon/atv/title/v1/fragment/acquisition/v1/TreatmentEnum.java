package com.amazon.atv.title.v1.fragment.acquisition.v1;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum TreatmentEnum implements NamedEnum {
    SUBSCRIPTION("SUBSCRIPTION"),
    RENTAL("RENTAL"),
    DISCOUNT("DISCOUNT");

    private final String strValue;

    TreatmentEnum(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static TreatmentEnum forValue(String str) {
        Preconditions.checkNotNull(str);
        for (TreatmentEnum treatmentEnum : values()) {
            if (treatmentEnum.strValue.equals(str)) {
                return treatmentEnum;
            }
        }
        return null;
    }
}
