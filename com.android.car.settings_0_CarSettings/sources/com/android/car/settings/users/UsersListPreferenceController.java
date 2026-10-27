package com.android.car.settings.users;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.UserManager;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class UsersListPreferenceController extends UsersBasePreferenceController {
    public UsersListPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.users.UsersBasePreferenceController
    protected void userClicked(UserInfo userInfo) {
        if (UserUtils.isAdminViewingNonAdmin(UserManager.get(getContext()), userInfo)) {
            getFragmentController().launchFragment(UserDetailsPermissionsFragment.newInstance(userInfo.id));
        } else {
            getFragmentController().launchFragment(UserDetailsFragment.newInstance(userInfo.id));
        }
    }
}
