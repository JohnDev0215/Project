package com.android.car.settings.applications;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import com.android.car.settings.R;
import com.android.car.settings.applications.ApplicationListItemManager;
import com.android.car.settings.storage.StorageUtils;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationsSettingsFragment extends AppListFragment {
    private ApplicationListItemManager mAppListItemManager;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.applications_settings_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Application application = requireActivity().getApplication();
        StorageManager sm = (StorageManager) context.getSystemService(StorageManager.class);
        VolumeInfo volume = StorageUtils.maybeInitializeVolume(sm, getArguments());
        ApplicationListItemManager applicationListItemManager = new ApplicationListItemManager(volume, getLifecycle(), ApplicationsState.getInstance(application), getContext().getResources().getInteger(R.integer.millisecond_app_data_update_interval), getContext().getResources().getInteger(R.integer.millisecond_max_app_load_wait_interval));
        this.mAppListItemManager = applicationListItemManager;
        applicationListItemManager.registerListener((ApplicationListItemManager.AppListItemListener) use(ApplicationsSettingsPreferenceController.class, R.string.pk_all_applications_settings_list));
    }

    @Override // com.android.car.settings.applications.AppListFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mAppListItemManager.startLoading(getAppFilter(), ApplicationsState.ALPHA_COMPARATOR);
    }

    @Override // com.android.car.settings.applications.AppListFragment, com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mAppListItemManager.onFragmentStart();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mAppListItemManager.onFragmentStop();
    }

    @Override // com.android.car.settings.applications.AppListFragment
    protected void onToggleShowSystemApps(boolean showSystem) {
        this.mAppListItemManager.rebuildWithFilter(getAppFilter());
    }

    private ApplicationsState.AppFilter getAppFilter() {
        if (shouldShowSystemApps()) {
            return null;
        }
        return ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER_AND_INSTANT;
    }
}
