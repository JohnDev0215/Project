package com.android.permissioncontroller.role.ui.handheld;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.ui.DefaultAppChildFragment;

/* JADX INFO: loaded from: classes8.dex */
public class HandheldDefaultAppFragment extends SettingsFragment implements DefaultAppChildFragment.Parent {
    private String mRoleName;
    private UserHandle mUser;

    public static HandheldDefaultAppFragment newInstance(String roleName, UserHandle user) {
        HandheldDefaultAppFragment fragment = new HandheldDefaultAppFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.ROLE_NAME", roleName);
        arguments.putParcelable("android.intent.extra.USER", user);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.mRoleName = arguments.getString("android.intent.extra.ROLE_NAME");
        this.mUser = (UserHandle) arguments.getParcelable("android.intent.extra.USER");
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            DefaultAppChildFragment fragment = DefaultAppChildFragment.newInstance(this.mRoleName, this.mUser);
            getChildFragmentManager().beginTransaction().add(fragment, (String) null).commit();
        }
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.SettingsFragment
    protected int getEmptyTextResource() {
        return R.string.default_app_no_apps;
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppChildFragment.Parent
    public void setTitle(CharSequence title) {
        requireActivity().setTitle(title);
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppChildFragment.Parent
    public TwoStatePreference createApplicationPreference(Context context) {
        return new AppIconRadioButtonPreference(context);
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppChildFragment.Parent
    public Preference createFooterPreference(Context context) {
        return new FooterPreference(context);
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppChildFragment.Parent
    public void onPreferenceScreenChanged() {
        updateState();
    }
}
