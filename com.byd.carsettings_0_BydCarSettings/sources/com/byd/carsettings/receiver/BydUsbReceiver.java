package com.byd.carsettings.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import b.b.g.p.j.e;
import b.b.m.c.a;

/* JADX INFO: loaded from: classes2.dex */
public class BydUsbReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        a.a("BydUsbReceiver", "---onReceive---action: " + action);
        e eVarB = b.b.g.e.a.b("usb_state");
        if (eVarB == null) {
        }
        action.hashCode();
        switch (action) {
            case "android.intent.action.MEDIA_REMOVED":
            case "android.intent.action.MEDIA_UNMOUNTED":
            case "android.intent.action.MEDIA_EJECT":
            case "action:android.hardware.usb.action.USB_DEVICE_DETACHED":
                eVarB.a(intent);
                break;
            case "android.intent.action.MEDIA_MOUNTED":
            case "action:android.hardware.usb.action.USB_DEVICE_ATTACHED":
                eVarB.b(intent);
                break;
        }
    }
}
