package com.spotify.connectivity;

/* JADX INFO: loaded from: classes.dex */
public final class ApplicationScopeConfiguration {
    private String accesspointLanguage;
    private String cachePath;
    private String clientId;
    private int clientRevision;
    private String clientVersionLong;
    private String contentAccessRefreshToken;
    private String defaultHTTPUserAgent;
    private String deviceHardwareModel;
    private String deviceId;
    private boolean enableClientToken;
    private boolean enableOldNetworkImplementation;
    private boolean enablePerformanceTracing;
    private String encryptedPersistedClientToken;
    private boolean enableIPv6 = true;
    private int reconnectPolicy = ReconnectPolicy.AUTOMATIC_ON_CHANGE;
    private int protocolOsOverride = -1;
    private int protocolPlatformOverride = -1;
    private int protocolProductOverride = -1;

    public final String getAccesspointLanguage() {
        return this.accesspointLanguage;
    }

    public final String getCachePath() {
        return this.cachePath;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final int getClientRevision() {
        return this.clientRevision;
    }

    public final String getClientVersionLong() {
        return this.clientVersionLong;
    }

    public final String getContentAccessRefreshToken() {
        return this.contentAccessRefreshToken;
    }

    public final String getDefaultHTTPUserAgent() {
        return this.defaultHTTPUserAgent;
    }

    public final String getDeviceHardwareModel() {
        return this.deviceHardwareModel;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final boolean getEnableClientToken() {
        return this.enableClientToken;
    }

    public final boolean getEnableIPv6() {
        return this.enableIPv6;
    }

    public final boolean getEnableOldNetworkImplementation() {
        return this.enableOldNetworkImplementation;
    }

    public final boolean getEnablePerformanceTracing() {
        return this.enablePerformanceTracing;
    }

    public final String getEncryptedPersistedClientToken() {
        return this.encryptedPersistedClientToken;
    }

    public final int getProtocolOsOverride() {
        return this.protocolOsOverride;
    }

    public final int getProtocolPlatformOverride() {
        return this.protocolPlatformOverride;
    }

    public final int getProtocolProductOverride() {
        return this.protocolProductOverride;
    }

    public final int getReconnectPolicy() {
        return this.reconnectPolicy;
    }

    public final void setAccesspointLanguage(String str) {
        this.accesspointLanguage = str;
    }

    public final void setCachePath(String str) {
        this.cachePath = str;
    }

    public final void setClientId(String str) {
        this.clientId = str;
    }

    public final void setClientRevision(int i) {
        this.clientRevision = i;
    }

    public final void setClientVersionLong(String str) {
        this.clientVersionLong = str;
    }

    public final void setContentAccessRefreshToken(String str) {
        this.contentAccessRefreshToken = str;
    }

    public final void setDefaultHTTPUserAgent(String str) {
        this.defaultHTTPUserAgent = str;
    }

    public final void setDeviceHardwareModel(String str) {
        this.deviceHardwareModel = str;
    }

    public final void setDeviceId(String str) {
        this.deviceId = str;
    }

    public final void setEnableClientToken(boolean z) {
        this.enableClientToken = z;
    }

    public final void setEnableIPv6(boolean z) {
        this.enableIPv6 = z;
    }

    public final void setEnableOldNetworkImplementation(boolean z) {
        this.enableOldNetworkImplementation = z;
    }

    public final void setEnablePerformanceTracing(boolean z) {
        this.enablePerformanceTracing = z;
    }

    public final void setEncryptedPersistedClientToken(String str) {
        this.encryptedPersistedClientToken = str;
    }

    public final void setProtocolOsOverride(int i) {
        this.protocolOsOverride = i;
    }

    public final void setProtocolPlatformOverride(int i) {
        this.protocolPlatformOverride = i;
    }

    public final void setProtocolProductOverride(int i) {
        this.protocolProductOverride = i;
    }

    public final void setReconnectPolicy(int i) {
        this.reconnectPolicy = i;
    }
}
