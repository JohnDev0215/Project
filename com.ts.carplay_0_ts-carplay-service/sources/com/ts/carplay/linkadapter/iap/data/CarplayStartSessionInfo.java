package com.ts.carplay.linkadapter.iap.data;

import com.ts.carplay.common.util.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class CarplayStartSessionInfo {
    private String mDeviceIdentifier;
    private int mPort;
    private String mPublicKey;
    private String mSourceVersion;
    private WiredAttribute mWiredAttribute;
    private WirelessAttribute mWirelessAttribute;

    public static class WirelessAttribute {
        int mChannel;
        String mIpAddress;
        String mPassphrase;
        int mSecurityType;
        String mWifiSsid;

        public String getIpAddress() {
            return this.mIpAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.mIpAddress = ipAddress;
        }

        public String getWifiSsid() {
            return this.mWifiSsid;
        }

        public void setWifiSsid(String wifiSsid) {
            this.mWifiSsid = wifiSsid;
        }

        public String getPassphrase() {
            return this.mPassphrase;
        }

        public void setPassphrase(String passphrase) {
            this.mPassphrase = passphrase;
        }

        public int getChannel() {
            return this.mChannel;
        }

        public void setChannel(int channel) {
            this.mChannel = channel;
        }

        public int getSecurityType() {
            return this.mSecurityType;
        }

        public void setSecurityType(int securityType) {
            this.mSecurityType = securityType;
        }

        public String toString() {
            return "WirelessAttribute [channel=" + this.mChannel + ", ipAddress=" + LogEncrypt.IpAddressEncrypt(this.mIpAddress) + ", passphrase=" + this.mPassphrase + ", wifiSsid=" + this.mWifiSsid + ", securityType=" + this.mSecurityType + "]";
        }
    }

    public static class WiredAttribute {
        private String mIpAddress;

        public String getIpAddress() {
            return this.mIpAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.mIpAddress = ipAddress;
        }

        public String toString() {
            return "WiredAttribute [ipAddress=" + LogEncrypt.IpAddressEncrypt(this.mIpAddress) + "]";
        }
    }

    public WirelessAttribute getWirelessAttribute() {
        return this.mWirelessAttribute;
    }

    public void setWirelessAttribute(WirelessAttribute wirelessAttribute) {
        this.mWirelessAttribute = wirelessAttribute;
    }

    public WiredAttribute getWiredAttribute() {
        return this.mWiredAttribute;
    }

    public void setWiredAttribute(WiredAttribute wiredAttribute) {
        this.mWiredAttribute = wiredAttribute;
    }

    public int getPort() {
        return this.mPort;
    }

    public void setPort(int port) {
        this.mPort = port;
    }

    public String getDeviceIdentifier() {
        return this.mDeviceIdentifier;
    }

    public void setDeviceIdentifier(String deviceIdentifier) {
        this.mDeviceIdentifier = deviceIdentifier;
    }

    public String getPublicKey() {
        return this.mPublicKey;
    }

    public void setPublicKey(String publicKey) {
        this.mPublicKey = publicKey;
    }

    public String getSourceVersion() {
        return this.mSourceVersion;
    }

    public void setSourceVersion(String sourceVersion) {
        this.mSourceVersion = sourceVersion;
    }

    public String toString() {
        return "CarplayStartSessionInfo [deviceIdentifier=" + LogEncrypt.MacAddressEncrypt(this.mDeviceIdentifier) + ", port=" + this.mPort + ", publicKey=" + this.mPublicKey + ", sourceVersion=" + this.mSourceVersion + ", wiredAttribute=" + this.mWiredAttribute + ", wirelessAttribute=" + this.mWirelessAttribute + "]";
    }

    public boolean checkValidity() {
        if (this.mDeviceIdentifier == null || this.mPublicKey == null || this.mSourceVersion == null) {
            return false;
        }
        return true;
    }
}
