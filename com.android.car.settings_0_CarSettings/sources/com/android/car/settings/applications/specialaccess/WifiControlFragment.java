package com.android.car.settings.applications.specialaccess;

import com.android.car.settings.R;
import com.android.car.settings.applications.AppListFragment;

/* JADX INFO: loaded from: classes5.dex */
public class WifiControlFragment extends AppListFragment {
    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.wifi_control_fragment;
    }

    @Override // com.android.car.settings.applications.AppListFragment
    protected void onToggleShowSystemApps(boolean showSystem) {
        ((WifiControlPreferenceController) use(WifiControlPreferenceController.class, R.string.pk_wifi_control)).setShowSystem(showSystem);
    }
}
