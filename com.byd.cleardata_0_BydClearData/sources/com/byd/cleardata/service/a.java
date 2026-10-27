package com.byd.cleardata.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.cleardata.i.c;

/* JADX INFO: loaded from: classes.dex */
public class a extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        String action = intent.getAction();
        c.b("volume action = " + action);
        action.hashCode();
        switch (action) {
            case "android.hardware.usb.action.USB_DEVICE_ATTACHED":
                str = "attached";
                break;
            case "android.hardware.usb.action.USB_DEVICE_DETACHED":
                str = "detached";
                break;
            case "android.intent.action.MEDIA_MOUNTED":
                c.b("volume mounted");
                String stringExtra = intent.getStringExtra("device_type");
                c.b("volume deviceType = " + stringExtra);
                if ("usbotg".equals(stringExtra)) {
                    com.byd.cleardata.a.f().d();
                    return;
                } else {
                    if ("sdcard".equals(stringExtra)) {
                        com.byd.cleardata.a.f().c();
                        return;
                    }
                    return;
                }
            case "android.intent.action.MEDIA_EJECT":
                c.b("volume unmounted");
                String stringExtra2 = intent.getStringExtra("device_type");
                c.b("volume deviceType = " + stringExtra2);
                if ("usbotg".equals(stringExtra2)) {
                    com.byd.cleardata.a.f().t(2);
                    return;
                } else {
                    if ("sdcard".equals(stringExtra2)) {
                        com.byd.cleardata.a.f().t(1);
                        return;
                    }
                    return;
                }
            default:
                return;
        }
        c.b(str);
    }
}
