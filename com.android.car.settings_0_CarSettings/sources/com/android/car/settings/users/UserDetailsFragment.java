package com.android.car.settings.users;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.android.car.settings.R;

/* JADX INFO: loaded from: classes5.dex */
public class UserDetailsFragment extends UserDetailsBaseFragment {
    private boolean mIsStarted;
    final BroadcastReceiver mUserUpdateReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.users.UserDetailsFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            UserDetailsFragment.this.refreshUserInfo();
            if (UserDetailsFragment.this.mIsStarted) {
                UserDetailsFragment.this.getToolbar().setTitle(UserDetailsFragment.this.getTitleText());
            }
        }
    };

    public static UserDetailsFragment newInstance(int userId) {
        return (UserDetailsFragment) UserDetailsBaseFragment.addUserIdToFragmentArguments(new UserDetailsFragment(), userId);
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.user_details_fragment;
    }

    @Override // com.android.car.settings.users.UserDetailsBaseFragment, com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((EditUserNameEntryPreferenceController) use(EditUserNameEntryPreferenceController.class, R.string.pk_edit_user_name_entry)).setUserInfo(getUserInfo());
    }

    @Override // com.android.car.settings.users.UserDetailsBaseFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerForUserEvents();
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mIsStarted = true;
        getToolbar().setTitle(getTitleText());
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        this.mIsStarted = false;
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        unregisterForUserEvents();
        super.onDestroy();
    }

    @Override // com.android.car.settings.users.UserDetailsBaseFragment
    protected String getTitleText() {
        return UserUtils.getUserDisplayName(getContext(), getUserInfo());
    }

    private void registerForUserEvents() {
        IntentFilter filter = new IntentFilter("android.intent.action.USER_INFO_CHANGED");
        getContext().registerReceiver(this.mUserUpdateReceiver, filter);
    }

    private void unregisterForUserEvents() {
        getContext().unregisterReceiver(this.mUserUpdateReceiver);
    }
}
