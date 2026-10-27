package ts.car.service.carplay.data;

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

    public void setCurWirelessCap(boolean z) {
        this.mCurWirelessCap = z;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public void setDeviceStatus(int i) {
        this.mDeviceStatus = i;
    }

    public void setIsFirstConntect(boolean z) {
        this.mIsFirstConntect = z;
    }

    public void setProjectionConnectType(int i) {
        this.mProjectionConnectType = i;
    }

    public void setProjectionConnectedTimeMillis(long j) {
        this.mProjectionConnectedTimeMillis = j;
    }

    public void setUsbDevice(boolean z) {
        this.mUsbDevice = z;
    }

    public void setUsbSerialNumber(String str) {
        this.mUsbSerialNumber = str;
    }

    public void setWifiAddr(String str) {
        this.mWifiAddr = str;
    }

    public void setWirelessAvailableState(int i) {
        this.mWirelessAvailableState = i;
    }

    public String toString() {
        return "CarPlayDevice{mDeviceName='" + this.mDeviceName + ", mUsbSerialNumber='" + this.mUsbSerialNumber + ", mBtAddr='" + this.mBtAddr + ", mWifiAddr='" + this.mWifiAddr + ", mDeviceStatus=" + this.mDeviceStatus + ", mProjectionConnectType=" + this.mProjectionConnectType + ", mUsbDevice=" + this.mUsbDevice + ", mProjectionConnectedTimeMillis=" + this.mProjectionConnectedTimeMillis + ", mIsFirstConntect=" + this.mIsFirstConntect + ", mCurWirelessCap=" + this.mCurWirelessCap + ", mWirelessAvailableState=" + this.mWirelessAvailableState + "}";
    }
}
