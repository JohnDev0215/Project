package com.micphone.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.micphone.utils.Config;

/* JADX INFO: loaded from: classes.dex */
public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "MicphoneService";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: " + intent.getAction());
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED") || intent.getAction().equals("android.intent.action.MEDIA_MOUNTED")) {
            Intent intent2 = new Intent(context, (Class<?>) MicphoneService.class);
            if (!Config.isFrontService() || Build.VERSION.SDK_INT < 26) {
                context.startService(intent2);
            } else {
                context.startForegroundService(intent2);
            }
        }
    }
}
