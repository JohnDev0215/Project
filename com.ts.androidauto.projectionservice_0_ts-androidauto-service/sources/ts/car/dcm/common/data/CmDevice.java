package ts.car.dcm.common.data;

import android.hardware.usb.UsbDevice;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;
import ts.car.dcm.common.util.LogEncrypt;

/* JADX INFO: loaded from: classes2.dex */
public class CmDevice implements Parcelable {
    public static final Parcelable.Creator<CmDevice> CREATOR = new Parcelable.Creator<CmDevice>() { // from class: ts.car.dcm.common.data.CmDevice.1
        @Override // android.os.Parcelable.Creator
        public CmDevice createFromParcel(Parcel source) {
            CmDevice device = new CmDevice(source);
            return device;
        }

        @Override // android.os.Parcelable.Creator
        public CmDevice[] newArray(int size) {
            return new CmDevice[size];
        }
    };
    private static final String TAG = "CmDevice";
    private int mAvailableCapability;
    private String mBtAddr;
    private String mDevId;
    private int mDeviceType;
    private String mIapNode;
    private boolean mIsPaired;
    private UsbDevice mUsbDevice;
    private String mUsbHidNode;
    private int mUsbMode;
    private String mUsbNcmNode;
    private String mUsbSerialNumber;
    private String mWifiAddr;
    private String mWifiIpAddress;

    private CmDevice() {
        this.mIsPaired = false;
    }

    public CmDevice(String deviceUuid) {
        this.mIsPaired = false;
        this.mDevId = deviceUuid;
    }

    public CmDevice(Parcel in) {
        this.mIsPaired = false;
        this.mDevId = in.readString();
        this.mAvailableCapability = in.readInt();
        this.mUsbSerialNumber = in.readString();
        this.mDeviceType = in.readInt();
        this.mUsbMode = in.readInt();
        this.mBtAddr = in.readString();
        this.mWifiAddr = in.readString();
        this.mWifiIpAddress = in.readString();
        this.mUsbHidNode = in.readString();
        this.mIapNode = in.readString();
        this.mUsbNcmNode = in.readString();
        this.mIsPaired = in.readByte() != 0;
        this.mUsbDevice = (UsbDevice) in.readParcelable(UsbDevice.class.getClassLoader());
    }

    public int getUsbMode() {
        return this.mUsbMode;
    }

    public void setUsbMode(int usbMode) {
        this.mUsbMode = usbMode;
    }

    public String getDeviceId() {
        return this.mDevId;
    }

    public void setDeviceId(String devId) {
        this.mDevId = devId;
    }

    public String getUsbSerialNumber() {
        return this.mUsbSerialNumber;
    }

    public void setUsbSerialNumber(String usbSerialNumber) {
        this.mUsbSerialNumber = usbSerialNumber;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int deviceType) {
        this.mDeviceType = deviceType;
    }

    public UsbDevice getUsbDevice() {
        return this.mUsbDevice;
    }

    public void setUsbDevice(UsbDevice usbDevice) {
        this.mUsbDevice = usbDevice;
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

    public int getAvailableCapability() {
        return this.mAvailableCapability;
    }

    public void setAvailableCapability(int availableCapability) {
        this.mAvailableCapability = availableCapability;
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CmDevice that = (CmDevice) o;
        return Objects.equals(this.mDevId, that.mDevId);
    }

    public int hashCode() {
        return this.mDevId.hashCode();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        synchronized (dest) {
            dest.writeString(this.mDevId);
            dest.writeInt(this.mAvailableCapability);
            dest.writeString(this.mUsbSerialNumber);
            dest.writeInt(this.mDeviceType);
            dest.writeInt(this.mUsbMode);
            dest.writeString(this.mBtAddr);
            dest.writeString(this.mWifiAddr);
            dest.writeString(this.mWifiIpAddress);
            dest.writeString(this.mUsbHidNode);
            dest.writeString(this.mIapNode);
            dest.writeString(this.mUsbNcmNode);
            dest.writeByte((byte) (this.mIsPaired ? 1 : 0));
            dest.writeParcelable(this.mUsbDevice, flags);
        }
    }

    public String toString() {
        return "CmDevice{mDeviceUuid='" + LogEncrypt.MacAddressEncrypt(this.mDevId) + "', mUsbSerialNumber='" + LogEncrypt.MacAddressEncrypt(this.mUsbSerialNumber) + "', mDeviceType='" + this.mDeviceType + "', mBtAddr='" + LogEncrypt.MacAddressEncrypt(this.mBtAddr) + "', mWifiAddr='" + LogEncrypt.MacAddressEncrypt(this.mWifiAddr) + "', mWifiIpAddress='" + LogEncrypt.IpAddressEncrypt(this.mWifiIpAddress) + "', mAvailableCapability=" + this.mAvailableCapability + "', usbMode=" + this.mUsbMode + "', mUsbHidNode='" + this.mUsbHidNode + "', mIapNode='" + this.mIapNode + "', mUsbNcmNode='" + this.mUsbNcmNode + "', mIsPaired='" + this.mIsPaired + "'}";
    }
}
