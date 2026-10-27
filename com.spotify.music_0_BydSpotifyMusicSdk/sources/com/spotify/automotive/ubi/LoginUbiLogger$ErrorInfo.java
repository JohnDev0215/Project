package com.spotify.automotive.ubi;

/* JADX INFO: loaded from: classes.dex */
public enum LoginUbiLogger$ErrorInfo {
    UNKNOWN("unknown"),
    GENERAL("general"),
    /* JADX INFO: Fake field, exist only in values array */
    PAIR_PINNING("pair-pinning"),
    /* JADX INFO: Fake field, exist only in values array */
    RESTRICTED_WHILE_DRIVING("restricted-while-driving"),
    API_RATE_LIMITED("api-rate-limited"),
    BAD_CREDENTIALS("login-bad-credentials"),
    TRAVEL_RESTRICTION("travel-restriction");

    private final String id;

    LoginUbiLogger$ErrorInfo(String str) {
        this.id = str;
    }

    public final String a() {
        return this.id;
    }
}
