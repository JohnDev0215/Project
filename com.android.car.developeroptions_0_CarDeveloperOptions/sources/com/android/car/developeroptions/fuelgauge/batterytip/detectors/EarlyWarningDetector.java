package com.android.car.developeroptions.fuelgauge.batterytip.detectors;

import android.content.Context;
import android.content.IntentFilter;
import android.os.PowerManager;
import com.android.car.developeroptions.fuelgauge.PowerUsageFeatureProvider;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPolicy;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.EarlyWarningTip;
import com.android.car.developeroptions.overlay.FeatureFactory;

/* JADX INFO: loaded from: classes.dex */
public class EarlyWarningDetector {
    private Context mContext;
    private BatteryTipPolicy mPolicy;
    private PowerManager mPowerManager;
    private PowerUsageFeatureProvider mPowerUsageFeatureProvider;

    public EarlyWarningDetector(BatteryTipPolicy batteryTipPolicy, Context context) {
        this.mPolicy = batteryTipPolicy;
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mContext = context;
        this.mPowerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
    }

    public BatteryTip detect() {
        int i = 1;
        boolean z = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1) == 0;
        boolean zIsPowerSaveMode = this.mPowerManager.isPowerSaveMode();
        boolean z2 = this.mPowerUsageFeatureProvider.getEarlyWarningSignal(this.mContext, EarlyWarningDetector.class.getName()) || this.mPolicy.testBatterySaverTip;
        if (!zIsPowerSaveMode) {
            i = (this.mPolicy.batterySaverTipEnabled && z && z2) ? 0 : 2;
        }
        return new EarlyWarningTip(i, zIsPowerSaveMode);
    }
}
