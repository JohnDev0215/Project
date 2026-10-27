package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class DMLSConfig {
    final String appVersion;
    final String customerID;
    final String deviceID;
    final String deviceTypeID;
    final boolean gamma;
    final String marketplaceID;
    final String territoryID;

    public DMLSConfig(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        this.customerID = str;
        this.deviceTypeID = str2;
        this.deviceID = str3;
        this.appVersion = str4;
        this.marketplaceID = str5;
        this.territoryID = str6;
        this.gamma = z;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getDeviceTypeID() {
        return this.deviceTypeID;
    }

    public String getDeviceID() {
        return this.deviceID;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getMarketplaceID() {
        return this.marketplaceID;
    }

    public String getTerritoryID() {
        return this.territoryID;
    }

    public boolean getGamma() {
        return this.gamma;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DMLSConfig)) {
            return false;
        }
        DMLSConfig dMLSConfig = (DMLSConfig) obj;
        return this.customerID.equals(dMLSConfig.customerID) && this.deviceTypeID.equals(dMLSConfig.deviceTypeID) && this.deviceID.equals(dMLSConfig.deviceID) && this.appVersion.equals(dMLSConfig.appVersion) && this.marketplaceID.equals(dMLSConfig.marketplaceID) && this.territoryID.equals(dMLSConfig.territoryID) && this.gamma == dMLSConfig.gamma;
    }

    public int hashCode() {
        return ((((((((((((527 + this.customerID.hashCode()) * 31) + this.deviceTypeID.hashCode()) * 31) + this.deviceID.hashCode()) * 31) + this.appVersion.hashCode()) * 31) + this.marketplaceID.hashCode()) * 31) + this.territoryID.hashCode()) * 31) + (this.gamma ? 1 : 0);
    }

    public String toString() {
        return "DMLSConfig{customerID=" + this.customerID + ",deviceTypeID=" + this.deviceTypeID + ",deviceID=" + this.deviceID + ",appVersion=" + this.appVersion + ",marketplaceID=" + this.marketplaceID + ",territoryID=" + this.territoryID + ",gamma=" + this.gamma + "}";
    }
}
