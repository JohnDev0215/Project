package com.android.car.settings.users;

import android.car.drivingstate.CarUxRestrictions;
import android.car.userlib.CarUserManagerHelper;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public abstract class UserDetailsBasePreferenceController<V extends Preference> extends PreferenceController<V> {
    private final CarUserManagerHelper mCarUserManagerHelper;
    private UserInfo mUserInfo;
    private final BroadcastReceiver mUserUpdateReceiver;

    public UserDetailsBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUserUpdateReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.users.UserDetailsBasePreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                UserDetailsBasePreferenceController.this.refreshUserInfo();
                UserDetailsBasePreferenceController.this.refreshUi();
            }
        };
        this.mCarUserManagerHelper = new CarUserManagerHelper(getContext());
    }

    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return this.mUserInfo;
    }

    protected void refreshUserInfo() {
        this.mUserInfo = UserUtils.getUserInfo(getContext(), this.mUserInfo.id);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mUserInfo == null) {
            throw new IllegalStateException("UserInfo should be non-null by this point");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        registerForUserEvents();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        unregisterForUserEvents();
    }

    private void registerForUserEvents() {
        IntentFilter filter = new IntentFilter("android.intent.action.USER_INFO_CHANGED");
        getContext().registerReceiver(this.mUserUpdateReceiver, filter);
    }

    private void unregisterForUserEvents() {
        getContext().unregisterReceiver(this.mUserUpdateReceiver);
    }

    protected CarUserManagerHelper getCarUserManagerHelper() {
        return this.mCarUserManagerHelper;
    }
}
