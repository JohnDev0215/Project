package com.ts.androidauto.sdk.aidl.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.ts.androidauto.sdk.common.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class AndroidAutoDevice implements Parcelable {
    public static final Parcelable.Creator<AndroidAutoDevice> CREATOR = new Parcelable.Creator<AndroidAutoDevice>() { // from class: com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AndroidAutoDevice createFromParcel(Parcel source) {
            AndroidAutoDevice linkDevice = new AndroidAutoDevice(source);
            return linkDevice;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AndroidAutoDevice[] newArray(int size) {
            return new AndroidAutoDevice[size];
        }
    };
    private String mBtAddr;
    private String mDeviceName;
    private int mDeviceStatus;
    private boolean mIsFirstConntect;
    private int mProjectionConnectType;
    private long mProjectionConnectedTimeMillis;
    private boolean mUsbDevice;
    private String mUsbSerialNumber;
    private String mWifiAddr;

    public AndroidAutoDevice() {
        this.mDeviceName = null;
        this.mUsbSerialNumber = null;
        this.mBtAddr = null;
        this.mWifiAddr = null;
        this.mDeviceStatus = -1;
        this.mProjectionConnectedTimeMillis = -1L;
    }

    public AndroidAutoDevice(Parcel in) {
        this.mDeviceName = in.readString();
        this.mUsbSerialNumber = in.readString();
        this.mBtAddr = in.readString();
        this.mWifiAddr = in.readString();
        this.mDeviceStatus = in.readInt();
        this.mUsbDevice = in.readBoolean();
        this.mProjectionConnectType = in.readInt();
        this.mProjectionConnectedTimeMillis = in.readLong();
        this.mIsFirstConntect = in.readBoolean();
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
        dest.writeBoolean(this.mUsbDevice);
        dest.writeInt(this.mProjectionConnectType);
        dest.writeLong(this.mProjectionConnectedTimeMillis);
        dest.writeBoolean(this.mIsFirstConntect);
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
        return "AndroidAutoDevice{mDeviceName='" + this.mDeviceName + ", mUsbSerialNumber='" + LogEncrypt.MacAddressEncrypt(this.mUsbSerialNumber) + ", mBtAddr='" + LogEncrypt.MacAddressEncrypt(this.mBtAddr) + ", mWifiAddr='" + LogEncrypt.MacAddressEncrypt(this.mWifiAddr) + ", mDeviceStatus=" + this.mDeviceStatus + ", mUsbDevice=" + this.mUsbDevice + ", mProjectionConnectType=" + this.mProjectionConnectType + ", mProjectionConnectedTimeMillis=" + this.mProjectionConnectedTimeMillis + ", mIsFirstConntect=" + this.mIsFirstConntect + "}";
    }
}
