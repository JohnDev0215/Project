package com.android.bluetooth.gatt;

/* JADX INFO: loaded from: classes.dex */
class CallbackInfo {
    public String address;
    public int handle;
    public int status;

    CallbackInfo(String address, int status, int handle) {
        this.address = address;
        this.status = status;
        this.handle = handle;
    }

    CallbackInfo(String address, int status) {
        this.address = address;
        this.status = status;
    }
}
