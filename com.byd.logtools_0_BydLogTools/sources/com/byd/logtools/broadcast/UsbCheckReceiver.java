package com.byd.logtools.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.byd.logtools.logservice.BYDLogService;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class UsbCheckReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f3156a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3157b = UsbCheckReceiver.class.getSimpleName();

    private void a(Context context, String str) {
        if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(str)) {
            BydLogHelper.logD("receive ACTION_USB_DEVICE_ATTACHED, mount = USB inserted");
            Intent intent = new Intent(context, (Class<?>) BYDLogService.class);
            intent.setAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
            context.startService(intent);
            return;
        }
        if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(str)) {
            BydLogHelper.logD("receive ACTION_USB_DEVICE_DETACHED, unMount: USB removed");
            Intent intent2 = new Intent(context, (Class<?>) BYDLogService.class);
            intent2.setAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
            context.startService(intent2);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        f3156a = intent.getAction();
        Log.d(this.f3157b, "UsbCheckReceiver usb action= " + f3156a);
        a(context, f3156a);
    }
}
