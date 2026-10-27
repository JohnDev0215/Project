package com.byd.vrsettings.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrsettings.utils.Common;
import com.byd.vrsettings.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class BootCompletedReceiver extends BroadcastReceiver {
    public static final String TAG = "NEU_BootCompletedReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            if (Common.getVehicleType().contains("Di3.0_3.0UI")) {
                RLog.i(TAG, "TangStep车型退出");
                return;
            }
            RLog.i(TAG, "start OTA_NotifyService");
            Intent intent2 = new Intent(context, (Class<?>) NotifyService.class);
            intent2.putExtra("NotifyParams", "source_boot");
            context.startForegroundService(intent2);
        }
    }
}
