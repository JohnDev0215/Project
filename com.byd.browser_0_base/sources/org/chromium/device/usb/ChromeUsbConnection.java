package org.chromium.device.usb;

import android.hardware.usb.UsbDeviceConnection;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class ChromeUsbConnection {
    public final UsbDeviceConnection a;

    public ChromeUsbConnection(UsbDeviceConnection usbDeviceConnection) {
        this.a = usbDeviceConnection;
    }

    @CalledByNative
    public static ChromeUsbConnection create(UsbDeviceConnection usbDeviceConnection) {
        return new ChromeUsbConnection(usbDeviceConnection);
    }

    @CalledByNative
    public final void close() {
        this.a.close();
    }

    @CalledByNative
    public final int getFileDescriptor() {
        return this.a.getFileDescriptor();
    }
}
