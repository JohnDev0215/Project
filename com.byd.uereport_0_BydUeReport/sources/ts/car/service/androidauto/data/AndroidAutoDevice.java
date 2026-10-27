package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidAutoDevice {
    private String mBtAddr;
    private String mDeviceName;
    private int mDeviceStatus;
    private boolean mIsFirstConntect;
    private int mProjectionConnectType;
    private long mProjectionConnectedTimeMillis;
    private boolean mUsbDevice;
    private String mUsbSerialNumber;
    private String mWifiAddr;

    public String getBtAddr() {
        return this.mBtAddr;
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

    public boolean isFirstConntect() {
        return this.mIsFirstConntect;
    }

    public boolean isUsbDevice() {
        return this.mUsbDevice;
    }

    public void setBtAddr(String str) {
        this.mBtAddr = str;
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

    public String toString() {
        return "AndroidAutoDevice{mDeviceName='" + this.mDeviceName + ", mUsbSerialNumber='" + this.mUsbSerialNumber + ", mBtAddr='" + this.mBtAddr + ", mWifiAddr='" + this.mWifiAddr + ", mDeviceStatus=" + this.mDeviceStatus + ", mUsbDevice=" + this.mUsbDevice + ", mProjectionConnectType" + this.mProjectionConnectType + ", mProjectionConnectedTimeMillis" + this.mProjectionConnectedTimeMillis + ", mIsFirstConntect=" + this.mIsFirstConntect + "}";
    }
}
