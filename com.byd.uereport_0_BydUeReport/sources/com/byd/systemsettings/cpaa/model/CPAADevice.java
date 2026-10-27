package com.byd.systemsettings.cpaa.model;

import java.io.Serializable;
import m4.d;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes2.dex */
public class CPAADevice implements Serializable, Comparable<CPAADevice> {
    private String btMac;
    private String btName;
    private boolean checked;
    public int connectType;
    private boolean connected;
    private ChannelType connectedChannel;
    public int deviceStatus;
    private boolean hasBtSignal;
    private boolean hasPhoneSignal;
    private boolean hasUsbSignal;
    private boolean hasWiFiSignal;
    private long lastConnectedTime;
    public int mWirelessAvailableState;
    private String name;
    private CarPlayType type;
    private String usbSN;
    private String wifiMac;
    private String wifiName;

    public CPAADevice(String str) {
        this.type = CarPlayType.CAR_PLAY;
        this.connectedChannel = ChannelType.BT_WIFI;
        this.lastConnectedTime = 0L;
        this.hasBtSignal = false;
        this.hasWiFiSignal = false;
        this.hasUsbSignal = false;
        this.checked = false;
        this.hasPhoneSignal = false;
        this.name = str;
    }

    public String getBtMac() {
        return this.btMac;
    }

    public String getBtName() {
        return this.btName;
    }

    public ChannelType getConnectedChannel() {
        return this.connectedChannel;
    }

    public long getLastConnectedTime() {
        return this.lastConnectedTime;
    }

    public String getName() {
        return this.name;
    }

    public CarPlayType getType() {
        return this.type;
    }

    public String getUsbSN() {
        return this.usbSN;
    }

    public String getWifiMac() {
        return this.wifiMac;
    }

    public String getWifiName() {
        return this.wifiName;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public boolean isHasBtSignal() {
        return this.hasBtSignal;
    }

    public boolean isHasPhoneSignal() {
        return this.hasPhoneSignal;
    }

    public boolean isHasUsbSignal() {
        return this.hasUsbSignal;
    }

    public boolean isHasWiFiSignal() {
        return this.hasWiFiSignal;
    }

    public void setBtMac(String str) {
        this.btMac = str;
    }

    public void setBtName(String str) {
        this.btName = str;
    }

    public void setChecked(boolean z7) {
        this.checked = z7;
    }

    public void setConnected(boolean z7) {
        this.connected = z7;
    }

    public void setConnectedChannel(ChannelType channelType) {
        this.connectedChannel = channelType;
    }

    public void setHasBtSignal(boolean z7) {
        this.hasBtSignal = z7;
    }

    public void setHasPhoneSignal(boolean z7) {
        this.hasPhoneSignal = z7;
    }

    public void setHasUsbSignal(boolean z7) {
        this.hasUsbSignal = z7;
    }

    public void setHasWiFiSignal(boolean z7) {
        this.hasWiFiSignal = z7;
    }

    public void setLastConnectedTime(long j8) {
        this.lastConnectedTime = j8;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setType(CarPlayType carPlayType) {
        this.type = carPlayType;
    }

    public void setUsbSN(String str) {
        this.usbSN = str;
    }

    public void setWifiMac(String str) {
        this.wifiMac = str;
    }

    public void setWifiName(String str) {
        this.wifiName = str;
    }

    public String toString() {
        return "CarPlayDevice{dn='" + d.f(this.name) + "', type=" + this.type + ", connected=" + this.connected + ", connectedChannel=" + this.connectedChannel + ", btName='" + d.f(this.btName) + "', wifiName='" + d.f(this.wifiName) + "', lastConnectedTime=" + this.lastConnectedTime + ", hasBtSignal=" + this.hasBtSignal + ", hasWiFiSignal=" + this.hasWiFiSignal + ", hasUsbSignal=" + this.hasUsbSignal + ", hasPhoneSignal=" + this.hasPhoneSignal + ", checked=" + this.checked + ",deviceStatus=" + this.deviceStatus + '}';
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    @Override // java.lang.Comparable
    public int compareTo(CPAADevice cPAADevice) {
        ?? r02 = this.connected;
        ?? r12 = cPAADevice.connected;
        if (r02 > r12) {
            return -1;
        }
        if (r02 < r12) {
            return 1;
        }
        long j8 = this.lastConnectedTime;
        long j9 = cPAADevice.lastConnectedTime;
        if (j8 > j9) {
            return -1;
        }
        return j8 < j9 ? 1 : 0;
    }

    public CPAADevice(CarPlayDevice carPlayDevice) {
        CarPlayType carPlayType = CarPlayType.CAR_PLAY;
        this.type = carPlayType;
        this.connectedChannel = ChannelType.BT_WIFI;
        this.lastConnectedTime = 0L;
        this.hasBtSignal = false;
        this.hasWiFiSignal = false;
        this.hasUsbSignal = false;
        this.checked = false;
        this.hasPhoneSignal = false;
        this.name = carPlayDevice.getDeviceName();
        this.type = carPlayType;
        this.usbSN = carPlayDevice.getUsbSerialNumber();
        this.btMac = carPlayDevice.getBtAddr();
        this.wifiMac = carPlayDevice.getWifiAddr();
        this.connectType = carPlayDevice.isUsbDevice() ? 1 : 2;
        this.deviceStatus = carPlayDevice.getDeviceStatus();
        this.connected = carPlayDevice.getDeviceStatus() == 4;
        this.mWirelessAvailableState = carPlayDevice.getWirelessAvailableState();
    }

    public CPAADevice(AndroidAutoDevice androidAutoDevice) {
        this.type = CarPlayType.CAR_PLAY;
        this.connectedChannel = ChannelType.BT_WIFI;
        this.lastConnectedTime = 0L;
        this.hasBtSignal = false;
        this.hasWiFiSignal = false;
        this.hasUsbSignal = false;
        this.checked = false;
        this.hasPhoneSignal = false;
        this.name = androidAutoDevice.getDeviceName();
        this.type = CarPlayType.ANDROID_AUTO;
        this.usbSN = androidAutoDevice.getUsbSerialNumber();
        this.btMac = androidAutoDevice.getBtAddr();
        this.wifiMac = androidAutoDevice.getWifiAddr();
        this.connectType = androidAutoDevice.isUsbDevice() ? 1 : 2;
        this.deviceStatus = androidAutoDevice.getDeviceStatus();
        this.connected = androidAutoDevice.getDeviceStatus() == 4;
    }
}
