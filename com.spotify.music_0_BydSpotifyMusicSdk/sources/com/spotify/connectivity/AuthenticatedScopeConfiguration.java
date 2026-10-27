package com.spotify.connectivity;

/* JADX INFO: loaded from: classes.dex */
public final class AuthenticatedScopeConfiguration {
    private String cachePath;
    private String clientId;
    private String clientVersionLong;
    private int dealerPingTime = 30000;
    private String deviceId;
    private boolean includePayloads;
    private String webgateLocale;
    private String webgateUserAgentParameterAppPlatform;
    private String webgateUserAgentParameterAppVersion;
    private String webgateUserAgentParameterExtraInformation;
    private String webgateUserAgentParameterOsVersion;

    public final String getCachePath() {
        return this.cachePath;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getClientVersionLong() {
        return this.clientVersionLong;
    }

    public final int getDealerPingTime() {
        return this.dealerPingTime;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final boolean getIncludePayloads() {
        return this.includePayloads;
    }

    public final String getWebgateLocale() {
        return this.webgateLocale;
    }

    public final String getWebgateUserAgentParameterAppPlatform() {
        return this.webgateUserAgentParameterAppPlatform;
    }

    public final String getWebgateUserAgentParameterAppVersion() {
        return this.webgateUserAgentParameterAppVersion;
    }

    public final String getWebgateUserAgentParameterExtraInformation() {
        return this.webgateUserAgentParameterExtraInformation;
    }

    public final String getWebgateUserAgentParameterOsVersion() {
        return this.webgateUserAgentParameterOsVersion;
    }

    public final void setCachePath(String str) {
        this.cachePath = str;
    }

    public final void setClientId(String str) {
        this.clientId = str;
    }

    public final void setClientVersionLong(String str) {
        this.clientVersionLong = str;
    }

    public final void setDealerPingTime(int i) {
        this.dealerPingTime = i;
    }

    public final void setDeviceId(String str) {
        this.deviceId = str;
    }

    public final void setIncludePayloads(boolean z) {
        this.includePayloads = z;
    }

    public final void setWebgateLocale(String str) {
        this.webgateLocale = str;
    }

    public final void setWebgateUserAgentParameterAppPlatform(String str) {
        this.webgateUserAgentParameterAppPlatform = str;
    }

    public final void setWebgateUserAgentParameterAppVersion(String str) {
        this.webgateUserAgentParameterAppVersion = str;
    }

    public final void setWebgateUserAgentParameterExtraInformation(String str) {
        this.webgateUserAgentParameterExtraInformation = str;
    }

    public final void setWebgateUserAgentParameterOsVersion(String str) {
        this.webgateUserAgentParameterOsVersion = str;
    }
}
