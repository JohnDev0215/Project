package com.ts.car.dcm.common.data;

/* JADX INFO: loaded from: classes.dex */
public class LinkDevice implements Cloneable {
    private String mBtAddress;
    private int mCapability;
    private int mConnectionType;
    private int mDeviceState;
    private int mDeviceType;
    private String mFriendlyName;
    private String mIapNode;
    private String mId;
    private String mUsbHidNode;
    private String mUsbNcmNode;
    private String mUsbSsid;
    private String mWifiAddress;
    private String mWifiIpAddrress;
    private boolean mIsPaired = false;
    private boolean mIsUsbOtgPort = false;
    private boolean mIsSupportCpByUuid = false;

    public boolean isPaired() {
        return this.mIsPaired;
    }

    public void setPaired(boolean isPaired) {
        this.mIsPaired = isPaired;
    }

    public boolean isUsbOtgPort() {
        return this.mIsUsbOtgPort;
    }

    public void setUsbOtgFlag(boolean isUsbOtgPort) {
        this.mIsUsbOtgPort = isUsbOtgPort;
    }

    public boolean isSupportCpByUuid() {
        return this.mIsSupportCpByUuid;
    }

    public void setSupportCpByUuid(boolean isSupportCpByUuid) {
        this.mIsSupportCpByUuid = isSupportCpByUuid;
    }

    public String toString() {
        return "LinkDevice{mId='" + LogEncrypt.MacAddressEncrypt(this.mId) + "', mFriendlyName='" + this.mFriendlyName + "', mUsbSsid='" + LogEncrypt.IpAddressEncrypt(this.mUsbSsid) + "', mBtAddress='" + LogEncrypt.MacAddressEncrypt(this.mBtAddress) + "', mWifiAddress='" + LogEncrypt.MacAddressEncrypt(this.mWifiAddress) + "', mWifiIpAddrress='" + LogEncrypt.IpAddressEncrypt(this.mWifiIpAddrress) + "', mConnType=" + this.mConnectionType + ", mCapability=" + this.mCapability + ", mDeviceConnState=" + this.mDeviceState + ", mDeviceType=" + this.mDeviceType + ", mUsbHidNode='" + this.mUsbHidNode + "', mIapNode='" + this.mIapNode + "', mUsbNcmNode='" + this.mUsbNcmNode + "', mIsPaired='" + this.mIsPaired + "', mIsUsbOtgPort ='" + this.mIsUsbOtgPort + "', mIsSupportCpByUuid ='" + this.mIsSupportCpByUuid + "'}";
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getFriendlyName() {
        return this.mFriendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.mFriendlyName = friendlyName;
    }

    public String getUsbSsid() {
        return this.mUsbSsid;
    }

    public void setUsbSsid(String usbSsid) {
        this.mUsbSsid = usbSsid;
    }

    public String getBtAddress() {
        return this.mBtAddress;
    }

    public void setBtAddress(String btAddress) {
        this.mBtAddress = btAddress;
    }

    public String getWifiAddress() {
        return this.mWifiAddress;
    }

    public void setWifiAddress(String wifiAddress) {
        this.mWifiAddress = wifiAddress;
    }

    public int getConnectionType() {
        return this.mConnectionType;
    }

    public void setConnectionType(int connType) {
        this.mConnectionType = connType;
    }

    public int getCapability() {
        return this.mCapability;
    }

    public void setCapability(int capability) {
        this.mCapability = capability;
    }

    public int getDeviceState() {
        return this.mDeviceState;
    }

    public void setDeviceState(int state) {
        this.mDeviceState = state;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int deviceType) {
        this.mDeviceType = deviceType;
    }

    public String getUsbHidNode() {
        return this.mUsbHidNode;
    }

    public void setUsbHidNode(String usbHidNode) {
        this.mUsbHidNode = usbHidNode;
    }

    public String getIapNode() {
        return this.mIapNode;
    }

    public void setIapNode(String iapNode) {
        this.mIapNode = iapNode;
    }

    public String getUsbNcmNode() {
        return this.mUsbNcmNode;
    }

    public void setUsbNcmNode(String usbNcmNode) {
        this.mUsbNcmNode = usbNcmNode;
    }

    public String getWifiIpAddrress() {
        return this.mWifiIpAddrress;
    }

    public void setWifiIpAddrress(String wifiIpAddrress) {
        this.mWifiIpAddrress = wifiIpAddrress;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
