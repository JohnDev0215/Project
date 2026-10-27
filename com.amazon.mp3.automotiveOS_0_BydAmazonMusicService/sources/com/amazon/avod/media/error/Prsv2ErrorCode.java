package com.amazon.avod.media.error;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum Prsv2ErrorCode {
    Denied("Denied"),
    ConcurrencyLimitBreached("Denied.ConcurrencyLimitBreached"),
    Fault("Fault"),
    Timeout("Fault.Timeout"),
    Invalid("Invalid"),
    PlaybackEnvelopeExpired("PlaybackEnvelope.Expired"),
    PlaybackEnvelopeInvalid("PlaybackEnvelope.Invalid");

    private final String mValue;

    Prsv2ErrorCode(String str) {
        this.mValue = str;
    }

    @Nonnull
    public static Prsv2ErrorCode fromString(@Nullable String str) {
        for (Prsv2ErrorCode prsv2ErrorCode : values()) {
            if (prsv2ErrorCode.mValue.equals(str)) {
                return prsv2ErrorCode;
            }
        }
        return Invalid;
    }
}
