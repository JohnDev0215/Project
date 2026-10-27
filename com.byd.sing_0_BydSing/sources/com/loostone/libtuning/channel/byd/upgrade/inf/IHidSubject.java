package com.loostone.libtuning.channel.byd.upgrade.inf;

import android.hardware.usb.UsbDevice;

/* JADX INFO: loaded from: classes.dex */
public interface IHidSubject {
    void endHid();

    boolean startHid(UsbDevice usbDevice);
}
