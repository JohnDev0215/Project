package com.qtxa.monitor.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public class UsbStateReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f81a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f82b = false;
    private long c;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = "\n";
        for (String str2 : intent.getExtras().keySet()) {
            str = str + "key:" + str2 + "  value:" + intent.getExtras().get(str2) + "\n";
        }
        com.qtxa.monitor.c.a.a("UsbStateReceiver", "notifyUsbStateChange : onReceive" + str);
        if (!intent.getAction().equals("android.hardware.usb.action.USB_STATE")) {
            if (intent.getAction().equals("android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
                a.a().b(((UsbDevice) intent.getParcelableExtra("device")).getDeviceName());
                return;
            }
            return;
        }
        this.f81a = intent.getExtras().getBoolean("connected");
        boolean z = intent.getExtras().getBoolean("configured");
        boolean z2 = Settings.Global.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
        this.f82b = z2;
        long j = 0;
        if (this.f81a) {
            if (z2 && z) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.c = jCurrentTimeMillis;
                a.a().c(new b(jCurrentTimeMillis, 0L));
                return;
            }
            return;
        }
        if (z2) {
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            long j2 = this.c;
            if (jCurrentTimeMillis2 > j2 && j2 != 0) {
                j = jCurrentTimeMillis2 - j2;
            }
            a.a().c(new b(jCurrentTimeMillis2, j));
        }
    }
}
