package com.android.car.developeroptions.fuelgauge.batterytip.actions;

import android.content.Context;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.fuelgauge.batterysaver.BatterySaverSettings;

/* JADX INFO: loaded from: classes.dex */
public class OpenBatterySaverAction extends BatteryTipAction {
    public OpenBatterySaverAction(Context context) {
        super(context);
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.actions.BatteryTipAction
    public void handlePositiveAction(int i) {
        this.mMetricsFeatureProvider.action(this.mContext, 1388, i);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(BatterySaverSettings.class.getName());
        subSettingLauncher.setSourceMetricsCategory(i);
        subSettingLauncher.launch();
    }
}
