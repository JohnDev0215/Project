package com.loostone.libtuning.inf.extern;

import android.hardware.usb.UsbDevice;

/* JADX INFO: loaded from: classes.dex */
public interface IDongleListener {
    void onDongleAttach();

    void onDongleDetach();

    void onMicConnectState(UsbDevice usbDevice, boolean z);
}
