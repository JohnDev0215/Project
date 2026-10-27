package com.android.car.developeroptions.fuelgauge.batterytip.detectors;

import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPolicy;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.SummaryTip;

/* JADX INFO: loaded from: classes.dex */
public class SummaryDetector {
    private long mAverageTimeMs;
    private BatteryTipPolicy mPolicy;

    public SummaryDetector(BatteryTipPolicy batteryTipPolicy, long j) {
        this.mPolicy = batteryTipPolicy;
        this.mAverageTimeMs = j;
    }

    public BatteryTip detect() {
        return new SummaryTip(this.mPolicy.summaryEnabled ? 0 : 2, this.mAverageTimeMs);
    }
}
