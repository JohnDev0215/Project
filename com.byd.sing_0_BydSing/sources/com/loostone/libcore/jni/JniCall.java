package com.loostone.libcore.jni;

import android.hardware.usb.UsbDeviceConnection;

/* JADX INFO: loaded from: classes.dex */
public final class JniCall {

    /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
    public static final JniCall f3801OooO00o = new JniCall();

    static {
        System.loadLibrary("UsbDongle");
    }

    public final native int exec(int i, int i2);

    public final native byte[] getBytes(UsbDeviceConnection usbDeviceConnection, int i, byte b2, byte b3, byte b4);

    public final native int setUsbDeviceParam(String str, String str2, String str3, int i);
}
