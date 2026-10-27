package com.ts.carplay.linkadapter.device.data;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class LinkDevice implements Parcelable {
    public static final Parcelable.Creator<LinkDevice> CREATOR = new Parcelable.Creator<LinkDevice>() { // from class: com.ts.carplay.linkadapter.device.data.LinkDevice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LinkDevice createFromParcel(Parcel source) {
            LinkDevice linkDevice = new LinkDevice(source);
            return linkDevice;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LinkDevice[] newArray(int size) {
            return new LinkDevice[size];
        }
    };
    private static final String TAG = "LinkDevice";
    private BluetoothDevice mBluetoothDevice;
    private String mBtAddr;
    private String mDeviceId;
    private String mDeviceName;
    private int mLinkType;
    private int mStatus;
    private String mUsbSerialNumber;
    private String mWifiAddr;

    public LinkDevice() {
    }

    public LinkDevice(Parcel in) {
        this.mStatus = in.readInt();
        this.mLinkType = in.readInt();
        this.mBluetoothDevice = (BluetoothDevice) in.readParcelable(BluetoothDevice.class.getClassLoader());
        this.mDeviceId = in.readString();
        this.mUsbSerialNumber = in.readString();
        this.mBtAddr = in.readString();
        this.mWifiAddr = in.readString();
        this.mDeviceName = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mStatus);
        dest.writeInt(this.mLinkType);
        dest.writeParcelable(this.mBluetoothDevice, flags);
        dest.writeString(this.mDeviceId);
        dest.writeString(this.mUsbSerialNumber);
        dest.writeString(this.mBtAddr);
        dest.writeString(this.mWifiAddr);
        dest.writeString(this.mDeviceName);
    }

    public boolean isWireLink() {
        return this.mLinkType == 1;
    }

    public boolean isWireLink(int linkType) {
        return linkType == 1;
    }

    public boolean isWireLessLink() {
        return this.mLinkType == 2;
    }

    public boolean isWireLessLink(int linkType) {
        return linkType == 2;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int status) {
        this.mStatus = status;
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

    public String getUsbSerialNumber() {
        return this.mUsbSerialNumber;
    }

    public void setUsbSerialNumber(String usbSerialNumber) {
        this.mUsbSerialNumber = usbSerialNumber;
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

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceName(String mDeviceName) {
        this.mDeviceName = mDeviceName;
    }

    public boolean equals(Object o) {
        if (o != null && (o instanceof LinkDevice)) {
            LinkDevice device = (LinkDevice) o;
            if (this.mLinkType != device.getLinkType()) {
                return false;
            }
            if (this.mLinkType == 1) {
                String targetSerialNumber = device.getUsbSerialNumber();
                LogUtil.debug(TAG, "mUsbSerialNumber:" + LogEncrypt.MacAddressEncrypt(this.mUsbSerialNumber) + " targetSerialNumber:" + LogEncrypt.MacAddressEncrypt(targetSerialNumber));
                if (!TextUtils.isEmpty(this.mUsbSerialNumber) && this.mUsbSerialNumber.equals(targetSerialNumber)) {
                    return true;
                }
            }
            if (this.mLinkType == 2) {
                String targetBtMac = device.getBtAddr();
                LogUtil.debug(TAG, "mBtAddr:" + LogEncrypt.MacAddressEncrypt(this.mBtAddr) + " targetBtMac:" + LogEncrypt.MacAddressEncrypt(targetBtMac));
                if (!TextUtils.isEmpty(this.mBtAddr) && this.mBtAddr.equals(targetBtMac)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void copy(LinkDevice mirrorDevice) {
        if (mirrorDevice != null) {
            this.mStatus = mirrorDevice.getStatus();
            this.mLinkType = mirrorDevice.getLinkType();
            this.mBluetoothDevice = mirrorDevice.getBluetoothDevice();
            this.mDeviceId = mirrorDevice.getDeviceId();
            this.mUsbSerialNumber = mirrorDevice.getUsbSerialNumber();
            this.mBtAddr = mirrorDevice.getBtAddr();
            this.mWifiAddr = mirrorDevice.getWifiAddr();
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("LinkDevice{");
        sb.append(" mDeviceName=");
        sb.append(this.mDeviceName);
        sb.append(" mStatus is:");
        sb.append(this.mStatus);
        sb.append(" mLinkType=");
        sb.append(this.mLinkType);
        sb.append(" mDeviceId=");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mDeviceId));
        sb.append(" mUsbSerialNumber=");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mUsbSerialNumber));
        sb.append(" mBtAddr=");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mBtAddr));
        sb.append(" mWifiAddr=");
        sb.append(LogEncrypt.MacAddressEncrypt(this.mWifiAddr));
        sb.append(" mBluetoothDevice=");
        sb.append(getBluetoothDevice());
        sb.append(" }");
        return sb.toString();
    }
}
