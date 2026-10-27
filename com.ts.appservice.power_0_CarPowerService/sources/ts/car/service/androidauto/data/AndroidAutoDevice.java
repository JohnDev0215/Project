package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes.dex */
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

    public void setIsFirstConntect(boolean isFirstConntect) {
        this.mIsFirstConntect = isFirstConntect;
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

    public String toString() {
        return "AndroidAutoDevice{mDeviceName='" + this.mDeviceName + ", mUsbSerialNumber='" + this.mUsbSerialNumber + ", mBtAddr='" + this.mBtAddr + ", mWifiAddr='" + this.mWifiAddr + ", mDeviceStatus=" + this.mDeviceStatus + ", mUsbDevice=" + this.mUsbDevice + ", mProjectionConnectType" + this.mProjectionConnectType + ", mProjectionConnectedTimeMillis" + this.mProjectionConnectedTimeMillis + ", mIsFirstConntect=" + this.mIsFirstConntect + "}";
    }
}
