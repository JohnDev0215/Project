package org.chromium.device.usb;

import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbDevice;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeUsbDevice {
    public final UsbDevice a;

    public ChromeUsbDevice(UsbDevice usbDevice) {
        this.a = usbDevice;
    }

    @CalledByNative
    public static ChromeUsbDevice create(UsbDevice usbDevice) {
        return new ChromeUsbDevice(usbDevice);
    }

    @CalledByNative
    public final UsbConfiguration[] getConfigurations() {
        UsbDevice usbDevice = this.a;
        int configurationCount = usbDevice.getConfigurationCount();
        UsbConfiguration[] usbConfigurationArr = new UsbConfiguration[configurationCount];
        for (int i = 0; i < configurationCount; i++) {
            usbConfigurationArr[i] = usbDevice.getConfiguration(i);
        }
        return usbConfigurationArr;
    }

    @CalledByNative
    public final int getDeviceClass() {
        return this.a.getDeviceClass();
    }

    @CalledByNative
    public final int getDeviceId() {
        return this.a.getDeviceId();
    }

    @CalledByNative
    public final int getDeviceProtocol() {
        return this.a.getDeviceProtocol();
    }

    @CalledByNative
    public final int getDeviceSubclass() {
        return this.a.getDeviceSubclass();
    }

    @CalledByNative
    public final int getDeviceVersion() {
        String[] strArrSplit = this.a.getVersion().split("\\.");
        return Integer.parseInt(strArrSplit[1]) | (Integer.parseInt(strArrSplit[0]) << 8);
    }

    @CalledByNative
    public final String getManufacturerName() {
        return this.a.getManufacturerName();
    }

    @CalledByNative
    public final int getProductId() {
        return this.a.getProductId();
    }

    @CalledByNative
    public final String getProductName() {
        return this.a.getProductName();
    }

    @CalledByNative
    public final String getSerialNumber() {
        return this.a.getSerialNumber();
    }

    @CalledByNative
    public final int getVendorId() {
        return this.a.getVendorId();
    }
}
