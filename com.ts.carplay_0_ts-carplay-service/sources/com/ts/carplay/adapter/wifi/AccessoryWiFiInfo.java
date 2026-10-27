package com.ts.carplay.adapter.wifi;

import com.ts.carplay.adapter.common.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class AccessoryWiFiInfo {
    private String mSsid = null;
    private String mPassphrase = null;
    private int mSecurity = -1;
    private int mChannel = -1;
    private String mIpAddress = null;
    private String mMacAddress = null;
    private String mInterfaceName = null;

    public String getSsid() {
        return this.mSsid;
    }

    public void setSsid(String ssid) {
        this.mSsid = ssid;
    }

    public String getPassphrase() {
        return this.mPassphrase;
    }

    public void setPassphrase(String passphrase) {
        this.mPassphrase = passphrase;
    }

    public int getSecurity() {
        return this.mSecurity;
    }

    public void setSecurity(int security) {
        this.mSecurity = security;
    }

    public int getChannel() {
        return this.mChannel;
    }

    public void setChannel(int channel) {
        this.mChannel = channel;
    }

    public String getIpAddress() {
        return this.mIpAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.mIpAddress = ipAddress;
    }

    public String getMacAddress() {
        return this.mMacAddress;
    }

    public void setMacAddress(String macAddress) {
        this.mMacAddress = macAddress;
    }

    public String getInterfaceName() {
        return this.mInterfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.mInterfaceName = interfaceName;
    }

    public String toString() {
        return "AccessoryWiFiInfo {mSsid='" + LogEncrypt.IpAddressEncrypt(this.mSsid) + "', mPassphrase='" + LogEncrypt.IpAddressEncrypt(this.mPassphrase) + "', mSecurity='" + this.mSecurity + "', mChannel='" + this.mChannel + "', mIpAddress='" + LogEncrypt.IpAddressEncrypt(this.mInterfaceName) + "', mMacAddress='" + LogEncrypt.MacAddressEncrypt(this.mMacAddress) + "', mInterfaceName='" + this.mInterfaceName + "'}";
    }
}
