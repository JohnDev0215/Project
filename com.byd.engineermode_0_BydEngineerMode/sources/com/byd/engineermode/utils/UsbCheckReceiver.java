package com.byd.engineermode.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class UsbCheckReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f2498a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2499b = UsbCheckReceiver.class.getSimpleName();

    private void a(Context context, String str) {
        if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(str)) {
            i.a(this.f2499b, "receive ACTION_USB_DEVICE_ATTACHED, mount = U盘插入了");
        } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(str)) {
            i.a(this.f2499b, "receive ACTION_USB_DEVICE_DETACHED, unMount: U盘移除了");
            h.f2515a = false;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        f2498a = intent.getAction();
        Log.d(this.f2499b, "UsbCheckReceiver usb action= " + f2498a);
        a(context, f2498a);
    }
}
