package com.neusoft.na.byd.broadcast;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class OtaBroadcastReceiver extends BroadcastReceiver {
    public static final String OTA_IN = "com.byd.ota.broadcast.ENTER_OTA_MODEL";
    public static final String OTA_OUT = "com.byd.ota.broadcast.EXIT_OTA_MODEL";
    private static final String TAG = "XJOtaBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        Log.i(TAG, "onReceive: " + intent.getAction());
        if (intent.getAction().equals(OTA_IN)) {
            context.sendBroadcast(new Intent("com.neusoft.na.navigation.action.stop"));
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals("com.neusoft.na.navigation:Service") || runningAppProcessInfo.processName.equals("com.neusoft.na.navigation:OneCore") || runningAppProcessInfo.processName.equals("com.neusoft.na.navigation:Telematics") || runningAppProcessInfo.processName.equals("com.neusoft.na.navigation:Dbu") || runningAppProcessInfo.processName.equals("com.neusoft.na.navigation:Pos") || runningAppProcessInfo.processName.equals("com.neusoft.na.navigation:Sens") || runningAppProcessInfo.processName.equals("com.neusoft.na.navigation:Dlt") || runningAppProcessInfo.processName.equals("com.neusoft.na.navigation") || runningAppProcessInfo.processName.equals("com.neusoft.na.navigation:Guard") || runningAppProcessInfo.processName.equals("com.neusoft.na.navigation:Ehp")) {
                    Process.killProcess(runningAppProcessInfo.pid);
                    Log.i(TAG, "onReceive: " + runningAppProcessInfo.processName);
                }
            }
            Log.i(TAG, "onReceive: " + Process.myPid());
            Process.killProcess(Process.myPid());
            return;
        }
        intent.getAction().equals(OTA_OUT);
    }
}
