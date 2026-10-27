package com.amazon.mp3.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.app.NotificationCompat;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceUtil {
    public static boolean isCharging(Context context) {
        if (context == null) {
            return false;
        }
        Intent intentRegisterReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = intentRegisterReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
        int intExtra2 = intentRegisterReceiver.getIntExtra("plugged", -1);
        if (intExtra != 2) {
            if (intExtra != 5) {
                return false;
            }
            if (intExtra2 != 2 && intExtra2 != 1 && intExtra2 != 4) {
                return false;
            }
        }
        return true;
    }

    public static int getBatteryPercentage(Context context) {
        if (context == null) {
            return 0;
        }
        Intent intentRegisterReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return Math.round((intentRegisterReceiver.getIntExtra("level", -1) / intentRegisterReceiver.getIntExtra("scale", -1)) * 100.0f);
    }
}
