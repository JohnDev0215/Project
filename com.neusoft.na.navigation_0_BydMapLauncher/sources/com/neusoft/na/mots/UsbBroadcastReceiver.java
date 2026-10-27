package com.neusoft.na.mots;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class UsbBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "XJUsbBroadcastReceiver";
    private static UsbStatusCallBack mCallback = null;
    private static String usbPath = "";
    private static boolean usbStatus = false;

    public static void setUsbStatusCallBack(UsbStatusCallBack usbStatusCallBack) {
        Log.i(TAG, "setUsbStatusCallBack");
        mCallback = usbStatusCallBack;
        if (!usbStatus || usbStatusCallBack == null) {
            return;
        }
        usbStatusCallBack.usbMount(usbPath);
    }

    public void unSetUsbStatusCallBack() {
        Log.i(TAG, "unSetUsbStatusCallBack");
        mCallback = null;
    }

    public static void usbMount(String str) {
        Log.i(TAG, "usbMount:" + str);
        usbPath = str;
        usbStatus = true;
        UsbStatusCallBack usbStatusCallBack = mCallback;
        if (usbStatusCallBack != null) {
            usbStatusCallBack.usbMount(str);
        }
    }

    public static void usbUnMount() {
        Log.i(TAG, "usbUnMount:");
        usbPath = "";
        usbStatus = false;
        UsbStatusCallBack usbStatusCallBack = mCallback;
        if (usbStatusCallBack != null) {
            usbStatusCallBack.usbUnMount();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        Log.i(TAG, "onReceive: " + intent.getAction());
        if (intent.getAction().equals("android.intent.action.MEDIA_MOUNTED")) {
            usbMount(intent.getData().getPath());
        } else if (intent.getAction().equals("android.intent.action.MEDIA_EJECT")) {
            usbUnMount();
        }
    }
}
