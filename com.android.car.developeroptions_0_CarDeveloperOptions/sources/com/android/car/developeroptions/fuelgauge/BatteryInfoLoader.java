package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settingslib.utils.AsyncLoaderCompat;

/* JADX INFO: loaded from: classes.dex */
public class BatteryInfoLoader extends AsyncLoaderCompat<BatteryInfo> {
    BatteryUtils batteryUtils;
    BatteryStatsHelper mStatsHelper;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.utils.AsyncLoaderCompat
    public void onDiscardResult(BatteryInfo batteryInfo) {
    }

    public BatteryInfoLoader(Context context, BatteryStatsHelper batteryStatsHelper) {
        super(context);
        this.mStatsHelper = batteryStatsHelper;
        this.batteryUtils = BatteryUtils.getInstance(context);
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public BatteryInfo loadInBackground() {
        return this.batteryUtils.getBatteryInfo(this.mStatsHelper, "BatteryInfoLoader");
    }
}
