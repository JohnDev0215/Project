package org.chromium.device.usb;

import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbInterface;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
final class ChromeUsbDevice {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "Usb";
    final UsbDevice mDevice;

    private ChromeUsbDevice(UsbDevice usbDevice) {
        this.mDevice = usbDevice;
        Log.v(TAG, "ChromeUsbDevice created.");
    }

    public UsbDevice getDevice() {
        return this.mDevice;
    }

    private static ChromeUsbDevice create(UsbDevice usbDevice) {
        return new ChromeUsbDevice(usbDevice);
    }

    private int getDeviceId() {
        return this.mDevice.getDeviceId();
    }

    private int getDeviceClass() {
        return this.mDevice.getDeviceClass();
    }

    private int getDeviceSubclass() {
        return this.mDevice.getDeviceSubclass();
    }

    private int getDeviceProtocol() {
        return this.mDevice.getDeviceProtocol();
    }

    private int getVendorId() {
        return this.mDevice.getVendorId();
    }

    private int getProductId() {
        return this.mDevice.getProductId();
    }

    private int getDeviceVersion() {
        String[] strArrSplit = this.mDevice.getVersion().split("\\.");
        return Integer.parseInt(strArrSplit[1]) | (Integer.parseInt(strArrSplit[0]) << 8);
    }

    private String getManufacturerName() {
        return this.mDevice.getManufacturerName();
    }

    private String getProductName() {
        return this.mDevice.getProductName();
    }

    private String getSerialNumber() {
        return this.mDevice.getSerialNumber();
    }

    private UsbConfiguration[] getConfigurations() {
        int configurationCount = this.mDevice.getConfigurationCount();
        UsbConfiguration[] usbConfigurationArr = new UsbConfiguration[configurationCount];
        for (int i = 0; i < configurationCount; i++) {
            usbConfigurationArr[i] = this.mDevice.getConfiguration(i);
        }
        return usbConfigurationArr;
    }

    private UsbInterface[] getInterfaces() {
        int interfaceCount = this.mDevice.getInterfaceCount();
        UsbInterface[] usbInterfaceArr = new UsbInterface[interfaceCount];
        for (int i = 0; i < interfaceCount; i++) {
            usbInterfaceArr[i] = this.mDevice.getInterface(i);
        }
        return usbInterfaceArr;
    }
}
