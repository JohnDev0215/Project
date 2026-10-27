package ts.car.dcm.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import ts.car.service.dcm.data.LogEncrypt;

/* JADX INFO: loaded from: classes2.dex */
public class AbnormalDevice implements Parcelable {
    public static final Parcelable.Creator<AbnormalDevice> CREATOR = new Parcelable.Creator<AbnormalDevice>() { // from class: ts.car.dcm.common.data.AbnormalDevice.1
        @Override // android.os.Parcelable.Creator
        public AbnormalDevice createFromParcel(Parcel in) {
            return new AbnormalDevice(in);
        }

        @Override // android.os.Parcelable.Creator
        public AbnormalDevice[] newArray(int size) {
            return new AbnormalDevice[size];
        }
    };
    private int mAbnormalType;
    private String mBtAddr;
    private int mConnType;
    private String mDeviceId;
    private String mUsbSerialNumber;
    private String mWifiAddr;
    private String mWifiIpAddress;

    protected AbnormalDevice(Parcel in) {
        this.mDeviceId = in.readString();
        this.mConnType = in.readInt();
        this.mAbnormalType = in.readInt();
        this.mUsbSerialNumber = in.readString();
        this.mBtAddr = in.readString();
        this.mWifiAddr = in.readString();
        this.mWifiIpAddress = in.readString();
    }

    public AbnormalDevice(String deviceId) {
        this.mDeviceId = deviceId;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public void setDeviceId(String deviceId) {
        this.mDeviceId = deviceId;
    }

    public int getConnType() {
        return this.mConnType;
    }

    public void setConnType(int connType) {
        this.mConnType = connType;
    }

    public int getAbnormalType() {
        return this.mAbnormalType;
    }

    public void setAbnormalType(int abnormalType) {
        this.mAbnormalType = abnormalType;
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

    public String getWifiIpAddress() {
        return this.mWifiIpAddress;
    }

    public void setWifiIpAddress(String wifiIpAddress) {
        this.mWifiIpAddress = wifiIpAddress;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getDeviceId());
        dest.writeInt(getConnType());
        dest.writeInt(getAbnormalType());
        dest.writeString(getUsbSerialNumber());
        dest.writeString(getBtAddr());
        dest.writeString(getWifiAddr());
        dest.writeString(getWifiIpAddress());
    }

    public String toString() {
        return "AbnormalDevice{mDeviceId='" + LogEncrypt.IpAddressEncrypt(this.mDeviceId) + "', mConnType=" + this.mConnType + ", mAbnormalType=" + this.mAbnormalType + ", mUsbSerialNumber='" + LogEncrypt.IpAddressEncrypt(this.mUsbSerialNumber) + "', mBtAddr='" + LogEncrypt.MacAddressEncrypt(this.mBtAddr) + "', mWifiAddr='" + LogEncrypt.MacAddressEncrypt(this.mWifiAddr) + "', mWifiIpAddress='" + LogEncrypt.IpAddressEncrypt(this.mWifiIpAddress) + "'}";
    }
}
