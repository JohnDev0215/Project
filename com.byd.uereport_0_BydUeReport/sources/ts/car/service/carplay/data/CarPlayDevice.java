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

    public String getBtAddr() {
        return this.mBtAddr;
    }

    public boolean getCurWirelessCap() {
        return this.mCurWirelessCap;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public int getDeviceStatus() {
        return this.mDeviceStatus;
    }

    public int getProjectionConnectType() {
        return this.mProjectionConnectType;
    }

    public long getProjectionConnectedTimeMillis() {
        return this.mProjectionConnectedTimeMillis;
    }

    public String getUsbSerialNumber() {
        return this.mUsbSerialNumber;
    }

    public String getWifiAddr() {
        return this.mWifiAddr;
    }

    public int getWirelessAvailableState() {
        return this.mWirelessAvailableState;
    }

    public boolean isFirstConntect() {
        return this.mIsFirstConntect;
    }

    public boolean isUsbDevice() {
        return this.mUsbDevice;
    }

    public void setBtAddr(String str) {
        this.mBtAddr = str;
    }

    public void setCurWirelessCap(boolean z7) {
        this.mCurWirelessCap = z7;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public void setDeviceStatus(int i8) {
        this.mDeviceStatus = i8;
    }

    public void setIsFirstConntect(boolean z7) {
        this.mIsFirstConntect = z7;
    }

    public void setProjectionConnectType(int i8) {
        this.mProjectionConnectType = i8;
    }

    public void setProjectionConnectedTimeMillis(long j8) {
        this.mProjectionConnectedTimeMillis = j8;
    }

    public void setUsbDevice(boolean z7) {
        this.mUsbDevice = z7;
    }

    public void setUsbSerialNumber(String str) {
        this.mUsbSerialNumber = str;
    }

    public void setWifiAddr(String str) {
        this.mWifiAddr = str;
    }

    public void setWirelessAvailableState(int i8) {
        this.mWirelessAvailableState = i8;
    }

    public String toString() {
        return "CarPlayDevice{mDeviceName='" + this.mDeviceName + ", mUsbSerialNumber='" + LogEncrypt.MacAddressEncrypt(this.mUsbSerialNumber) + ", mBtAddr='" + LogEncrypt.MacAddressEncrypt(this.mBtAddr) + ", mWifiAddr='" + LogEncrypt.MacAddressEncrypt(this.mWifiAddr) + ", mDeviceStatus=" + this.mDeviceStatus + ", mProjectionConnectType=" + this.mProjectionConnectType + ", mUsbDevice=" + this.mUsbDevice + ", mProjectionConnectedTimeMillis=" + this.mProjectionConnectedTimeMillis + ", mIsFirstConntect=" + this.mIsFirstConntect + ", mCurWirelessCap=" + this.mCurWirelessCap + ", mWirelessAvailableState=" + this.mWirelessAvailableState + "}";
    }
}
