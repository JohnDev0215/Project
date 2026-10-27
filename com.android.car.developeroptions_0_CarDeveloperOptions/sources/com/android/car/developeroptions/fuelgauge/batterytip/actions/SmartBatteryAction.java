package com.android.car.developeroptions.fuelgauge.batterytip.actions;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.fuelgauge.SmartBatterySettings;
import com.android.settingslib.core.instrumentation.Instrumentable;

/* JADX INFO: loaded from: classes.dex */
public class SmartBatteryAction extends BatteryTipAction {
    private Fragment mFragment;
    private SettingsActivity mSettingsActivity;

    public SmartBatteryAction(SettingsActivity settingsActivity, Fragment fragment) {
        super(settingsActivity.getApplicationContext());
        this.mSettingsActivity = settingsActivity;
        this.mFragment = fragment;
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.actions.BatteryTipAction
    public void handlePositiveAction(int i) {
        this.mMetricsFeatureProvider.action(this.mContext, 1364, i);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mSettingsActivity);
        LifecycleOwner lifecycleOwner = this.mFragment;
        subSettingLauncher.setSourceMetricsCategory(lifecycleOwner instanceof Instrumentable ? ((Instrumentable) lifecycleOwner).getMetricsCategory() : 0);
        subSettingLauncher.setDestination(SmartBatterySettings.class.getName());
        subSettingLauncher.setTitleRes(R.string.smart_battery_manager_title);
        subSettingLauncher.launch();
    }
}
