package com.spotify.connectivity.auth.oauth;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NativeAccessToken {
    private long expiresAt;
    private String tokenType = "";
    private String accessToken = "";

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final long getExpiresAt() {
        return this.expiresAt;
    }

    public final String getTokenType() {
        return this.tokenType;
    }

    public final void setAccessToken(String str) {
        v03.j(str, "<set-?>");
        this.accessToken = str;
    }

    public final void setExpiresAt(long j) {
        this.expiresAt = j;
    }

    public final void setTokenType(String str) {
        v03.j(str, "<set-?>");
        this.tokenType = str;
    }
}
