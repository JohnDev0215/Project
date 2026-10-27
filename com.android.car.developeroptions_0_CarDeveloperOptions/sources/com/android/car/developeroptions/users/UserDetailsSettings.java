package com.android.car.developeroptions.users;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.settingslib.RestrictedLockUtilsInternal;

/* JADX INFO: loaded from: classes.dex */
public class UserDetailsSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    private Bundle mDefaultGuestRestrictions;
    private boolean mGuestUser;
    private SwitchPreference mPhonePref;
    private Preference mRemoveUserPref;
    private UserInfo mUserInfo;
    private UserManager mUserManager;

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        if (i == 1) {
            return 591;
        }
        if (i != 2) {
            return i != 3 ? 0 : 593;
        }
        return 592;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 98;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        this.mUserManager = (UserManager) activity.getSystemService("user");
        addPreferencesFromResource(R.xml.user_details_settings);
        this.mPhonePref = (SwitchPreference) findPreference("enable_calling");
        this.mRemoveUserPref = findPreference("remove_user");
        boolean z = getArguments().getBoolean("guest_user", false);
        this.mGuestUser = z;
        if (!z) {
            int i = getArguments().getInt("user_id", -1);
            if (i == -1) {
                throw new RuntimeException("Arguments to this fragment must contain the user id");
            }
            this.mUserInfo = this.mUserManager.getUserInfo(i);
            this.mPhonePref.setChecked(!this.mUserManager.hasUserRestriction("no_outgoing_calls", new UserHandle(i)));
            this.mRemoveUserPref.setOnPreferenceClickListener(this);
        } else {
            removePreference("remove_user");
            this.mPhonePref.setTitle(R.string.user_enable_calling);
            this.mDefaultGuestRestrictions = this.mUserManager.getDefaultGuestRestrictions();
            this.mPhonePref.setChecked(!r1.getBoolean("no_outgoing_calls"));
        }
        if (RestrictedLockUtilsInternal.hasBaseUserRestriction(activity, "no_remove_user", UserHandle.myUserId())) {
            removePreference("remove_user");
        }
        this.mPhonePref.setOnPreferenceChangeListener(this);
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        if (preference != this.mRemoveUserPref) {
            return false;
        }
        if (!this.mUserManager.isAdminUser()) {
            throw new RuntimeException("Only admins can remove a user");
        }
        showDialog(1);
        return true;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (Boolean.TRUE.equals(obj)) {
            showDialog(this.mGuestUser ? 2 : 3);
            return false;
        }
        enableCallsAndSms(false);
        return true;
    }

    void enableCallsAndSms(boolean z) {
        this.mPhonePref.setChecked(z);
        if (this.mGuestUser) {
            this.mDefaultGuestRestrictions.putBoolean("no_outgoing_calls", !z);
            this.mDefaultGuestRestrictions.putBoolean("no_sms", true);
            this.mUserManager.setDefaultGuestRestrictions(this.mDefaultGuestRestrictions);
            for (UserInfo userInfo : this.mUserManager.getUsers(true)) {
                if (userInfo.isGuest()) {
                    UserHandle userHandleOf = UserHandle.of(userInfo.id);
                    for (String str : this.mDefaultGuestRestrictions.keySet()) {
                        this.mUserManager.setUserRestriction(str, this.mDefaultGuestRestrictions.getBoolean(str), userHandleOf);
                    }
                }
            }
            return;
        }
        UserHandle userHandleOf2 = UserHandle.of(this.mUserInfo.id);
        this.mUserManager.setUserRestriction("no_outgoing_calls", !z, userHandleOf2);
        this.mUserManager.setUserRestriction("no_sms", !z, userHandleOf2);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        if (getActivity() == null) {
            return null;
        }
        if (i == 1) {
            return UserDialogs.createRemoveDialog(getActivity(), this.mUserInfo.id, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.UserDetailsSettings.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    UserDetailsSettings.this.removeUser();
                }
            });
        }
        if (i == 2) {
            return UserDialogs.createEnablePhoneCallsDialog(getActivity(), new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.UserDetailsSettings.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    UserDetailsSettings.this.enableCallsAndSms(true);
                }
            });
        }
        if (i == 3) {
            return UserDialogs.createEnablePhoneCallsAndSmsDialog(getActivity(), new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.UserDetailsSettings.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    UserDetailsSettings.this.enableCallsAndSms(true);
                }
            });
        }
        throw new IllegalArgumentException("Unsupported dialogId " + i);
    }

    void removeUser() {
        this.mUserManager.removeUser(this.mUserInfo.id);
        finishFragment();
    }
}
