package org.chromium.device.usb;

import android.hardware.usb.UsbDeviceConnection;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
class ChromeUsbConnection {
    private static final String TAG = "Usb";
    final UsbDeviceConnection mConnection;

    private ChromeUsbConnection(UsbDeviceConnection usbDeviceConnection) {
        this.mConnection = usbDeviceConnection;
        Log.v(TAG, "ChromeUsbConnection created.");
    }

    private static ChromeUsbConnection create(UsbDeviceConnection usbDeviceConnection) {
        return new ChromeUsbConnection(usbDeviceConnection);
    }

    private int getFileDescriptor() {
        return this.mConnection.getFileDescriptor();
    }

    private void close() {
        this.mConnection.close();
    }
}
