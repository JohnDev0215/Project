package com.android.car.settings.security;

import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.internal.widget.LockscreenCredential;

/* JADX INFO: loaded from: classes5.dex */
public class ChooseLockTypeFragment extends SettingsFragment {
    private LockscreenCredential mLockscreenCredential;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.choose_lock_type_fragment;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.mLockscreenCredential = args.getParcelable(PasswordHelper.EXTRA_CURRENT_SCREEN_LOCK);
            int passwordQuality = args.getInt(PasswordHelper.EXTRA_CURRENT_PASSWORD_QUALITY);
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            int preferenceCount = preferenceScreen.getPreferenceCount();
            for (int i = 0; i < preferenceCount; i++) {
                Preference preference = preferenceScreen.getPreference(i);
                preference.getExtras().putParcelable(PasswordHelper.EXTRA_CURRENT_SCREEN_LOCK, this.mLockscreenCredential);
                preference.getExtras().putInt(PasswordHelper.EXTRA_CURRENT_PASSWORD_QUALITY, passwordQuality);
            }
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        PasswordHelper.zeroizeCredentials(this.mLockscreenCredential);
    }
}
