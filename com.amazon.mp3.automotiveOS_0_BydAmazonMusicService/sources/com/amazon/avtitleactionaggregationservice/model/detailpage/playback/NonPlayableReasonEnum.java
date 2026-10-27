package com.amazon.avtitleactionaggregationservice.model.detailpage.playback;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum NonPlayableReasonEnum implements NamedEnum {
    UNKNOWN_REASON("UNKNOWN_REASON"),
    FEATURE_NOT_SUPPORTED_BY_CLIENT("FEATURE_NOT_SUPPORTED_BY_CLIENT"),
    DEVICE_RESTRICTED("DEVICE_RESTRICTED"),
    NOT_STREAMABLE("NOT_STREAMABLE"),
    LIVE_EVENT_IN_FUTURE("LIVE_EVENT_IN_FUTURE"),
    NO_ENTITLEMENT("NO_ENTITLEMENT"),
    LIVE_EVENT_CANCELLED("LIVE_EVENT_CANCELLED"),
    LINEAR_DATA_NOT_SUPPORTED("LINEAR_DATA_NOT_SUPPORTED"),
    VIDEO_DEFINITION_NOT_ALLOWED("VIDEO_DEFINITION_NOT_ALLOWED"),
    LIVE_EVENT_POSTPONED("LIVE_EVENT_POSTPONED"),
    CHILD_PROFILE_AD_SUPPRESSION("CHILD_PROFILE_AD_SUPPRESSION"),
    LIVE_EVENT_EVENT_BLACKOUT("LIVE_EVENT_EVENT_BLACKOUT"),
    LOCATION_SERVICES_REQUIRED("LOCATION_SERVICES_REQUIRED"),
    DEVICE_NOT_AUTHORIZED_FOR_LOWER_TIER_PRIME("DEVICE_NOT_AUTHORIZED_FOR_LOWER_TIER_PRIME"),
    LIVE_EVENT_PARTICIPANT_BLACKOUT("LIVE_EVENT_PARTICIPANT_BLACKOUT"),
    LIVE_EVENT_ENDED("LIVE_EVENT_ENDED"),
    GEO_POLICY_REJECTION("GEO_POLICY_REJECTION"),
    LIVE_EVENT_SUSPENDED("LIVE_EVENT_SUSPENDED");

    private final String strValue;

    NonPlayableReasonEnum(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static NonPlayableReasonEnum forValue(String str) {
        Preconditions.checkNotNull(str);
        for (NonPlayableReasonEnum nonPlayableReasonEnum : values()) {
            if (nonPlayableReasonEnum.strValue.equals(str)) {
                return nonPlayableReasonEnum;
            }
        }
        return null;
    }
}
