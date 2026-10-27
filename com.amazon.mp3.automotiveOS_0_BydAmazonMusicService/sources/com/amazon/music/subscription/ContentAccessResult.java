package com.amazon.music.subscription;

import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class ContentAccessResult {
    private final boolean accessible;
    private final Reason reason;

    public enum Reason {
        HAS_ACCESS,
        ACCOUNT_LOCKED,
        ACCOUNT_NOT_VERIFIED,
        TERMS_OF_USE_NOT_ACCEPTED,
        DEVICE_NOT_AUTHORIZED,
        DEVICE_NOT_PRIME_AUTHORIZED,
        MARKETPLACE_NOT_SUPPORTED,
        CUSTOMER_HAS_HAWKFIRE_HOME_SUBSCRIPTION,
        CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION,
        CUSTOMER_HAS_NO_SONIC_RUSH_SUBSCRIPTION,
        CUSTOMER_HAS_NO_PRIME_SUBSCRIPTION,
        CUSTOMER_HAS_NO_NIGHTWING_SUBSCRIPTION,
        NIGHTWING_TIER_RESTRICTIONS,
        CUSTOMER_DATA_NOT_READY,
        ACCOUNT_NOT_CREATED,
        ACCOUNT_INVALID,
        BOP_TIER_RESTRICTIONS
    }

    private ContentAccessResult(boolean z, Reason reason) {
        this.accessible = z;
        this.reason = reason;
    }

    public boolean isAccessible() {
        return this.accessible;
    }

    public Reason getReason() {
        return this.reason;
    }

    static ContentAccessResult successfulContentAccessResult() {
        return new ContentAccessResult(true, Reason.HAS_ACCESS);
    }

    static ContentAccessResult failedContentAccessResult(Reason reason) {
        Validate.notNull(reason, "reason must not be null", new Object[0]);
        return new ContentAccessResult(false, reason);
    }
}
