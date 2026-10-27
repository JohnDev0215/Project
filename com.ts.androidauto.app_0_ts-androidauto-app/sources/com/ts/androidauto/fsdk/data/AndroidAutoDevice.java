package com.ts.androidauto.fsdk.data;

import android.bluetooth.BluetoothDevice;
import android.hardware.usb.UsbDevice;
import android.os.Parcel;
import android.os.Parcelable;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidAutoDevice implements Parcelable {
    public static final Parcelable.Creator<AndroidAutoDevice> CREATOR = new Parcelable.Creator<AndroidAutoDevice>() { // from class: com.ts.androidauto.fsdk.data.AndroidAutoDevice.1
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
    private static final String TAG = "AAPAndroidAutoDevice";
    private int mAvailableCapability;
    private int mBatteryVol;
    private BluetoothDevice mBluetoothDevice;
    private String mBtAddr;
    private int mConnMode;
    private int mCurrentMode;
    private String mDeviceId;
    private int mDeviceType;
    private int mErrorcode;
    private int mLinkType;
    private String mMobileDeviceId;
    private int mPortNo;
    private int mStatus;
    private int mSupportMode;
    private UsbDevice mUsbDevice;
    private String mUsbHidNode;
    private int mUsbMode;
    private String mUsbNcmNode;
    private String mUsbSerialNumber;
    private String mWifiAddr;

    public AndroidAutoDevice() {
        this.mCurrentMode = AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber();
        this.mConnMode = AndroidAutoConst.ConnMode.MODE_DEFAULT_CONN.ordinal();
        this.mErrorcode = -1;
        this.mBatteryVol = -1;
        this.mPortNo = -1;
    }

    public AndroidAutoDevice(Parcel in) {
        this.mCurrentMode = AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber();
        this.mConnMode = AndroidAutoConst.ConnMode.MODE_DEFAULT_CONN.ordinal();
        this.mErrorcode = -1;
        this.mBatteryVol = -1;
        this.mPortNo = -1;
        this.mCurrentMode = in.readInt();
        this.mStatus = in.readInt();
        this.mUsbDevice = (UsbDevice) in.readParcelable(UsbDevice.class.getClassLoader());
        this.mLinkType = in.readInt();
        this.mBluetoothDevice = (BluetoothDevice) in.readParcelable(BluetoothDevice.class.getClassLoader());
        this.mDeviceId = in.readString();
        this.mSupportMode = in.readInt();
        this.mConnMode = in.readInt();
        this.mErrorcode = in.readInt();
        this.mBatteryVol = in.readInt();
        this.mPortNo = in.readInt();
        this.mMobileDeviceId = in.readString();
        this.mAvailableCapability = in.readInt();
        this.mUsbSerialNumber = in.readString();
        this.mUsbMode = in.readInt();
        this.mBtAddr = in.readString();
        this.mWifiAddr = in.readString();
        this.mUsbHidNode = in.readString();
        this.mUsbNcmNode = in.readString();
        this.mDeviceType = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mCurrentMode);
        dest.writeInt(this.mStatus);
        dest.writeParcelable(this.mUsbDevice, flags);
        dest.writeInt(this.mLinkType);
        dest.writeParcelable(this.mBluetoothDevice, flags);
        dest.writeString(this.mDeviceId);
        dest.writeInt(this.mSupportMode);
        dest.writeInt(this.mConnMode);
        dest.writeInt(this.mErrorcode);
        dest.writeInt(this.mBatteryVol);
        dest.writeInt(this.mPortNo);
        dest.writeString(this.mMobileDeviceId);
        dest.writeInt(this.mAvailableCapability);
        dest.writeString(this.mUsbSerialNumber);
        dest.writeInt(this.mUsbMode);
        dest.writeString(this.mBtAddr);
        dest.writeString(this.mWifiAddr);
        dest.writeString(this.mUsbHidNode);
        dest.writeString(this.mUsbNcmNode);
        dest.writeInt(this.mDeviceType);
    }

    public boolean isWireLink() {
        return this.mLinkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal();
    }

    public boolean isWireLink(int linkType) {
        return linkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal();
    }

    public boolean isWireLessLink() {
        return this.mLinkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal();
    }

    public boolean isWireLessLink(int linkType) {
        return linkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal();
    }

    public boolean isAapMode() {
        return this.mCurrentMode == AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber();
    }

    public boolean isCarPlayMode() {
        return false;
    }

    public boolean isCarLifeMode() {
        return false;
    }

    public boolean isAutoConnect() {
        return this.mConnMode == AndroidAutoConst.ConnMode.MODE_AUTO_CONN.ordinal();
    }

    public int getCurrentMode() {
        return this.mCurrentMode;
    }

    public void setCurrentMode(int currentMode) {
        this.mCurrentMode = currentMode;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public UsbDevice getUsbDevice() {
        return this.mUsbDevice;
    }

    public void setUsbDevice(UsbDevice usbDevice) {
        this.mUsbDevice = usbDevice;
    }

    public int getLinkType() {
        return this.mLinkType;
    }

    public void setLinkType(int linkType) {
        this.mLinkType = linkType;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.mBluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.mBluetoothDevice = bluetoothDevice;
    }

    public void setDeviceId(String deviceId) {
        this.mDeviceId = deviceId;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public int getSupportMode() {
        return this.mSupportMode;
    }

    public void setSupportMode(int supportMode) {
        this.mSupportMode = supportMode;
    }

    public int getConnMode() {
        return this.mConnMode;
    }

    public void setConnMode(int connMode) {
        this.mConnMode = connMode;
    }

    public int getErrorCode() {
        return this.mErrorcode;
    }

    public void setErrorCode(int errorCode) {
        this.mErrorcode = errorCode;
    }

    public int getBatteryVol() {
        return this.mBatteryVol;
    }

    public void setBatteryVol(int batteryVol) {
        this.mBatteryVol = batteryVol;
    }

    public int getAvailableCapability() {
        return this.mAvailableCapability;
    }

    public void setAvailableCapability(int availableCapability) {
        this.mAvailableCapability = availableCapability;
    }

    public String getUsbSerialNumber() {
        return this.mUsbSerialNumber;
    }

    public void setUsbSerialNumber(String usbSerialNumber) {
        this.mUsbSerialNumber = usbSerialNumber;
    }

    public String getUsbHidNode() {
        return this.mUsbHidNode;
    }

    public void setUsbHidNode(String usbHidNode) {
        this.mUsbHidNode = usbHidNode;
    }

    public String getUsbNcmNode() {
        return this.mUsbNcmNode;
    }

    public void setUsbNcmNode(String usbNcmNode) {
        this.mUsbNcmNode = usbNcmNode;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int deviceType) {
        this.mDeviceType = deviceType;
    }

    public int getPortNo() {
        return this.mPortNo;
    }

    public void setPortNo(int portNo) {
        this.mPortNo = portNo;
    }

    public String getMobileDeviceId() {
        return this.mMobileDeviceId;
    }

    public void setMobileDeviceId(String mobileDeviceId) {
        this.mMobileDeviceId = mobileDeviceId;
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

    public int getUsbMode() {
        return this.mUsbMode;
    }

    public void setUsbMode(int usbMode) {
        this.mUsbMode = usbMode;
    }

    public boolean equals(Object o) {
        String str;
        if (o != null && (o instanceof AndroidAutoDevice)) {
            AndroidAutoDevice androidAutoDevice = (AndroidAutoDevice) o;
            if (this.mLinkType == androidAutoDevice.getLinkType()) {
                if (this.mLinkType != AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal()) {
                    return this.mLinkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal() && (str = this.mBtAddr) != null && str.equals(androidAutoDevice.getBtAddr());
                }
                String str2 = this.mDeviceId;
                return str2 != null && str2.equals(androidAutoDevice.getDeviceId());
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        if (this.mLinkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal()) {
            String str = this.mDeviceId;
            if (str != null) {
                return str.hashCode();
            }
        } else if (this.mLinkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
            String str2 = this.mBtAddr;
            if (str2 != null) {
                return str2.hashCode();
            }
        } else {
            LogUtil.error(TAG, "Invalid mirror device: " + toString());
            return -1;
        }
        LogUtil.error(TAG, "Invalid mirror device: " + toString());
        return -1;
    }

    public void copy(AndroidAutoDevice androidAutoDevice) {
        if (androidAutoDevice != null) {
            this.mCurrentMode = androidAutoDevice.getCurrentMode();
            this.mStatus = androidAutoDevice.getStatus();
            this.mLinkType = androidAutoDevice.getLinkType();
            this.mUsbDevice = androidAutoDevice.getUsbDevice();
            this.mBluetoothDevice = androidAutoDevice.getBluetoothDevice();
            this.mDeviceId = androidAutoDevice.getDeviceId();
            this.mSupportMode = androidAutoDevice.getSupportMode();
            this.mConnMode = androidAutoDevice.getConnMode();
            this.mErrorcode = androidAutoDevice.getErrorCode();
            this.mBatteryVol = androidAutoDevice.getBatteryVol();
            this.mPortNo = androidAutoDevice.getPortNo();
            this.mMobileDeviceId = androidAutoDevice.getMobileDeviceId();
            this.mBtAddr = androidAutoDevice.getBtAddr();
            this.mUsbSerialNumber = androidAutoDevice.getUsbSerialNumber();
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Device(Id=");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mDeviceId));
        sb.append(" LinkType=");
        sb.append(this.mLinkType);
        sb.append(" CurrentMode=");
        sb.append(this.mCurrentMode);
        sb.append(" Status=");
        sb.append(this.mStatus);
        sb.append(" Err=");
        sb.append(this.mErrorcode);
        sb.append(" usb=");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mUsbSerialNumber));
        sb.append(" btaddr=");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mBtAddr));
        sb.append(" btdevice=");
        sb.append(LogEncrypt.MacAddressEncrypt(String.valueOf(this.mBluetoothDevice)));
        sb.append(" mobileDeviceId=");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mMobileDeviceId));
        sb.append(")");
        return sb.toString();
    }
}
