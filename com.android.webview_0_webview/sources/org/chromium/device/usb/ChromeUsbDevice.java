package org.chromium.device.usb;

import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChromeUsbDevice {
    public final UsbDevice a;

    public ChromeUsbDevice(UsbDevice usbDevice) {
        this.a = usbDevice;
    }

    public static ChromeUsbDevice create(UsbDevice usbDevice) {
        return new ChromeUsbDevice(usbDevice);
    }

    private UsbConfiguration[] getConfigurations() {
        int configurationCount = this.a.getConfigurationCount();
        UsbConfiguration[] usbConfigurationArr = new UsbConfiguration[configurationCount];
        for (int i = 0; i < configurationCount; i++) {
            usbConfigurationArr[i] = this.a.getConfiguration(i);
        }
        return usbConfigurationArr;
    }

    private int getDeviceClass() {
        return this.a.getDeviceClass();
    }

    private int getDeviceId() {
        return this.a.getDeviceId();
    }

    private int getDeviceProtocol() {
        return this.a.getDeviceProtocol();
    }

    private int getDeviceSubclass() {
        return this.a.getDeviceSubclass();
    }

    private int getDeviceVersion() {
        String[] strArrSplit = this.a.getVersion().split("\\.");
        return Integer.parseInt(strArrSplit[1]) | (Integer.parseInt(strArrSplit[0]) << 8);
    }

    private UsbInterface[] getInterfaces() {
        int interfaceCount = this.a.getInterfaceCount();
        UsbInterface[] usbInterfaceArr = new UsbInterface[interfaceCount];
        for (int i = 0; i < interfaceCount; i++) {
            usbInterfaceArr[i] = this.a.getInterface(i);
        }
        return usbInterfaceArr;
    }

    private String getManufacturerName() {
        return this.a.getManufacturerName();
    }

    private int getProductId() {
        return this.a.getProductId();
    }

    private String getProductName() {
        return this.a.getProductName();
    }

    private String getSerialNumber() {
        return this.a.getSerialNumber();
    }

    private int getVendorId() {
        return this.a.getVendorId();
    }
}
