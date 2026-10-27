package com.amazon.avtitleactionaggregationservice.model.detailpage.acquisition;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum PrimeAcquisitionOfferLevelSuppressionReason implements NamedEnum {
    GEO_ANONYMIZER_SCORE_ABOVE_THRESHOLD("GEO_ANONYMIZER_SCORE_ABOVE_THRESHOLD"),
    UNKNOWN_REASON("UNKNOWN_REASON"),
    DEVICE_RESTRICTED("DEVICE_RESTRICTED"),
    LIVE_EVENT_EVENT_BLACKOUT("LIVE_EVENT_EVENT_BLACKOUT"),
    GEO_LOCATION_IS_NOT_VALID_FOR_THIS_TITLE("GEO_LOCATION_IS_NOT_VALID_FOR_THIS_TITLE"),
    DAI_NOT_SUPPORTED("DAI_NOT_SUPPORTED"),
    LIVE_EVENT_PARTICIPANT_BLACKOUT("LIVE_EVENT_PARTICIPANT_BLACKOUT"),
    GEO_POLICY_REJECTION("GEO_POLICY_REJECTION"),
    DEVICE_FORM_FACTOR_NOT_PHONE("DEVICE_FORM_FACTOR_NOT_PHONE"),
    DEVICE_FAMILY_NOT_SUPPORTED("DEVICE_FAMILY_NOT_SUPPORTED");

    private final String strValue;

    PrimeAcquisitionOfferLevelSuppressionReason(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static PrimeAcquisitionOfferLevelSuppressionReason forValue(String str) {
        Preconditions.checkNotNull(str);
        for (PrimeAcquisitionOfferLevelSuppressionReason primeAcquisitionOfferLevelSuppressionReason : values()) {
            if (primeAcquisitionOfferLevelSuppressionReason.strValue.equals(str)) {
                return primeAcquisitionOfferLevelSuppressionReason;
            }
        }
        return null;
    }
}
