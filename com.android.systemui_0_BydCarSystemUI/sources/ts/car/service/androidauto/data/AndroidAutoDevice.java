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

    public boolean isFirstConntect() {
        return this.mIsFirstConntect;
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

    public void setIsFirstConntect(boolean z) {
        this.mIsFirstConntect = z;
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

    public String toString() {
        return "AndroidAutoDevice{mDeviceName='" + this.mDeviceName + ", mUsbSerialNumber='" + this.mUsbSerialNumber + ", mBtAddr='" + this.mBtAddr + ", mWifiAddr='" + this.mWifiAddr + ", mDeviceStatus=" + this.mDeviceStatus + ", mUsbDevice=" + this.mUsbDevice + ", mProjectionConnectType" + this.mProjectionConnectType + ", mProjectionConnectedTimeMillis" + this.mProjectionConnectedTimeMillis + ", mIsFirstConntect=" + this.mIsFirstConntect + "}";
    }
}
