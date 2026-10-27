package com.android.car.settings.users;

import android.content.Context;
import com.android.car.settings.R;

/* JADX INFO: loaded from: classes5.dex */
public class UserDetailsPermissionsFragment extends UserDetailsBaseFragment {
    public static UserDetailsPermissionsFragment newInstance(int userId) {
        return (UserDetailsPermissionsFragment) UserDetailsBaseFragment.addUserIdToFragmentArguments(new UserDetailsPermissionsFragment(), userId);
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.user_details_permissions_fragment;
    }

    @Override // com.android.car.settings.users.UserDetailsBaseFragment, com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MakeAdminPreferenceController) use(MakeAdminPreferenceController.class, R.string.pk_make_user_admin)).setUserInfo(getUserInfo());
        ((PermissionsPreferenceController) use(PermissionsPreferenceController.class, R.string.pk_user_permissions)).setUserInfo(getUserInfo());
    }

    @Override // com.android.car.settings.users.UserDetailsBaseFragment
    protected String getTitleText() {
        return getContext().getString(R.string.user_details_admin_title, UserUtils.getUserDisplayName(getContext(), getUserInfo()));
    }
}
