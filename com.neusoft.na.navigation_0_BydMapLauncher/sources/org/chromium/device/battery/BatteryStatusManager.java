package org.chromium.device.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;
import javax.annotation.Nullable;
import org.chromium.mojom.device.BatteryStatus;

/* JADX INFO: loaded from: classes3.dex */
class BatteryStatusManager {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "BatteryStatusManager";
    private AndroidBatteryManagerWrapper mAndroidBatteryManager;
    private final Context mAppContext;
    private final BatteryStatusCallback mCallback;
    private boolean mEnabled;
    private final IntentFilter mFilter;
    private final boolean mIgnoreBatteryPresentState;
    private final BroadcastReceiver mReceiver;

    interface BatteryStatusCallback {
        void onBatteryStatusChanged(BatteryStatus batteryStatus);
    }

    static class AndroidBatteryManagerWrapper {
        private final BatteryManager mBatteryManager;

        protected AndroidBatteryManagerWrapper(BatteryManager batteryManager) {
            this.mBatteryManager = batteryManager;
        }

        public int getIntProperty(int i) {
            return this.mBatteryManager.getIntProperty(i);
        }
    }

    private BatteryStatusManager(Context context, BatteryStatusCallback batteryStatusCallback, boolean z, @Nullable AndroidBatteryManagerWrapper androidBatteryManagerWrapper) {
        this.mFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        this.mReceiver = new BroadcastReceiver() { // from class: org.chromium.device.battery.BatteryStatusManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                BatteryStatusManager.this.onReceive(intent);
            }
        };
        this.mEnabled = false;
        this.mAppContext = context.getApplicationContext();
        this.mCallback = batteryStatusCallback;
        this.mIgnoreBatteryPresentState = z;
        this.mAndroidBatteryManager = androidBatteryManagerWrapper;
    }

    BatteryStatusManager(Context context, BatteryStatusCallback batteryStatusCallback) {
        this(context, batteryStatusCallback, Build.MODEL.equals("Galaxy Nexus"), new AndroidBatteryManagerWrapper((BatteryManager) context.getSystemService("batterymanager")));
    }

    static BatteryStatusManager createBatteryStatusManagerForTesting(Context context, BatteryStatusCallback batteryStatusCallback, @Nullable AndroidBatteryManagerWrapper androidBatteryManagerWrapper) {
        return new BatteryStatusManager(context, batteryStatusCallback, false, androidBatteryManagerWrapper);
    }

    boolean start() {
        if (!this.mEnabled && this.mAppContext.registerReceiver(this.mReceiver, this.mFilter) != null) {
            this.mEnabled = true;
        }
        return this.mEnabled;
    }

    void stop() {
        if (this.mEnabled) {
            this.mAppContext.unregisterReceiver(this.mReceiver);
            this.mEnabled = false;
        }
    }

    void onReceive(Intent intent) {
        if (!intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
            Log.e(TAG, "Unexpected intent.");
            return;
        }
        boolean booleanExtra = this.mIgnoreBatteryPresentState ? true : intent.getBooleanExtra("present", false);
        int intExtra = intent.getIntExtra("plugged", -1);
        if (!booleanExtra || intExtra == -1) {
            this.mCallback.onBatteryStatusChanged(new BatteryStatus());
            return;
        }
        double intExtra2 = ((double) intent.getIntExtra("level", -1)) / ((double) intent.getIntExtra("scale", -1));
        if (intExtra2 < 0.0d || intExtra2 > 1.0d) {
            intExtra2 = 1.0d;
        }
        boolean z = intExtra != 0;
        double d = (z && (intent.getIntExtra("status", -1) == 5)) ? 0.0d : Double.POSITIVE_INFINITY;
        BatteryStatus batteryStatus = new BatteryStatus();
        batteryStatus.charging = z;
        batteryStatus.chargingTime = d;
        batteryStatus.dischargingTime = Double.POSITIVE_INFINITY;
        batteryStatus.level = intExtra2;
        if (this.mAndroidBatteryManager != null) {
            updateBatteryStatusForLollipop(batteryStatus);
        }
        this.mCallback.onBatteryStatusChanged(batteryStatus);
    }

    private void updateBatteryStatusForLollipop(BatteryStatus batteryStatus) {
        double intProperty = ((double) this.mAndroidBatteryManager.getIntProperty(4)) / 100.0d;
        double intProperty2 = this.mAndroidBatteryManager.getIntProperty(1);
        double intProperty3 = this.mAndroidBatteryManager.getIntProperty(3);
        if (!batteryStatus.charging) {
            if (intProperty3 < 0.0d) {
                batteryStatus.dischargingTime = Math.floor(intProperty * (intProperty2 / (-intProperty3)) * 3600.0d);
            }
        } else {
            if (batteryStatus.chargingTime != Double.POSITIVE_INFINITY || intProperty3 <= 0.0d) {
                return;
            }
            batteryStatus.chargingTime = Math.ceil((1.0d - intProperty) * (intProperty2 / intProperty3) * 3600.0d);
        }
    }
}
