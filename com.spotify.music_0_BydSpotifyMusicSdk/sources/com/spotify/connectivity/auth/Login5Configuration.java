package com.spotify.connectivity.auth;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Login5Configuration {
    private String clientId = "";
    private String deviceId = "";
    private int protocolVersion = 3;

    public final String getClientId() {
        return this.clientId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final int getProtocolVersion() {
        return this.protocolVersion;
    }

    public final void setClientId(String str) {
        v03.j(str, "<set-?>");
        this.clientId = str;
    }

    public final void setDeviceId(String str) {
        v03.j(str, "<set-?>");
        this.deviceId = str;
    }

    public final void setProtocolVersion(int i) {
        this.protocolVersion = i;
    }
}
