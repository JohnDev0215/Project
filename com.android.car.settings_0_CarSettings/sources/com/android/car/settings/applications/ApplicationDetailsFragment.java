package com.android.car.settings.applications;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.UserHandle;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.SettingsFragment;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationDetailsFragment extends SettingsFragment {
    public static final String EXTRA_PACKAGE_NAME = "extra_package_name";
    private static final Logger LOG = new Logger((Class<?>) ApplicationDetailsFragment.class);
    private ApplicationsState.AppEntry mAppEntry;
    private ApplicationsState mAppState;
    private PackageInfo mPackageInfo;
    private String mPackageName;
    private PackageManager mPm;

    public static ApplicationDetailsFragment getInstance(String packageName) {
        ApplicationDetailsFragment applicationDetailFragment = new ApplicationDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("extra_package_name", packageName);
        applicationDetailFragment.setArguments(bundle);
        return applicationDetailFragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.application_details_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mPm = context.getPackageManager();
        this.mPackageName = getArguments().getString("extra_package_name");
        this.mAppState = ApplicationsState.getInstance(requireActivity().getApplication());
        retrieveAppEntry();
        ((ApplicationPreferenceController) use(ApplicationPreferenceController.class, R.string.pk_application_details_app)).setAppEntry(this.mAppEntry).setAppState(this.mAppState);
        ((ApplicationActionButtonsPreferenceController) use(ApplicationActionButtonsPreferenceController.class, R.string.pk_application_details_action_buttons)).setAppEntry(this.mAppEntry).setAppState(this.mAppState).setPackageName(this.mPackageName);
        ((NotificationsPreferenceController) use(NotificationsPreferenceController.class, R.string.pk_application_details_notifications)).setPackageInfo(this.mPackageInfo);
        ((PermissionsPreferenceController) use(PermissionsPreferenceController.class, R.string.pk_application_details_permissions)).setPackageName(this.mPackageName);
        ((StoragePreferenceController) use(StoragePreferenceController.class, R.string.pk_application_details_storage)).setAppEntry(this.mAppEntry).setPackageName(this.mPackageName);
        ((VersionPreferenceController) use(VersionPreferenceController.class, R.string.pk_application_details_version)).setPackageInfo(this.mPackageInfo);
    }

    private void retrieveAppEntry() {
        ApplicationsState.AppEntry entry = this.mAppState.getEntry(this.mPackageName, UserHandle.myUserId());
        this.mAppEntry = entry;
        if (entry != null) {
            try {
                this.mPackageInfo = this.mPm.getPackageInfo(this.mPackageName, 4198976);
                return;
            } catch (PackageManager.NameNotFoundException e) {
                LOG.e("Exception when retrieving package:" + this.mPackageName, e);
                this.mPackageInfo = null;
                return;
            }
        }
        this.mPackageInfo = null;
    }
}
