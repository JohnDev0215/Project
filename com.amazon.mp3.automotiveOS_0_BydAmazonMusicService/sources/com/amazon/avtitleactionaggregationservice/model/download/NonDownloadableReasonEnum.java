package com.amazon.avtitleactionaggregationservice.model.download;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum NonDownloadableReasonEnum implements NamedEnum {
    UNKNOWN_REASON("UNKNOWN_REASON"),
    FEATURE_NOT_SUPPORTED_BY_CLIENT("FEATURE_NOT_SUPPORTED_BY_CLIENT"),
    NOT_STREAMABLE("NOT_STREAMABLE"),
    TITLE_NOT_DOWNLOADABLE("TITLE_NOT_DOWNLOADABLE"),
    DEVICE_NOT_AUTHORIZED_FOR_LOWER_TIER_PRIME("DEVICE_NOT_AUTHORIZED_FOR_LOWER_TIER_PRIME"),
    NO_ENTITLEMENT("NO_ENTITLEMENT"),
    GEO_POLICY_REJECTION("GEO_POLICY_REJECTION"),
    TERMS_AND_CONDITIONS_NOT_ACCEPTED("TERMS_AND_CONDITIONS_NOT_ACCEPTED"),
    VIDEO_DEFINITION_NOT_ALLOWED("VIDEO_DEFINITION_NOT_ALLOWED"),
    DOWNLOAD_LIMIT_EXCEEDED("DOWNLOAD_LIMIT_EXCEEDED"),
    IDENTIFIER_NOT_FOUND("IDENTIFIER_NOT_FOUND");

    private final String strValue;

    NonDownloadableReasonEnum(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static NonDownloadableReasonEnum forValue(String str) {
        Preconditions.checkNotNull(str);
        for (NonDownloadableReasonEnum nonDownloadableReasonEnum : values()) {
            if (nonDownloadableReasonEnum.strValue.equals(str)) {
                return nonDownloadableReasonEnum;
            }
        }
        return null;
    }
}
