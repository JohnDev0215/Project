package com.google.android.projection.protocol;

import com.google.android.projection.proto.Protos;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;

/* JADX INFO: loaded from: classes.dex */
public abstract class CarWifi {
    public static final int OPEN = 1;
    public static final int UNKNOWN_SECURITY_MODE = 0;
    public static final int WEP_128 = 3;
    public static final int WEP_64 = 2;
    public static final int WPA2_ENTERPRISE = 8;
    public static final int WPA2_PERSONAL = 5;
    public static final int WPA_ENTERPRISE = 7;
    public static final int WPA_PERSONAL = 4;
    public static final int WPA_WPA2_ENTERPRISE = 9;
    public static final int WPA_WPA2_PERSONAL = 6;

    public abstract CarWifiCredentials getCarWifiCredentials();

    public static class CarWifiCredentials {
        public final Protos.AccessPointType accessPointType;
        public final int[] supportedWifiChannels;
        public final String wifiPassword;
        public final Protos.WifiSecurityMode wifiSecurityMode;
        public final String wifiSsid;

        private CarWifiCredentials(String wifiSsid, String wifiPassword, Protos.WifiSecurityMode wifiSecurityMode, Protos.AccessPointType accessPointType, int[] supportedWifiChannels) {
            this.wifiSsid = wifiSsid;
            this.wifiPassword = wifiPassword;
            this.wifiSecurityMode = wifiSecurityMode;
            this.accessPointType = accessPointType;
            this.supportedWifiChannels = supportedWifiChannels;
        }

        private int getWifiSecurityModeNumber() {
            return this.wifiSecurityMode.getNumber();
        }

        private int getAccessPointType() {
            return this.accessPointType.getNumber();
        }

        public static class Builder {
            private String wifiSsid = AndroidAutoConst.DEFAULT_SCREEN_NAME;
            private String wifiPassword = AndroidAutoConst.DEFAULT_SCREEN_NAME;
            private Protos.WifiSecurityMode wifiSecurityMode = Protos.WifiSecurityMode.UNKNOWN_SECURITY_MODE;
            private Protos.AccessPointType accessPointType = Protos.AccessPointType.STATIC;
            private int[] supportedWifiChannels = new int[0];

            public Builder setWifiSsid(String wifiSsid) {
                this.wifiSsid = wifiSsid;
                return this;
            }

            public Builder setWifiPassword(String wifiPassword) {
                this.wifiPassword = wifiPassword;
                return this;
            }

            public Builder setWifiSecurityMode(Protos.WifiSecurityMode wifiSecurityMode) {
                this.wifiSecurityMode = wifiSecurityMode;
                return this;
            }

            public Builder setAccessPointType(Protos.AccessPointType accessPointType) {
                this.accessPointType = accessPointType;
                return this;
            }

            public Builder addSupportedWifiChannels(int[] supportedWifiChannels) {
                this.supportedWifiChannels = supportedWifiChannels;
                return this;
            }

            public CarWifiCredentials build() {
                return new CarWifiCredentials(this.wifiSsid, this.wifiPassword, this.wifiSecurityMode, this.accessPointType, this.supportedWifiChannels);
            }
        }
    }
}
