package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes5.dex */
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

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public void setUsbSerialNumber(String str) {
        this.mUsbSerialNumber = str;
    }

    public void setBtAddr(String str) {
        this.mBtAddr = str;
    }

    public void setWifiAddr(String str) {
        this.mWifiAddr = str;
    }

    public void setDeviceStatus(int i) {
        this.mDeviceStatus = i;
    }

    public void setUsbDevice(boolean z) {
        this.mUsbDevice = z;
    }

    public void setProjectionConnectType(int i) {
        this.mProjectionConnectType = i;
    }

    public int getProjectionConnectType() {
        return this.mProjectionConnectType;
    }

    public void setProjectionConnectedTimeMillis(long j) {
        this.mProjectionConnectedTimeMillis = j;
    }

    public long getProjectionConnectedTimeMillis() {
        return this.mProjectionConnectedTimeMillis;
    }

    public void setIsFirstConntect(boolean z) {
        this.mIsFirstConntect = z;
    }

    public boolean isFirstConntect() {
        return this.mIsFirstConntect;
    }

    public boolean getCurWirelessCap() {
        return this.mCurWirelessCap;
    }

    public void setCurWirelessCap(boolean z) {
        this.mCurWirelessCap = z;
    }

    public String toString() {
        return "CarPlayDevice{mDeviceName='" + this.mDeviceName + ", mUsbSerialNumber='" + this.mUsbSerialNumber + ", mBtAddr='" + this.mBtAddr + ", mWifiAddr='" + this.mWifiAddr + ", mDeviceStatus=" + this.mDeviceStatus + ", mProjectionConnectType=" + this.mProjectionConnectType + ", mUsbDevice=" + this.mUsbDevice + ", mProjectionConnectedTimeMillis=" + this.mProjectionConnectedTimeMillis + ", mIsFirstConntect=" + this.mIsFirstConntect + ", mCurWirelessCap=" + this.mCurWirelessCap + "}";
    }
}
