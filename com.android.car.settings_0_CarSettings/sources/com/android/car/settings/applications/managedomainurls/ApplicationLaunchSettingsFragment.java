package com.android.car.settings.applications.managedomainurls;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.settingslib.applications.ApplicationsState;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationLaunchSettingsFragment extends SettingsFragment {
    static final String ARG_PACKAGE_NAME = "arg_package_name";
    private ApplicationsState.AppEntry mAppEntry;
    private ApplicationsState mState;

    public static ApplicationLaunchSettingsFragment newInstance(String pkg) {
        ApplicationLaunchSettingsFragment fragment = new ApplicationLaunchSettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PACKAGE_NAME, pkg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.application_launch_settings_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mState = ApplicationsState.getInstance(requireActivity().getApplication());
        String pkgName = getArguments().getString(ARG_PACKAGE_NAME);
        this.mAppEntry = this.mState.getEntry(pkgName, UserHandle.myUserId());
        ApplicationWithVersionPreferenceController appController = (ApplicationWithVersionPreferenceController) use(ApplicationWithVersionPreferenceController.class, R.string.pk_opening_links_app_details);
        appController.setAppState(this.mState);
        appController.setAppEntry(this.mAppEntry);
        List<AppLaunchSettingsBasePreferenceController> preferenceControllers = Arrays.asList((AppLaunchSettingsBasePreferenceController) use(AppLinkStatePreferenceController.class, R.string.pk_opening_links_app_details_state), (AppLaunchSettingsBasePreferenceController) use(DomainUrlsPreferenceController.class, R.string.pk_opening_links_app_details_urls), (AppLaunchSettingsBasePreferenceController) use(ClearDefaultsPreferenceController.class, R.string.pk_opening_links_app_details_reset));
        for (AppLaunchSettingsBasePreferenceController controller : preferenceControllers) {
            controller.setAppEntry(this.mAppEntry);
        }
    }
}
