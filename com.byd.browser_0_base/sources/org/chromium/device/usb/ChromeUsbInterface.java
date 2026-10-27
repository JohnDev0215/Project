package org.chromium.device.usb;

import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeUsbInterface {
    public final UsbInterface a;

    public ChromeUsbInterface(UsbInterface usbInterface) {
        this.a = usbInterface;
    }

    @CalledByNative
    public static ChromeUsbInterface create(UsbInterface usbInterface) {
        return new ChromeUsbInterface(usbInterface);
    }

    @CalledByNative
    public final int getAlternateSetting() {
        return this.a.getAlternateSetting();
    }

    @CalledByNative
    public final UsbEndpoint[] getEndpoints() {
        UsbInterface usbInterface = this.a;
        int endpointCount = usbInterface.getEndpointCount();
        UsbEndpoint[] usbEndpointArr = new UsbEndpoint[endpointCount];
        for (int i = 0; i < endpointCount; i++) {
            usbEndpointArr[i] = usbInterface.getEndpoint(i);
        }
        return usbEndpointArr;
    }

    @CalledByNative
    public final int getInterfaceClass() {
        return this.a.getInterfaceClass();
    }

    @CalledByNative
    public final int getInterfaceNumber() {
        return this.a.getId();
    }

    @CalledByNative
    public final int getInterfaceProtocol() {
        return this.a.getInterfaceProtocol();
    }

    @CalledByNative
    public final int getInterfaceSubclass() {
        return this.a.getInterfaceSubclass();
    }
}
