package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum SvodAcquisitionOfferLevelSuppressionReason implements NamedEnum {
    UNKNOWN_REASON("UNKNOWN_REASON"),
    DEVICE_SUPPRESSED_BY_CONTENT_PROVIDER("DEVICE_SUPPRESSED_BY_CONTENT_PROVIDER"),
    LIVE_EVENT_EVENT_BLACKOUT("LIVE_EVENT_EVENT_BLACKOUT"),
    LIVE_EVENT_PARTICIPANT_BLACKOUT("LIVE_EVENT_PARTICIPANT_BLACKOUT"),
    LINEAR_DATA_NOT_SUPPORTED("LINEAR_DATA_NOT_SUPPORTED");

    private final String strValue;

    SvodAcquisitionOfferLevelSuppressionReason(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static SvodAcquisitionOfferLevelSuppressionReason forValue(String str) {
        Preconditions.checkNotNull(str);
        for (SvodAcquisitionOfferLevelSuppressionReason svodAcquisitionOfferLevelSuppressionReason : values()) {
            if (svodAcquisitionOfferLevelSuppressionReason.strValue.equals(str)) {
                return svodAcquisitionOfferLevelSuppressionReason;
            }
        }
        return null;
    }
}
