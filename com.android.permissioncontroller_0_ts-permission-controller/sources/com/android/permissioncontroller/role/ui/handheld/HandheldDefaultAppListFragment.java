package com.android.permissioncontroller.role.ui.handheld;

import android.content.Context;
import android.os.Bundle;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.ui.DefaultAppListChildFragment;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;

/* JADX INFO: loaded from: classes8.dex */
public class HandheldDefaultAppListFragment extends SettingsFragment implements DefaultAppListChildFragment.Parent {
    public static HandheldDefaultAppListFragment newInstance() {
        return new HandheldDefaultAppListFragment();
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            DefaultAppListChildFragment fragment = DefaultAppListChildFragment.newInstance();
            getChildFragmentManager().beginTransaction().add(fragment, (String) null).commit();
        }
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment
    protected int getEmptyTextResource() {
        return R.string.no_default_apps;
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment
    protected int getHelpUriResource() {
        return R.string.help_uri_default_apps;
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppListChildFragment.Parent
    public TwoTargetPreference createPreference(Context context) {
        return new AppIconSettingsButtonPreference(context);
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppListChildFragment.Parent
    public void onPreferenceScreenChanged() {
        updateState();
    }
}
