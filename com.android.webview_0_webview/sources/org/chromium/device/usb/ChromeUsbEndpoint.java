package org.chromium.device.usb;

import android.hardware.usb.UsbEndpoint;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChromeUsbEndpoint {
    public final UsbEndpoint a;

    public ChromeUsbEndpoint(UsbEndpoint usbEndpoint) {
        this.a = usbEndpoint;
    }

    public static ChromeUsbEndpoint create(UsbEndpoint usbEndpoint) {
        return new ChromeUsbEndpoint(usbEndpoint);
    }

    private int getAddress() {
        return this.a.getAddress();
    }

    private int getAttributes() {
        return this.a.getAttributes();
    }

    private int getInterval() {
        return this.a.getInterval();
    }

    private int getMaxPacketSize() {
        return this.a.getMaxPacketSize();
    }
}
