package org.chromium.chrome.browser.offlinepages;

import android.content.Intent;
import android.content.IntentFilter;
import defpackage.a8;
import defpackage.bc;
import defpackage.h90;
import defpackage.jc;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class BackgroundSchedulerBridge {
    @CalledByNative
    public static void backupSchedule(TriggerConditions triggerConditions, long j) {
        bc.a(triggerConditions, j * 1000, false);
    }

    @CalledByNative
    public static TriggerConditions createTriggerConditions(boolean z, int i, boolean z2) {
        return new TriggerConditions(i, z, z2);
    }

    @CalledByNative
    public static int getBatteryConditions() {
        int intExtra;
        Intent intentG = xz.g(xz.a, null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentG == null || (intExtra = intentG.getIntExtra("scale", -1)) == 0) {
            return 0;
        }
        return Math.round((intentG.getIntExtra("level", -1) * 100) / intExtra);
    }

    @CalledByNative
    public static int getNetworkConditions() {
        return h90.b(xz.a);
    }

    @CalledByNative
    public static boolean getPowerConditions() {
        Intent intentG = xz.g(xz.a, null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentG == null) {
            return false;
        }
        int intExtra = intentG.getIntExtra("status", -1);
        return intExtra == 2 || intExtra == 5;
    }

    @CalledByNative
    public static void schedule(TriggerConditions triggerConditions) {
        bc.a(triggerConditions, 0L, true);
    }

    @CalledByNative
    public static void unschedule() {
        ((jc) a8.E()).a(xz.a, 77);
    }
}
