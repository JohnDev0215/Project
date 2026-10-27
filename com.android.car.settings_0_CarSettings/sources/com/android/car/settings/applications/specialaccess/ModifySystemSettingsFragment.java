package com.android.car.settings.applications.specialaccess;

import android.content.Context;
import com.android.car.settings.R;
import com.android.car.settings.applications.AppListFragment;

/* JADX INFO: loaded from: classes5.dex */
public class ModifySystemSettingsFragment extends AppListFragment {
    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.modify_system_settings_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((AppOpsPreferenceController) use(AppOpsPreferenceController.class, R.string.pk_modify_system_settings)).init(23, "android.permission.WRITE_SETTINGS", 2);
    }

    @Override // com.android.car.settings.applications.AppListFragment
    protected void onToggleShowSystemApps(boolean showSystem) {
        ((AppOpsPreferenceController) use(AppOpsPreferenceController.class, R.string.pk_modify_system_settings)).setShowSystem(showSystem);
    }
}
