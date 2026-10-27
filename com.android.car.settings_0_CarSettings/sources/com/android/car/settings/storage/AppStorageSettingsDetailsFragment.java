package com.android.car.settings.storage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import androidx.loader.app.LoaderManager;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.SettingsFragment;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.applications.StorageStatsSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AppStorageSettingsDetailsFragment extends SettingsFragment {
    public static final String EXTRA_PACKAGE_NAME = "extra_package_name";
    private static final Logger LOG = new Logger((Class<?>) AppStorageSettingsDetailsFragment.class);
    private ApplicationsState.AppEntry mAppEntry;
    private ApplicationsState mAppState;
    private AppsStorageStatsManager mAppsStorageStatsManager;
    protected PackageManager mPackageManager;
    private String mPackageName;
    private int mUserId;

    public static AppStorageSettingsDetailsFragment getInstance(String packageName) {
        AppStorageSettingsDetailsFragment applicationDetailFragment = new AppStorageSettingsDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("extra_package_name", packageName);
        applicationDetailFragment.setArguments(bundle);
        return applicationDetailFragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.app_storage_settings_details_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) throws PackageManager.NameNotFoundException, IOException {
        super.onAttach(context);
        this.mUserId = UserHandle.myUserId();
        this.mPackageName = getArguments().getString("extra_package_name");
        ApplicationsState applicationsState = ApplicationsState.getInstance(requireActivity().getApplication());
        this.mAppState = applicationsState;
        this.mAppEntry = applicationsState.getEntry(this.mPackageName, this.mUserId);
        StorageStatsSource storageStatsSource = new StorageStatsSource(context);
        StorageStatsSource.AppStorageStats stats = null;
        this.mPackageManager = context.getPackageManager();
        try {
            stats = storageStatsSource.getStatsForPackage(null, this.mPackageName, UserHandle.of(this.mUserId));
        } catch (Exception e) {
            LOG.w("App unexpectedly not found", e);
        }
        this.mAppsStorageStatsManager = new AppsStorageStatsManager(context);
        ((StorageApplicationPreferenceController) use(StorageApplicationPreferenceController.class, R.string.pk_storage_application_details)).setAppEntry(this.mAppEntry).setAppState(this.mAppState);
        ((StorageApplicationActionButtonsPreferenceController) use(StorageApplicationActionButtonsPreferenceController.class, R.string.pk_storage_application_action_buttons)).setAppEntry(this.mAppEntry).setPackageName(this.mPackageName).setAppsStorageStatsManager(this.mAppsStorageStatsManager).setLoaderManager(LoaderManager.getInstance(this));
        List<? extends StorageSizeBasePreferenceController> preferenceControllers = Arrays.asList((StorageSizeBasePreferenceController) use(StorageApplicationSizePreferenceController.class, R.string.pk_storage_application_size), (StorageSizeBasePreferenceController) use(StorageApplicationTotalSizePreferenceController.class, R.string.pk_storage_application_total_size), (StorageSizeBasePreferenceController) use(StorageApplicationUserDataPreferenceController.class, R.string.pk_storage_application_data_size), (StorageSizeBasePreferenceController) use(StorageApplicationCacheSizePreferenceController.class, R.string.pk_storage_application_cache_size));
        for (StorageSizeBasePreferenceController pc : preferenceControllers) {
            pc.setAppsStorageStatsManager(this.mAppsStorageStatsManager);
            pc.setAppStorageStats(stats);
        }
    }

    @Override // com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        enableRotaryScroll();
    }
}
