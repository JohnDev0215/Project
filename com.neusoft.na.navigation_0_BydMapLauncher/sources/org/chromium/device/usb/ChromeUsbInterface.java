package org.chromium.device.usb;

import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
final class ChromeUsbInterface {
    private static final String TAG = "Usb";
    final UsbInterface mInterface;

    private ChromeUsbInterface(UsbInterface usbInterface) {
        this.mInterface = usbInterface;
        Log.v(TAG, "ChromeUsbInterface created.");
    }

    private static ChromeUsbInterface create(UsbInterface usbInterface) {
        return new ChromeUsbInterface(usbInterface);
    }

    private int getInterfaceNumber() {
        return this.mInterface.getId();
    }

    private int getAlternateSetting() {
        return this.mInterface.getAlternateSetting();
    }

    private int getInterfaceClass() {
        return this.mInterface.getInterfaceClass();
    }

    private int getInterfaceSubclass() {
        return this.mInterface.getInterfaceSubclass();
    }

    private int getInterfaceProtocol() {
        return this.mInterface.getInterfaceProtocol();
    }

    private UsbEndpoint[] getEndpoints() {
        int endpointCount = this.mInterface.getEndpointCount();
        UsbEndpoint[] usbEndpointArr = new UsbEndpoint[endpointCount];
        for (int i = 0; i < endpointCount; i++) {
            usbEndpointArr[i] = this.mInterface.getEndpoint(i);
        }
        return usbEndpointArr;
    }
}
