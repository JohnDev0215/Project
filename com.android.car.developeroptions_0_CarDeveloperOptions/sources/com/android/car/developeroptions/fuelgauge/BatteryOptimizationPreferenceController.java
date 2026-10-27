package com.android.car.developeroptions.fuelgauge;

import android.os.Bundle;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Settings$HighPowerApplicationsActivity;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.applications.manageapplications.ManageApplications;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.fuelgauge.PowerWhitelistBackend;

/* JADX INFO: loaded from: classes.dex */
public class BatteryOptimizationPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    private PowerWhitelistBackend mBackend;
    private DashboardFragment mFragment;
    private String mPackageName;
    private SettingsActivity mSettingsActivity;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "battery_optimization";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public BatteryOptimizationPreferenceController(SettingsActivity settingsActivity, DashboardFragment dashboardFragment, String str) {
        super(settingsActivity);
        this.mFragment = dashboardFragment;
        this.mSettingsActivity = settingsActivity;
        this.mPackageName = str;
        this.mBackend = PowerWhitelistBackend.getInstance(settingsActivity);
    }

    BatteryOptimizationPreferenceController(SettingsActivity settingsActivity, DashboardFragment dashboardFragment, String str, PowerWhitelistBackend powerWhitelistBackend) {
        super(settingsActivity);
        this.mFragment = dashboardFragment;
        this.mSettingsActivity = settingsActivity;
        this.mPackageName = str;
        this.mBackend = powerWhitelistBackend;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        preference.setSummary(this.mBackend.isWhitelisted(this.mPackageName) ? R.string.high_power_on : R.string.high_power_off);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!"battery_optimization".equals(preference.getKey())) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("classname", Settings$HighPowerApplicationsActivity.class.getName());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mSettingsActivity);
        subSettingLauncher.setDestination(ManageApplications.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(R.string.high_power_apps);
        subSettingLauncher.setSourceMetricsCategory(this.mFragment.getMetricsCategory());
        subSettingLauncher.launch();
        return true;
    }
}
