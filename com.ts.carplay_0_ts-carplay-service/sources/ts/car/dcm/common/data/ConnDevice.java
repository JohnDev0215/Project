package ts.car.dcm.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.HashMap;
import java.util.Objects;
import ts.car.dcm.common.util.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public final class ConnDevice implements Parcelable {
    public static final Parcelable.Creator<ConnDevice> CREATOR = new Parcelable.Creator<ConnDevice>() { // from class: ts.car.dcm.common.data.ConnDevice.1
        @Override // android.os.Parcelable.Creator
        public ConnDevice createFromParcel(Parcel source) {
            ConnDevice device = new ConnDevice(source);
            return device;
        }

        @Override // android.os.Parcelable.Creator
        public ConnDevice[] newArray(int size) {
            return new ConnDevice[size];
        }
    };
    public static final long MASK_ACCEPTED = 128;
    public static final long MASK_ACTIVE_CAPABILITY = 1048576;
    public static final long MASK_ACTIVE_CAPABILITY_STATE = 2097152;
    public static final long MASK_ALWAYS_CAPABILITY = 4194304;
    public static final long MASK_AVAILABLE_CAPABILITY = 524288;
    public static final long MASK_BT_ADDR = 2048;
    public static final long MASK_BT_DEVICE_PAIRED_TIME = 536870912;
    public static final long MASK_BT_PROFILE_STATE = 8388608;
    public static final long MASK_CAPABILITY = 262144;
    public static final long MASK_CONNECTED_TYPE_STATE = 512;
    public static final long MASK_DEVICE_ADDED = 1;
    public static final long MASK_DEVICE_CONNECTED_STATE = 256;
    public static final long MASK_DEVICE_DELETED = 2;
    public static final long MASK_DEVICE_TYPE = 64;
    public static final long MASK_DEVICE_UPDATED = 4;
    public static final long MASK_DEVICE_UUID = 16;
    public static final long MASK_FREENDLY_NAME = 32;
    public static final long MASK_IAP_NODE = 67108864;
    public static final long MASK_IS_FAVORITE = 131072;
    public static final long MASK_IS_LAST_DEVICE = 65536;
    public static final long MASK_IS_PAIRED = 268435456;
    public static final long MASK_IS_USB_DEVICE_MODE = 16777216;
    public static final long MASK_IS_USB_OTG_PORT = 4294967296L;
    public static final long MASK_LAST_SERVICE = 32768;
    public static final long MASK_LAST_UP_TIME = 16384;
    public static final long MASK_TEMP_DEVICE = 8;
    public static final long MASK_USB_CARPLAY_CONNECTED_TIME = 2147483648L;
    public static final long MASK_USB_HID_NODE = 33554432;
    public static final long MASK_USB_NCM_NODE = 134217728;
    public static final long MASK_USB_SERIAL_NUMBER = 1024;
    public static final long MASK_WIFI_ADDR = 4096;
    public static final long MASK_WIFI_IP_ADDR = 8192;
    public static final long MASK_WIRELESS_CARPLAY_CONNECTED_TIME = 1073741824;
    private int mAccepted;
    private int mActiveCapability;
    private HashMap<Integer, Integer> mActiveCapabilityState;
    private int mAlwaysCapability;
    private int mAvailableCapability;
    private String mBtAddr;
    private long mBtDevicePairedTime;
    private HashMap<Integer, Integer> mBtProfileState;
    private int mCapability;
    private long mChangedMask;
    private final HashMap<Integer, Integer> mConnectedTypeState;
    private int mDeviceConnectedState;
    private int mDeviceType;
    private String mDeviceUuid;
    private boolean mFavorite;
    private String mFriendlyName;
    private String mIapNode;
    private boolean mInUsbDeviceMode;
    private boolean mIsAndroidAutoFirstConn;
    private boolean mIsCarplayFirstConn;
    private boolean mIsPaired;
    private boolean mIsUsbOtgPort;
    private boolean mLast;
    private int mLastService;
    private String mLastUptime;
    private String mMobileDeviceId;
    private long mUsbAndroidAutoConnectedTime;
    private long mUsbCarplayConnectedTime;
    private String mUsbHidNode;
    private String mUsbNcmNode;
    private String mUsbSerialNumber;
    private String mWifiAddr;
    private String mWifiIpAddress;
    private long mWirelessAndroidAutoConnectedTime;
    private long mWirelessCarplayConnectedTime;

    private ConnDevice() {
        this.mConnectedTypeState = new HashMap<>();
        this.mActiveCapabilityState = new HashMap<>();
        this.mBtProfileState = new HashMap<>();
        this.mInUsbDeviceMode = false;
        this.mIsPaired = false;
        this.mIsUsbOtgPort = false;
        this.mChangedMask = 0L;
        this.mIsCarplayFirstConn = true;
        this.mIsAndroidAutoFirstConn = true;
    }

    public ConnDevice(String deviceUuid) {
        this.mConnectedTypeState = new HashMap<>();
        this.mActiveCapabilityState = new HashMap<>();
        this.mBtProfileState = new HashMap<>();
        this.mInUsbDeviceMode = false;
        this.mIsPaired = false;
        this.mIsUsbOtgPort = false;
        this.mChangedMask = 0L;
        this.mIsCarplayFirstConn = true;
        this.mIsAndroidAutoFirstConn = true;
        this.mDeviceUuid = deviceUuid;
    }

    public ConnDevice(Parcel in) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        this.mConnectedTypeState = new HashMap<>();
        this.mActiveCapabilityState = new HashMap<>();
        this.mBtProfileState = new HashMap<>();
        this.mInUsbDeviceMode = false;
        this.mIsPaired = false;
        this.mIsUsbOtgPort = false;
        this.mChangedMask = 0L;
        this.mIsCarplayFirstConn = true;
        this.mIsAndroidAutoFirstConn = true;
        this.mDeviceUuid = in.readString();
        this.mFriendlyName = in.readString();
        this.mDeviceType = in.readInt();
        this.mAccepted = in.readInt();
        this.mDeviceConnectedState = in.readInt();
        in.readMap(this.mConnectedTypeState, String.class.getClassLoader());
        this.mUsbSerialNumber = in.readString();
        this.mBtAddr = in.readString();
        this.mWifiAddr = in.readString();
        this.mWifiIpAddress = in.readString();
        this.mLastUptime = in.readString();
        this.mLastService = in.readInt();
        if (in.readByte() == 0) {
            z = false;
        } else {
            z = true;
        }
        this.mLast = z;
        if (in.readByte() == 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.mFavorite = z2;
        this.mCapability = in.readInt();
        this.mAvailableCapability = in.readInt();
        this.mActiveCapability = in.readInt();
        in.readMap(this.mActiveCapabilityState, String.class.getClassLoader());
        this.mAlwaysCapability = in.readInt();
        in.readMap(this.mBtProfileState, String.class.getClassLoader());
        if (in.readByte() == 0) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.mInUsbDeviceMode = z3;
        this.mUsbHidNode = in.readString();
        this.mIapNode = in.readString();
        this.mUsbNcmNode = in.readString();
        if (in.readByte() == 0) {
            z4 = false;
        } else {
            z4 = true;
        }
        this.mIsPaired = z4;
        this.mBtDevicePairedTime = in.readLong();
        this.mWirelessCarplayConnectedTime = in.readLong();
        this.mUsbCarplayConnectedTime = in.readLong();
        if (in.readByte() == 0) {
            z5 = false;
        } else {
            z5 = true;
        }
        this.mIsUsbOtgPort = z5;
        this.mWirelessAndroidAutoConnectedTime = in.readLong();
        this.mUsbAndroidAutoConnectedTime = in.readLong();
        if (in.readByte() == 0) {
            z6 = false;
        } else {
            z6 = true;
        }
        this.mIsCarplayFirstConn = z6;
        this.mIsAndroidAutoFirstConn = in.readByte() != 0;
        this.mMobileDeviceId = in.readString();
    }

    public String getDeviceUuid() {
        return this.mDeviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.mDeviceUuid = deviceUuid;
    }

    public String getFriendlyName() {
        return this.mFriendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.mFriendlyName = friendlyName;
    }

    public int getAccepted() {
        return this.mAccepted;
    }

    public void setAccepted(int accepted) {
        this.mAccepted = accepted;
    }

    public String getUsbSerialNumber() {
        return this.mUsbSerialNumber;
    }

    public void setUsbSerialNumber(String usbSerial) {
        this.mUsbSerialNumber = usbSerial;
    }

    public String getBtAddr() {
        return this.mBtAddr;
    }

    public void setBtAddr(String btAddr) {
        this.mBtAddr = btAddr;
    }

    public String getWifiAddr() {
        return this.mWifiAddr;
    }

    public void setWifiAddr(String wifiAddr) {
        this.mWifiAddr = wifiAddr;
    }

    public String getLastUptime() {
        return this.mLastUptime;
    }

    public void setLastUptime(String lastUptime) {
        this.mLastUptime = lastUptime;
    }

    public int getLastService() {
        return this.mLastService;
    }

    public void setLastService(int lastService) {
        this.mLastService = lastService;
    }

    public boolean isLast() {
        return this.mLast;
    }

    public void setLast(boolean last) {
        this.mLast = last;
    }

    public boolean isFavorite() {
        return this.mFavorite;
    }

    public void setFavorite(boolean favorite) {
        this.mFavorite = favorite;
    }

    public int getDeviceConnectedState() {
        return this.mDeviceConnectedState;
    }

    public void setDeviceConnectedState(int state) {
        Log.d("ConnDevice", "setDeviceConnectedState mDeviceConnectedState:" + this.mDeviceConnectedState + ",state:" + state);
        this.mDeviceConnectedState = state;
    }

    public int getCapability() {
        return this.mCapability;
    }

    public void setCapability(int capability) {
        this.mCapability = capability;
    }

    public boolean isAvailableCapability(int capability) {
        return (this.mAvailableCapability & capability) > 0;
    }

    public String getUsbHidNode() {
        return this.mUsbHidNode;
    }

    public void setUsbHidNode(String node) {
        this.mUsbHidNode = node;
    }

    public String getIapNode() {
        return this.mIapNode;
    }

    public void setIapNode(String node) {
        this.mIapNode = node;
    }

    public String getUsbNcmNode() {
        return this.mUsbNcmNode;
    }

    public void setUsbNcmNode(String node) {
        this.mUsbNcmNode = node;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int deviceType) {
        this.mDeviceType = deviceType;
    }

    public boolean isInUsbDeviceMode() {
        return this.mInUsbDeviceMode;
    }

    public void setInUsbDeviceMode(boolean mode) {
        this.mInUsbDeviceMode = mode;
    }

    public int getAvailableCapabilitys() {
        return this.mAvailableCapability;
    }

    public void setAvailableCapability(int availableCapability) {
        this.mAvailableCapability = availableCapability;
    }

    public int getActiveCapability() {
        return this.mActiveCapability;
    }

    public void setActiveCapability(int activeCapability) {
        this.mActiveCapability = activeCapability;
    }

    public boolean isActivatedCapability(int capability) {
        return (this.mActiveCapability & capability) > 0;
    }

    public int getAlwaysCapability() {
        return this.mAlwaysCapability;
    }

    public void setAlwaysCapability(int alwaysCapability) {
        this.mAlwaysCapability = alwaysCapability;
    }

    public String getWifiIpAddress() {
        return this.mWifiIpAddress;
    }

    public void setWifiIpAddress(String wifiIpAddress) {
        this.mWifiIpAddress = wifiIpAddress;
    }

    @Deprecated
    public boolean isIsPaired() {
        return this.mIsPaired;
    }

    public boolean isPaired() {
        return this.mIsPaired;
    }

    public void setIsPaired(boolean isPaired) {
        this.mIsPaired = isPaired;
    }

    public long getBtDevicePairedTime() {
        return this.mBtDevicePairedTime;
    }

    public void setBtDevicePairedTime(long btDevicePairedTime) {
        this.mBtDevicePairedTime = btDevicePairedTime;
    }

    public long getWirelessCarplayConnectedTime() {
        return this.mWirelessCarplayConnectedTime;
    }

    public void setWirelessCarplayConnectedTime(long wirelessCarplayConnectedTime) {
        this.mWirelessCarplayConnectedTime = wirelessCarplayConnectedTime;
    }

    public long getUsbCarplayConnectedTime() {
        return this.mUsbCarplayConnectedTime;
    }

    public void setUsbCarplayConnectedTime(long usbCarplayConnectedTime) {
        this.mUsbCarplayConnectedTime = usbCarplayConnectedTime;
    }

    public long getWirelessAndroidAutoConnectedTime() {
        return this.mWirelessAndroidAutoConnectedTime;
    }

    public void setWirelessAndroidAutoConnectedTime(long wirelessAndroidAutoConnectedTime) {
        this.mWirelessAndroidAutoConnectedTime = wirelessAndroidAutoConnectedTime;
    }

    public long getUsbAndroidAutoConnectedTime() {
        return this.mUsbAndroidAutoConnectedTime;
    }

    public void setUsbAndroidAutoConnectedTime(long usbAndroidAutoConnectedTime) {
        this.mUsbAndroidAutoConnectedTime = usbAndroidAutoConnectedTime;
    }

    public boolean isUsbOtgPort() {
        return this.mIsUsbOtgPort;
    }

    public void setUsbOtgFlag(boolean isUsbOtgPort) {
        this.mIsUsbOtgPort = isUsbOtgPort;
    }

    public boolean isCarplayFirstConn() {
        return this.mIsCarplayFirstConn;
    }

    public void setIsCarplayFirstConn(boolean isCarplayFirstConn) {
        this.mIsCarplayFirstConn = isCarplayFirstConn;
    }

    public boolean isAndroidAutoFirstConn() {
        return this.mIsAndroidAutoFirstConn;
    }

    public void setIsAndroidAutoFirstConn(boolean isAndroidAutoFirstConn) {
        this.mIsAndroidAutoFirstConn = isAndroidAutoFirstConn;
    }

    public int getConnectedTypeState(int connectedType) {
        if (this.mConnectedTypeState.containsKey(Integer.valueOf(connectedType))) {
            return this.mConnectedTypeState.get(Integer.valueOf(connectedType)).intValue();
        }
        return 0;
    }

    public HashMap<Integer, Integer> getConnectedTypeState() {
        return this.mConnectedTypeState;
    }

    public void setConnectedTypeState(HashMap<Integer, Integer> state) {
        this.mConnectedTypeState.clear();
        this.mConnectedTypeState.putAll(state);
    }

    public boolean isOnline() {
        int i;
        return (this.mConnectedTypeState.size() <= 0 || (i = this.mDeviceConnectedState) == 3 || i == 2 || i == 4) ? false : true;
    }

    public int getActiveCapabilityState(int capability) {
        if (this.mActiveCapabilityState.containsKey(Integer.valueOf(capability))) {
            return this.mActiveCapabilityState.get(Integer.valueOf(capability)).intValue();
        }
        return 0;
    }

    public HashMap<Integer, Integer> getActiveCapabilityState() {
        return this.mActiveCapabilityState;
    }

    public void setActiveCapaState(HashMap<Integer, Integer> capabilityState) {
        this.mActiveCapabilityState.clear();
        this.mActiveCapabilityState.putAll(capabilityState);
    }

    public int getBtProfileState(int profile) {
        if (this.mBtProfileState.containsKey(Integer.valueOf(profile))) {
            return this.mBtProfileState.get(Integer.valueOf(profile)).intValue();
        }
        return 0;
    }

    public HashMap<Integer, Integer> getBtProfileState() {
        return this.mBtProfileState;
    }

    public void setBtProfileState(HashMap<Integer, Integer> profileState) {
        this.mBtProfileState.clear();
        this.mBtProfileState.putAll(profileState);
    }

    public void setChangedMask(long changedMask) {
        this.mChangedMask = changedMask;
    }

    public long getChangedMask() {
        return this.mChangedMask;
    }

    public String getMobileDeviceId() {
        return this.mMobileDeviceId;
    }

    public void setMobileDeviceId(String mobileDeviceId) {
        this.mMobileDeviceId = mobileDeviceId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConnDevice that = (ConnDevice) o;
        return Objects.equals(this.mDeviceUuid, that.mDeviceUuid);
    }

    public int hashCode() {
        String str = this.mDeviceUuid;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        synchronized (dest) {
            dest.writeString(this.mDeviceUuid);
            dest.writeString(this.mFriendlyName);
            dest.writeInt(this.mDeviceType);
            dest.writeInt(this.mAccepted);
            dest.writeInt(this.mDeviceConnectedState);
            dest.writeMap(this.mConnectedTypeState);
            dest.writeString(this.mUsbSerialNumber);
            dest.writeString(this.mBtAddr);
            dest.writeString(this.mWifiAddr);
            dest.writeString(this.mWifiIpAddress);
            dest.writeString(this.mLastUptime);
            dest.writeInt(this.mLastService);
            int i = 1;
            dest.writeByte((byte) (this.mLast ? 1 : 0));
            dest.writeByte((byte) (this.mFavorite ? 1 : 0));
            dest.writeInt(this.mCapability);
            dest.writeInt(this.mAvailableCapability);
            dest.writeInt(this.mActiveCapability);
            dest.writeMap(this.mActiveCapabilityState);
            dest.writeInt(this.mAlwaysCapability);
            dest.writeMap(this.mBtProfileState);
            dest.writeByte((byte) (this.mInUsbDeviceMode ? 1 : 0));
            dest.writeString(this.mUsbHidNode);
            dest.writeString(this.mIapNode);
            dest.writeString(this.mUsbNcmNode);
            dest.writeByte((byte) (this.mIsPaired ? 1 : 0));
            dest.writeLong(this.mBtDevicePairedTime);
            dest.writeLong(this.mWirelessCarplayConnectedTime);
            dest.writeLong(this.mUsbCarplayConnectedTime);
            dest.writeByte((byte) (this.mIsUsbOtgPort ? 1 : 0));
            dest.writeLong(this.mWirelessAndroidAutoConnectedTime);
            dest.writeLong(this.mUsbAndroidAutoConnectedTime);
            dest.writeByte((byte) (this.mIsCarplayFirstConn ? 1 : 0));
            if (!this.mIsAndroidAutoFirstConn) {
                i = 0;
            }
            dest.writeByte((byte) i);
            dest.writeString(this.mMobileDeviceId);
        }
    }

    public String toString() {
        return "ConnDevice{mConnectedTypeState=" + this.mConnectedTypeState + ", mDeviceUuid='" + LogEncrypt.IpAddressEncrypt(this.mDeviceUuid) + "', mFriendlyName='" + this.mFriendlyName + "', mAccepted=" + this.mAccepted + ", mDeviceType=" + this.mDeviceType + ", mDeviceConnectedState=" + this.mDeviceConnectedState + ", mUsbSerialNumber='" + LogEncrypt.IpAddressEncrypt(this.mUsbSerialNumber) + "', mBtAddr='" + LogEncrypt.MacAddressEncrypt(this.mBtAddr) + "', mWifiAddr='" + LogEncrypt.MacAddressEncrypt(this.mWifiAddr) + "', mWifiIpAddress='" + LogEncrypt.IpAddressEncrypt(this.mWifiIpAddress) + "', mLastUptime='" + this.mLastUptime + "', mLastService=" + this.mLastService + ", mLast=" + this.mLast + ", mFavorite=" + this.mFavorite + ", mCapability=" + this.mCapability + ", mAvailableCapability=" + this.mAvailableCapability + ", mActiveCapability=" + this.mActiveCapability + ", mAlwaysCapability=" + this.mAlwaysCapability + ", mActiveCapabilityState=" + this.mActiveCapabilityState + ", mBtProfileState=" + this.mBtProfileState + ", mInUsbDeviceMode=" + this.mInUsbDeviceMode + ", mUsbHidNode='" + this.mUsbHidNode + "', mIapNode='" + this.mIapNode + "', mUsbNcmNode='" + this.mUsbNcmNode + "', mIsPaired=" + this.mIsPaired + ", mBtDevicePairedTime=" + this.mBtDevicePairedTime + ", mWirelessCarplayConnectedTime=" + this.mWirelessCarplayConnectedTime + ", mUsbCarplayConnectedTime=" + this.mUsbCarplayConnectedTime + ", mIsUsbOtgPort=" + this.mIsUsbOtgPort + ", mIsCarplayFirstConn=" + this.mIsCarplayFirstConn + ", mIsAndroidAutoFirstConn=" + this.mIsAndroidAutoFirstConn + ", mWirelessAndroidAutoConnectedTime=" + this.mWirelessAndroidAutoConnectedTime + ", mUsbAndroidAutoConnectedTime=" + this.mUsbAndroidAutoConnectedTime + '}';
    }
}
