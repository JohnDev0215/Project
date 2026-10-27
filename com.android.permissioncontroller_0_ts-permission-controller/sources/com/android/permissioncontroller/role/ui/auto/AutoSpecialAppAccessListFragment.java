package com.android.permissioncontroller.role.ui.auto;

import android.content.Context;
import android.os.Bundle;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.auto.AutoSettingsFrameFragment;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;
import com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessListChildFragment;

/* JADX INFO: loaded from: classes8.dex */
public class AutoSpecialAppAccessListFragment extends AutoSettingsFrameFragment implements SpecialAppAccessListChildFragment.Parent {
    public static AutoSpecialAppAccessListFragment newInstance() {
        return new AutoSpecialAppAccessListFragment();
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            SpecialAppAccessListChildFragment fragment = SpecialAppAccessListChildFragment.newInstance();
            getChildFragmentManager().beginTransaction().add(fragment, (String) null).commit();
        }
        setHeaderLabel(getString(R.string.special_app_access));
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessListChildFragment.Parent
    public TwoTargetPreference createPreference(Context context) {
        return new AutoSettingsPreference(context);
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessListChildFragment.Parent
    public void onPreferenceScreenChanged() {
    }
}
