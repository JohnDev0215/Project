package com.ts.carplay.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.ts.carplay.common.util.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayDevice implements Parcelable {
    public static final Parcelable.Creator<CarPlayDevice> CREATOR = new Parcelable.Creator<CarPlayDevice>() { // from class: com.ts.carplay.common.data.CarPlayDevice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPlayDevice createFromParcel(Parcel source) {
            CarPlayDevice linkDevice = new CarPlayDevice(source);
            return linkDevice;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CarPlayDevice[] newArray(int size) {
            return new CarPlayDevice[size];
        }
    };
    private static final String TAG = "CarPlayDevice";
    private String mBtAddr;
    private boolean mCurWirelessCap;
    private String mDevId;
    private String mDeviceName;
    private int mDeviceStatus;
    private boolean mIsFirstConntect;
    private int mProjectionConnectType;
    private long mProjectionConnectedTimeMillis;
    private boolean mUsbDevice;
    private String mUsbSerialNumber;
    private String mWifiAddr;
    private int mWirelessAvailableState;

    public CarPlayDevice() {
        this.mDeviceName = null;
        this.mUsbSerialNumber = null;
        this.mBtAddr = null;
        this.mWifiAddr = null;
        this.mDeviceStatus = -1;
        this.mProjectionConnectedTimeMillis = -1L;
        this.mUsbDevice = false;
        this.mIsFirstConntect = true;
        this.mCurWirelessCap = true;
        this.mWirelessAvailableState = 0;
    }

    public CarPlayDevice(Parcel in) {
        this.mDeviceName = in.readString();
        this.mUsbSerialNumber = in.readString();
        this.mBtAddr = in.readString();
        this.mWifiAddr = in.readString();
        this.mDeviceStatus = in.readInt();
        this.mProjectionConnectedTimeMillis = in.readLong();
        this.mUsbDevice = in.readBoolean();
        this.mProjectionConnectType = in.readInt();
        this.mDevId = in.readString();
        this.mIsFirstConntect = in.readBoolean();
        this.mCurWirelessCap = in.readBoolean();
        this.mWirelessAvailableState = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDeviceName);
        dest.writeString(this.mUsbSerialNumber);
        dest.writeString(this.mBtAddr);
        dest.writeString(this.mWifiAddr);
        dest.writeInt(this.mDeviceStatus);
        dest.writeLong(this.mProjectionConnectedTimeMillis);
        dest.writeBoolean(this.mUsbDevice);
        dest.writeInt(this.mProjectionConnectType);
        dest.writeString(this.mDevId);
        dest.writeBoolean(this.mIsFirstConntect);
        dest.writeBoolean(this.mCurWirelessCap);
        dest.writeInt(this.mWirelessAvailableState);
    }

    public void clear() {
        this.mDeviceName = null;
        this.mUsbSerialNumber = null;
        this.mBtAddr = null;
        this.mWifiAddr = null;
        this.mDeviceStatus = -1;
        this.mProjectionConnectedTimeMillis = -1L;
        this.mUsbDevice = false;
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

    public boolean isFirstConntect() {
        return this.mIsFirstConntect;
    }

    public boolean getCurWirelessCap() {
        return this.mCurWirelessCap;
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

    public void setProjectionConnectedTimeMillis(long projectionConnectedTimeMillis) {
        this.mProjectionConnectedTimeMillis = projectionConnectedTimeMillis;
    }

    public long getProjectionConnectedTimeMillis() {
        return this.mProjectionConnectedTimeMillis;
    }

    public void setUsbDevice(boolean isUsbDevice) {
        this.mUsbDevice = isUsbDevice;
    }

    public void setIsFirstConntect(boolean isFirstConntect) {
        this.mIsFirstConntect = isFirstConntect;
    }

    public void setCurWirelessCap(boolean curWirelessCap) {
        this.mCurWirelessCap = curWirelessCap;
    }

    public void setProjectionConnectType(int projectionConnectType) {
        this.mProjectionConnectType = projectionConnectType;
    }

    public int getProjectionConnectType() {
        return this.mProjectionConnectType;
    }

    public String getDeviceId() {
        return this.mDevId;
    }

    public void setDeviceId(String devId) {
        this.mDevId = devId;
    }

    public int getWirelessAvailableState() {
        return this.mWirelessAvailableState;
    }

    public void setWirelessAvailableState(int wirelessAvailableState) {
        this.mWirelessAvailableState = wirelessAvailableState;
    }

    public String toString() {
        return "CarPlayDevice{mDeviceName='" + this.mDeviceName + ", mUsbSerialNumber='" + LogEncrypt.MacAddressEncrypt(this.mUsbSerialNumber) + ", mBtAddr='" + LogEncrypt.MacAddressEncrypt(this.mBtAddr) + ", mWifiAddr='" + LogEncrypt.MacAddressEncrypt(this.mWifiAddr) + ", mDeviceStatus=" + this.mDeviceStatus + ", mProjectionConnectedTimeMillis=" + this.mProjectionConnectedTimeMillis + ", mUsbDevice=" + this.mUsbDevice + ", mProjectionConnectType=" + this.mProjectionConnectType + ", mIsFirstConntect=" + this.mIsFirstConntect + ", mCurWirelessCap=" + this.mCurWirelessCap + ", mWirelessAvailableState=" + this.mWirelessAvailableState + "}";
    }
}
