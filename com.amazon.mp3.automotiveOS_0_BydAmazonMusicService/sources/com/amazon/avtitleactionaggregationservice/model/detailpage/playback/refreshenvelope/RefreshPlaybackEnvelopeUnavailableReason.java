package com.amazon.avtitleactionaggregationservice.model.detailpage.playback.refreshenvelope;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum RefreshPlaybackEnvelopeUnavailableReason implements NamedEnum {
    UNKNOWN_REASON("UNKNOWN_REASON"),
    CUSTOMER_ENTITLEMENT_CHANGED("CUSTOMER_ENTITLEMENT_CHANGED"),
    CUSTOMER_NOT_ENTITLED("CUSTOMER_NOT_ENTITLED");

    private final String strValue;

    RefreshPlaybackEnvelopeUnavailableReason(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static RefreshPlaybackEnvelopeUnavailableReason forValue(String str) {
        Preconditions.checkNotNull(str);
        for (RefreshPlaybackEnvelopeUnavailableReason refreshPlaybackEnvelopeUnavailableReason : values()) {
            if (refreshPlaybackEnvelopeUnavailableReason.strValue.equals(str)) {
                return refreshPlaybackEnvelopeUnavailableReason;
            }
        }
        return null;
    }
}
