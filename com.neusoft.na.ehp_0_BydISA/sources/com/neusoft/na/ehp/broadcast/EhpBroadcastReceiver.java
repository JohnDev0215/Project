package com.neusoft.na.ehp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.neusoft.na.ehp.EhpService;

/* JADX INFO: loaded from: classes.dex */
public class EhpBroadcastReceiver extends BroadcastReceiver {
    private static final String COAGENT_ACTION_READY = "com.coagent.intent.action.READY";
    private static final String FIRST_BOOT_COMPLETED = "android.intent.action.FIRST_BOOT_COMPLETED";
    private static final String TAG = "NeuEhpReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        Log.d(TAG, "onReceive: " + intent.getAction());
        String action = intent.getAction();
        if ("android.intent.action.BOOT_COMPLETED".equals(action) || COAGENT_ACTION_READY.equals(action) || FIRST_BOOT_COMPLETED.equals(action)) {
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(new Intent(context, (Class<?>) EhpService.class));
            } else {
                context.startService(new Intent(context, (Class<?>) EhpService.class));
            }
        }
    }
}
