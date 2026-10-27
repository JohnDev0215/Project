package com.android.permissioncontroller.role.ui.specialappaccess.handheld;

import android.content.Context;
import android.os.Bundle;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;
import com.android.permissioncontroller.role.ui.handheld.AppIconSettingsButtonPreference;
import com.android.permissioncontroller.role.ui.handheld.SettingsFragment;
import com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessListChildFragment;

/* JADX INFO: loaded from: classes8.dex */
public class HandheldSpecialAppAccessListFragment extends SettingsFragment implements SpecialAppAccessListChildFragment.Parent {
    public static HandheldSpecialAppAccessListFragment newInstance() {
        return new HandheldSpecialAppAccessListFragment();
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            SpecialAppAccessListChildFragment fragment = SpecialAppAccessListChildFragment.newInstance();
            getChildFragmentManager().beginTransaction().add(fragment, (String) null).commit();
        }
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment
    protected int getEmptyTextResource() {
        return R.string.no_special_app_access;
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment
    protected int getHelpUriResource() {
        return R.string.help_uri_special_app_access;
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessListChildFragment.Parent
    public TwoTargetPreference createPreference(Context context) {
        return new AppIconSettingsButtonPreference(context);
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessListChildFragment.Parent
    public void onPreferenceScreenChanged() {
        updateState();
    }
}
