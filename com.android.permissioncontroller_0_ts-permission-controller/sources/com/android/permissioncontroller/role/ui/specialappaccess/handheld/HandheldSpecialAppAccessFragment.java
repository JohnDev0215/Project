package com.android.permissioncontroller.role.ui.specialappaccess.handheld;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.ui.handheld.FooterPreference;
import com.android.permissioncontroller.role.ui.handheld.SettingsFragment;
import com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment;

/* JADX INFO: loaded from: classes8.dex */
public class HandheldSpecialAppAccessFragment extends SettingsFragment implements SpecialAppAccessChildFragment.Parent {
    private String mRoleName;

    public static HandheldSpecialAppAccessFragment newInstance(String roleName) {
        HandheldSpecialAppAccessFragment fragment = new HandheldSpecialAppAccessFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.ROLE_NAME", roleName);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.mRoleName = arguments.getString("android.intent.extra.ROLE_NAME");
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            SpecialAppAccessChildFragment fragment = SpecialAppAccessChildFragment.newInstance(this.mRoleName);
            getChildFragmentManager().beginTransaction().add(fragment, (String) null).commit();
        }
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment
    protected int getEmptyTextResource() {
        return R.string.special_app_access_no_apps;
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment.Parent
    public void setTitle(CharSequence title) {
        requireActivity().setTitle(title);
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment.Parent
    public TwoStatePreference createApplicationPreference(Context context) {
        return new AppIconSwitchPreference(context);
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment.Parent
    public Preference createFooterPreference(Context context) {
        return new FooterPreference(context);
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment.Parent
    public void onPreferenceScreenChanged() {
        updateState();
    }
}
