package com.amazon.avtitleactionaggregationservice.model.acquisitions;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ContractPeriodType implements NamedEnum {
    INTRODUCTORY_PERIOD("INTRODUCTORY_PERIOD"),
    BASE_PERIOD("BASE_PERIOD"),
    FREE_TRIAL_PERIOD("FREE_TRIAL_PERIOD");

    private final String strValue;

    ContractPeriodType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ContractPeriodType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ContractPeriodType contractPeriodType : values()) {
            if (contractPeriodType.strValue.equals(str)) {
                return contractPeriodType;
            }
        }
        return null;
    }
}
