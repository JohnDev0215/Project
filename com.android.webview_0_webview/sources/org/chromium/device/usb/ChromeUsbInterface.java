package org.chromium.device.usb;

import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChromeUsbInterface {
    public final UsbInterface a;

    public ChromeUsbInterface(UsbInterface usbInterface) {
        this.a = usbInterface;
    }

    public static ChromeUsbInterface create(UsbInterface usbInterface) {
        return new ChromeUsbInterface(usbInterface);
    }

    private int getAlternateSetting() {
        return this.a.getAlternateSetting();
    }

    private UsbEndpoint[] getEndpoints() {
        int endpointCount = this.a.getEndpointCount();
        UsbEndpoint[] usbEndpointArr = new UsbEndpoint[endpointCount];
        for (int i = 0; i < endpointCount; i++) {
            usbEndpointArr[i] = this.a.getEndpoint(i);
        }
        return usbEndpointArr;
    }

    private int getInterfaceClass() {
        return this.a.getInterfaceClass();
    }

    private int getInterfaceNumber() {
        return this.a.getId();
    }

    private int getInterfaceProtocol() {
        return this.a.getInterfaceProtocol();
    }

    private int getInterfaceSubclass() {
        return this.a.getInterfaceSubclass();
    }
}
