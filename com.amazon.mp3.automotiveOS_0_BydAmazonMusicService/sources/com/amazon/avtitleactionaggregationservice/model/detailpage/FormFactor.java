package com.amazon.avtitleactionaggregationservice.model.detailpage;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum FormFactor implements NamedEnum {
    PHONE("PHONE");

    private final String strValue;

    FormFactor(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static FormFactor forValue(String str) {
        Preconditions.checkNotNull(str);
        for (FormFactor formFactor : values()) {
            if (formFactor.strValue.equals(str)) {
                return formFactor;
            }
        }
        return null;
    }
}
