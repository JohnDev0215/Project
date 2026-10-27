package com.ecarx.xui.adaptapi.policy;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class UsbDeviceInfo {
    private static final String TAG = "UsbDeviceInfo";
    private String usb1Path = "";
    private String usb2Path = "";

    public String getUsb1Path() {
        return this.usb1Path;
    }

    public String getUsb2Path() {
        return this.usb2Path;
    }

    public UsbDeviceInfo(String str, String str2) {
        initNickName(str2, str);
    }

    private void initNickName(String str, String str2) {
        if (str.equals("1")) {
            this.usb1Path = str2;
            Log.d(TAG, "判定为USB1");
        } else if (str.equals("2")) {
            this.usb2Path = str2;
            Log.d(TAG, "判定为USB2");
        }
    }
}
