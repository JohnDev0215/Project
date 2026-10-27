package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryStats;
import android.os.SystemClock;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settingslib.utils.AsyncLoaderCompat;
import com.android.settingslib.utils.PowerUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DebugEstimatesLoader extends AsyncLoaderCompat<List<BatteryInfo>> {
    private BatteryStatsHelper mStatsHelper;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.utils.AsyncLoaderCompat
    public void onDiscardResult(List<BatteryInfo> list) {
    }

    public DebugEstimatesLoader(Context context, BatteryStatsHelper batteryStatsHelper) {
        super(context);
        this.mStatsHelper = batteryStatsHelper;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public List<BatteryInfo> loadInBackground() {
        Context context = getContext();
        PowerUsageFeatureProvider powerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
        long jConvertMsToUs = PowerUtil.convertMsToUs(SystemClock.elapsedRealtime());
        Intent intentRegisterReceiver = getContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        BatteryStats stats = this.mStatsHelper.getStats();
        BatteryInfo batteryInfoOld = BatteryInfo.getBatteryInfoOld(getContext(), intentRegisterReceiver, stats, jConvertMsToUs, false);
        Estimate enhancedBatteryPrediction = powerUsageFeatureProvider.getEnhancedBatteryPrediction(context);
        if (enhancedBatteryPrediction == null) {
            enhancedBatteryPrediction = new Estimate(0L, false, -1L);
        }
        BatteryInfo batteryInfo = BatteryInfo.getBatteryInfo(getContext(), intentRegisterReceiver, stats, enhancedBatteryPrediction, jConvertMsToUs, false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(batteryInfoOld);
        arrayList.add(batteryInfo);
        return arrayList;
    }
}
