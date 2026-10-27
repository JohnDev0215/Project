package com.android.permissioncontroller.role.ui.auto;

import android.content.Context;
import android.os.Bundle;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.auto.AutoSettingsFrameFragment;
import com.android.permissioncontroller.role.ui.DefaultAppListChildFragment;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;

/* JADX INFO: loaded from: classes8.dex */
public class AutoDefaultAppListFragment extends AutoSettingsFrameFragment implements DefaultAppListChildFragment.Parent {
    public static AutoDefaultAppListFragment newInstance() {
        return new AutoDefaultAppListFragment();
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            DefaultAppListChildFragment fragment = DefaultAppListChildFragment.newInstance();
            getChildFragmentManager().beginTransaction().add(fragment, (String) null).commit();
        }
        setHeaderLabel(getString(R.string.default_apps));
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppListChildFragment.Parent
    public TwoTargetPreference createPreference(Context context) {
        return new AutoSettingsPreference(context);
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppListChildFragment.Parent
    public void onPreferenceScreenChanged() {
    }
}
