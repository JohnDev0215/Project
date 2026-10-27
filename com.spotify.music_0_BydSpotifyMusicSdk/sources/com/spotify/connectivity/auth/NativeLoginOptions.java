package com.spotify.connectivity.auth;

/* JADX INFO: loaded from: classes.dex */
public final class NativeLoginOptions {
    private String apHostOverride;
    private String apSrvHostOverride;
    private boolean authOnly;
    private boolean bootstrapRequired;
    private byte[] cacheId;
    private byte[] cacheSalt;
    private String canonicalUsername;
    private boolean enableAlwaysBootstrap;
    private boolean maintainPersistentApConnection = true;
    private String proxyHostName;
    private int proxyOption;
    private String proxyPassword;
    private int proxyPort;
    private String proxyUserName;
    private String referrer;
    private String xResolveHostOverride;

    public final String getApHostOverride() {
        return this.apHostOverride;
    }

    public final String getApSrvHostOverride() {
        return this.apSrvHostOverride;
    }

    public final boolean getAuthOnly() {
        return this.authOnly;
    }

    public final boolean getBootstrapRequired() {
        return this.bootstrapRequired;
    }

    public final byte[] getCacheId() {
        return this.cacheId;
    }

    public final byte[] getCacheSalt() {
        return this.cacheSalt;
    }

    public final String getCanonicalUsername() {
        return this.canonicalUsername;
    }

    public final boolean getEnableAlwaysBootstrap() {
        return this.enableAlwaysBootstrap;
    }

    public final boolean getMaintainPersistentApConnection() {
        return this.maintainPersistentApConnection;
    }

    public final String getProxyHostName() {
        return this.proxyHostName;
    }

    public final int getProxyOption() {
        return this.proxyOption;
    }

    public final String getProxyPassword() {
        return this.proxyPassword;
    }

    public final int getProxyPort() {
        return this.proxyPort;
    }

    public final String getProxyUserName() {
        return this.proxyUserName;
    }

    public final String getReferrer() {
        return this.referrer;
    }

    public final String getXResolveHostOverride() {
        return this.xResolveHostOverride;
    }

    public final void setApHostOverride(String str) {
        this.apHostOverride = str;
    }

    public final void setApSrvHostOverride(String str) {
        this.apSrvHostOverride = str;
    }

    public final void setAuthOnly(boolean z) {
        this.authOnly = z;
    }

    public final void setBootstrapRequired(boolean z) {
        this.bootstrapRequired = z;
    }

    public final void setCacheId(byte[] bArr) {
        this.cacheId = bArr;
    }

    public final void setCacheSalt(byte[] bArr) {
        this.cacheSalt = bArr;
    }

    public final void setCanonicalUsername(String str) {
        this.canonicalUsername = str;
    }

    public final void setEnableAlwaysBootstrap(boolean z) {
        this.enableAlwaysBootstrap = z;
    }

    public final void setMaintainPersistentApConnection(boolean z) {
        this.maintainPersistentApConnection = z;
    }

    public final void setProxyHostName(String str) {
        this.proxyHostName = str;
    }

    public final void setProxyOption(int i) {
        this.proxyOption = i;
    }

    public final void setProxyPassword(String str) {
        this.proxyPassword = str;
    }

    public final void setProxyPort(int i) {
        this.proxyPort = i;
    }

    public final void setProxyUserName(String str) {
        this.proxyUserName = str;
    }

    public final void setReferrer(String str) {
        this.referrer = str;
    }

    public final void setXResolveHostOverride(String str) {
        this.xResolveHostOverride = str;
    }
}
