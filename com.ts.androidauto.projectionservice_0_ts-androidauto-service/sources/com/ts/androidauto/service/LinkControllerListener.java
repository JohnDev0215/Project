package com.ts.androidauto.service;

import android.hardware.usb.UsbDevice;
import com.ts.androidauto.aap.common.ConnectionInfo;
import com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice;

/* JADX INFO: loaded from: classes2.dex */
public interface LinkControllerListener {
    void connectProjection(ConnectionInfo connectionInfo);

    void disconnectProjection(String str, int i, int i2);

    void onAaEnableUsbAoaFinished(boolean z, String str, UsbDevice usbDevice);

    void onAndroidDetachedOverUsb(AndroidAutoDevice androidAutoDevice);

    void onDeviceStatusChanged(AndroidAutoDevice androidAutoDevice);

    void onDevicesMerged(AndroidAutoDevice androidAutoDevice);

    void onLastAvailableDeviceChanged(String str, int i);

    void onRequestProjection(AndroidAutoDevice androidAutoDevice, int i);

    void setOtherProjectionActive(boolean z);
}
