package org.chromium.device.usb;

import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbInterface;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ChromeUsbConfiguration {
    public final UsbConfiguration a;

    public ChromeUsbConfiguration(UsbConfiguration usbConfiguration) {
        this.a = usbConfiguration;
    }

    @CalledByNative
    public static ChromeUsbConfiguration create(UsbConfiguration usbConfiguration) {
        return new ChromeUsbConfiguration(usbConfiguration);
    }

    @CalledByNative
    public final int getConfigurationValue() {
        return this.a.getId();
    }

    @CalledByNative
    public final UsbInterface[] getInterfaces() {
        UsbConfiguration usbConfiguration = this.a;
        int interfaceCount = usbConfiguration.getInterfaceCount();
        UsbInterface[] usbInterfaceArr = new UsbInterface[interfaceCount];
        for (int i = 0; i < interfaceCount; i++) {
            usbInterfaceArr[i] = usbConfiguration.getInterface(i);
        }
        return usbInterfaceArr;
    }

    @CalledByNative
    public final int getMaxPower() {
        return this.a.getMaxPower();
    }

    @CalledByNative
    public final boolean isRemoteWakeup() {
        return this.a.isRemoteWakeup();
    }

    @CalledByNative
    public final boolean isSelfPowered() {
        return this.a.isSelfPowered();
    }
}
