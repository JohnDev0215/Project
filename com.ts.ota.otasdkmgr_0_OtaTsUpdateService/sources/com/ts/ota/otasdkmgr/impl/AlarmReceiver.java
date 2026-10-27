package com.ts.ota.otasdkmgr.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class AlarmReceiver extends BroadcastReceiver {
    private static final String ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";
    private static final String ACTION_NEXT_WAKEUP = "android.intent.action.ACTION_NEXT_WAKEUP";
    private static final String REBOOT = "reboot";
    private static final String TAG = "AlarmReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent mIntent) {
        Map<String, Long> alarmMap;
        Log.d(TAG, "onReceive: intent: " + mIntent);
        AppointTime mAppointTime = AppointTime.getInstance(context);
        String action = mIntent.getAction();
        Log.w(TAG, "onReceive: action is " + action);
        if (ACTION_BOOT_COMPLETED.equals(action)) {
            Map<String, Long> alarmMap2 = mAppointTime.getAllAppointTime();
            boolean hasTimeOut = false;
            long currentTime = System.currentTimeMillis();
            Log.w(TAG, "onReceive: currentTime->" + currentTime);
            if (alarmMap2.isEmpty()) {
                Log.e(TAG, "onReceive: has no appointment time");
                return;
            }
            Set<Map.Entry<String, Long>> setOfAlarm = alarmMap2.entrySet();
            for (Map.Entry<String, Long> entry : setOfAlarm) {
                long value = entry.getValue().longValue();
                String key = entry.getKey();
                if (currentTime >= value) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onReceive: alarm->");
                    sb.append(key);
                    alarmMap = alarmMap2;
                    sb.append(", time->");
                    sb.append(value);
                    sb.append(" has been reached");
                    Log.w(TAG, sb.toString());
                    mAppointTime.removeAppointTime(key);
                    hasTimeOut = true;
                } else {
                    alarmMap = alarmMap2;
                    Log.w(TAG, "onReceive: type of " + key + " has set alarm");
                    mAppointTime.setAlarm(value, key);
                }
                alarmMap2 = alarmMap;
            }
            if (hasTimeOut) {
                mAppointTime.sendWakeUpTime();
                return;
            } else {
                Log.w(TAG, "onReceive: has no alarm timeout");
                return;
            }
        }
        if (ACTION_NEXT_WAKEUP.equals(action)) {
            String type = mIntent.getType();
            if (REBOOT.equals(type)) {
                mAppointTime.setNextTimeReboot();
                return;
            }
            mAppointTime.removeAppointTime(type);
            Log.w(TAG, "onReceive: alarm->" + type + " has been reached");
            mAppointTime.sendWakeUpTime();
        }
    }
}
