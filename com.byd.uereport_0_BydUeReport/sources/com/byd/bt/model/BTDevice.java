package com.byd.bt.model;

import android.bluetooth.BluetoothDevice;
import e3.a;
import java.io.Serializable;
import m4.d;

/* JADX INFO: loaded from: classes.dex */
public class BTDevice implements Serializable, Comparable<BTDevice> {
    public static final int BOND_SUCCESS = 0;
    public static final long CONNECTED_TIME_STANDARD = 2000;
    public static String TAG = null;
    public static final int UNBOND_REASON_AUTH_CANCELED = 3;
    public static final int UNBOND_REASON_AUTH_FAILED = 1;
    public static final int UNBOND_REASON_AUTH_REJECTED = 2;
    public static final int UNBOND_REASON_AUTH_TIMEOUT = 6;
    public static final int UNBOND_REASON_DISCOVERY_IN_PROGRESS = 5;
    public static final int UNBOND_REASON_REMOTE_AUTH_CANCELED = 8;
    public static final int UNBOND_REASON_REMOTE_DEVICE_DOWN = 4;
    public static final int UNBOND_REASON_REPEATED_ATTEMPTS = 7;
    public boolean connected;
    public BluetoothDevice device;
    public String mac;
    public String name;
    public int rssi;
    public BTMajorType type;
    public long preConnectedTime = 0;
    public long preDisconnectedTime = 0;
    public long connectedTimeCount = 0;

    public BTDevice() {
        TAG = getClass().getSimpleName();
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public String getEncryptMac() {
        return d.d(this.mac);
    }

    public String getMac() {
        return this.mac;
    }

    public String getName() {
        return this.name;
    }

    public long getPreConnectedTime() {
        return this.preConnectedTime;
    }

    public long getPreDisconnectedTime() {
        return this.preDisconnectedTime;
    }

    public int getRssi() {
        return this.rssi;
    }

    public BTMajorType getType() {
        return this.type;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void release() {
        this.device = null;
    }

    public void setConnected(boolean z7) {
        this.connected = z7;
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPreConnectedTime(long j8) {
        this.preConnectedTime = j8;
    }

    public void setPreDisconnectedTime(long j8) {
        this.preDisconnectedTime = j8;
        this.connectedTimeCount = j8 - this.preConnectedTime;
    }

    public void setRssi(int i8) {
        this.rssi = i8;
    }

    public void setType(BTMajorType bTMajorType) {
        this.type = bTMajorType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BTDevice{dn='");
        sb.append(d.f(this.name));
        sb.append('\'');
        sb.append(", addr='");
        sb.append(getEncryptMac());
        sb.append('\'');
        sb.append(", type=");
        sb.append(this.type);
        sb.append(", device=");
        sb.append(this.device == null ? "null" : "not null");
        sb.append(", connected=");
        sb.append(this.connected);
        sb.append(", rssi=");
        sb.append(this.rssi);
        sb.append(", preConnectedTime=");
        sb.append(this.preConnectedTime);
        sb.append(", connectedTimeCount=");
        sb.append(this.connectedTimeCount / 1000);
        sb.append(", preDisconnectedTime=");
        sb.append(this.preDisconnectedTime);
        sb.append('}');
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(BTDevice bTDevice) {
        long j8 = this.preConnectedTime;
        long j9 = bTDevice.preConnectedTime;
        if (j8 > j9) {
            return -1;
        }
        return j8 < j9 ? 1 : 0;
    }

    public BTDevice(BluetoothDevice bluetoothDevice) {
        TAG = getClass().getSimpleName();
        this.device = bluetoothDevice;
        this.name = bluetoothDevice.getName();
        this.mac = bluetoothDevice.getAddress();
        this.type = a.a(bluetoothDevice);
    }
}
