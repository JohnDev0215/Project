package org.chromium.device.usb;

import android.hardware.usb.UsbDeviceConnection;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ChromeUsbConnection {
    public final UsbDeviceConnection a;

    public ChromeUsbConnection(UsbDeviceConnection usbDeviceConnection) {
        this.a = usbDeviceConnection;
    }

    private void close() {
        this.a.close();
    }

    public static ChromeUsbConnection create(UsbDeviceConnection usbDeviceConnection) {
        return new ChromeUsbConnection(usbDeviceConnection);
    }

    private int getFileDescriptor() {
        return this.a.getFileDescriptor();
    }
}
