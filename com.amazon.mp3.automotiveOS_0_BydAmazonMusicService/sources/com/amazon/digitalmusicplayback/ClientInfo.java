package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class ClientInfo {
    final String deviceCategory;
    final String deviceDescription;
    final String osVersion;
    final String versionID;

    public ClientInfo(String str, String str2, String str3, String str4) {
        this.versionID = str;
        this.osVersion = str2;
        this.deviceDescription = str3;
        this.deviceCategory = str4;
    }

    public String getVersionID() {
        return this.versionID;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getDeviceDescription() {
        return this.deviceDescription;
    }

    public String getDeviceCategory() {
        return this.deviceCategory;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ClientInfo)) {
            return false;
        }
        ClientInfo clientInfo = (ClientInfo) obj;
        return this.versionID.equals(clientInfo.versionID) && this.osVersion.equals(clientInfo.osVersion) && this.deviceDescription.equals(clientInfo.deviceDescription) && this.deviceCategory.equals(clientInfo.deviceCategory);
    }

    public int hashCode() {
        return ((((((527 + this.versionID.hashCode()) * 31) + this.osVersion.hashCode()) * 31) + this.deviceDescription.hashCode()) * 31) + this.deviceCategory.hashCode();
    }

    public String toString() {
        return "ClientInfo{versionID=" + this.versionID + ",osVersion=" + this.osVersion + ",deviceDescription=" + this.deviceDescription + ",deviceCategory=" + this.deviceCategory + "}";
    }
}
