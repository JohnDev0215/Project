package com.android.car.settings.applications.specialaccess;

import android.content.Context;
import com.android.car.settings.R;
import com.android.car.settings.applications.AppListFragment;

/* JADX INFO: loaded from: classes5.dex */
public class UsageAccessFragment extends AppListFragment {
    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.usage_access_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((AppOpsPreferenceController) use(AppOpsPreferenceController.class, R.string.pk_usage_access)).init(43, "android.permission.PACKAGE_USAGE_STATS", 1);
    }

    @Override // com.android.car.settings.applications.AppListFragment
    protected void onToggleShowSystemApps(boolean showSystem) {
        ((AppOpsPreferenceController) use(AppOpsPreferenceController.class, R.string.pk_usage_access)).setShowSystem(showSystem);
    }
}
