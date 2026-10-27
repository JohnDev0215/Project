package com.amazon.weblab.mobile.model;

/* JADX INFO: loaded from: classes5.dex */
public final class SessionInfo {
    private String mMarketplaceId;
    private String mSessionId;

    public SessionInfo(String str, String str2) throws IllegalArgumentException {
        setSessionId(str);
        setMarketplaceId(str2);
    }

    public SessionInfo(SessionInfo sessionInfo) {
        this(sessionInfo.mSessionId, sessionInfo.mMarketplaceId);
    }

    public String getSessionId() {
        return this.mSessionId;
    }

    public String getMarketplaceId() {
        return this.mMarketplaceId;
    }

    private String setSessionId(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Session can't be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Session can't be empty");
        }
        this.mSessionId = str;
        return null;
    }

    private String setMarketplaceId(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Marketplace can't be null");
        }
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Marketplace can't be empty");
        }
        this.mMarketplaceId = str;
        return null;
    }

    public int hashCode() {
        String str = this.mMarketplaceId;
        int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.mSessionId;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionInfo)) {
            return false;
        }
        SessionInfo sessionInfo = (SessionInfo) obj;
        return sessionInfo.mMarketplaceId.equals(this.mMarketplaceId) && sessionInfo.mSessionId.equals(this.mSessionId);
    }
}
