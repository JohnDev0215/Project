package ts.car.dm.fsdk.interfaces;

/* JADX INFO: loaded from: classes.dex */
public abstract class ProjectionDevice {
    private String mDeviceId;
    private String mDeviceName;
    private int mDeviceStatus = -1;
    protected String mMacAddress;
    private int mProjectionConnectType;
    private boolean mUsbDevice;
    protected String mUsbSerialNumber;

    public static final class ProjectionConnectType {
        public static final int PROJECTION_CONNECT_NONE = 0;
        public static final int PROJECTION_CONNECT_USB = 1;
        public static final int PROJECTION_CONNECT_WIRELESS = 2;
    }

    public abstract int connect(int i, boolean z);

    public abstract int disconnect();

    public class ConnectResult {
        public static final int ERROR_REMOTE_EXCEPTION = 0;
        public static final int ERROR_SERVICE_NULL = -1;
        public static final int SUCCESS = 1;

        public ConnectResult() {
        }
    }

    public class ConnectType {
        public static final int ALL = 0;
        public static final int USB = 1;
        public static final int WIFI = 2;

        public ConnectType() {
        }
    }

    public class DeviceStatus {
        public static final int AVAILABLE = 1;
        public static final int CONNECTED = 4;
        public static final int CONNECTING = 3;
        public static final int DISCONNECTING = 2;
        public static final int INVALID = -1;
        public static final int UNAVAILABLE = 0;

        public DeviceStatus() {
        }
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceName(String deviceName) {
        this.mDeviceName = deviceName;
    }

    public String getUsbSerialNumber() {
        return this.mUsbSerialNumber;
    }

    public void setUsbSerialNumber(String usbSerialNumber) {
        this.mUsbSerialNumber = usbSerialNumber;
    }

    public String getMacAddress() {
        return this.mMacAddress;
    }

    public void setMacAddress(String macAddress) {
        this.mMacAddress = macAddress;
    }

    public String getDeviceId() {
        String str = this.mDeviceId;
        if (str != null) {
            return str;
        }
        if (getMacAddress() != null) {
            String macAddress = getMacAddress();
            this.mDeviceId = macAddress;
            return macAddress;
        }
        if (getUsbSerialNumber() != null) {
            String usbSerialNumber = getUsbSerialNumber();
            this.mDeviceId = usbSerialNumber;
            return usbSerialNumber;
        }
        return null;
    }

    public int getDeviceStatus() {
        return this.mDeviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.mDeviceStatus = deviceStatus;
    }

    public void setUsbDevice(boolean isUsbDevice) {
        this.mUsbDevice = isUsbDevice;
    }

    public boolean isUsbDevice() {
        return this.mUsbDevice;
    }

    public void setProjectionConnectType(int projectionConnectType) {
        this.mProjectionConnectType = projectionConnectType;
    }

    public int getProjectionConnectType() {
        return this.mProjectionConnectType;
    }

    public String toString() {
        return "ProjectionDevice{deviceName='" + this.mDeviceName + "', usbSerialNumber='" + this.mUsbSerialNumber + "', btAddress='" + this.mMacAddress + "', deviceStatus=" + this.mDeviceStatus + ", mUsbDevice=" + this.mUsbDevice + ", mProjectionConnectType=" + this.mProjectionConnectType + '}';
    }
}
