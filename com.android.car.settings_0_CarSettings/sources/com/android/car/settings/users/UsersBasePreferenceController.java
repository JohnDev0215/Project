package com.android.car.settings.users;

import android.car.drivingstate.CarUxRestrictions;
import android.car.userlib.CarUserManagerHelper;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.users.UsersPreferenceProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public abstract class UsersBasePreferenceController extends PreferenceController<PreferenceGroup> {
    private CarUserManagerHelper mCarUserManagerHelper;
    private UsersPreferenceProvider mPreferenceProvider;
    private final BroadcastReceiver mUserUpdateReceiver;
    private List<Preference> mUsersToDisplay;

    protected abstract void userClicked(UserInfo userInfo);

    public UsersBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUsersToDisplay = new ArrayList();
        this.mUserUpdateReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.users.UsersBasePreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                UsersBasePreferenceController.this.refreshUi();
            }
        };
        this.mCarUserManagerHelper = new CarUserManagerHelper(context);
        UsersPreferenceProvider.UserClickListener userClickListener = new UsersPreferenceProvider.UserClickListener() { // from class: com.android.car.settings.users.-$$Lambda$_P6tBaP5Rqz7XPEGDwpiv1a68Jc
            @Override // com.android.car.settings.users.UsersPreferenceProvider.UserClickListener
            public final void onUserClicked(UserInfo userInfo) {
                this.f$0.userClicked(userInfo);
            }
        };
        this.mPreferenceProvider = new UsersPreferenceProvider(context, userClickListener);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        registerForUserEvents();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        unregisterForUserEvents();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        List<Preference> newUsers = this.mPreferenceProvider.createUserList();
        if (userListsAreDifferent(this.mUsersToDisplay, newUsers)) {
            this.mUsersToDisplay = newUsers;
            preferenceGroup.removeAll();
            for (Preference preference : this.mUsersToDisplay) {
                preferenceGroup.addPreference(preference);
            }
        }
    }

    protected CarUserManagerHelper getCarUserManagerHelper() {
        return this.mCarUserManagerHelper;
    }

    protected UsersPreferenceProvider getPreferenceProvider() {
        return this.mPreferenceProvider;
    }

    private boolean userListsAreDifferent(List<Preference> currentList, List<Preference> newList) {
        if (currentList.size() != newList.size()) {
            return true;
        }
        for (int i = 0; i < currentList.size(); i++) {
            if (preferencesAreDifferent(currentList.get(i), newList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean preferencesAreDifferent(Preference lhs, Preference rhs) {
        return (Objects.equals(lhs.getTitle(), rhs.getTitle()) && Objects.equals(lhs.getSummary(), rhs.getSummary())) ? false : true;
    }

    private void registerForUserEvents() {
        IntentFilter filter = new IntentFilter("android.intent.action.USER_INFO_CHANGED");
        getContext().registerReceiver(this.mUserUpdateReceiver, filter);
    }

    private void unregisterForUserEvents() {
        getContext().unregisterReceiver(this.mUserUpdateReceiver);
    }
}
