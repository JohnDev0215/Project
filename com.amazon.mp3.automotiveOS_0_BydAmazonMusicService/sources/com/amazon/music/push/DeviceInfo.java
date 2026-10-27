package com.amazon.music.push;

import com.amazon.music.marketplace.Marketplace;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceInfo {
    private final String appVersion;
    private final String customerId;
    private final String deviceId;
    private final String deviceTimeZone;
    private final String deviceType;
    private final Boolean isFireOS;
    private final Marketplace marketplace;
    private final String territory;

    public DeviceInfo(String str, String str2, String str3, String str4, Marketplace marketplace, String str5, String str6, Boolean bool) {
        this.customerId = str;
        this.deviceId = str2;
        this.deviceType = str3;
        this.deviceTimeZone = str4;
        this.marketplace = marketplace;
        this.territory = str5;
        this.appVersion = str6;
        this.isFireOS = bool;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public Marketplace getMarketplace() {
        return this.marketplace;
    }

    public String getTerritory() {
        return this.territory;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public Boolean isFireOS() {
        return this.isFireOS;
    }

    public String toString() {
        return "DeviceInfo{customerId='" + this.customerId + "', deviceId='" + this.deviceId + "', deviceType='" + this.deviceType + "', deviceTimeZone='" + this.deviceTimeZone + "', marketplace=" + this.marketplace + ", territory='" + this.territory + "', appVersion='" + this.appVersion + "'}";
    }
}
