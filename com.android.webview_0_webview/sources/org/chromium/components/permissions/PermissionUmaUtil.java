package org.chromium.components.permissions;

import android.content.Intent;
import android.content.IntentFilter;
import defpackage.Is;
import defpackage.Lt;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PermissionUmaUtil {
    public static void recordWithBatteryBucket(String str) {
        Intent intentRegisterReceiver = Is.a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
        int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
        if (intExtra2 == 0) {
            return;
        }
        Lt.a.d(str, (int) ((((double) intExtra) * 100.0d) / ((double) intExtra2)), 1, 101, 102);
    }
}
