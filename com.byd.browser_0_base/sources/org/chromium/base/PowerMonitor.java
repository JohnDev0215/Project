package org.chromium.base;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.PowerManager;
import defpackage.f32;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class PowerMonitor {
    public static PowerMonitor b;
    public boolean a;

    public class a extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            PowerMonitor.b.a = intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED");
            N.MCImhGql();
        }
    }

    public static void a() {
        Object obj = ThreadUtils.a;
        if (b != null) {
            return;
        }
        Context context = xz.a;
        b = new PowerMonitor();
        Intent intentG = xz.g(context, null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentG != null) {
            b.a = intentG.getIntExtra("plugged", 0) == 0;
            N.MCImhGql();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        xz.g(context, new a(), intentFilter);
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager != null) {
            powerManager.addThermalStatusListener(new f32());
        }
    }

    @CalledByNative
    public static int getCurrentThermalStatus() {
        if (b == null) {
            a();
        }
        PowerManager powerManager = (PowerManager) xz.a.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        return powerManager.getCurrentThermalStatus();
    }

    @CalledByNative
    public static int getRemainingBatteryCapacity() {
        if (b == null) {
            a();
        }
        return ((BatteryManager) xz.a.getSystemService("batterymanager")).getIntProperty(1);
    }

    @CalledByNative
    public static boolean isBatteryPower() {
        if (b == null) {
            a();
        }
        return b.a;
    }
}
