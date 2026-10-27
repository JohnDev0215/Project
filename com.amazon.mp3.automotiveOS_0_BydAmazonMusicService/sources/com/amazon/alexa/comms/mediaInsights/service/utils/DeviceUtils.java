package com.amazon.alexa.comms.mediaInsights.service.utils;

import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUtils {
    public static WifiManager.WifiLock acquireWifiLock(WifiManager wifiManager, String str) {
        if (wifiManager == null) {
            Log.d("MediaInsightsPublisher", "Wifi lock not acquired because wifiManager is null");
            return null;
        }
        WifiManager.WifiLock wifiLockCreateWifiLock = wifiManager.createWifiLock(3, str);
        if (wifiLockCreateWifiLock == null) {
            Log.d("MediaInsightsPublisher", "Wifi lock not acquired because wifi lock is null");
            return null;
        }
        wifiLockCreateWifiLock.setReferenceCounted(true);
        wifiLockCreateWifiLock.acquire();
        return wifiLockCreateWifiLock;
    }

    public static void releaseWifiLock(WifiManager.WifiLock wifiLock) {
        if (wifiLock == null || !wifiLock.isHeld()) {
            return;
        }
        wifiLock.release();
    }

    public static PowerManager.WakeLock acquireWakeLock(PowerManager powerManager, long j, String str) {
        if (powerManager == null) {
            Log.d("MediaInsightsPublisher", "Wake lock not acquired because powerManager is null");
            return null;
        }
        PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(1, str);
        if (wakeLockNewWakeLock == null) {
            Log.d("MediaInsightsPublisher", "Wake lock not acquired because wakeLock is null");
            return null;
        }
        wakeLockNewWakeLock.setReferenceCounted(true);
        wakeLockNewWakeLock.acquire(j);
        return wakeLockNewWakeLock;
    }

    public static void releaseWakeLock(PowerManager.WakeLock wakeLock) {
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        wakeLock.release();
    }
}
