package ts.car.service.dcm.data;

import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Device {
    private static final String TAG = "Device";
    private String mBtAddr;
    private String mDevId;
    private String mUsbSerialNumber;
    private String mWifiAddr;
    private String mWifiIpAddress;

    public Device() {
    }

    public Device(String deviceId) {
        this.mDevId = deviceId;
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device that = (Device) o;
        return Objects.equals(this.mDevId, that.mDevId);
    }

    public int hashCode() {
        return this.mDevId.hashCode();
    }

    public String toString() {
        return "CmDevice{mDeviceUuid='" + LogEncrypt.IpAddressEncrypt(this.mDevId) + "}";
    }
}
