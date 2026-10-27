package com.amazon.atv.playbackauthority.service;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum NotConsumableReasonType implements NamedEnum {
    FEATURE_NOT_SUPPORTED_BY_CLIENT("FEATURE_NOT_SUPPORTED_BY_CLIENT"),
    DEVICE_RESTRICTED("DEVICE_RESTRICTED"),
    CHILD_PROFILE_AD_SUPPRESSION("CHILD_PROFILE_AD_SUPPRESSION"),
    NOT_STREAMABLE("NOT_STREAMABLE"),
    NO_ENTITLEMENTS("NO_ENTITLEMENTS"),
    DEVICE_NOT_AUTHORIZED_FOR_LOWER_TIER_PRIME("DEVICE_NOT_AUTHORIZED_FOR_LOWER_TIER_PRIME"),
    GEO_POLICY_REJECTION("GEO_POLICY_REJECTION"),
    TERMS_AND_CONDITIONS_NOT_ACCEPTED("TERMS_AND_CONDITIONS_NOT_ACCEPTED"),
    VIDEO_DEFINITION_NOT_ALLOWED("VIDEO_DEFINITION_NOT_ALLOWED"),
    UNKNOWN("UNKNOWN"),
    DOWNLOAD_LIMIT_EXCEEDED("DOWNLOAD_LIMIT_EXCEEDED");

    private final String strValue;

    NotConsumableReasonType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static NotConsumableReasonType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (NotConsumableReasonType notConsumableReasonType : values()) {
            if (notConsumableReasonType.strValue.equals(str)) {
                return notConsumableReasonType;
            }
        }
        return null;
    }
}
