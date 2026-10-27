package ts.car.service.carplay.data;

import ts.car.service.carplay.data.config.LogEncrypt;

/* JADX INFO: loaded from: classes2.dex */
public class CarPlayDevice {
    private String mDeviceName = null;
    private String mUsbSerialNumber = null;
    private String mBtAddr = null;
    private String mWifiAddr = null;
    private int mDeviceStatus = -1;
    private boolean mUsbDevice = false;
    private int mProjectionConnectType = 0;
    private long mProjectionConnectedTimeMillis = -1;
    private boolean mIsFirstConntect = true;
    private boolean mCurWirelessCap = true;
    private int mWirelessAvailableState = 0;

    public void clear() {
        this.mDeviceName = null;
        this.mUsbSerialNumber = null;
        this.mBtAddr = null;
        this.mWifiAddr = null;
        this.mDeviceStatus = -1;
        this.mUsbDevice = false;
        this.mProjectionConnectType = 0;
        this.mProjectionConnectedTimeMillis = -1L;
        this.mIsFirstConntect = true;
        this.mCurWirelessCap = true;
        this.mWirelessAvailableState = 0;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public String getUsbSerialNumber() {
        return this.mUsbSerialNumber;
    }

    public String getBtAddr() {
        return this.mBtAddr;
    }

    public String getWifiAddr() {
        return this.mWifiAddr;
    }

    public int getDeviceStatus() {
        return this.mDeviceStatus;
    }

    public boolean isUsbDevice() {
        return this.mUsbDevice;
    }

    public void setDeviceName(String deviceName) {
        this.mDeviceName = deviceName;
    }

    public void setUsbSerialNumber(String usbSerialNumber) {
        this.mUsbSerialNumber = usbSerialNumber;
    }

    public void setBtAddr(String btAddr) {
        this.mBtAddr = btAddr;
    }

    public void setWifiAddr(String wifiAddr) {
        this.mWifiAddr = wifiAddr;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.mDeviceStatus = deviceStatus;
    }

    public void setUsbDevice(boolean isUsbDevice) {
        this.mUsbDevice = isUsbDevice;
    }

    public void setProjectionConnectType(int projectionConnectType) {
        this.mProjectionConnectType = projectionConnectType;
    }

    public int getProjectionConnectType() {
        return this.mProjectionConnectType;
    }

    public void setProjectionConnectedTimeMillis(long projectionConnectedTimeMillis) {
        this.mProjectionConnectedTimeMillis = projectionConnectedTimeMillis;
    }

    public long getProjectionConnectedTimeMillis() {
        return this.mProjectionConnectedTimeMillis;
    }

    public void setIsFirstConntect(boolean isFirstConntect) {
        this.mIsFirstConntect = isFirstConntect;
    }

    public boolean isFirstConntect() {
        return this.mIsFirstConntect;
    }

    public boolean getCurWirelessCap() {
        return this.mCurWirelessCap;
    }

    public void setCurWirelessCap(boolean curWirelessCap) {
        this.mCurWirelessCap = curWirelessCap;
    }

    public int getWirelessAvailableState() {
        return this.mWirelessAvailableState;
    }

    public void setWirelessAvailableState(int wirelessAvailableState) {
        this.mWirelessAvailableState = wirelessAvailableState;
    }

    public String toString() {
        return "CarPlayDevice{mDeviceName='" + this.mDeviceName + ", mUsbSerialNumber='" + LogEncrypt.MacAddressEncrypt(this.mUsbSerialNumber) + ", mBtAddr='" + LogEncrypt.MacAddressEncrypt(this.mBtAddr) + ", mWifiAddr='" + LogEncrypt.MacAddressEncrypt(this.mWifiAddr) + ", mDeviceStatus=" + this.mDeviceStatus + ", mProjectionConnectType=" + this.mProjectionConnectType + ", mUsbDevice=" + this.mUsbDevice + ", mProjectionConnectedTimeMillis=" + this.mProjectionConnectedTimeMillis + ", mIsFirstConntect=" + this.mIsFirstConntect + ", mCurWirelessCap=" + this.mCurWirelessCap + ", mWirelessAvailableState=" + this.mWirelessAvailableState + "}";
    }
}
