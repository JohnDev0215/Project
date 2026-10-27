package com.byd.bt.model;

import android.bluetooth.BluetoothDevice;
import com.byd.mvp.model.BydData;
import m4.d;

/* JADX INFO: loaded from: classes.dex */
public class PagerBtDevice extends BydData {
    private BluetoothDevice device;
    private int errCode;
    private String name;
    private int pairCode;

    public PagerBtDevice() {
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public int getErrCode() {
        return this.errCode;
    }

    public String getName() {
        return this.name;
    }

    public int getPairCode() {
        return this.pairCode;
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public void setErrCode(int i8) {
        this.errCode = i8;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPairCode(int i8) {
        this.pairCode = i8;
    }

    public String toString() {
        return "PagerBtDevice{dn='" + d.f(this.name) + "', pairCode=" + this.pairCode + ", errCode=" + this.errCode + ", device=" + d.c(this.device) + '}';
    }

    public PagerBtDevice(String str) {
        this.name = str;
    }
}
