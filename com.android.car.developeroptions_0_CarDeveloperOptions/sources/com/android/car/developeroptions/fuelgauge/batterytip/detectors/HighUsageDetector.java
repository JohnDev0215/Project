package com.android.car.developeroptions.fuelgauge.batterytip.detectors;

import android.content.Context;
import com.android.car.developeroptions.fuelgauge.BatteryInfo;
import com.android.car.developeroptions.fuelgauge.BatteryUtils;
import com.android.car.developeroptions.fuelgauge.batterytip.AppInfo;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPolicy;
import com.android.car.developeroptions.fuelgauge.batterytip.HighUsageDataParser;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.HighUsageTip;
import com.android.internal.os.BatterySipper;
import com.android.internal.os.BatteryStatsHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class HighUsageDetector {
    private BatteryStatsHelper mBatteryStatsHelper;
    BatteryUtils mBatteryUtils;
    HighUsageDataParser mDataParser;
    boolean mDischarging;
    private List<AppInfo> mHighUsageAppList = new ArrayList();
    private BatteryTipPolicy mPolicy;

    public HighUsageDetector(Context context, BatteryTipPolicy batteryTipPolicy, BatteryStatsHelper batteryStatsHelper, boolean z) {
        this.mPolicy = batteryTipPolicy;
        this.mBatteryStatsHelper = batteryStatsHelper;
        this.mBatteryUtils = BatteryUtils.getInstance(context);
        BatteryTipPolicy batteryTipPolicy2 = this.mPolicy;
        this.mDataParser = new HighUsageDataParser(batteryTipPolicy2.highUsagePeriodMs, batteryTipPolicy2.highUsageBatteryDraining);
        this.mDischarging = z;
    }

    public BatteryTip detect() {
        long jCalculateLastFullChargeTime = this.mBatteryUtils.calculateLastFullChargeTime(this.mBatteryStatsHelper, System.currentTimeMillis());
        if (this.mPolicy.highUsageEnabled && this.mDischarging) {
            parseBatteryData();
            if (this.mDataParser.isDeviceHeavilyUsed() || this.mPolicy.testHighUsageTip) {
                List usageList = this.mBatteryStatsHelper.getUsageList();
                int size = usageList.size();
                for (int i = 0; i < size; i++) {
                    BatterySipper batterySipper = (BatterySipper) usageList.get(i);
                    if (!this.mBatteryUtils.shouldHideSipper(batterySipper)) {
                        long processTimeMs = this.mBatteryUtils.getProcessTimeMs(1, batterySipper.uidObj, 0);
                        if (processTimeMs >= 60000) {
                            List<AppInfo> list = this.mHighUsageAppList;
                            AppInfo.Builder builder = new AppInfo.Builder();
                            builder.setUid(batterySipper.getUid());
                            builder.setPackageName(this.mBatteryUtils.getPackageName(batterySipper.getUid()));
                            builder.setScreenOnTimeMs(processTimeMs);
                            list.add(builder.build());
                        }
                    }
                }
                if (this.mPolicy.testHighUsageTip && this.mHighUsageAppList.isEmpty()) {
                    List<AppInfo> list2 = this.mHighUsageAppList;
                    AppInfo.Builder builder2 = new AppInfo.Builder();
                    builder2.setPackageName("com.android.car.developeroptions");
                    builder2.setScreenOnTimeMs(TimeUnit.HOURS.toMillis(3L));
                    list2.add(builder2.build());
                }
                Collections.sort(this.mHighUsageAppList, Collections.reverseOrder());
                List<AppInfo> list3 = this.mHighUsageAppList;
                this.mHighUsageAppList = list3.subList(0, Math.min(this.mPolicy.highUsageAppCount, list3.size()));
            }
        }
        return new HighUsageTip(jCalculateLastFullChargeTime, this.mHighUsageAppList);
    }

    void parseBatteryData() {
        BatteryInfo.parse(this.mBatteryStatsHelper.getStats(), this.mDataParser);
    }
}
