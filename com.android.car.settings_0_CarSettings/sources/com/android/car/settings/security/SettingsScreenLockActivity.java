package com.android.car.settings.security;

import android.os.Bundle;
import android.os.UserHandle;
import androidx.fragment.app.Fragment;
import com.android.car.settings.R;
import com.android.car.settings.common.BaseCarSettingsActivity;
import com.android.car.settings.common.Logger;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockscreenCredential;

/* JADX INFO: loaded from: classes5.dex */
public class SettingsScreenLockActivity extends BaseCarSettingsActivity implements CheckLockListener {
    private static final Logger LOG = new Logger((Class<?>) SettingsScreenLockActivity.class);
    private int mPasswordQuality;

    @Override // com.android.car.settings.common.BaseCarSettingsActivity
    protected Fragment getInitialFragment() {
        Fragment fragment;
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            return currentFragment;
        }
        int keyguardStoredPasswordQuality = new LockPatternUtils(this).getKeyguardStoredPasswordQuality(UserHandle.myUserId());
        this.mPasswordQuality = keyguardStoredPasswordQuality;
        if (keyguardStoredPasswordQuality == 0) {
            fragment = new ChooseLockTypeFragment();
        } else if (keyguardStoredPasswordQuality == 65536) {
            fragment = new ConfirmLockPatternFragment();
        } else if (keyguardStoredPasswordQuality == 131072 || keyguardStoredPasswordQuality == 196608) {
            fragment = ConfirmLockPinPasswordFragment.newPinInstance();
        } else if (keyguardStoredPasswordQuality == 262144 || keyguardStoredPasswordQuality == 327680) {
            fragment = ConfirmLockPinPasswordFragment.newPasswordInstance();
        } else {
            LOG.e("Unexpected password quality: " + String.valueOf(this.mPasswordQuality));
            fragment = ConfirmLockPinPasswordFragment.newPasswordInstance();
        }
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(PasswordHelper.EXTRA_CURRENT_PASSWORD_QUALITY, this.mPasswordQuality);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override // com.android.car.settings.security.CheckLockListener
    public void onLockVerified(LockscreenCredential lock) {
        Fragment fragment = new ChooseLockTypeFragment();
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putParcelable(PasswordHelper.EXTRA_CURRENT_SCREEN_LOCK, lock);
        bundle.putInt(PasswordHelper.EXTRA_CURRENT_PASSWORD_QUALITY, this.mPasswordQuality);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override // com.android.car.settings.common.BaseCarSettingsActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        PasswordHelper.zeroizeCredentials(new LockscreenCredential[0]);
    }
}
