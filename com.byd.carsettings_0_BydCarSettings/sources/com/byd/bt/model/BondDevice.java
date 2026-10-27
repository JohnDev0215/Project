package com.byd.bt.model;

import b.b.m.g.f;

/* JADX INFO: loaded from: classes.dex */
public class BondDevice extends BTDevice {
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    private boolean aaConnected;
    private boolean checked;
    private int connectState;
    private boolean cpConnected;
    private boolean hfpConnected;
    private BTPowerLevel level;
    private PhoneType phoneType;

    public BondDevice() {
        this.level = BTPowerLevel.UNDEFINED;
        this.connectState = 0;
        this.phoneType = PhoneType.NORMAL;
        this.cpConnected = false;
        this.aaConnected = false;
    }

    public void copy(BondDevice bondDevice) {
        this.device = bondDevice.getDevice();
        this.name = bondDevice.getName();
        this.mac = bondDevice.getMac();
        this.type = bondDevice.getType();
        this.connected = bondDevice.isConnected();
        this.rssi = bondDevice.getRssi();
        this.preConnectedTime = bondDevice.getPreConnectedTime();
        this.preDisconnectedTime = bondDevice.getPreDisconnectedTime();
        this.level = bondDevice.getLevel();
        if (this.connected) {
            this.connectState = 2;
            this.hfpConnected = bondDevice.isHfpConnected();
        }
        this.phoneType = bondDevice.getPhoneType();
    }

    public int getConnectState() {
        return this.connectState;
    }

    public BTPowerLevel getLevel() {
        return this.level;
    }

    public PhoneType getPhoneType() {
        return this.phoneType;
    }

    public boolean isAaConnected() {
        return this.aaConnected;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public boolean isCpConnected() {
        return this.cpConnected;
    }

    public boolean isHfpConnected() {
        return this.hfpConnected;
    }

    public void setAaConnected(boolean z) {
        this.aaConnected = z;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public void setConnectState(int i) {
        this.connectState = i;
    }

    @Override // com.byd.bt.model.BTDevice
    public void setConnected(boolean z) {
        super.setConnected(z);
        if (z) {
            this.connectState = 2;
        }
    }

    public void setCpConnected(boolean z) {
        this.cpConnected = z;
    }

    public void setHfpConnected(boolean z) {
        this.hfpConnected = z;
    }

    public void setLevel(BTPowerLevel bTPowerLevel) {
        this.level = bTPowerLevel;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    @Override // com.byd.bt.model.BTDevice
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BondDevice{dn='");
        sb.append(f.g(this.name));
        sb.append('\'');
        sb.append(", addr='");
        sb.append(getEncryptMac());
        sb.append('\'');
        sb.append(", type=");
        sb.append(this.type);
        sb.append(", phoneType=");
        sb.append(this.phoneType);
        sb.append(", cpConnected=");
        sb.append(this.cpConnected);
        sb.append(", aaConnected=");
        sb.append(this.aaConnected);
        sb.append(this.device == null ? ", device=null" : ", device is not null");
        sb.append(", connected=");
        sb.append(this.connected);
        sb.append(", hfpConnected=");
        sb.append(this.hfpConnected);
        sb.append(", rssi=");
        sb.append(this.rssi);
        sb.append(", preConnectedTime=");
        sb.append(this.preConnectedTime);
        sb.append(", preDisconnectedTime=");
        sb.append(this.preDisconnectedTime);
        sb.append(", connectedTimeCount=");
        sb.append(this.connectedTimeCount / 1000);
        sb.append(", checked=");
        sb.append(this.checked);
        sb.append(", level=");
        sb.append(this.level);
        sb.append(", connectState=");
        sb.append(this.connectState);
        sb.append('}');
        return sb.toString();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v2, types: [boolean] */
    @Override // com.byd.bt.model.BTDevice, java.lang.Comparable
    public int compareTo(BTDevice bTDevice) {
        BondDevice bondDevice = (BondDevice) bTDevice;
        ?? IsHfpConnected = isHfpConnected();
        ?? IsHfpConnected2 = bondDevice.isHfpConnected();
        if (IsHfpConnected > IsHfpConnected2) {
            return -1;
        }
        if (IsHfpConnected < IsHfpConnected2) {
            return 1;
        }
        ?? r0 = this.connected;
        ?? r1 = bondDevice.connected;
        if (r0 > r1) {
            return -1;
        }
        if (r0 < r1) {
            return 1;
        }
        ?? r02 = this.aaConnected;
        ?? r12 = bondDevice.aaConnected;
        if (r02 > r12) {
            return -1;
        }
        if (r02 < r12) {
            return 1;
        }
        return super.compareTo((BTDevice) bondDevice);
    }

    public BondDevice(BTDevice bTDevice) {
        this.level = BTPowerLevel.UNDEFINED;
        this.connectState = 0;
        this.phoneType = PhoneType.NORMAL;
        this.cpConnected = false;
        this.aaConnected = false;
        this.device = bTDevice.getDevice();
        this.name = bTDevice.getName();
        this.mac = bTDevice.getMac();
        this.type = bTDevice.getType();
        this.connected = bTDevice.isConnected();
        this.rssi = bTDevice.getRssi();
        this.preConnectedTime = bTDevice.getPreConnectedTime();
        this.preDisconnectedTime = bTDevice.getPreDisconnectedTime();
        if (this.connected) {
            this.connectState = 2;
        }
    }
}
