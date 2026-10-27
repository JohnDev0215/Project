package com.amazon.mp3.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes4.dex */
public class ScreenWakeLock {
    private static final String TAG = "ScreenWakeLock";
    private static final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.util.ScreenWakeLock.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.debug(ScreenWakeLock.TAG, "IntentAction is null. Not taking any action.");
            }
            if (ScreenWakeLock.mMostRecentForegroundActivity == null) {
                return;
            }
            switch (action) {
                case "com.amazon.mp3.wakewordcommand.app.in.background":
                    ScreenWakeLock.onApplicationInBackground();
                    break;
                case "android.intent.action.BATTERY_CHANGED":
                    ScreenWakeLock.onBatteryStatusChanged(context);
                    break;
                case "com.amazon.mp3.screenwakelock.activity.in.foreground":
                    ScreenWakeLock.onActivityInForeground();
                    break;
                default:
                    Log.error(ScreenWakeLock.TAG, "Intent action unknown.");
                    break;
            }
        }
    };
    private static boolean mForceKeepScreenOn;
    private static Activity mMostRecentForegroundActivity;

    /* JADX INFO: Access modifiers changed from: private */
    public static void onBatteryStatusChanged(final Context context) {
        if (DeviceUtil.isCharging(context)) {
            setActivityScreenOnFlag();
        } else {
            if (mForceKeepScreenOn) {
                return;
            }
            clearActivityScreenOnFlag();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onActivityInForeground() {
        if (DeviceUtil.isCharging(mMostRecentForegroundActivity) || mForceKeepScreenOn) {
            setActivityScreenOnFlag();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onApplicationInBackground() {
        clearActivityScreenOnFlag();
        setForceKeepScreenOn(false);
        mMostRecentForegroundActivity = null;
    }

    private static void clearActivityScreenOnFlag() {
        Activity activity = mMostRecentForegroundActivity;
        if (activity == null) {
            return;
        }
        activity.getWindow().clearFlags(128);
    }

    private static void setActivityScreenOnFlag() {
        Activity activity = mMostRecentForegroundActivity;
        if (activity == null) {
            return;
        }
        activity.getWindow().addFlags(128);
    }

    public static void registerDeviceChargingReceiver(final Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("com.amazon.mp3.wakewordcommand.app.in.background");
        intentFilter.addAction("com.amazon.mp3.screenwakelock.activity.in.foreground");
        context.getApplicationContext().registerReceiver(mBroadcastReceiver, intentFilter);
    }

    public static void unregisterDeviceChargingReceiver(final Context context) {
        context.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
    }

    public static void setActivity(final Activity activity) {
        mMostRecentForegroundActivity = activity;
    }

    public static void onWakeWordDetected() {
        setForceKeepScreenOn(true);
        setActivityScreenOnFlag();
    }

    public static synchronized void setForceKeepScreenOn(final boolean mForceKeepScreenOn2) {
        mForceKeepScreenOn = mForceKeepScreenOn2;
    }
}
