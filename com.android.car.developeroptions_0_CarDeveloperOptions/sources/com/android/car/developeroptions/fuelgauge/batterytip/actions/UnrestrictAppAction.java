package com.android.car.developeroptions.fuelgauge.batterytip.actions;

import android.content.Context;
import com.android.car.developeroptions.fuelgauge.BatteryUtils;
import com.android.car.developeroptions.fuelgauge.batterytip.AppInfo;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.UnrestrictAppTip;

/* JADX INFO: loaded from: classes.dex */
public class UnrestrictAppAction extends BatteryTipAction {
    BatteryUtils mBatteryUtils;
    private UnrestrictAppTip mUnRestrictAppTip;

    public UnrestrictAppAction(Context context, UnrestrictAppTip unrestrictAppTip) {
        super(context);
        this.mUnRestrictAppTip = unrestrictAppTip;
        this.mBatteryUtils = BatteryUtils.getInstance(context);
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.actions.BatteryTipAction
    public void handlePositiveAction(int i) {
        AppInfo unrestrictAppInfo = this.mUnRestrictAppTip.getUnrestrictAppInfo();
        this.mBatteryUtils.setForceAppStandby(unrestrictAppInfo.uid, unrestrictAppInfo.packageName, 0);
        this.mMetricsFeatureProvider.action(0, 1363, i, unrestrictAppInfo.packageName, 0);
    }
}
