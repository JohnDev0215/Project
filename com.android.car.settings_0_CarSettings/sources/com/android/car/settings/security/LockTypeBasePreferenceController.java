package com.android.car.settings.security;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.internal.widget.LockscreenCredential;

/* JADX INFO: loaded from: classes5.dex */
public abstract class LockTypeBasePreferenceController extends PreferenceController<Preference> {
    private LockscreenCredential mCurrentPassword;
    private int mCurrentPasswordQuality;
    private final UserManager mUserManager;

    protected abstract int[] allowedPasswordQualities();

    protected abstract Fragment fragmentToOpen();

    public LockTypeBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUserManager = UserManager.get(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    public void setCurrentPasswordQuality(int currentPasswordQuality) {
        this.mCurrentPasswordQuality = currentPasswordQuality;
    }

    protected boolean isCurrentLock() {
        for (int allowedQuality : allowedPasswordQualities()) {
            if (this.mCurrentPasswordQuality == allowedQuality) {
                return true;
            }
        }
        return false;
    }

    public void setCurrentPassword(LockscreenCredential currentPassword) {
        this.mCurrentPassword = currentPassword;
    }

    protected LockscreenCredential getCurrentPassword() {
        return this.mCurrentPassword;
    }

    protected int getCurrentPasswordQuality() {
        return this.mCurrentPasswordQuality;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setSummary(getSummary());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        Fragment fragment = fragmentToOpen();
        if (fragment != null) {
            if (this.mCurrentPassword != null) {
                Bundle args = fragment.getArguments();
                if (args == null) {
                    args = new Bundle();
                }
                args.putParcelable(PasswordHelper.EXTRA_CURRENT_SCREEN_LOCK, this.mCurrentPassword);
                fragment.setArguments(args);
            }
            getFragmentController().launchFragment(fragment);
            return true;
        }
        return false;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        if (getPreference().peekExtras() != null) {
            setCurrentPassword((LockscreenCredential) getPreference().peekExtras().getParcelable(PasswordHelper.EXTRA_CURRENT_SCREEN_LOCK));
            setCurrentPasswordQuality(getPreference().peekExtras().getInt(PasswordHelper.EXTRA_CURRENT_PASSWORD_QUALITY));
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    public int getAvailabilityStatus() {
        return this.mUserManager.isGuestUser() ? 3 : 0;
    }

    private CharSequence getSummary() {
        return isCurrentLock() ? getContext().getString(R.string.current_screen_lock) : "";
    }
}
