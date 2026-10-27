package com.android.permissioncontroller.role.ui.auto;

import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.auto.AutoSettingsFrameFragment;
import com.android.permissioncontroller.role.ui.DefaultAppChildFragment;

/* JADX INFO: loaded from: classes8.dex */
public class AutoDefaultAppFragment extends AutoSettingsFrameFragment implements DefaultAppChildFragment.Parent {
    private String mRoleName;
    private UserHandle mUser;

    public static AutoDefaultAppFragment newInstance(String roleName, UserHandle user) {
        AutoDefaultAppFragment fragment = new AutoDefaultAppFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.ROLE_NAME", roleName);
        arguments.putParcelable("android.intent.extra.USER", user);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.mRoleName = arguments.getString("android.intent.extra.ROLE_NAME");
        this.mUser = (UserHandle) arguments.getParcelable("android.intent.extra.USER");
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            DefaultAppChildFragment fragment = DefaultAppChildFragment.newInstance(this.mRoleName, this.mUser);
            getChildFragmentManager().beginTransaction().add(fragment, (String) null).commit();
        }
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppChildFragment.Parent
    public void setTitle(CharSequence title) {
        setHeaderLabel(title);
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppChildFragment.Parent
    public TwoStatePreference createApplicationPreference(Context context) {
        return new AutoDefaultAppPreference(context);
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppChildFragment.Parent
    public Preference createFooterPreference(Context context) {
        Preference preference = new Preference(context);
        preference.setIcon(R.drawable.ic_info_outline);
        preference.setSelectable(false);
        return preference;
    }

    @Override // com.android.permissioncontroller.role.ui.DefaultAppChildFragment.Parent
    public void onPreferenceScreenChanged() {
    }
}
