package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.os.UserManager;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settingslib.utils.AsyncLoaderCompat;

/* JADX INFO: loaded from: classes.dex */
public class BatteryStatsHelperLoader extends AsyncLoaderCompat<BatteryStatsHelper> {
    BatteryUtils mBatteryUtils;
    UserManager mUserManager;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.utils.AsyncLoaderCompat
    public void onDiscardResult(BatteryStatsHelper batteryStatsHelper) {
    }

    public BatteryStatsHelperLoader(Context context) {
        super(context);
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mBatteryUtils = BatteryUtils.getInstance(context);
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public BatteryStatsHelper loadInBackground() {
        BatteryStatsHelper batteryStatsHelper = new BatteryStatsHelper(getContext(), true);
        this.mBatteryUtils.initBatteryStatsHelper(batteryStatsHelper, null, this.mUserManager);
        return batteryStatsHelper;
    }
}
