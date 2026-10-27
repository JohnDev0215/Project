package org.chromium.base;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import defpackage.C0572bt;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PowerMonitor {
    public static PowerMonitor b;
    public boolean a;

    public static void a(Intent intent) {
        int intExtra = intent.getIntExtra("plugged", -1);
        b.a = (intExtra == 2 || intExtra == 1) ? false : true;
        N.MCImhGql();
    }

    public static boolean isBatteryPower() {
        if (b == null) {
            ThreadUtils.b();
            if (b == null) {
                Context context = Is.a;
                b = new PowerMonitor();
                Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                if (intentRegisterReceiver != null) {
                    a(intentRegisterReceiver);
                }
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
                intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
                context.registerReceiver(new C0572bt(), intentFilter);
            }
        }
        return b.a;
    }
}
