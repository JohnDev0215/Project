package com.android.car.developeroptions.fuelgauge.batterytip;

import android.content.Context;
import com.android.car.developeroptions.fuelgauge.BatteryInfo;
import com.android.car.developeroptions.fuelgauge.BatteryUtils;
import com.android.car.developeroptions.fuelgauge.batterytip.detectors.EarlyWarningDetector;
import com.android.car.developeroptions.fuelgauge.batterytip.detectors.HighUsageDetector;
import com.android.car.developeroptions.fuelgauge.batterytip.detectors.LowBatteryDetector;
import com.android.car.developeroptions.fuelgauge.batterytip.detectors.RestrictAppDetector;
import com.android.car.developeroptions.fuelgauge.batterytip.detectors.SmartBatteryDetector;
import com.android.car.developeroptions.fuelgauge.batterytip.detectors.SummaryDetector;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settingslib.utils.AsyncLoaderCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BatteryTipLoader extends AsyncLoaderCompat<List<BatteryTip>> {
    private BatteryStatsHelper mBatteryStatsHelper;
    BatteryUtils mBatteryUtils;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.utils.AsyncLoaderCompat
    public void onDiscardResult(List<BatteryTip> list) {
    }

    public BatteryTipLoader(Context context, BatteryStatsHelper batteryStatsHelper) {
        super(context);
        this.mBatteryStatsHelper = batteryStatsHelper;
        this.mBatteryUtils = BatteryUtils.getInstance(context);
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public List<BatteryTip> loadInBackground() {
        ArrayList arrayList = new ArrayList();
        BatteryTipPolicy batteryTipPolicy = new BatteryTipPolicy(getContext());
        BatteryInfo batteryInfo = this.mBatteryUtils.getBatteryInfo(this.mBatteryStatsHelper, "BatteryTipLoader");
        Context context = getContext();
        arrayList.add(new LowBatteryDetector(context, batteryTipPolicy, batteryInfo).detect());
        arrayList.add(new HighUsageDetector(context, batteryTipPolicy, this.mBatteryStatsHelper, batteryInfo.discharging).detect());
        arrayList.add(new SmartBatteryDetector(batteryTipPolicy, context.getContentResolver()).detect());
        arrayList.add(new EarlyWarningDetector(batteryTipPolicy, context).detect());
        arrayList.add(new SummaryDetector(batteryTipPolicy, batteryInfo.averageTimeToDischarge).detect());
        arrayList.add(new RestrictAppDetector(context, batteryTipPolicy).detect());
        Collections.sort(arrayList);
        return arrayList;
    }
}
