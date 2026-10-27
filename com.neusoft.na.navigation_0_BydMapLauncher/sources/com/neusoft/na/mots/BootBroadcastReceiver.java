package com.neusoft.na.mots;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.ocservice.MotsService;
import com.neusoft.na.ocservice.util.CheckBoardKt;

/* JADX INFO: loaded from: classes2.dex */
public class BootBroadcastReceiver extends BroadcastReceiver {
    private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";
    private static final String COAGENT_ACTION_READY = "com.coagent.intent.action.READY";
    private static final String FIRST_BOOT_COMPLETED = "android.intent.action.FIRST_BOOT_COMPLETED";
    private static final String TAG = "XJBootBroadcastReceiver";
    private boolean isStart = false;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        Log.i(TAG, "onReceive: " + intent.getAction() + "::isStart::" + this.isStart);
        String action = intent.getAction();
        boolean z = !CheckBoardKt.isBydSeries() || FileUtils.getInstance(context).isBydHasAll();
        boolean z2 = ACTION_BOOT.equals(action) || COAGENT_ACTION_READY.equals(action) || FIRST_BOOT_COMPLETED.equals(action);
        if (CheckBoardKt.isBydSeries() && z2) {
            Intent intent2 = new Intent();
            intent2.setClassName(context.getPackageName(), "com.neusoft.na.byd.sdk.NaviService");
            context.startForegroundService(intent2);
        }
        if (z && !this.isStart && z2) {
            this.isStart = true;
            if (!CheckBoardKt.isGreatwallV61()) {
                context.startForegroundService(new Intent(context, (Class<?>) MotsMainService.class));
                context.startForegroundService(new Intent(context, (Class<?>) MotsService.class));
            } else {
                context.startService(new Intent(context, (Class<?>) MotsMainService.class));
                context.startService(new Intent(context, (Class<?>) MotsService.class));
            }
        }
    }
}
