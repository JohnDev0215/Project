package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum SvodAcquisitionGroupLevelSuppressionReason implements NamedEnum {
    UNKNOWN_REASON("UNKNOWN_REASON"),
    LOCATION_OUT_OF_REGULATORY_TERRITORY("LOCATION_OUT_OF_REGULATORY_TERRITORY"),
    DAI_NOT_SUPPORTED("DAI_NOT_SUPPORTED"),
    LIVE_EVENT_ENDED("LIVE_EVENT_ENDED"),
    PAS_FAILURE("PAS_FAILURE"),
    LIVE_EVENT_CANCELLED("LIVE_EVENT_CANCELLED"),
    CHILDREN_PROFILE("CHILDREN_PROFILE"),
    LIVE_EVENT_POSTPONED("LIVE_EVENT_POSTPONED");

    private final String strValue;

    SvodAcquisitionGroupLevelSuppressionReason(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static SvodAcquisitionGroupLevelSuppressionReason forValue(String str) {
        Preconditions.checkNotNull(str);
        for (SvodAcquisitionGroupLevelSuppressionReason svodAcquisitionGroupLevelSuppressionReason : values()) {
            if (svodAcquisitionGroupLevelSuppressionReason.strValue.equals(str)) {
                return svodAcquisitionGroupLevelSuppressionReason;
            }
        }
        return null;
    }
}
