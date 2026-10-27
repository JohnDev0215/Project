package com.android.permissioncontroller.role.ui.auto;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import androidx.preference.TwoStatePreference;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.auto.AutoSettingsFrameFragment;
import com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment;

/* JADX INFO: loaded from: classes8.dex */
public class AutoSpecialAppAccessFragment extends AutoSettingsFrameFragment implements SpecialAppAccessChildFragment.Parent {
    private String mRoleName;

    public static AutoSpecialAppAccessFragment newInstance(String roleName) {
        AutoSpecialAppAccessFragment fragment = new AutoSpecialAppAccessFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.ROLE_NAME", roleName);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.mRoleName = arguments.getString("android.intent.extra.ROLE_NAME");
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            SpecialAppAccessChildFragment fragment = SpecialAppAccessChildFragment.newInstance(this.mRoleName);
            getChildFragmentManager().beginTransaction().add(fragment, (String) null).commit();
        }
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment.Parent
    public void setTitle(CharSequence title) {
        setHeaderLabel(title);
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment.Parent
    public TwoStatePreference createApplicationPreference(Context context) {
        return new SwitchPreference(context);
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment.Parent
    public Preference createFooterPreference(Context context) {
        Preference preference = new Preference(context);
        preference.setIcon(R.drawable.ic_info_outline);
        preference.setSelectable(false);
        return preference;
    }

    @Override // com.android.permissioncontroller.role.ui.specialappaccess.SpecialAppAccessChildFragment.Parent
    public void onPreferenceScreenChanged() {
    }
}
