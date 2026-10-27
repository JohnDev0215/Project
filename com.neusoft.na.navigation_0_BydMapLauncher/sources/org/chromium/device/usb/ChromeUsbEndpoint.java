package org.chromium.device.usb;

import android.hardware.usb.UsbEndpoint;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
final class ChromeUsbEndpoint {
    private static final String TAG = "Usb";
    final UsbEndpoint mEndpoint;

    private ChromeUsbEndpoint(UsbEndpoint usbEndpoint) {
        this.mEndpoint = usbEndpoint;
        Log.v(TAG, "ChromeUsbEndpoint created.");
    }

    private static ChromeUsbEndpoint create(UsbEndpoint usbEndpoint) {
        return new ChromeUsbEndpoint(usbEndpoint);
    }

    private int getAddress() {
        return this.mEndpoint.getAddress();
    }

    private int getMaxPacketSize() {
        return this.mEndpoint.getMaxPacketSize();
    }

    private int getAttributes() {
        return this.mEndpoint.getAttributes();
    }

    private int getInterval() {
        return this.mEndpoint.getInterval();
    }
}
