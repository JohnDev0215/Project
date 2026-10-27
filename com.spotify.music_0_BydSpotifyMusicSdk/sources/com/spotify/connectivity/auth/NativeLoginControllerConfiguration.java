package com.spotify.connectivity.auth;

/* JADX INFO: loaded from: classes.dex */
public final class NativeLoginControllerConfiguration {
    private String accesspointLanguage;
    private String cachePath;
    private String clientID;
    private String contentAccessRefreshToken;
    private String deviceHardwareModel;
    private String deviceId;
    private int protocolOsOverride;
    private int reconnectPolicy = 2;
    private long revision;
    private int streamingRulesSupported;
    private boolean tracingEnabled;
    private String versionLong;

    public final String getAccesspointLanguage() {
        return this.accesspointLanguage;
    }

    public final String getCachePath() {
        return this.cachePath;
    }

    public final String getClientID() {
        return this.clientID;
    }

    public final String getContentAccessRefreshToken() {
        return this.contentAccessRefreshToken;
    }

    public final String getDeviceHardwareModel() {
        return this.deviceHardwareModel;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final int getProtocolOsOverride() {
        return this.protocolOsOverride;
    }

    public final int getReconnectPolicy() {
        return this.reconnectPolicy;
    }

    public final long getRevision() {
        return this.revision;
    }

    public final int getStreamingRulesSupported() {
        return this.streamingRulesSupported;
    }

    public final boolean getTracingEnabled() {
        return this.tracingEnabled;
    }

    public final String getVersionLong() {
        return this.versionLong;
    }

    public final void setAccesspointLanguage(String str) {
        this.accesspointLanguage = str;
    }

    public final void setCachePath(String str) {
        this.cachePath = str;
    }

    public final void setClientID(String str) {
        this.clientID = str;
    }

    public final void setContentAccessRefreshToken(String str) {
        this.contentAccessRefreshToken = str;
    }

    public final void setDeviceHardwareModel(String str) {
        this.deviceHardwareModel = str;
    }

    public final void setDeviceId(String str) {
        this.deviceId = str;
    }

    public final void setProtocolOsOverride(int i) {
        this.protocolOsOverride = i;
    }

    public final void setReconnectPolicy(int i) {
        this.reconnectPolicy = i;
    }

    public final void setRevision(long j) {
        this.revision = j;
    }

    public final void setStreamingRulesSupported(int i) {
        this.streamingRulesSupported = i;
    }

    public final void setTracingEnabled(boolean z) {
        this.tracingEnabled = z;
    }

    public final void setVersionLong(String str) {
        this.versionLong = str;
    }
}
