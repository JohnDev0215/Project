package com.ts.car.dcm.wirelesscontroller;

import com.ts.car.dcm.common.data.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class WifiDevice {
    private volatile int mCapability;
    private volatile String mIpAddress;
    private volatile String mMacAddress;
    private volatile String mName;

    public String getMacAddress() {
        return this.mMacAddress;
    }

    public void setMacAddress(String macAddress) {
        this.mMacAddress = macAddress;
    }

    public String getIpAddress() {
        return this.mIpAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.mIpAddress = ipAddress;
    }

    public int getCapability() {
        return this.mCapability;
    }

    public void setCapability(int capability) {
        this.mCapability = capability;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String toString() {
        return "WifiDevice{mMacAddress='" + LogEncrypt.MacAddressEncrypt(this.mMacAddress) + "', mIpAddress='" + LogEncrypt.IpAddressEncrypt(this.mIpAddress) + "', mCapability=" + this.mCapability + ", mName='" + this.mName + "'}";
    }
}
