package org.chromium.device.usb;

import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbInterface;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
final class ChromeUsbConfiguration {
    private static final String TAG = "Usb";
    final UsbConfiguration mConfiguration;

    private ChromeUsbConfiguration(UsbConfiguration usbConfiguration) {
        this.mConfiguration = usbConfiguration;
        Log.v(TAG, "ChromeUsbConfiguration created.");
    }

    private static ChromeUsbConfiguration create(UsbConfiguration usbConfiguration) {
        return new ChromeUsbConfiguration(usbConfiguration);
    }

    private int getConfigurationValue() {
        return this.mConfiguration.getId();
    }

    private boolean isSelfPowered() {
        return this.mConfiguration.isSelfPowered();
    }

    private boolean isRemoteWakeup() {
        return this.mConfiguration.isRemoteWakeup();
    }

    private int getMaxPower() {
        return this.mConfiguration.getMaxPower();
    }

    private UsbInterface[] getInterfaces() {
        int interfaceCount = this.mConfiguration.getInterfaceCount();
        UsbInterface[] usbInterfaceArr = new UsbInterface[interfaceCount];
        for (int i = 0; i < interfaceCount; i++) {
            usbInterfaceArr[i] = this.mConfiguration.getInterface(i);
        }
        return usbInterfaceArr;
    }
}
