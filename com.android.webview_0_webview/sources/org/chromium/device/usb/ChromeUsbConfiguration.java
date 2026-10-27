package org.chromium.device.usb;

import android.hardware.usb.UsbConfiguration;
import android.hardware.usb.UsbInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChromeUsbConfiguration {
    public final UsbConfiguration a;

    public ChromeUsbConfiguration(UsbConfiguration usbConfiguration) {
        this.a = usbConfiguration;
    }

    public static ChromeUsbConfiguration create(UsbConfiguration usbConfiguration) {
        return new ChromeUsbConfiguration(usbConfiguration);
    }

    private int getConfigurationValue() {
        return this.a.getId();
    }

    private UsbInterface[] getInterfaces() {
        int interfaceCount = this.a.getInterfaceCount();
        UsbInterface[] usbInterfaceArr = new UsbInterface[interfaceCount];
        for (int i = 0; i < interfaceCount; i++) {
            usbInterfaceArr[i] = this.a.getInterface(i);
        }
        return usbInterfaceArr;
    }

    private int getMaxPower() {
        return this.a.getMaxPower();
    }

    private boolean isRemoteWakeup() {
        return this.a.isRemoteWakeup();
    }

    private boolean isSelfPowered() {
        return this.a.isSelfPowered();
    }
}
