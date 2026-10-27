package org.chromium.base;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
public class PowerMonitor implements ApplicationStatus.ApplicationStateListener {
    private static final long SUSPEND_DELAY_MS = 60000;
    private static PowerMonitor sInstance;
    private static final Runnable sSuspendTask = new Runnable() { // from class: org.chromium.base.PowerMonitor.1
        @Override // java.lang.Runnable
        public void run() {
            PowerMonitor.nativeOnMainActivitySuspended();
        }
    };
    private final Handler mHandler;
    private boolean mIsBatteryPower;

    private static native void nativeOnBatteryChargingChanged();

    private static native void nativeOnMainActivityResumed();

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeOnMainActivitySuspended();

    private static class LazyHolder {
        private static final PowerMonitor INSTANCE = new PowerMonitor();

        private LazyHolder() {
        }
    }

    public static void createForTests(Context context) {
        sInstance = LazyHolder.INSTANCE;
    }

    public static void create(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (sInstance == null) {
            PowerMonitor powerMonitor = LazyHolder.INSTANCE;
            sInstance = powerMonitor;
            ApplicationStatus.registerApplicationStateListener(powerMonitor);
            Intent intentRegisterReceiver = applicationContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (intentRegisterReceiver != null) {
                onBatteryChargingChanged(intentRegisterReceiver);
            }
        }
    }

    private PowerMonitor() {
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void onBatteryChargingChanged(android.content.Intent r2) {
        /*
            org.chromium.base.PowerMonitor r0 = org.chromium.base.PowerMonitor.sInstance
            if (r0 != 0) goto L5
            return
        L5:
            java.lang.String r0 = "plugged"
            r1 = -1
            int r2 = r2.getIntExtra(r0, r1)
            org.chromium.base.PowerMonitor r0 = org.chromium.base.PowerMonitor.sInstance
            r1 = 2
            if (r2 == r1) goto L15
            r1 = 1
            if (r2 == r1) goto L15
            goto L16
        L15:
            r1 = 0
        L16:
            r0.mIsBatteryPower = r1
            nativeOnBatteryChargingChanged()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.PowerMonitor.onBatteryChargingChanged(android.content.Intent):void");
    }

    @Override // org.chromium.base.ApplicationStatus.ApplicationStateListener
    public void onApplicationStateChange(int i) {
        if (i == 1) {
            this.mHandler.removeCallbacks(sSuspendTask);
            nativeOnMainActivityResumed();
        } else if (i == 2) {
            this.mHandler.postDelayed(sSuspendTask, 60000L);
        }
    }

    private static boolean isBatteryPower() {
        return sInstance.mIsBatteryPower;
    }
}
