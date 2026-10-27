package org.chromium.device.usb;

import android.hardware.usb.UsbEndpoint;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeUsbEndpoint {
    public final UsbEndpoint a;

    public ChromeUsbEndpoint(UsbEndpoint usbEndpoint) {
        this.a = usbEndpoint;
    }

    @CalledByNative
    public static ChromeUsbEndpoint create(UsbEndpoint usbEndpoint) {
        return new ChromeUsbEndpoint(usbEndpoint);
    }

    @CalledByNative
    public final int getAddress() {
        return this.a.getAddress();
    }

    @CalledByNative
    public final int getAttributes() {
        return this.a.getAttributes();
    }

    @CalledByNative
    public final int getInterval() {
        return this.a.getInterval();
    }

    @CalledByNative
    public final int getMaxPacketSize() {
        return this.a.getMaxPacketSize();
    }
}
