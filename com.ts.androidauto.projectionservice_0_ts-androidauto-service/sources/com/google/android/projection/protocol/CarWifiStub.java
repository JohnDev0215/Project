package com.google.android.projection.protocol;

import com.google.android.projection.proto.Protos;
import com.google.android.projection.protocol.CarWifi;

/* JADX INFO: loaded from: classes.dex */
public class CarWifiStub extends CarWifi {
    private CarWifi.CarWifiCredentials mWifiCreds;

    public CarWifiStub(int[] channels) {
        this.mWifiCreds = new CarWifi.CarWifiCredentials.Builder().setWifiSsid("wifiSSID").setWifiPassword("wifiPassword").setWifiSecurityMode(Protos.WifiSecurityMode.WPA2_PERSONAL).addSupportedWifiChannels(channels).setAccessPointType(Protos.AccessPointType.STATIC).build();
    }

    public CarWifiStub(String wifiSsid, String wifiPassword, Protos.WifiSecurityMode securityType, int[] channels, Protos.AccessPointType apType) {
        this.mWifiCreds = new CarWifi.CarWifiCredentials.Builder().setWifiSsid(wifiSsid).setWifiPassword(wifiPassword).setWifiSecurityMode(securityType).addSupportedWifiChannels(channels).setAccessPointType(apType).build();
    }

    @Override // com.google.android.projection.protocol.CarWifi
    public CarWifi.CarWifiCredentials getCarWifiCredentials() {
        return this.mWifiCreds;
    }
}
