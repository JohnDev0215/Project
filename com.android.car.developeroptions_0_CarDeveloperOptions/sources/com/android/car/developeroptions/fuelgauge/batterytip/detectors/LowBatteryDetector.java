package com.android.car.developeroptions.fuelgauge.batterytip.detectors;

import android.R;
import android.content.Context;
import android.os.PowerManager;
import com.android.car.developeroptions.fuelgauge.BatteryInfo;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPolicy;

/* JADX INFO: loaded from: classes.dex */
public class LowBatteryDetector {
    private BatteryInfo mBatteryInfo;
    private BatteryTipPolicy mPolicy;
    private PowerManager mPowerManager;
    private int mWarningLevel;

    public LowBatteryDetector(Context context, BatteryTipPolicy batteryTipPolicy, BatteryInfo batteryInfo) {
        this.mPolicy = batteryTipPolicy;
        this.mBatteryInfo = batteryInfo;
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mWarningLevel = context.getResources().getInteger(R.integer.config_delay_for_ims_dereg_millis);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip detect() {
        /*
            r8 = this;
            android.os.PowerManager r0 = r8.mPowerManager
            boolean r0 = r0.isPowerSaveMode()
            com.android.car.developeroptions.fuelgauge.BatteryInfo r1 = r8.mBatteryInfo
            int r2 = r1.batteryLevel
            int r3 = r8.mWarningLevel
            r4 = 0
            r5 = 1
            if (r2 <= r3) goto L2e
            boolean r2 = r1.discharging
            if (r2 == 0) goto L2c
            long r1 = r1.remainingTimeUs
            r6 = 0
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 == 0) goto L2c
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.HOURS
            com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPolicy r6 = r8.mPolicy
            int r6 = r6.lowBatteryHour
            long r6 = (long) r6
            long r6 = r3.toMicros(r6)
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 >= 0) goto L2c
            goto L2e
        L2c:
            r1 = r4
            goto L2f
        L2e:
            r1 = r5
        L2f:
            r2 = 2
            com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPolicy r3 = r8.mPolicy
            boolean r6 = r3.lowBatteryEnabled
            if (r6 == 0) goto L47
            if (r0 == 0) goto L3a
            r4 = r5
            goto L48
        L3a:
            boolean r3 = r3.testLowBatteryTip
            if (r3 != 0) goto L48
            com.android.car.developeroptions.fuelgauge.BatteryInfo r3 = r8.mBatteryInfo
            boolean r3 = r3.discharging
            if (r3 == 0) goto L47
            if (r1 == 0) goto L47
            goto L48
        L47:
            r4 = r2
        L48:
            com.android.car.developeroptions.fuelgauge.batterytip.tips.LowBatteryTip r1 = new com.android.car.developeroptions.fuelgauge.batterytip.tips.LowBatteryTip
            com.android.car.developeroptions.fuelgauge.BatteryInfo r8 = r8.mBatteryInfo
            java.lang.String r8 = r8.suggestionLabel
            r1.<init>(r4, r0, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.fuelgauge.batterytip.detectors.LowBatteryDetector.detect():com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip");
    }
}
