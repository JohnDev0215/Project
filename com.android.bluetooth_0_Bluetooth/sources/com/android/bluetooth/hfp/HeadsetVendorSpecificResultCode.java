package com.android.bluetooth.hfp;

import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes.dex */
class HeadsetVendorSpecificResultCode extends HeadsetMessageObject {
    String mArg;
    String mCommand;
    BluetoothDevice mDevice;

    HeadsetVendorSpecificResultCode(BluetoothDevice device, String command, String arg) {
        this.mDevice = device;
        this.mCommand = command;
        this.mArg = arg;
    }

    @Override // com.android.bluetooth.hfp.HeadsetMessageObject
    public void buildString(StringBuilder builder) {
        if (builder == null) {
            return;
        }
        builder.append(getClass().getSimpleName());
        builder.append("[device=");
        builder.append(this.mDevice);
        builder.append(", command=");
        builder.append(this.mCommand);
        builder.append(", arg=");
        builder.append(this.mArg);
        builder.append("]");
    }
}
